// patientdashboard.js
document.addEventListener("DOMContentLoaded", function () {
    const patientEmail = localStorage.getItem("patientEmail");
    if (patientEmail) {
        const header = document.querySelector("header h1");
        header.innerHTML += ` - Welcome, ${patientEmail}`;
    }
});
