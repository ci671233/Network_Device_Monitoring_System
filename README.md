# 네트워크 장비 모니터링 시스템

> Kafka, Spring Boot, MongoDB, MySQL, JWT 인증 기반의 실시간 장비 모니터링 시스템

---

## 프로젝트 개요

- 네트워크 장비의 상태(정상, 대기, 오류 등)를 Kafka로 수집
- Spring Boot 기반 REST API 서버에서 실시간 처리
- 장비 상태는 MySQL, 상세 로그는 MongoDB에 저장
- WebSocket을 통해 실시간 알림 데모 구현
- JWT 기반 사용자 인증 기능 포함

---

## 사용 기술

- **Backend**: Java 21, Spring Boot, Spring Security, JPA
- **Database**: MySQL, MongoDB
- **Messaging**: Apache Kafka
- **Auth**: JWT, Bcrypt
- **DevOps**: Docker, Docker Compose
- **Test/Tooling**: Postman, IntelliJ, VS Code

---

## 주요 기능

| 기능 | 설명 |
|------|------|
| ✅ Kafka 연동 | 장비 상태 데이터 수집 및 전달 |
| ✅ REST API | 장비 상태 조회, 에러 로그 조회, 사용자 인증 |
| ✅ JWT 인증 | 로그인 / 회원가입 / 권한 확인 |
| ✅ MongoDB | 에러 발생 장비의 상세 로그 저장 |
| ✅ WebSocket | 실시간 알림 데모 구현 (카메라 스트리밍 등)

---

## 📊 ERD & 구조도

> (필요 시 ERD, 시스템 아키텍처 다이어그램, 시퀀스 다이어그램 이미지 첨부)

---

## 실행 방법

```bash
# 1. Docker로 전체 구성 실행
docker-compose up --build

# 2. API 테스트는 Postman으로 수행
