window.onload = function() {
    fetch('/MammasMonitor/medcheck')  // Ensure correct path to servlet
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('medCheckTable');
            tableBody.innerHTML = ''; // Clear the loading text

            data.forEach(record => {
                let row = document.createElement('tr');
                row.innerHTML = `
                    <td>${record.patientName}</td>
                    <td>${record.medicineName}</td>
                    <td>${record.intakeDate}</td>
                    <td>${record.intakeStatus}</td>
                    <td><button class="update-btn">Update</button></td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
};
