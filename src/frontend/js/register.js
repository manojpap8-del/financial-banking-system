/*==========================================
        KUBERX REGISTER JAVASCRIPT
==========================================*/

"use strict";

document.addEventListener("DOMContentLoaded", () => {

    console.log("Register JavaScript Loaded Successfully - register.js:9");

    // DOM Elements

    const registerForm = document.getElementById("registerForm");

    const fullName = document.getElementById("fullName");
    const email = document.getElementById("email");
    const phone = document.getElementById("phone");
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");

    registerForm.addEventListener("submit", function (e) {

        e.preventDefault();

        const fullNameValue = fullName.value.trim();
        const emailValue = email.value.trim();
        const phoneValue = phone.value.trim();
        const passwordValue = password.value.trim();
        const confirmPasswordValue = confirmPassword.value.trim();

        // Full Name Validation

        if (fullNameValue === "") {

            alert("Please enter your full name.");
            fullName.focus();
            return;

        }

        // Email Validation

        if (emailValue === "") {

            alert("Please enter your email.");
            email.focus();
            return;

        }

        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailPattern.test(emailValue)) {

            alert("Please enter a valid email.");
            email.focus();
            return;

        }

        // Phone Validation

        const phonePattern = /^[0-9]{10}$/;

        if (!phonePattern.test(phoneValue)) {

            alert("Please enter a valid 10 digit phone number.");
            phone.focus();
            return;

        }

        // Password Validation

        if (passwordValue.length < 8) {

            alert("Password must be at least 8 characters.");
            password.focus();
            return;

        }

        // Confirm Password

        if (passwordValue !== confirmPasswordValue) {

            alert("Password and Confirm Password do not match.");
            confirmPassword.focus();
            return;

        }

        // Call API

        registerUser(
            fullNameValue,
            emailValue,
            passwordValue,
            phoneValue
        );

    });






});


//==========================================
// Register API
//==========================================

async function registerUser(fullName, email, password, phone) {

    try {

        const response = await fetch("http://localhost:8081/api/users/register", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                fullName: fullName,
                email: email,
                password: password,
                phone: phone
            })

        });

        if (!response.ok) {

            const error = await response.text();

            alert(error);

            return;

        }

        const message = await response.text();

        alert(message);

        // Redirect to Login Page

        window.location.href = "login.html";

    }

   catch (error) {

    console.error("Full Error: - register.js:159", error);

    alert(error.message);

}

}
