# OpenCV Microservice

A Spring Boot microservice that provides image processing and face detection capabilities using OpenCV.

## Features

- Convert images to grayscale
- Detect faces in images
- Face recognition (placeholder for future implementation)

## Prerequisites

- Java 17
- Maven 3.6+
- OpenCV native libraries (automatically downloaded via Maven)

## Building and Running

1. Clone the repository
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The service will start on port 8080 by default.

## API Endpoints

### 1. Convert Image to Grayscale

```bash
curl -X POST -F "image=@/path/to/your/image.jpg" http://localhost:8080/api/image/process -o output.jpg
```

### 2. Detect Faces in Image

```bash
curl -X POST -F "image=@/path/to/your/image.jpg" http://localhost:8080/api/image/face-detect -o output.jpg
```

### 3. Recognize Faces in Image

```bash
curl -X POST -F "image=@/path/to/your/image.jpg" http://localhost:8080/api/image/face-recognize -o output.jpg
```

## Response

All endpoints return the processed image as a JPEG file with `Content-Type: image/jpeg`.

## Error Handling

The service includes basic error handling for:
- Invalid image formats
- Processing failures
- Face detection errors

## Development

The project uses:
- Spring Boot 3.x
- OpenCV 4.8.0
- Java 17
- Maven for dependency management

## License

This project is licensed under the MIT License. 