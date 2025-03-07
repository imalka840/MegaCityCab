package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import controller.DBConnect;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("fetch".equals(action)) {
            fetchCustomers(response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
            deleteCustomer(request, response);
        } else if ("update".equals(action)) {
            updateCustomer(request, response);
        }
    }
    
    private void fetchCustomers(HttpServletResponse response) throws IOException {
        List<Map<String, String>> customers = new ArrayList<>();
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM customers");
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Map<String, String> customer = new HashMap<>();
                customer.put("reg_num", rs.getString("reg_num"));
                customer.put("name", rs.getString("name"));
                customer.put("nic", rs.getString("nic"));
                customer.put("address", rs.getString("address"));
                customer.put("phone", rs.getString("phone"));
                customer.put("email", rs.getString("email"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(customers));
    }
    
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String regNum = request.getParameter("reg_num");
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM customers WHERE reg_num = ?")) {
            ps.setString(1, regNum);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Map<String, String> customer = gson.fromJson(request.getReader(), Map.class);
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE customers SET name = ?, nic = ?, address = ?, phone = ?, email = ? WHERE reg_num = ?")) {
            ps.setString(1, customer.get("name"));
            ps.setString(2, customer.get("nic"));
            ps.setString(3, customer.get("address"));
            ps.setString(4, customer.get("phone"));
            ps.setString(5, customer.get("email"));
            ps.setString(6, customer.get("reg_num"));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
