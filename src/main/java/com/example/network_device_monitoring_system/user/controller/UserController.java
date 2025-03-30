package com.example.network_device_monitoring_system.user.controller;

import com.example.network_device_monitoring_system.user.model.User;
import com.example.network_device_monitoring_system.user.service.UserService;
import com.example.network_device_monitoring_system.user.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * 로그인 요청을 처리하는 컨트롤러
 * 이메일 + 비밀번호 검증 → JWT 발급
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 로그인 엔드포인트
     * @param loginRequest email, password 입력 받음
     * @return JWT 토큰 or 오류 메시지
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("존재하지 않는 사용자입니다.");
        }

        User user = userOpt.get();
        if (!userService.checkPassword(password, user.getPassword())) {
            return ResponseEntity.status(401).body("비밀번호가 일치하지 않습니다.");
        }

        // JWT 토큰 발급
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }

    /**
     * 회원가입 엔드포인트
     * - 이메일 중복 확인
     * - 비밀번호는 서버에서 해시 처리
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerRequest) {
        String email = registerRequest.get("email");
        String username = registerRequest.get("username");
        String rawPassword = registerRequest.get("password");

        if (userService.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");
        }

        userService.register(email, username, rawPassword);
        return ResponseEntity.ok("회원가입 완료");
    }
}

