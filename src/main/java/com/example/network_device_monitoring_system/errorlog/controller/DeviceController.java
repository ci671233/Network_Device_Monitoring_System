package com.example.network_device_monitoring_system.errorlog.controller;

import com.example.network_device_monitoring_system.errorlog.service.ErrorLogService;
import com.example.network_device_monitoring_system.room.dto.DeviceDetailResponse;
import com.example.network_device_monitoring_system.room.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 장비 상세 정보 및 에러 로그를 반환하는 컨트롤러
 * - /api/devices/{deviceId} : 장비 상세 상태 (MySQL)
 * - /api/devices/{deviceId}/error-log : 고장 상태일 경우 에러 로그 (MongoDB)
 */
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;         // 장비 상태 조회용 서비스 (MySQL)
    private final ErrorLogService errorLogService;     // 에러 로그 조회용 서비스 (MongoDB)

    // 생성자를 통해 두 서비스 주입
    public DeviceController(DeviceService deviceService, ErrorLogService errorLogService) {
        this.deviceService = deviceService;
        this.errorLogService = errorLogService;
    }

    /**
     * 특정 장비(deviceId)의 상세 상태 정보 조회
     * @param deviceId 장비 고유 ID
     * @return DeviceDetailResponse (상태 + 위치 + 통신 강도 + 타임스탬프 등)
     */
    @GetMapping("/{deviceId}")
    public ResponseEntity<?> getDeviceDetail(@PathVariable String deviceId) {
        Optional<DeviceDetailResponse> device = deviceService.getDeviceDetail(deviceId);

        return device
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 특정 장비(deviceId)의 에러 로그 상세 조회
     * - 상태가 ERROR인 경우에만 존재
     * - MongoDB에서 조회
     * @param deviceId 장비 고유 ID
     * @return DeviceErrorLogResponse (에러 로그 + 재시도 횟수 등)
     */
    @GetMapping("/{deviceId}/error-log")
    public ResponseEntity<?> getErrorLog(@PathVariable String deviceId) {
        return errorLogService.getErrorLog(deviceId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}



