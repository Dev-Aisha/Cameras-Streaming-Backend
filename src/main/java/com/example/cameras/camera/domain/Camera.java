package com.example.cameras.camera.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cameras")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double latitude;
    private double longitude;

    @Column(name = "stream_url")
    private String streamUrl;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }
}