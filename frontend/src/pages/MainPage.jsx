// src/pages/MainPage.jsx
import React from "react";
import { useNavigate } from "react-router-dom";
import bulkTestData from "../data/bulk-test.json";
import axios from "../api/axios";

function MainPage() {
    const navigate = useNavigate();

    const goToRoom = (roomId) => {
        navigate(`/room/${roomId}`);
    };

    const handleBulkSend = async () => {
        try {
            await axios.post("/device/bulk-send", bulkTestData);
            alert("Bulk data sent successfully!");
        } catch (error) {
            console.error("Bulk send error:", error);
            alert("Failed to send bulk data.");
        }
    };

    return (
        <div style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            height: "100vh"
        }}>
            <h1>Main Page</h1>
            <div style={{
                display: "flex",
                flexWrap: "wrap",
                justifyContent: "center",
                gap: "10px",
                marginBottom: "20px"
            }}>
                {Array.from({ length: 10 }, (_, i) => (
                    <button
                        key={i}
                        onClick={() => goToRoom(`Room${i + 1}`)}
                        style={{
                            padding: "10px 20px",
                            fontSize: "16px",
                            cursor: "pointer"
                        }}
                    >
                        Room{i + 1}
                    </button>
                ))}
            </div>
            <button
                onClick={handleBulkSend}
                style={{
                    marginTop: "20px",
                    padding: "10px 20px",
                    backgroundColor: "black",
                    color: "white",
                    borderRadius: "5px"
                }}
            >
                test_upload
            </button>
        </div>
    );
}

export default MainPage;
