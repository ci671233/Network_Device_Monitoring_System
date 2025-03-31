package com.example.network_device_monitoring_system.errorlog.service;

import com.example.network_device_monitoring_system.errorlog.dto.DeviceErrorLogResponse;
import com.example.network_device_monitoring_system.errorlog.model.DeviceStatusDocument;
import com.example.network_device_monitoring_system.errorlog.repository.DeviceStatusMongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ErrorLogService {

    private final DeviceStatusMongoRepository mongoRepository;

    public ErrorLogService(DeviceStatusMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public Optional<DeviceErrorLogResponse> getErrorLog(String deviceId) {
        return mongoRepository.findFirstByDeviceIdOrderByTimestampDesc(deviceId) // 시간 내림차순
                .filter(doc -> doc.getErrorLog() != null) // 필터링
                .map(doc -> new DeviceErrorLogResponse( // DTO 형태
                        doc.getDeviceId(),
                        doc.getStatus(),
                        doc.getLocation(),
                        doc.getNetworkStrength(),
                        doc.getTimestamp(),
                        new DeviceErrorLogResponse.ErrorLog(
                                doc.getErrorLog().getCode(),
                                doc.getErrorLog().getMessage(),
                                doc.getErrorLog().getTrace()
                        ),
                        doc.getRetryAttempts()
                ));
    }
}

