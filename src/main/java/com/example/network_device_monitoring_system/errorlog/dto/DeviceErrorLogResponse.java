package com.example.network_device_monitoring_system.errorlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 고장 상태인 장비의 MongoDB 상세 로그 응답 DTO
 */
@Data
@AllArgsConstructor
public class DeviceErrorLogResponse {
    private String deviceId;
    private String status;
    private String location;
    private String networkStrength;
    private LocalDateTime timestamp;

    private ErrorLog errorLog;
    private Integer retryAttempts;

    @Data
    @AllArgsConstructor
    public static class ErrorLog {
        private int code;
        private String message;
        private String trace;
    }
}

