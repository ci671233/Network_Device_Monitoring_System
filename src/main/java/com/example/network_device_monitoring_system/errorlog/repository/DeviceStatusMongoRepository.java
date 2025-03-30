package com.example.network_device_monitoring_system.errorlog.repository;

import com.example.network_device_monitoring_system.errorlog.model.DeviceStatusDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// MongoDB용 Repository
@Repository
public interface DeviceStatusMongoRepository extends MongoRepository<DeviceStatusDocument, String> {
    // ✅ 가장 최근 하나만 가져오기 (동일 deviceId가 여러 개여도 OK)
    Optional<DeviceStatusDocument> findFirstByDeviceIdOrderByTimestampDesc(String deviceId);
}

