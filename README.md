# 네트워크 장비 모니터링 시스템


---

## 프로젝트 개요

- 네트워크 장비의 상태를 저장, 분류, 조회할 수 있는 시스템
- REST API 서버에서 실시간 처리, 실무 환경의 흐름을 간소화하여 구현
- 장비 상태는 **MySQL**, 상세 로그는 **MongoDB**에 저장
- **Spring Boot, Kafka, Docker** 등 핵심 백엔드 기술을 조합하여 설계

---

## 사용 기술

- **Backend**: Java 21, Spring Boot
- **Database**: MySQL, MongoDB
- **Messaging**: Apache Kafka
- **Auth**: JWT, Bcrypt
- **DevOps**: Docker, Docker Compose
- **Testing/Tooling**: Postman, IntelliJ

---

## 주요 기능 요약

<img width="700" alt="기능 요약 이미지" src="https://github.com/user-attachments/assets/cb939978-83ac-4721-9cb5-f76d3beb9b9f" />

---

## 구조도

<img width="700" alt="구조도" src="https://github.com/user-attachments/assets/b6a9fe3a-c75a-4006-adf9-de880e5c4c49" />

---

## 주요 코드 스니펫

### 🟡 Producer, Consumer  
Kafka 토픽 생성 및 처리 과정  
<br/>
<img width="732" alt="Kafka 처리" src="https://github.com/user-attachments/assets/04eb0431-dde1-43a6-ade3-5b83f11e5cf7" />

---

### 🟡 DB 설계 이유  
고장 상태일 경우 비정형 상세 로그가 발생하므로, NoSQL과 SQL을 혼합하여 사용  
→ Join 연산 제거로 성능 최적화  
<br/>
<img width="687" alt="DB 설계" src="https://github.com/user-attachments/assets/bc4dfd38-720d-4a30-9a1a-e11a9bed8732" />

---

### 🟡 알고리즘 적용  
단순 Map 대신 `LinkedHashMap` 활용 → 데이터 순서 유지로 가독성 향상  
<br/>
<img width="687" alt="LinkedHashMap" src="https://github.com/user-attachments/assets/3d2e1e9a-a881-4b34-acea-f99293e5fdb7" />

---

### 🟡 접근 인가 처리  
JWT 토큰에 유효 시간 부여  
<br/>
<img width="663" alt="JWT 처리" src="https://github.com/user-attachments/assets/1d3ba4ac-bc7b-484f-b8d2-f68f229b2d1d" />

---

### 🟡 컨테이너화 구성  
<br/>
<img width="712" alt="도커 구성" src="https://github.com/user-attachments/assets/29f7788a-e297-45e0-86ce-97435b95ceab" />

---

## 결과 화면

<img width="666" alt="결과1" src="https://github.com/user-attachments/assets/0f55cbf5-0a65-4302-b867-9e49540d4872" />
<br/>
<img width="720" alt="결과2" src="https://github.com/user-attachments/assets/186b8602-9497-44e7-a4fd-4c6a366b66cf" />
<br/>
<img width="720" alt="결과3" src="https://github.com/user-attachments/assets/119bef21-5aa6-49a6-9941-33883b2e9f63" />

---

## 실행 방법

```bash
# 1. Docker로 전체 구성 실행
docker-compose up --build

# 2. API 테스트는 Postman으로 수행

