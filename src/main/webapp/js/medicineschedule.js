document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8080/MammasMonitor/getMedicineSchedule")
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("medicineTableBody");
            tableBody.innerHTML = ""; // Clear previous data

            if (data.length === 0) {
                tableBody.innerHTML = "<tr><td colspan='4'>No medicines found.</td></tr>";
                return;
            }

            data.forEach(med => {
                const row = `<tr>
                    <td>${med.name}</td>
                    <td>${med.dosage}</td>
                    <td>${med.time}</td>
                    <td>${med.instructions}</td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("Error fetching medicines:", error);
            document.getElementById("medicineTableBody").innerHTML =
                "<tr><td colspan='4'>Failed to load medicines.</td></tr>";
        });
});
