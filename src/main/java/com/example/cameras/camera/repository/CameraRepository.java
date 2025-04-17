package com.example.cameras.camera.repository;

import com.example.cameras.camera.domain.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

    List<Camera> findByName(String name);
}