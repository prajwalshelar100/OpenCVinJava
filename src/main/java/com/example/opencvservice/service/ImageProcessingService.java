package com.example.opencvservice.service;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

@Service
@Slf4j
public class ImageProcessingService {

    private final CascadeClassifier faceDetector;

    public ImageProcessingService() {
        // Load the face detection classifier
        faceDetector = new CascadeClassifier(getClass().getResource("/haarcascade_frontalface_default.xml").getPath());
    }

    public byte[] convertToGrayscale(byte[] imageBytes) throws IOException {
        try {
            // Convert byte array to OpenCV Mat
            Mat mat = imdecode(new Mat(imageBytes), IMREAD_COLOR);
            
            // Convert to grayscale
            Mat grayMat = new Mat();
            cvtColor(mat, grayMat, COLOR_BGR2GRAY);
            
            // Convert back to byte array
            return matToByteArray(grayMat);
        } catch (Exception e) {
            log.error("Error processing image: {}", e.getMessage());
            throw new IOException("Failed to process image", e);
        }
    }

    public byte[] detectFaces(byte[] imageBytes) throws IOException {
        try {
            // Convert byte array to OpenCV Mat
            Mat mat = imdecode(new Mat(imageBytes), IMREAD_COLOR);
            
            // Convert to grayscale for face detection
            Mat grayMat = new Mat();
            cvtColor(mat, grayMat, COLOR_BGR2GRAY);
            
            // Detect faces
            RectVector faces = new RectVector();
            faceDetector.detectMultiScale(grayMat, faces);
            
            // Draw rectangles around detected faces
            for (int i = 0; i < faces.size(); i++) {
                Rect face = faces.get(i);
                rectangle(mat, face, new Scalar(0, 255, 0, 0), 3, 0, 0);
            }
            
            return matToByteArray(mat);
        } catch (Exception e) {
            log.error("Error detecting faces: {}", e.getMessage());
            throw new IOException("Failed to detect faces", e);
        }
    }

    public byte[] recognizeFaces(byte[] imageBytes) throws IOException {
        // This is a placeholder for face recognition functionality
        // In a real implementation, you would:
        // 1. Load known faces from a database or directory
        // 2. Extract face features using a face recognition model
        // 3. Compare with the input image
        // 4. Return the image with recognized faces marked
        
        // For now, we'll just return the original image
        return imageBytes;
    }

    private byte[] matToByteArray(Mat mat) throws IOException {
        BytePointer bytePointer = new BytePointer();
        imencode(".jpg", mat, bytePointer);
        return bytePointer.getStringBytes();
    }
} 