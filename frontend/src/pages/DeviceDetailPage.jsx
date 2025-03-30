// src/pages/DeviceDetailPage.jsx
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../api/axios";

function DeviceDetailPage() {
    const { deviceId } = useParams();
    const [device, setDevice] = useState(null);
    const [errorLog, setErrorLog] = useState(null);

    useEffect(() => {
        const fetchDeviceDetails = async () => {
            try {
                const res = await axios.get(`/devices/${deviceId}`);
                setDevice(res.data);

                if (res.data.status === "ERROR") {
                    try {
                        const errorRes = await axios.get(`/devices/${deviceId}/error-log`);
                        setErrorLog(errorRes.data);
                    } catch (err) {
                        if (err.response?.status !== 404) {
                            console.error("에러 로그 오류", err);
                        }
                    }
                }
            } catch (err) {
                console.error("상세 정보 오류", err);
            }
        };

        fetchDeviceDetails();
    }, [deviceId]);

    if (!device) return <div className="text-center mt-20">로딩 중...</div>;

    return (
        <div className="max-w-md mx-auto mt-10 p-6 border rounded-xl shadow">
            <h2 className="text-2xl font-bold mb-4">장비 상세 정보</h2>
            <p><strong>Device ID:</strong> {device.deviceId}</p>
            <p><strong>Status:</strong> {device.status}</p>
            <p><strong>Location:</strong> {device.location}</p>
            <p><strong>Network Strength:</strong> {device.networkStrength}</p>
            <p><strong>Timestamp:</strong> {device.timestamp}</p>

            {errorLog && (
                <div className="mt-6 border-t pt-4">
                    <h3 className="text-xl font-semibold mb-2">에러 로그</h3>
                    <p><strong>Code:</strong> {errorLog.errorLog.code}</p>
                    <p><strong>Message:</strong> {errorLog.errorLog.message}</p>
                    <p><strong>Trace:</strong> {errorLog.errorLog.trace}</p>
                    <p><strong>Retry Attempts:</strong> {errorLog.retryAttempts}</p>
                </div>
            )}
        </div>
    );
}

export default DeviceDetailPage;

