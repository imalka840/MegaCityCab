<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Management</title>
    <link rel="stylesheet" type="text/css" href="styles/customers.css">
    <link rel="icon" type="image/png" href="stuff/mcclogo.png">
    
    
    <style>
        .button-container {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
            justify-content: center;
            margin-bottom: 20px;
        }

        .pill-button {
            display: flex;
            align-items: center;
            background-color: #ffd700;
            color: #111;
            padding: 10px 20px;
            border: none;
            border-radius: 50px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s, transform 0.2s;
            box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
        }

        .pill-button:hover {
            background-color: #e6b800;
            transform: scale(1.05);
        }

         .pill-button img {
            margin-right: 10px;
            width: 24px;
            height: 24px;
        }
    </style>
    
    
</head>
<body>

<a href="manage_bookings.jsp" style="position: absolute; top: 10px; left: 10px;">
    <img src="stuff/mcclogo.png" alt="MCC Logo" style="width: 120px; cursor: pointer;">
</a>

    <div class="container">
        <h1>Customer Management</h1>
        
        <!-- Search Bar -->
        <input type="text" id="searchInput" placeholder="Search by Reg Num, Name, or NIC...">
        
        
        
         <div class="button-container">
          </a>
        <a href="customerRegistration.jsp" class="pill-button">
            <img src="svgs/addcus.svg" alt="Icon 1">
            Register
        </a>
         
    </div>
        
        
        <!-- Customer Table -->
        <table id="customerTable">
            <thead>
                <tr>
                    <th>Reg Num</th>
                    <th>Name</th>
                    <th>NIC</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Data will be inserted dynamically here -->
            </tbody>
        </table>
    </div>
    
    <script src="js/customers.js"></script>
</body>
</html>
