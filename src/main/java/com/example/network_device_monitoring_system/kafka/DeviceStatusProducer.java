package com.example.network_device_monitoring_system.kafka;

import com.example.network_device_monitoring_system.room.model.DeviceStatusMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

// Kafka를 통해 장비 상태 메시지를 전송하는 클래스
@Component
public class DeviceStatusProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public DeviceStatusProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();

        // LocalDateTime 직렬화를 위한 모듈 등록
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Kafka에 JSON 메시지 전송
     * @param message 전송할 장비 상태 메시지 객체
     */
    public void sendStatus(DeviceStatusMessage message) {
        try {
            // 객체 → JSON 문자열 변환
            String json = objectMapper.writeValueAsString(message);

            // Kafka 토픽으로 메시지 전송
            kafkaTemplate.send("device-status", json); // String, String

            System.out.println("Kafka로 메시지 전송됨: " + json);

        } catch (JsonProcessingException e) {
            System.err.println("실패: " + e.getMessage());
        }
    }
}

