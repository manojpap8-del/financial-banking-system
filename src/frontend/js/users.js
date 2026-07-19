"use strict";

// ==========================================
// Page Load
// ==========================================

document.addEventListener("DOMContentLoaded", () => {

    loadUsers();

    document
        .getElementById("searchBtn")
        .addEventListener("click", searchUser);

});

// ==========================================
// Load All Users
// ==========================================

async function loadUsers() {

    const token = localStorage.getItem("adminToken");

    console.log("Admin Token : - users.js:25", token);

    if (!token) {

        alert("Admin Login Required");

        window.location.href = "login.html";

        return;
    }

    try {

        const response = await fetch(
            "http://localhost:8081/api/admin/users",
            {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + token,
                    "Content-Type": "application/json"
                }
            });

        console.log("Response Status : - users.js:48", response.status);

        if (!response.ok) {

            const error = await response.text();

            console.log("Server Error : - users.js:54", error);

            alert("Unable to load users.");

            return;
        }

        const users = await response.json();

        console.log("Users : - users.js:63", users);

        showUsers(users);

    }

    catch (error) {

        console.error(error);

        alert("Server Error");

    }

}

// ==========================================
// Show Users
// ==========================================

function showUsers(users) {

    const table = document.getElementById("userTable");

    table.innerHTML = "";

    if (users.length === 0) {

        table.innerHTML = `
        <tr>
            <td colspan="7" class="text-center">
                No Users Found
            </td>
        </tr>`;

        return;
    }

    users.forEach(user => {

        table.innerHTML += `
        <tr>

            <td>${user.id}</td>

            <td>${user.fullName}</td>

            <td>${user.email}</td>

            <td>${user.phone}</td>

            <td>${user.role}</td>

            <td>₹${user.balance ?? 0}</td>

            <td>

                <button class="btn btn-primary btn-sm">

                    View

                </button>

            </td>

        </tr>`;
    });

}

// ==========================================
// Search User
// ==========================================

function searchUser() {

    const keyword = document
        .getElementById("searchUser")
        .value
        .trim()
        .toLowerCase();

    const rows = document.querySelectorAll("#userTable tr");

    rows.forEach(row => {

        if (row.innerText.toLowerCase().includes(keyword)) {

            row.style.display = "";

        } else {

            row.style.display = "none";

        }

    });

}