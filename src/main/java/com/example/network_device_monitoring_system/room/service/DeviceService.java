package com.example.network_device_monitoring_system.room.service;

import com.example.network_device_monitoring_system.room.dto.DeviceDetailResponse;
import com.example.network_device_monitoring_system.room.model.DeviceStatusEntity;
import com.example.network_device_monitoring_system.room.repository.DeviceStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 장비 단일 조회 서비스
 * - 같은 deviceId가 여러 번 저장되더라도 가장 최신 데이터를 반환함
 */
@Service
public class DeviceService {

    private final DeviceStatusRepository deviceStatusRepository;

    public DeviceService(DeviceStatusRepository deviceStatusRepository) {
        this.deviceStatusRepository = deviceStatusRepository;
    }

    public Optional<DeviceDetailResponse> getDeviceDetail(String deviceId) {
        // deviceId가 같은 엔트리를 모두 조회
        List<DeviceStatusEntity> list = deviceStatusRepository.findAllByDeviceId(deviceId);

        if (list.isEmpty()) return Optional.empty();

        // 가장 최근(timestamp 기준)의 데이터를 선택
        DeviceStatusEntity latest = list.stream()
                .max(Comparator.comparing(DeviceStatusEntity::getTimestamp))
                .orElse(list.get(0)); // 혹시 모를 null 방지

        return Optional.of(new DeviceDetailResponse(
                latest.getDeviceId(),
                latest.getStatus(),
                latest.getLocation(),
                latest.getNetworkStrength(),
                latest.getTimestamp()
        ));
    }
}

