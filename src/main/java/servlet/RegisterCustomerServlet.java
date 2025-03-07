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

@WebServlet("/RegisterCustomer")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String nic = request.getParameter("nic");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        try (Connection con = DBConnect.getConnection()) {

            String checkNICQuery = "SELECT COUNT(*) FROM customers WHERE nic = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkNICQuery)) {
                checkStmt.setString(1, nic);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    rs.next();
                    if (rs.getInt(1) > 0) {
                        response.getWriter().println("Error: A customer with this NIC already exists!");
                        return;
                    }
                }
            }

            String insertQuery = "INSERT INTO customers (reg_num, name, nic, address, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {

                String regNum = "REG-" + System.currentTimeMillis();

                stmt.setString(1, regNum);
                stmt.setString(2, name);
                stmt.setString(3, nic);
                stmt.setString(4, address);
                stmt.setString(5, phone);
                stmt.setString(6, email);

                stmt.executeUpdate();
                response.sendRedirect("success.jsp");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
