package com.example.network_device_monitoring_system.auth.service;

import com.example.network_device_monitoring_system.auth.model.User;
import com.example.network_device_monitoring_system.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 사용자 정보를 처리하는 서비스 클래스
 * - 이메일로 사용자 조회
 * - 비밀번호 검증 (BCrypt)
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 해시 검증용
    }

    // 사용자 조회 (이메일 기준)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 비밀번호 일치 여부 확인
    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}

