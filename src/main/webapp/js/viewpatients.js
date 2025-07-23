document.addEventListener("DOMContentLoaded", function () {
    const tableBody = document.getElementById("patientsTable");

    fetch("http://localhost:8080/MammasMonitor/viewPatients")
        .then(response => response.json())
        .then(data => {
            console.log("✅ Patients Data:", data);
            tableBody.innerHTML = "";

            if (!Array.isArray(data) || data.length === 0) {
                tableBody.innerHTML = "<tr><td colspan='4'>No patients found.</td></tr>";
                return;
            }

            data.forEach(patient => {
                const row = `<tr>
                    <td>${patient.patientId}</td> 
                    <td>${patient.name}</td>
                    <td>${patient.age}</td>
                    <td>${patient.pregnancyMonth}</td> 
                </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("❌ Error fetching patients:", error);
            tableBody.innerHTML = "<tr><td colspan='4'>Failed to load patients.</td></tr>";
        });
});
