package com.example.network_device_monitoring_system.room.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// MySQL에 저장할 장비 상태 Entity 클래스
@Entity
@Table(name = "device_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    private String deviceId;         // 장비 ID
    private String status;           // 작동 상태 (작동 / 고장 / 대기)
    private String location;         // 위치 (Room1 ~ Room10)
    private String networkStrength;  // 통신 강도 (약 / 중 / 강)
    private LocalDateTime timestamp; // 수집 시각
}
