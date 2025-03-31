package com.example.network_device_monitoring_system.errorlog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// MongoDB에 저장될 장비 상태 상세 로그 문서
@Document(collection = "device_status_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusDocument {

    @Id
    private String id; // 기본 ID

    private String deviceId;         // 장비 ID
    private String status;           // 작동 상태
    private String location;         // 위치
    private String networkStrength;  // 통신 강도
    private LocalDateTime timestamp; // 수집 시각

    private ErrorLog errorLog;       // 고장 상세 로그
    private Integer retryAttempts;   // 재시도 횟수
}
