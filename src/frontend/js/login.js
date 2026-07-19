"use strict";

document.addEventListener("DOMContentLoaded", () => {

    const loginForm = document.getElementById("loginForm");
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");

    loginForm.addEventListener("submit", (event) => {

        event.preventDefault();

        const email = emailInput.value.trim();
        const password = passwordInput.value.trim();

        // Email Validation
        if (email === "") {
            alert("Please enter your email.");
            emailInput.focus();
            return;
        }

        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailPattern.test(email)) {
            alert("Please enter a valid email address.");
            emailInput.focus();
            return;
        }

        // Password Validation
        if (password === "") {
            alert("Please enter your password.");
            passwordInput.focus();
            return;
        }

        if (password.length < 8) {
            alert("Password must contain at least 8 characters.");
            passwordInput.focus();
            return;
        }

        loginUser(email, password);

    });

});

// ==========================================
// Login API
// ==========================================

async function loginUser(email, password) {

    try {

        const response = await fetch("http://localhost:8081/api/admin/login", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email,
                password
            })

        });

        const result = await response.json();

        if (!response.ok) {
            alert(result.message);
            return;
        }

        localStorage.setItem("adminToken", result.token);

        alert(result.message);

        window.location.href = "../dashboard.html";

    } catch (error) {

        console.error(error);

        alert("Unable to connect to the server.");

    }

}