package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DBConnect;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/plain");

        try {
            String nic = request.getParameter("nic");
            String driverIdStr = request.getParameter("driver_id");
            String vehicleType = request.getParameter("vehicle_type");
            String startDate = request.getParameter("start_date");
            String daysStr = request.getParameter("days");
            String startLocation = request.getParameter("start_location");
            String destination = request.getParameter("destination");
            String totalCostStr = request.getParameter("total_cost");

            if (nic == null || nic.isEmpty() ||
                driverIdStr == null || driverIdStr.isEmpty() ||
                vehicleType == null || vehicleType.isEmpty() ||
                startDate == null || startDate.isEmpty() ||
                daysStr == null || daysStr.isEmpty() ||
                startLocation == null || startLocation.isEmpty() ||
                destination == null || destination.isEmpty() ||
                totalCostStr == null || totalCostStr.isEmpty()) {

                response.getWriter().print("error: Missing required fields");
                return;
            }

            int driverId = Integer.parseInt(driverIdStr);
            int days = Integer.parseInt(daysStr);
            int totalCost = Integer.parseInt(totalCostStr);

            try (Connection con = DBConnect.getConnection()) {
                String query = "INSERT INTO bookingss (nic, driver_id, vehicle_type, start_date, days, start_location, destination, total_cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, nic);
                ps.setInt(2, driverId);
                ps.setString(3, vehicleType);
                ps.setString(4, startDate);
                ps.setInt(5, days);
                ps.setString(6, startLocation);
                ps.setString(7, destination);
                ps.setInt(8, totalCost);

                int result = ps.executeUpdate();
                if (result > 0) {
                    response.getWriter().print("Booking is successfull!");
                    

                } else {
                    response.getWriter().print("failed to save booking");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().print("error: Invalid number format");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("error: " + e.getMessage());
        }
    }
}
