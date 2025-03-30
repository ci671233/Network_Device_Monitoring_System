package com.example.network_device_monitoring_system.room.controller;

import com.example.network_device_monitoring_system.kafka.DeviceStatusProducer;
import com.example.network_device_monitoring_system.room.model.DeviceStatusMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 테스트 메시지 전송용 컨트롤러
@RestController
@RequestMapping("/api/device")
public class DeviceTestController {

    private final DeviceStatusProducer producer;

    public DeviceTestController(DeviceStatusProducer producer) {
        this.producer = producer;
    }

    /**
     * 단건 메시지 전송 (테스트용)
     */
    @PostMapping("/send")
    public String sendTestMessage(@RequestBody DeviceStatusMessage message) {
        producer.sendStatus(message);
        return "Kafka로 메시지 전송 완료";
    }

    /**
     * 다건 메시지 전송 (배열로)
     */
    @PostMapping("/bulk-send")
    public String sendBulkMessages(@RequestBody List<DeviceStatusMessage> messages) {
        messages.forEach(producer::sendStatus);
        return "총 " + messages.size() + "개의 메시지를 Kafka로 전송했습니다.";
    }
}


