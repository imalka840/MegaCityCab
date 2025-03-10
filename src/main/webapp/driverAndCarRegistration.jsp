<!DOCTYPE html>
<html>
<head>
    <title>Driver and Car Registration</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="icon" type="image/png" href="stuff/mcclogo.png">
    <script>
        function updateSeats() {
            const type = document.getElementById('type').value;
            const seats = document.getElementById('seats');
            if (type === 'Car') seats.value = '4';
            else if (type === 'Mini Van') seats.value = '6';
            else if (type === 'Van') seats.value = '15';
        }
    </script>
</head>
<body>

<a href="manage_bookings.jsp" style="position: absolute; top: 10px; left: 10px;">
    <img src="stuff/mcclogo.png" alt="MCC Logo" style="width: 120px; cursor: pointer;">
</a>

    <div class="container">
        <h1>Driver and Car Registration</h1>

        <!-- Driver Details Section -->
        <form action="RegisterDriverAndCar" method="post">
            <h2>Driver Details</h2>
            <div class="form-group">
                <label for="name">Driver Name:</label>
                <input type="text" id="name" name="name" required>
            </div>

            <div class="form-group">
                <label for="nic">NIC:</label>
                <input type="text" id="nic" name="nic" required>
            </div>

            <div class="form-group">
                <label for="location">Location:</label>
                <input type="text" id="location" name="location" required>
            </div>

            <div class="form-group">
                <label for="age">Age:</label>
                <input type="number" id="age" name="age" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phone" required>
            </div>

            <!-- Car Details Section -->
            <h2>Car Details</h2>
            <div class="form-group">
                <label for="type">Car Type:</label>
                <select id="type" name="type" onchange="updateSeats()" required>
                    <option value="Car">Car</option>
                    <option value="Mini Van">Mini Van</option>
                    <option value="Van">Van</option>
                </select>
            </div>

            <div class="form-group">
                <label for="seats">Seats:</label>
                <input type="text" id="seats" name="seats" readonly required>
            </div>

            <div class="form-group">
                <label for="color">Color:</label>
                <select id="color" name="color" required>
                    <option value="White">White</option>
                    <option value="Black">Black</option>
                    <option value="Red">Red</option>
                    <option value="Blue">Blue</option>
                    <option value="Yellow">Yellow</option>
                </select>
            </div>

            <div class="form-group">
                <label for="numberPlate">Number Plate:</label>
                <input type="text" id="numberPlate" name="numberPlate" required>
            </div>

            <div class="form-group">
                <label for="imageLink">Car Image (URL):</label>
                <input type="url" id="imageLink" name="imageLink" required>
            </div>

            <button type="submit" class="btn">Register</button>
        </form>
    </div>
</body>
</html>
