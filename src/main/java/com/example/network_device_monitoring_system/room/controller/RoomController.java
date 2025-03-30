package com.example.network_device_monitoring_system.room.controller;

import com.example.network_device_monitoring_system.room.dto.DeviceSimpleResponse;
import com.example.network_device_monitoring_system.room.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 특정 방(Room)에 속한 장비 리스트를 상태별로 분류하여 제공하는 API
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * 방 이름을 받아 해당 방의 장비 목록 반환
     */
    @GetMapping("/{room}")
    public ResponseEntity<Map<String, List<DeviceSimpleResponse>>> getDevicesByRoom(@PathVariable String room) {
        return ResponseEntity.ok(roomService.getDevicesByRoomGrouped(room));
    }
}
