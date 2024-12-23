import java.sql.*;

public class HotelManager {

    // Query 2: List hotels with no part-time staff working
    public void listHotelsWithNoPartTimeStaff() {
        String query = "SELECT Hotel_ID, Name " +
                       "FROM Hotel " +
                       "MINUS " +
                       "SELECT Hotel_ID, Name " +
                       "FROM Hotel " +
                       "WHERE Hotel_ID IN ( " +
                       "    SELECT Hotel_ID " +
                       "    FROM Staff " +
                       "    WHERE Staff_ID IN (SELECT Staff_ID FROM PartTime_Staff)" +
                       ")";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Hotels with no part-time staff:");
            while (rs.next()) {
                System.out.println("Hotel ID: " + rs.getInt("Hotel_ID") +
                                   ", Hotel Name: " + rs.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Query 5: List hotels with available rooms
    public void listHotelsWithAvailableRooms() {
        String query = "SELECT Name " +
                       "FROM Hotel h " +
                       "WHERE EXISTS ( " +
                       "    SELECT 1 " +
                       "    FROM Room r " +
                       "    WHERE r.Hotel_ID = h.Hotel_ID " +
                       "    AND r.AvailabilityStatus = 'Available' " +
                       ")";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Hotels with available rooms:");
            while (rs.next()) {
                System.out.println("Hotel Name: " + rs.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

