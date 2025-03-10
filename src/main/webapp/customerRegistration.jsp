<!DOCTYPE html>
<html>
<head>
    <title>Customer Registration</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="icon" type="image/png" href="stuff/mcclogo.png">
</head>
<body>

<a href="manage_bookings.jsp" style="position: absolute; top: 10px; left: 10px;">
    <img src="stuff/mcclogo.png" alt="MCC Logo" style="width: 120px; cursor: pointer;">
</a>

    <div class="container">
        <h1>Customer Registration</h1>
        
        <form action="RegisterCustomer" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>

            <div class="form-group">
                <label for="nic">NIC:</label>
                <input type="text" id="nic" name="nic" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phone" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>

            <button type="submit" class="btn">Register</button>
        </form>
    </div>
</body>
</html>
