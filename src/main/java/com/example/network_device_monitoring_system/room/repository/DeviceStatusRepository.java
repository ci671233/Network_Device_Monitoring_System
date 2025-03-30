package com.example.network_device_monitoring_system.room.repository;

import com.example.network_device_monitoring_system.room.model.DeviceStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

/**
 * MySQL에서 장비 상태를 조회하는 리포지토리
 */
public interface DeviceStatusRepository extends JpaRepository<DeviceStatusEntity, Long> {
    List<DeviceStatusEntity> findByLocation(String location);
    List<DeviceStatusEntity> findAllByDeviceId(String deviceId);
// 예: Room1, Room2 ...
}

