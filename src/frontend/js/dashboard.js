/*==========================================
        KUBERX DASHBOARD JAVASCRIPT
==========================================*/

"use strict";

let depositModal;
let withdrawModal;
let transferModal;
let transactionModal;
let balanceModal;
let changePasswordModal;
let closeAccountModal;

document.addEventListener("DOMContentLoaded", () => {

    console.log("Dashboard Loaded Successfully - dashboard.js:17");

    // ==========================
    // Check JWT Token
    // ==========================

    const token = localStorage.getItem("jwtToken");

    if (!token) {
        alert("Please Login First");
        window.location.href = "login.html";
        return;
    }

    console.log("JWT Token Found - dashboard.js:31");

    // ==========================
    // Initialize Bootstrap Modals
    // ==========================

    depositModal = new bootstrap.Modal(
        document.getElementById("depositModal")
    );

    withdrawModal = new bootstrap.Modal(
        document.getElementById("withdrawModal")
    );

    transferModal = new bootstrap.Modal(
        document.getElementById("transferModal")
    );
    transactionModal = new bootstrap.Modal(
        document.getElementById("transactionModal")
    );
    balanceModal = new bootstrap.Modal(
        document.getElementById("balanceModal")
    );
    changePasswordModal =
        new bootstrap.Modal(
            document.getElementById("changePasswordModal"));

    closeAccountModal =
        new bootstrap.Modal(
            document.getElementById("closeAccountModal"));

    // ==========================
    // Load Profile
    // ==========================

    loadUserProfile(token);

    // ==========================
    // Deposit
    // ==========================

    document
        .getElementById("depositCard")
        .addEventListener("click", () => {
            depositModal.show();
        });

    document
        .getElementById("depositBtn")
        .addEventListener("click", depositMoney);

    // ==========================
    // Withdraw
    // ==========================

    document
        .getElementById("withdrawCard")
        .addEventListener("click", () => {
            withdrawModal.show();
        });

    document
        .getElementById("withdrawBtn")
        .addEventListener("click", withdrawMoney);

    // ==========================
    // Transfer
    // ==========================

    document
        .getElementById("transferCard")
        .addEventListener("click", () => {

            transferModal.show();

        });

    document
        .getElementById("transferBtn")
        .addEventListener("click", transferMoney);

    // ==========================
    // Transaction History
    // ==========================

    document
        .getElementById("transactionCard")
        .addEventListener("click", loadTransactions);


    document
        .getElementById("balanceCard")
        .addEventListener("click", checkBalance);

    document
        .getElementById("changePasswordCard")
        .addEventListener("click", () => {

            changePasswordModal.show();

        });

    document
        .getElementById("changePasswordBtn")
        .addEventListener("click", changePassword);

    // close account
    document
        .getElementById("closeAccountCard")
        .addEventListener("click", () => {

            closeAccountModal.show();

        });

    document
        .getElementById("closeAccountBtn")
        .addEventListener("click", closeAccount);



    // ==========================
    // Admin Panel
    // ==========================

   document
    .getElementById("adminPanelCard")
    .addEventListener("click", function () {

        window.location.href = "users.html";

    });

    // ==========================
    // Logout
    // ==========================

    document
        .getElementById("logoutBtn")
        .addEventListener("click", () => {

            localStorage.removeItem("jwtToken");

            alert("Logout Successful");

            window.location.href = "login.html";

        });

});

/*==========================================
        LOAD PROFILE
==========================================*/

async function loadUserProfile(token) {

    try {

        const response = await fetch(
            "http://localhost:8081/api/users/profile",
            {

                method: "GET",

                headers: {
                    "Authorization": "Bearer " + token
                }

            });

        if (!response.ok) {

            alert("Session Expired");

            localStorage.removeItem("jwtToken");

            window.location.href = "login.html";

            return;
        }

        const data = await response.text();

        document.getElementById("welcomeUser").innerHTML =
            data + " 👋";

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}

/*==========================================
        DEPOSIT
==========================================*/

async function depositMoney() {

    const amount = Number(
        document.getElementById("depositAmount").value
    );

    if (amount <= 0) {

        alert("Enter Valid Amount");

        return;
    }

    const token = localStorage.getItem("jwtToken");

    try {

        const response = await fetch(
            "http://localhost:8081/api/accounts/deposit",
            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json",

                    "Authorization": "Bearer " + token

                },

                body: JSON.stringify({

                    amount: amount

                })

            });

        if (!response.ok) {

            const error = await response.text();

            alert(error);

            return;
        }

        const data = await response.json();

        alert(data.message);

        document.getElementById("balance").innerHTML =
            "₹" + data.currentBalance;

        depositModal.hide();

        document.getElementById("depositAmount").value = "";

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}

/*==========================================
        WITHDRAW
==========================================*/

async function withdrawMoney() {

    const amount = Number(
        document.getElementById("withdrawAmount").value
    );

    if (amount <= 0) {

        alert("Enter Valid Amount");

        return;
    }

    const token = localStorage.getItem("jwtToken");

    try {

        const response = await fetch(
            "http://localhost:8081/api/accounts/withdraw",
            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json",

                    "Authorization": "Bearer " + token

                },

                body: JSON.stringify({

                    amount: amount

                })

            });

        if (!response.ok) {

            const error = await response.text();

            alert(error);

            return;
        }

        const data = await response.json();

        alert(data.message);

        document.getElementById("balance").innerHTML =
            "₹" + data.currentBalance;

        withdrawModal.hide();

        document.getElementById("withdrawAmount").value = "";

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}

async function transferMoney() {

    const receiverAccountNumber =
        document.getElementById("receiverAccount").value.trim();

    const amount =
        Number(document.getElementById("transferAmount").value);

    if (receiverAccountNumber === "") {

        alert("Enter Receiver Account Number");

        return;

    }

    if (amount <= 0) {

        alert("Enter Valid Amount");

        return;

    }

    const token = localStorage.getItem("jwtToken");

    try {

        const response = await fetch(
            "http://localhost:8081/api/accounts/transfer",
            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json",

                    "Authorization": "Bearer " + token

                },

                body: JSON.stringify({

                    receiverAccountNumber: receiverAccountNumber,

                    amount: amount

                })

            });

        if (!response.ok) {

            const error = await response.text();

            alert(error);

            return;

        }

        const data = await response.json();

        alert(data.message);

        document.getElementById("balance").innerHTML =
            "₹" + data.currentBalance;

        transferModal.hide();

        document.getElementById("receiverAccount").value = "";

        document.getElementById("transferAmount").value = "";

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}

async function loadTransactions() {

    const token = localStorage.getItem("jwtToken");

    try {

        const response = await fetch(

            "http://localhost:8081/api/accounts/statement",

            {
                method: "GET",

                headers: {
                    "Authorization": "Bearer " + token
                }
            });

        if (!response.ok) {

            alert("Unable to load transactions");

            return;
        }

        const data = await response.json();

        const table =
            document.getElementById("transactionTable");

        table.innerHTML = "";

        data.forEach(transaction => {

            table.innerHTML += `

            <tr>

                <td>${transaction.transactionId}</td>

                <td>${transaction.transactionType}</td>

                <td>₹${transaction.amount}</td>

                <td>${transaction.transactionDate}</td>

            </tr>

            `;

        });

        transactionModal.show();

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}

async function checkBalance() {

    const token = localStorage.getItem("jwtToken");

    try {

        const response = await fetch(

            "http://localhost:8081/api/accounts/balance",

            {

                method: "GET",

                headers: {

                    "Authorization": "Bearer " + token

                }

            }

        );

        if (!response.ok) {

            alert("Unable to fetch balance.");

            return;

        }

        const data = await response.json();

        document.getElementById("accountNumber").innerHTML =
            data.accountNumber;

        document.getElementById("availableBalance").innerHTML =
            "₹" + data.balance.toLocaleString("en-IN");

        balanceModal.show();

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}

async function changePassword() {

    const oldPassword =
        document.getElementById("oldPassword").value;

    const newPassword =
        document.getElementById("newPassword").value;

    const confirmPassword =
        document.getElementById("confirmPassword").value;

    // Validation

    if (!oldPassword || !newPassword || !confirmPassword) {

        alert("Please fill all fields.");

        return;

    }

    if (newPassword.length < 8) {

        alert("New password must be at least 8 characters.");

        return;

    }

    if (newPassword !== confirmPassword) {

        alert("Confirm password does not match.");

        return;

    }

    const token = localStorage.getItem("jwtToken");

    try {

        const response = await fetch(

            "http://localhost:8081/api/users/change-password",

            {

                method: "PUT",

                headers: {

                    "Content-Type": "application/json",

                    "Authorization": "Bearer " + token

                },

                body: JSON.stringify({

                    oldPassword: oldPassword,

                    newPassword: newPassword

                })

            }

        );

        if (!response.ok) {

            const error = await response.text();

            alert(error);

            return;

        }

        const data = await response.json();

        alert(data.message);

        changePasswordModal.hide();

        document.getElementById("oldPassword").value = "";

        document.getElementById("newPassword").value = "";

        document.getElementById("confirmPassword").value = "";

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}


async function closeAccount() {

    const password =
        document.getElementById("closePassword").value;

    if (!password) {

        alert("Please enter your password.");

        return;

    }

    const token = localStorage.getItem("jwtToken");

    try {

        const response = await fetch(

            "http://localhost:8081/api/accounts/close",

            {

                method: "PUT",

                headers: {

                    "Content-Type": "application/json",

                    "Authorization": "Bearer " + token

                },

                body: JSON.stringify({

                    password: password

                })

            }

        );

        if (!response.ok) {

            const error = await response.json();

            alert(error.message);

            return;

        }

        const data = await response.json();

        alert(data.message);

        closeAccountModal.hide();

        localStorage.removeItem("jwtToken");

        alert("Your account has been closed. Please login again.");

        window.location.href = "login.html";

    }

    catch (error) {

        console.error(error);

        alert("Unable to connect to server.");

    }

}