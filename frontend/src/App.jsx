// src/App.jsx
import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import MainPage from "./pages/MainPage";
import RoomPage from "./pages/RoomPage";
import DeviceDetailPage from "./pages/DeviceDetailPage";
import Navbar from "./components/Navbar";

// ⬇️ 별도 컴포넌트로 분리해서 라우터 내부에서 경로 체크 가능하게 구성
function AppWrapper() {
    const location = useLocation();
    const token = localStorage.getItem("token");
    const hideNavbar = location.pathname === "/login" || location.pathname === "/register";

    return (
        <>
            {!hideNavbar && token && <Navbar />}
            <Routes>
                <Route path="/" element={<Navigate to="/login" />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/main" element={<MainPage />} />
                <Route path="/room/:roomId" element={<RoomPage />} />
                <Route path="/device/:deviceId" element={<DeviceDetailPage />} />
            </Routes>
        </>
    );
}

function App() {
    return (
        <Router>
            <AppWrapper />
        </Router>
    );
}

export default App;

