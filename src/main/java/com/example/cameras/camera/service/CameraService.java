package com.example.cameras.camera.service;

import com.example.cameras.camera.domain.Camera;
import com.example.cameras.camera.repository.CameraRepository;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

import java.util.List;

@Service
public class CameraService {

    private final CameraRepository cameraRepository;

    public CameraService(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    public List<Camera> getCamerasByName(String name) {
        return cameraRepository.findByName(name);
    }

    public Camera getCameraById(Long id) {
    return cameraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Camera not found"));
    }   

    public Camera createCamera(Camera camera){
        return cameraRepository.save(camera);
    }

    public String convertRtspToHls(Camera camera) throws IOException {
    String cameraId = camera.getId().toString();
    String rtspUrl = camera.getStreamUrl();
    String outputDir = "src/main/resources/static/hls/camera-" + cameraId;
    String outputFile = outputDir + "/stream.m3u8";

 
    new File(outputDir).mkdirs();

    // FFmpeg
    ProcessBuilder builder = new ProcessBuilder(
        "ffmpeg",
        "-i", rtspUrl,
        "-c:v", "copy",
        "-f", "hls",
        "-hls_time", "2",
        "-hls_list_size", "3",
        "-hls_flags", "delete_segments",
        outputFile
    );

    builder.redirectErrorStream(true);
    Process process = builder.start();


    return "/hls/camera-" + cameraId + "/stream.m3u8";
}

}