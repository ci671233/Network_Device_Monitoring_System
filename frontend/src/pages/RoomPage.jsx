// src/pages/RoomPage.jsx
import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "../api/axios";

function RoomPage() {
    const { roomId } = useParams();
    const navigate = useNavigate();
    const [devices, setDevices] = useState({ ERROR: [], ACTIVE: [], IDLE: [] });
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios
            .get(`/rooms/${roomId}`)
            .then((res) => {
                setDevices(res.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error("Failed to fetch devices:", err);
                setLoading(false);
            });
    }, [roomId]);

    if (loading) return <p>Loading...</p>;

    const renderDeviceGroup = (status) => (
        <div key={status} style={{ marginBottom: "30px" }}>
            <h2>{status}</h2>
            <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
                {devices[status]?.map((device) => (
                    <div
                        key={device.deviceId}
                        style={{
                            border: "1px solid #ccc",
                            padding: "10px",
                            borderRadius: "8px",
                            backgroundColor: "white",
                            cursor: "pointer",
                        }}
                        onClick={() => navigate(`/device/${device.deviceId}`)}
                    >
                        <p><strong>ID:</strong> {device.deviceId}</p>
                        <p><strong>Status:</strong> {device.status}</p>
                        <p><strong>Signal:</strong> {device.networkStrength}</p>
                    </div>
                ))}
            </div>
        </div>
    );

    return (
        <div style={{ maxWidth: "800px", margin: "0 auto", padding: "30px" }}>
            <h1>Room: {roomId}</h1>
            {renderDeviceGroup("ERROR")}
            {renderDeviceGroup("ACTIVE")}
            {renderDeviceGroup("IDLE")}
        </div>
    );
}

export default RoomPage;

