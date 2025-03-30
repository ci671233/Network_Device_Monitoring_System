package com.example.network_device_monitoring_system.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 장비 상세 정보 응답 DTO
 */
@Data
@AllArgsConstructor
public class DeviceDetailResponse {
    private String deviceId;
    private String status;
    private String location;
    private String networkStrength;
    private LocalDateTime timestamp;
}

