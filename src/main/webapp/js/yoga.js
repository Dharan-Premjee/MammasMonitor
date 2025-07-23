document.addEventListener("DOMContentLoaded", function () {
    const tableBody = document.getElementById("yogaTable"); // ✅ Correct ID

    fetch("http://localhost:8080/MammasMonitor/getYogaPlan")
        .then(response => response.json())
        .then(data => {
            console.log("✅ Yoga Plans:", data); // Debugging log

            // ✅ Clear existing rows
            tableBody.innerHTML = "";

            if (!Array.isArray(data) || data.length === 0) {
                tableBody.innerHTML = "<tr><td colspan='3'>No yoga plans found.</td></tr>";
                return;
            }

            // ✅ Append new rows
            data.forEach(yoga => {
                const row = `<tr>
                    <td>${yoga.trimester}</td>
                    <td>${yoga.pose_name}</td>
                    <td>${yoga.benefits}</td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("❌ Error fetching yoga plans:", error);
            tableBody.innerHTML = "<tr><td colspan='3'>Failed to load yoga plans.</td></tr>";
        });
});
