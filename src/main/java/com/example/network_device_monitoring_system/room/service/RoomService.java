package com.example.network_device_monitoring_system.room.service;

import com.example.network_device_monitoring_system.room.dto.DeviceSimpleResponse;
import com.example.network_device_monitoring_system.room.model.DeviceStatusEntity;
import com.example.network_device_monitoring_system.room.repository.DeviceStatusRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 특정 방(Room)에 속한 장비들을 상태별로 분류하는 서비스
 */
@Service
public class RoomService {

    private final DeviceStatusRepository deviceStatusRepository;

    public RoomService(DeviceStatusRepository deviceStatusRepository) {
        this.deviceStatusRepository = deviceStatusRepository;
    }

    /**
     * 방 이름(location)으로 해당 방의 장비들을 상태별로 분류하여 반환
     */
    public Map<String, List<DeviceSimpleResponse>> getDevicesByRoomGrouped(String roomName) {
        List<DeviceStatusEntity> devices = deviceStatusRepository.findByLocation(roomName);

        // 순서를 보장하기 위해 LinkedHashMap 사용 (ERROR → ACTIVE → IDLE)
        Map<String, List<DeviceSimpleResponse>> grouped = new LinkedHashMap<>();
        grouped.put("ERROR", new ArrayList<>());
        grouped.put("ACTIVE", new ArrayList<>());
        grouped.put("IDLE", new ArrayList<>());

        for (DeviceStatusEntity device : devices) {
            String status = device.getStatus(); // 상태는 항상 영어 (ex: ACTIVE)

            if (grouped.containsKey(status)) {
                grouped.get(status).add(
                        new DeviceSimpleResponse(
                                device.getDeviceId(),
                                device.getStatus(),
                                device.getNetworkStrength()
                        )
                );
            }
        }

        return grouped;
    }

}
