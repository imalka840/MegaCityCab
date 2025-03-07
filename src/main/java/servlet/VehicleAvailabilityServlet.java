package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import controller.DBConnect;

@WebServlet("/VehicleAvailabilityServlet")
public class VehicleAvailabilityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (Connection con = DBConnect.getConnection()) {
            String sql = "SELECT * FROM carndriver WHERE type = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                    rs.getInt("driver_id"),
                    rs.getInt("seats"),
                    rs.getInt("age"),
                    rs.getString("color"),
                    rs.getString("imageLink")
                );
                vehicles.add(vehicle);
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(vehicles));
        out.flush();
    }
}

class Vehicle {
    private int driver_id;
    private int seats;
    private int age;
    private String color;
    private String imageLink;

    public Vehicle(int driver_id, int seats, int age, String color, String imageLink) {
        this.driver_id = driver_id;
        this.seats = seats;
        this.age = age;
        this.color = color;
        this.imageLink = imageLink;
    }
}

