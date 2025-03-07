package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DBConnect;

@WebServlet("/CustomerInfoServlet")
public class CustomerInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String nic = request.getParameter("nic");

        if (nic == null || nic.trim().isEmpty()) {
            out.print("invalid");
            return;
        }

        try (Connection con = DBConnect.getConnection()) {
            String query = "SELECT * FROM customers WHERE nic = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nic);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.print("valid");
                System.out.println("NIC Found: " + nic); // Debugging output
            } else {
                out.print("invalid");
                System.out.println("NIC Not Found: " + nic); // Debugging output
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("error");
        }
    }
}
