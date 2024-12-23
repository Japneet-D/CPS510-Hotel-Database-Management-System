import java.sql.*;

public class StaffManager {

    // Query 3: List full-time staff whose salary is greater than the average salary of all full-time staff at their hotel
    public void listHighPayingFullTimeStaff() {
        String query = "SELECT " +
                       "'Name: ' || S.Name || ', Position: ' || S.Position || ', Salary: ' || F.Salary || ', Hotel: ' || H.Name AS \"High-Paying Full-Time Staff\" " +
                       "FROM Staff S, FullTime_Staff F, Hotel H " +
                       "WHERE S.Staff_ID = F.Staff_ID " +
                       "AND S.Hotel_ID = H.Hotel_ID " +
                       "AND F.Salary > ( " +
                       "    SELECT AVG(FT.Salary) " +
                       "    FROM FullTime_Staff FT " +
                       "    WHERE FT.Staff_ID IN ( " +
                       "        SELECT Staff_ID " +
                       "        FROM Staff " +
                       "        WHERE Hotel_ID = S.Hotel_ID " +
                       "    ) " +
                       ")";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("High-paying full-time staff members:");
            while (rs.next()) {
                System.out.println(rs.getString("High-Paying Full-Time Staff"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Query 4: List all staff members with employment type (Full-Time/Part-Time)
    public void listAllStaffWithEmploymentType() {
        String query = "SELECT Staff_ID, Name, 'Full-Time' AS EmploymentType " +
                       "FROM Staff " +
                       "WHERE Staff_ID IN (SELECT Staff_ID FROM FullTime_Staff) " +
                       "UNION " +
                       "SELECT Staff_ID, Name, 'Part-Time' AS EmploymentType " +
                       "FROM Staff " +
                       "WHERE Staff_ID IN (SELECT Staff_ID FROM PartTime_Staff)";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Staff Members with Employment Type:");
            while (rs.next()) {
                System.out.println("Staff ID: " + rs.getInt("Staff_ID") +
                                   ", Name: " + rs.getString("Name") +
                                   ", Employment Type: " + rs.getString("EmploymentType"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

