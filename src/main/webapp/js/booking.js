function validateNIC() {
    let nic = document.getElementById("nic").value;
    fetch("CustomerInfoServlet?nic=" + nic)
        .then(response => response.text())
        .then(data => {
            if (data === "valid") {
                document.getElementById("step1").style.display = "none";
                document.getElementById("step2").style.display = "block";
            } else {
                document.getElementById("nicMessage").innerText = "User does not exist, please register first!";
            }
        });
}

// Suggest vehicle based on passenger count
document.getElementById("passengerCount").addEventListener("input", function() {
    let count = parseInt(this.value) || 0;
    let suggestion = "";
    if (count <= 4) suggestion = "A car will be good for customer!.";
    else if (count <= 6) suggestion = "A Mini Van will be good for customer!.";
    else if (count <= 15) suggestion = "A Van will be good for customer!.";
    else suggestion = "Maximum passenger limit exceeded. Please split into 2 bookings.";
    document.getElementById("vehicleSuggestion").innerText = suggestion;
});

// Show available vehicles when clicking "Next" in Step 2
document.getElementById("nextStep2").addEventListener("click", function() {
    console.log("Next button clicked in Step 2");
    showAvailableVehicles();
});

// Fetch and display available vehicles
function showAvailableVehicles() {
    let type = document.getElementById("vehicleType").value;
    console.log("Fetching available vehicles of type:", type);
    
    fetch("VehicleAvailabilityServlet?type=" + type)
        .then(response => response.json())
        .then(data => {
            console.log("Vehicles data received:", data);
            let vehicleList = document.getElementById("vehicleList");
            vehicleList.innerHTML = "";
            data.forEach(vehicle => {
                let div = document.createElement("div");
                div.classList.add("vehicle-card");
                div.innerHTML = `
                    <img src="${vehicle.imageLink}" alt="Car">
                    <p>Seats: ${vehicle.seats}</p>
                    <p>Driver Age: ${vehicle.age}</p>
                    <p>Color: ${vehicle.color}</p>`;
				div.onclick = function(event) { selectVehicle(vehicle.driver_id, event); };
                vehicleList.appendChild(div);
            });
            document.getElementById("step2").style.display = "none";
            document.getElementById("step3").style.display = "block";
        }).catch(error => {
            console.error("Error fetching vehicle data:", error);
            alert("Failed to load available vehicles. Please try again.");
        });
}

function selectVehicle(driver_id, event) {
    localStorage.setItem("selectedDriver", driver_id);

    // Remove highlight from other cards
    document.querySelectorAll(".vehicle-card").forEach(card => {
        card.classList.remove("selected");
    });

    // Highlight the selected vehicle
    event.currentTarget.classList.add("selected");
}


function proceedToTripDetails() {
    let selectedDriver = localStorage.getItem("selectedDriver");

    if (!selectedDriver) {
        alert("Please select a vehicle before proceeding.");
        return;
    }

    document.getElementById("step3").style.display = "none";
    document.getElementById("step4").style.display = "block";
}


// Price calculation
document.getElementById("days").addEventListener("input", function() {
    let days = parseInt(this.value);
    let type = document.getElementById("vehicleType").value;
    let cost = 0;
    if (type === "Car") cost = [3000, 5500, 8000][days - 1] || (8000 + (days - 3) * 2500);
    if (type === "Mini Van") cost = [5000, 9000, 13000][days - 1] || (13000 + (days - 3) * 2500);
    if (type === "Van") cost = [7000, 12000, 18000][days - 1] || (18000 + (days - 3) * 2500);
    document.getElementById("totalCost").innerText = cost;
});

function confirmBooking() {
    let nic = document.getElementById("nic").value.trim();
    let driver_id = localStorage.getItem("selectedDriver");
    let vehicle_type = document.getElementById("vehicleType").value;
    let start_date = document.getElementById("startDate").value;
    let days = document.getElementById("days").value.trim();
    let start_location = document.getElementById("startLocation").value.trim();
    let destination = document.getElementById("destination").value.trim();
    let total_cost = document.getElementById("totalCost").innerText.replace(/[^\d]/g, "").trim();

    if (!driver_id) {
        alert("No vehicle selected! Please select a vehicle before proceeding.");
        return;
    }

    if (!nic || !vehicle_type || !start_date || !days || !start_location || !destination || !total_cost) {
        alert("Please fill in all fields before booking.");
        return;
    }

    if (isNaN(days) || parseInt(days) <= 0) {
        alert("Invalid number of days. Please enter a valid number.");
        return;
    }

    if (isNaN(total_cost) || parseInt(total_cost) <= 0) {
        alert("Invalid total cost. Please check your booking details.");
        return;
    }

    let bookButton = document.getElementById("confirmBookingButton");
    if (bookButton) {
        bookButton.disabled = true;
        bookButton.innerText = "Booking...";
    }

    let requestBody = new URLSearchParams({
        nic,
        driver_id,
        vehicle_type,
        start_date,
        days,
        start_location,
        destination,
        total_cost
    }).toString();

    fetch("BookingServlet", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: requestBody
    })
    .then(response => response.text())
    .then(data => {
        console.log("Server Response:", data);
        alert(data);

        if (data.includes("Booking is successfull!")) {
            // Clear the form
            document.getElementById("bookingForm").reset();

            // Clear local storage
            localStorage.clear();

            // Reset all form steps
            document.querySelectorAll(".step").forEach(step => step.style.display = "none");
            document.getElementById("step1").style.display = "block";
            document.getElementById("nicMessage").innerText = ""; // Clear error message if any

            // Confirm for new booking or go to home
            let anotherBooking = confirm("Booking Successful! Do you want to make another booking?");
            if (anotherBooking) {
                window.location.href = "booking.jsp";
            } else {
                window.location.href = "manage_bookings.jsp";
            }
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Something went wrong! Please try again.");
    })
    .finally(() => {
        if (bookButton) {
            bookButton.disabled = false;
            bookButton.innerText = "Confirm Booking";
        }
    });
}















