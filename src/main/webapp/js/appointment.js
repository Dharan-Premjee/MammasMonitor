document.addEventListener("DOMContentLoaded", function () {
    const tableBody = document.getElementById("appointmentsTable");

    // Fetch appointments data from the server
    fetch("http://localhost:8080/MammasMonitor/appointments")
        .then(response => response.json())
        .then(data => {
            console.log("Appointments Data:", data);
            tableBody.innerHTML = "";

            // Check if the data is an array and has at least one appointment
            if (!Array.isArray(data) || data.length === 0) {
                tableBody.innerHTML = "<tr><td colspan='5'>No appointments found.</td></tr>";
                return;
            }

            // Loop through the appointments data and populate the table rows
            data.forEach(appointment => {
                const row = `<tr>
                    <td>${appointment.doctorName}</td>
                    <td>${appointment.patientName}</td>
                    <td>${appointment.appointmentDate}</td>
                    <td>${appointment.appointmentTime}</td>
                    <td>${appointment.status}</td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("Error fetching appointments:", error);
            tableBody.innerHTML = "<tr><td colspan='5'>Failed to load appointments.</td></tr>";
        });
});
