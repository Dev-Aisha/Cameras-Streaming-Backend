package com.example.cameras.camera.controller;

import com.example.cameras.camera.domain.Camera;
import com.example.cameras.camera.service.CameraService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.HashMap;

import java.util.List;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping
    public List<Camera> getAllCameras() {
        return cameraService.getAllCameras();
    }

    @PostMapping
    public Camera createCamera(@RequestBody Camera camera){
        return cameraService.createCamera(camera);
    }

    //TODO
    @GetMapping("/search")
    public List<Camera> getCamerasByName(@RequestParam String name){
        return cameraService.getCamerasByName(name);
    }

    @GetMapping("/streamUrl")
    public ResponseEntity<Map<String, String>> getStreamUrl(@RequestParam Long id) {
    try {
        Camera camera = cameraService.getCameraById(id);
        String hlsUrl = cameraService.convertRtspToHls(camera);

        Map<String, String> response = new HashMap<>();
        response.put("hlsUrl", hlsUrl);
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
    }
}

}