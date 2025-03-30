package com.example.network_device_monitoring_system.user.service;

import com.example.network_device_monitoring_system.user.model.User;
import com.example.network_device_monitoring_system.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 사용자 관련 비즈니스 로직 처리
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 생성자에서 BCryptPasswordEncoder를 주입 받음
     * - Bean으로 등록된 인스턴스를 사용
     */
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 이메일로 사용자 조회
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 비밀번호 일치 여부 검증
    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    /**
     * 사용자 등록
     * - 비밀번호는 내부에서 해시 처리
     */
    public void register(String email, String username, String rawPassword) {
        String hashed = passwordEncoder.encode(rawPassword);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashed);
        userRepository.save(user);
    }

}


