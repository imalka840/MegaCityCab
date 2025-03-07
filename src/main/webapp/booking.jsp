<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vehicle Booking</title>
    <link rel="stylesheet" href="styles/booking.css">
</head>
<body>
    <div class="container">
        <form id="bookingForm"> <!-- Added form element -->
            <div class="step" id="step1">
                <h2>Step 1: Enter NIC</h2>
                <input type="text" id="nic" placeholder="Enter your NIC">
                <button type="button" onclick="validateNIC()">Next</button>
                <p id="nicMessage"></p>
            </div>

            <div class="step" id="step2" style="display: none;">
                <h2>Step 2: Enter Passengers</h2>
                <input type="number" id="passengerCount" placeholder="Enter number of passengers" min="1">
                <p id="vehicleSuggestion"></p>
                <select id="vehicleType">
                    <option value="Car">Car</option>
                    <option value="Mini Van">Mini Van</option>
                    <option value="Van">Van</option>
                </select>
                <button type="button" id="nextStep2">Next</button>
            </div>

            <div class="step" id="step3">
                <h2>Step 3: Select a Vehicle</h2>
                <div id="vehicleList"></div>
                <button type="button" onclick="proceedToTripDetails()">Next</button>
            </div>

            <div class="step" id="step4">
                <h2>Step 4: Trip Details</h2>
                <input type="date" id="startDate">
                <input type="number" id="days" placeholder="Enter number of days" min="1">
                <input type="text" id="startLocation" placeholder="Enter start location">
                <input type="text" id="destination" placeholder="Enter destination">
                <p>Total Cost: LKR <span id="totalCost">0</span></p>
                <button type="button" id="confirmBookingButton" onclick="confirmBooking()">Book</button>
            </div>
        </form>
    </div>

    <script src="js/booking.js"></script>
</body>
</html>
