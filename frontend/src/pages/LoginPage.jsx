// src/pages/LoginPage.jsx
import React, { useState } from "react";
import axios from "../api/axios"; // 우리가 만든 axios 인스턴스 import
import { useNavigate, Link } from "react-router-dom";

function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const res = await axios.post("/user/login", {
                email,
                password,
            });
            alert("로그인 성공!");
            console.log("JWT 토큰:", res.data.token);
            localStorage.setItem("token", res.data.token); // 토큰 저장
            navigate("/main");
        } catch (err) {
            alert("로그인 실패!");
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
                <h2>로그인</h2>
                <input
                    type="email"
                    placeholder="이메일"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    style={{ width: "100%", marginBottom: "10px" }}
                /><br />
                <input
                    type="password"
                    placeholder="비밀번호"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    style={{ width: "100%", marginBottom: "10px" }}
                /><br />
                <button onClick={handleLogin} style={{ width: "100%" }}>로그인</button>
                <hr style={{ margin: "15px 0" }} />
                <Link to="/register">
                    <button style={{ width: "100%" }}>회원가입</button>
                </Link>
            </div>
        </div>
    );
}

export default LoginPage;
