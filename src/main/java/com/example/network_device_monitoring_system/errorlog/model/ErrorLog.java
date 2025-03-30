package com.example.network_device_monitoring_system.errorlog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 고장 발생 시 포함되는 상세 로그 정보
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLog {
    private int code;           // 에러 코드
    private String message;     // 에러 메시지 (예: Ping timeout)
    private String trace;       // 상세 트레이스 경로 또는 원인
}

