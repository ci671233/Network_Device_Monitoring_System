package com.example.network_device_monitoring_system.room.model;

import com.example.network_device_monitoring_system.errorlog.model.ErrorLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Kafka를 통해 주고받을 장비 상태 메시지 DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatusMessage {

    private String deviceId;         // 장비 고유 ID
    private String status;           // 작동 상태 (작동 / 고장 / 대기)
    private String location;         // 위치 (방1 ~ 방10)
    private String networkStrength;  // 통신 강도 (약 / 중 / 강)
    private LocalDateTime timestamp; // 상태 수집 시간

    private ErrorLog errorLog;       // 고장 상세 로그 (고장일 경우에만 존재)
    private Integer retryAttempts;   // 재시도 횟수 (고장일 경우)

}

