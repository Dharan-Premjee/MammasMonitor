document.querySelector("form").addEventListener("submit", function (event) {
    event.preventDefault();

    const email = document.querySelector("input[type='email']").value;
    localStorage.setItem("patientEmail", email);

    window.location.href = "patientdashboard.html";
});
