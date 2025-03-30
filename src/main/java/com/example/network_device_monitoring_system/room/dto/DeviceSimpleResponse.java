package com.example.network_device_monitoring_system.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 장비 간단 응답 DTO (방 화면에서 아이콘 리스트)
 */
@Data
@AllArgsConstructor
public class DeviceSimpleResponse {
    private String deviceId;
    private String status;
    private String networkStrength;
}

