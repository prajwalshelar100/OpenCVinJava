package com.example.opencvservice.config;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class OpenCVConfig {

    @PostConstruct
    public void init() {
        // Load OpenCV native library
        Loader.load(opencv_java.class);
    }
} 