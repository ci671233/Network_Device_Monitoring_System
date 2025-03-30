package com.example.network_device_monitoring_system.kafka;

import com.example.network_device_monitoring_system.room.model.DeviceStatusMessage;
import com.example.network_device_monitoring_system.room.service.DeviceDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// Kafka로부터 장비 상태 메시지를 수신하는 클래스
@Component
public class DeviceStatusConsumer {

    private final ObjectMapper objectMapper;
    private final DeviceDataService deviceDataService;

    public DeviceStatusConsumer(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Kafka에서 메시지를 수신하여 MySQL/MongoDB에 저장
     * @param record Kafka 메시지 레코드
     */
    @KafkaListener(topics = "device-status", groupId = "ndms-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            // JSON 문자열 → DTO 객체로 변환
            String json = record.value();
            DeviceStatusMessage message = objectMapper.readValue(json, DeviceStatusMessage.class);

            System.out.println("Kafka 수신: " + json);

            // 서비스 계층을 통해 DB 저장 처리
            deviceDataService.saveMessage(message);

        } catch (Exception e) {
            System.err.println("Kafka 메시지 처리 실패: " + e.getMessage());
        }
    }
}


