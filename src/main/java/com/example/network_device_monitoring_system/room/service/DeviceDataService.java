package com.example.network_device_monitoring_system.room.service;

import com.example.network_device_monitoring_system.room.model.DeviceStatusMessage;
import com.example.network_device_monitoring_system.room.model.DeviceStatusEntity;
import com.example.network_device_monitoring_system.errorlog.model.DeviceStatusDocument;
import com.example.network_device_monitoring_system.room.repository.DeviceStatusRepository;
import com.example.network_device_monitoring_system.errorlog.repository.DeviceStatusMongoRepository;
import org.springframework.stereotype.Service;

/**
 * 장비 상태 메시지를 MySQL / MongoDB에 저장하는 서비스
 */
@Service
public class DeviceDataService {

    private final DeviceStatusRepository mysqlRepository;
    private final DeviceStatusMongoRepository mongoRepository;

    public DeviceDataService(DeviceStatusRepository mysqlRepository,
                             DeviceStatusMongoRepository mongoRepository) {
        this.mysqlRepository = mysqlRepository;
        this.mongoRepository = mongoRepository;
    }

    /**
     * Kafka로부터 수신된 메시지를 저장 처리
     */
    public void saveMessage(DeviceStatusMessage message) {
        System.out.println("==== Kafka 메시지 수신 ====");
        System.out.println("Device ID: " + message.getDeviceId());
        System.out.println("Status: " + message.getStatus());
        System.out.println("Location: " + message.getLocation());
        System.out.println("Network Strength: " + message.getNetworkStrength());
        System.out.println("Timestamp: " + message.getTimestamp());
        System.out.println("ErrorLog: " + message.getErrorLog());
        System.out.println("RetryAttempts: " + message.getRetryAttempts());

        // MySQL에 저장
        DeviceStatusEntity entity = new DeviceStatusEntity(
                null,
                message.getDeviceId(),
                message.getStatus(),
                message.getLocation(),
                message.getNetworkStrength(),
                message.getTimestamp()
        );
        mysqlRepository.save(entity);
        System.out.println("MySQL 저장 완료");

        // MongoDB에 저장 (고장 상태인 경우에만)
        if (message.getStatus() != null && message.getStatus().equalsIgnoreCase("ERROR")) {
            System.out.println("MongoDB 저장 시도");

            DeviceStatusDocument document = new DeviceStatusDocument(
                    null,
                    message.getDeviceId(),
                    message.getStatus(),
                    message.getLocation(),
                    message.getNetworkStrength(),
                    message.getTimestamp(),
                    message.getErrorLog(),
                    message.getRetryAttempts()
            );
            mongoRepository.save(document);
            System.out.println("MongoDB 저장 완료");
        } else {
            System.out.println("MongoDB 저장 에러");
        }

        System.out.println("==== 처리 완료 ====\n");
    }
}
