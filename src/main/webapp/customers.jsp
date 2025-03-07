<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Management</title>
    <link rel="stylesheet" type="text/css" href="styles/customers.css">
</head>
<body>
    <div class="container">
        <h1>Customer Management</h1>
        
        <!-- Search Bar -->
        <input type="text" id="searchInput" placeholder="Search by Reg Num, Name, or NIC...">
        
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
