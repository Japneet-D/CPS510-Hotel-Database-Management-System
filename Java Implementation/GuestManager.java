import java.sql.*;

public class GuestManager {

    // View all guests in the system
    public void viewGuests() {
        String query = "SELECT * FROM Guest";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Guest Information:");
            while (rs.next()) {
                System.out.println("Guest ID: " + rs.getInt("Guest_ID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Address ID: " + rs.getInt("Address_ID"));
                System.out.println("Phone Number: " + rs.getString("PhoneNumber"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Payment Method: " + rs.getString("PaymentMethod"));
                System.out.println("----------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Query 1: List all guests who have booked rooms in hotels with a 5-star rating
    public void listGuestsWith5StarHotelBookings() {
        String query = "SELECT g.Name, COUNT(r.Reservation_ID) AS ReservationCount " +
                       "FROM Guest g, Reservation r, Room ro, Hotel h " +
                       "WHERE g.Guest_ID = r.Guest_ID " +
                       "AND r.Room_ID = ro.Room_ID " +
                       "AND ro.Hotel_ID = h.Hotel_ID " +
                       "AND h.Rating = 5 " +
                       "GROUP BY g.Name";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Guests who booked rooms in 5-star hotels:");
            while (rs.next()) {
                System.out.println("Guest Name: " + rs.getString("Name") +
                                   ", Reservations Count: " + rs.getInt("ReservationCount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
