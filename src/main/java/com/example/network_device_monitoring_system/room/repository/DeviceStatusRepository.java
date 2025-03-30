package com.example.network_device_monitoring_system.room.repository;

import com.example.network_device_monitoring_system.room.model.DeviceStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// MySQL용 JPA Repository 인터페이스
@Repository
public interface DeviceStatusRepository extends JpaRepository<DeviceStatusEntity, Long> {
}
