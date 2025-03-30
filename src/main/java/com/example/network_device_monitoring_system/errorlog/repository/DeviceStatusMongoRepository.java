package com.example.network_device_monitoring_system.errorlog.repository;

import com.example.network_device_monitoring_system.errorlog.model.DeviceStatusDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// MongoDB용 Repository
@Repository
public interface DeviceStatusMongoRepository extends MongoRepository<DeviceStatusDocument, String> {
}

