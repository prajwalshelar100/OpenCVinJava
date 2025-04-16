package com.example.opencvservice.controller;

import com.example.opencvservice.service.ImageProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageProcessingService imageProcessingService;

    @PostMapping(value = "/process", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> processImage(@RequestParam("image") MultipartFile image) throws IOException {
        byte[] processedImage = imageProcessingService.convertToGrayscale(image.getBytes());
        return ResponseEntity.ok(processedImage);
    }

    @PostMapping(value = "/face-detect", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> detectFaces(@RequestParam("image") MultipartFile image) throws IOException {
        byte[] processedImage = imageProcessingService.detectFaces(image.getBytes());
        return ResponseEntity.ok(processedImage);
    }

    @PostMapping(value = "/face-recognize", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> recognizeFaces(@RequestParam("image") MultipartFile image) throws IOException {
        byte[] processedImage = imageProcessingService.recognizeFaces(image.getBytes());
        return ResponseEntity.ok(processedImage);
    }
} 