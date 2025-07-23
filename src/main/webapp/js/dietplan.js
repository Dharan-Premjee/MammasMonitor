document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8080/MammasMonitor/getDietPlan")
        .then(response => response.json())
        .then(data => {
            let container = document.getElementById("dietPlanContainer");
            container.innerHTML = "";

           if (data.length === 0) {
                container.innerHTML = "<p>Error loading diet plan. Try again later.</p>";
                return;
            }

            data.forEach(plan => {
                let card = document.createElement("div");
                card.classList.add("day-card");
                card.innerHTML = `<h3>${plan.day}</h3><p><strong>${plan.meal}</strong>: ${plan.foodItems}</p>`;
                container.appendChild(card);
            });
        })
        .catch(error => {
            console.error("Error fetching diet plan:", error);
            document.getElementById("dietPlanContainer").innerHTML = "<p>Error loading diet plan. Try again later.</p>";
        });
});

