// src/components/Navbar.jsx
import React from "react";
import { Link } from "react-router-dom";

function Navbar() {
    const token = localStorage.getItem("token");
    const username = token ? JSON.parse(atob(token.split(".")[1])).username : null;

    return (
        <div className="w-full p-4 bg-gray-800 text-white flex justify-center items-center">
            <div className="flex items-center space-x-4">
                <span className="text-base font-medium">{username}</span>
                <span className="text-4xl">🔔</span>
            </div>
        </div>
    );
}

export default Navbar;

