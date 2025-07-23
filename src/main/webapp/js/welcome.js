function navigate(page) {
    let role = document.getElementById("roleSelect").value;

    if (role === "patient") {
        if (page === "login") {
            window.location.href = "patientlogin.html";
        } else if (page === "signup") {
            window.location.href = "patientsignup.html";
        }
    } else if (role === "doctor") {
        if (page === "login") {
            window.location.href = "doctorlogin.html";
        } else if (page === "signup") {
            window.location.href = "doctorsignup.html";
        }
    } else {
        alert("Please select a role before proceeding!");
    }
}
