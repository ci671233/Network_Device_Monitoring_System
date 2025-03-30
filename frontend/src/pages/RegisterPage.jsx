// src/pages/RegisterPage.jsx
import React, { useState } from "react";
import axios from "../api/axios";
import { useNavigate } from "react-router-dom";

function RegisterPage() {
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            await axios.post("/user/register", {
                email,
                username,
                password,
            });
            alert("회원가입 성공! 로그인 페이지로 이동합니다.");
            navigate("/");
        } catch (err) {
            alert("회원가입 실패");
        }
    };

    return (
        <div style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            height: "100vh",
        }}>
            <div style={{
                width: "300px",
                padding: "2rem",
                border: "1px solid #ccc",
                borderRadius: "8px",
                boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
                textAlign: "center"
            }}>
                <h2>회원가입</h2>
                <input
                    type="email"
                    placeholder="이메일"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    style={{ width: "100%", marginBottom: "10px" }}
                /><br />
                <input
                    type="text"
                    placeholder="이름"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    style={{ width: "100%", marginBottom: "10px" }}
                /><br />
                <input
                    type="password"
                    placeholder="비밀번호"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    style={{ width: "100%", marginBottom: "10px" }}
                /><br />
                <button onClick={handleRegister} style={{ width: "100%" }}>가입하기</button>
            </div>
        </div>
    );
}

export default RegisterPage;
