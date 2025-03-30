package com.example.network_device_monitoring_system.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 정보를 저장하는 JPA 엔티티
 */
@Entity
@Table(name = "users") // DB 테이블 이름
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키

    @Column(nullable = false, unique = true)
    private String email;  // 로그인 ID 역할

    @Column(nullable = false)
    private String username;  // 사용자 이름

    @Column(nullable = false)
    private String password;  // 비밀번호 (BCrypt 해시 저장)
}
