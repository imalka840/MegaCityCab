package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DBConnect;

@WebServlet("/RegisterDriverAndCar")
public class RegisterDriverCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String nic = request.getParameter("nic");
        String location = request.getParameter("location");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        String seats = request.getParameter("seats");
        String color = request.getParameter("color");
        String numberPlate = request.getParameter("numberPlate");
        String imageLink = request.getParameter("imageLink");

        try (Connection con = DBConnect.getConnection()) {

            // Check if NIC already exists
            String checkNICQuery = "SELECT COUNT(*) FROM carndriver WHERE nic = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkNICQuery)) {
                checkStmt.setString(1, nic);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    rs.next();
                    if (rs.getInt(1) > 0) {
                        response.getWriter().println("Error: A driver with this NIC already exists!");
                        return;
                    }
                }
            }

            // Insert Driver and Car Details
            String insertQuery = "INSERT INTO carndriver (name, nic, location, age, phone, type, seats, color, numberPlate, imageLink) "
                               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {

                stmt.setString(1, name);
                stmt.setString(2, nic);
                stmt.setString(3, location);
                stmt.setString(4, age);
                stmt.setString(5, phone);
                stmt.setString(6, type);
                stmt.setString(7, seats);
                stmt.setString(8, color);
                stmt.setString(9, numberPlate);
                stmt.setString(10, imageLink);

                stmt.executeUpdate();
                response.sendRedirect("manage_drivers.jsp");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
