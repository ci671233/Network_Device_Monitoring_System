package com.example.network_device_monitoring_system.user.repository;

import com.example.network_device_monitoring_system.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 사용자 정보를 조회하는 JPA Repository
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일(로그인 ID)로 사용자 조회
    Optional<User> findByEmail(String email);
}
