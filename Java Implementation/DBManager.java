import java.sql.*;

public class DBManager {

    // Method to create all the tables
    public void createTables() {
        String[] createTableQueries = {
            "CREATE TABLE Hotel ("
            + "Hotel_ID INTEGER PRIMARY KEY, "
            + "Name VARCHAR2(50) NOT NULL, "
            + "Location VARCHAR2(100) NOT NULL, "
            + "Rating INTEGER NOT NULL CHECK (Rating BETWEEN 1 AND 5), "
            + "NumberOfRooms INTEGER NOT NULL CHECK (NumberOfRooms > 0)"
            + ")",

            "CREATE TABLE Address ("
            + "Address_ID INTEGER PRIMARY KEY, "
            + "Street VARCHAR2(30) NOT NULL, "
            + "PostalCode VARCHAR2(6) NOT NULL, "
            + "City VARCHAR2(30) NOT NULL, "
            + "Province VARCHAR2(15) DEFAULT 'ONTARIO'"
            + ")",

            "CREATE TABLE Guest ("
            + "Guest_ID INTEGER PRIMARY KEY, "
            + "Name VARCHAR2(50) NOT NULL, "
            + "Address_ID INTEGER NOT NULL, "
            + "PhoneNumber VARCHAR2(12) NOT NULL, "
            + "Email VARCHAR2(50) NOT NULL, "
            + "PaymentMethod VARCHAR2(10) CHECK (PaymentMethod IN ('Card', 'Cash')), "
            + "FOREIGN KEY (Address_ID) REFERENCES Address(Address_ID)"
            + ")",

            "CREATE TABLE Room ("
            + "Room_ID INTEGER PRIMARY KEY, "
            + "Hotel_ID INTEGER NOT NULL, "
            + "RoomType VARCHAR2(20) NOT NULL, "
            + "Price DECIMAL(10, 2) NOT NULL CHECK (Price >= 0), "
            + "AvailabilityStatus VARCHAR2(10) NOT NULL CHECK (AvailabilityStatus IN ('Available', 'Occupied')), "
            + "FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID)"
            + ")",

            "CREATE TABLE Reservation ("
            + "Reservation_ID INTEGER NOT NULL, "
            + "Guest_ID INTEGER NOT NULL, "
            + "Room_ID INTEGER NOT NULL, "
            + "CheckInDate DATE NOT NULL, "
            + "CheckOutDate DATE NOT NULL, "
            + "NumberOfGuests INTEGER NOT NULL, "
            + "PRIMARY KEY (Reservation_ID, Guest_ID), "
            + "FOREIGN KEY (Guest_ID) REFERENCES Guest(Guest_ID), "
            + "FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID)"
            + ")",

            "ALTER TABLE Reservation "
            + "ADD CONSTRAINT unique_reservation_id UNIQUE (Reservation_ID)",

            "CREATE TABLE Bill ("
            + "Bill_ID INTEGER PRIMARY KEY, "
            + "Reservation_ID INTEGER NOT NULL, "
            + "Amount DECIMAL(10, 2) NOT NULL CHECK (Amount >= 0), "
            + "PaymentStatus VARCHAR2(20) NOT NULL CHECK (PaymentStatus IN ('Paid', 'Unpaid')), "
            + "BillingDate DATE NOT NULL, "
            + "FOREIGN KEY (Reservation_ID) REFERENCES Reservation(Reservation_ID)"
            + ")",

            "CREATE TABLE Staff ("
            + "Staff_ID INTEGER PRIMARY KEY, "
            + "Hotel_ID INTEGER NOT NULL, "
            + "Name VARCHAR2(50) NOT NULL, "
            + "Position VARCHAR2(30) NOT NULL, "
            + "PhoneNumber VARCHAR2(12) NOT NULL, "
            + "FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID)"
            + ")",

            "CREATE TABLE FullTime_Staff ("
            + "Staff_ID INTEGER PRIMARY KEY, "
            + "Salary DECIMAL(10, 2) NOT NULL, "
            + "FOREIGN KEY (Staff_ID) REFERENCES Staff(Staff_ID)"
            + ")",

            "CREATE TABLE PartTime_Staff ("
            + "Staff_ID INTEGER PRIMARY KEY, "
            + "HourlyRate DECIMAL(6, 2) NOT NULL, "
            + "FOREIGN KEY (Staff_ID) REFERENCES Staff(Staff_ID)"
            + ")",

            "CREATE TABLE Service ("
            + "Service_ID INTEGER PRIMARY KEY, "
            + "Hotel_ID INTEGER NOT NULL, "
            + "Name VARCHAR2(50) NOT NULL, "
            + "Price DECIMAL(10, 2) NOT NULL CHECK (Price >= 0), "
            + "Description VARCHAR2(255) NOT NULL, "
            + "FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID)"
            + ")",

            "CREATE TABLE Uses ("
            + "Guest_ID INTEGER, "
            + "Service_ID INTEGER, "
            + "PRIMARY KEY (Guest_ID, Service_ID), "
            + "FOREIGN KEY (Guest_ID) REFERENCES Guest(Guest_ID), "
            + "FOREIGN KEY (Service_ID) REFERENCES Service(Service_ID)"
            + ")"
        };

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Loop through each CREATE TABLE query and execute them individually
            for (String query : createTableQueries) {
                stmt.executeUpdate(query);  // Execute each CREATE TABLE statement
            }

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to drop all the tables
    public void dropTables() {
        String[] dropTableQueries = {
            "DROP TABLE Uses",
            "DROP TABLE FullTime_Staff",
            "DROP TABLE PartTime_Staff",
            "DROP TABLE Service",
            "DROP TABLE Bill",
            "DROP TABLE Reservation",
            "DROP TABLE Room",
            "DROP TABLE Staff",
            "DROP TABLE Guest",
            "DROP TABLE Address",
            "DROP TABLE Hotel"
        };

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Loop through each DROP TABLE query and execute them individually
            for (String query : dropTableQueries) {
                stmt.executeUpdate(query);  // Execute each DROP TABLE statement
            }

            System.out.println("Tables dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to populate tables with some dummy data
    public void populateTables() {
        String[] populateQueries = {
            // Insert data into Hotel table
            "INSERT INTO Hotel (Hotel_ID, Name, Location, Rating, NumberOfRooms) "
            + "VALUES (1, 'Holiday Inn Richmond', '789 Hotel Road, Toronto', 4, 700)",
            
            "INSERT INTO Hotel (Hotel_ID, Name, Location, Rating, NumberOfRooms) "
            + "VALUES (2, 'Marriott Downtown', '101 City Center Dr, Mississauga', 5, 500)",
            
            "INSERT INTO Hotel (Hotel_ID, Name, Location, Rating, NumberOfRooms) "
            + "VALUES (3, 'Best Western Plus', '202 Lakeshore Rd, Oakville', 3, 250)",
            
            // Insert data into Address table
            "INSERT INTO Address (Address_ID, Street, PostalCode, City, Province) "
            + "VALUES (1, '123 Maple St', 'M1P2L1', 'Toronto', 'ONTARIO')",
            
            "INSERT INTO Address (Address_ID, Street, PostalCode, City, Province) "
            + "VALUES (2, '456 Oak St', 'M4B1V7', 'Mississauga', 'ONTARIO')",
            
            "INSERT INTO Address (Address_ID, Street, PostalCode, City, Province) "
            + "VALUES (3, '789 Pine St', 'L5T1G9', 'Brampton', 'ONTARIO')",
            
            "INSERT INTO Address (Address_ID, Street, PostalCode, City, Province) "
            + "VALUES (4, '234 Birch St', 'L4W5S2', 'Mississauga', 'ONTARIO')",
            
            "INSERT INTO Address (Address_ID, Street, PostalCode, City, Province) "
            + "VALUES (5, '567 Cedar St', 'M3J1B2', 'Toronto', 'ONTARIO')",
            
            // Insert data into Guest table
            "INSERT INTO Guest (Guest_ID, Name, Address_ID, PhoneNumber, Email, PaymentMethod) "
            + "VALUES (1, 'John Doe', 1, '416-555-1234', 'john.doe@gmail.com', 'Cash')",
            
            "INSERT INTO Guest (Guest_ID, Name, Address_ID, PhoneNumber, Email, PaymentMethod) "
            + "VALUES (2, 'Jane Smith', 2, '905-555-5678', 'jane.smith@gmail.com', 'Card')",
            
            "INSERT INTO Guest (Guest_ID, Name, Address_ID, PhoneNumber, Email, PaymentMethod) "
            + "VALUES (3, 'Mike Johnson', 3, '905-555-4321', 'mike.johnson@yahoo.com', 'Cash')",
            
            "INSERT INTO Guest (Guest_ID, Name, Address_ID, PhoneNumber, Email, PaymentMethod) "
            + "VALUES (4, 'Sarah Lee', 4, '905-555-9999', 'sarah.lee@gmail.com', 'Card')",
            
            "INSERT INTO Guest (Guest_ID, Name, Address_ID, PhoneNumber, Email, PaymentMethod) "
            + "VALUES (5, 'David Park', 5, '416-555-7777', 'david.park@gmail.com', 'Cash')",
            
            // Insert data into Room table
            "INSERT INTO Room (Room_ID, Hotel_ID, RoomType, Price, AvailabilityStatus) "
            + "VALUES (101, 1, 'Deluxe', 150.00, 'Available')",
            
            "INSERT INTO Room (Room_ID, Hotel_ID, RoomType, Price, AvailabilityStatus) "
            + "VALUES (102, 1, 'Standard', 120.00, 'Occupied')",
            
            "INSERT INTO Room (Room_ID, Hotel_ID, RoomType, Price, AvailabilityStatus) "
            + "VALUES (103, 1, 'Suite', 200.00, 'Available')",
            
            "INSERT INTO Room (Room_ID, Hotel_ID, RoomType, Price, AvailabilityStatus) "
            + "VALUES (104, 2, 'Suite', 220.00, 'Available')",
            
            "INSERT INTO Room (Room_ID, Hotel_ID, RoomType, Price, AvailabilityStatus) "
            + "VALUES (105, 3, 'Standard', 110.00, 'Occupied')",
            
            // Insert data into Reservation table
            "INSERT INTO Reservation (Reservation_ID, Guest_ID, Room_ID, CheckInDate, CheckOutDate, NumberOfGuests) "
            + "VALUES (1001, 1, 101, TO_DATE('2024-10-01', 'YYYY-MM-DD'), TO_DATE('2024-10-05', 'YYYY-MM-DD'), 2)",
            
            "INSERT INTO Reservation (Reservation_ID, Guest_ID, Room_ID, CheckInDate, CheckOutDate, NumberOfGuests) "
            + "VALUES (1002, 2, 102, TO_DATE('2024-10-03', 'YYYY-MM-DD'), TO_DATE('2024-10-07', 'YYYY-MM-DD'), 1)",
            
            "INSERT INTO Reservation (Reservation_ID, Guest_ID, Room_ID, CheckInDate, CheckOutDate, NumberOfGuests) "
            + "VALUES (1003, 3, 103, TO_DATE('2024-10-02', 'YYYY-MM-DD'), TO_DATE('2024-10-06', 'YYYY-MM-DD'), 3)",
            
            "INSERT INTO Reservation (Reservation_ID, Guest_ID, Room_ID, CheckInDate, CheckOutDate, NumberOfGuests) "
            + "VALUES (1004, 4, 104, TO_DATE('2024-10-04', 'YYYY-MM-DD'), TO_DATE('2024-10-08', 'YYYY-MM-DD'), 2)",
            
            "INSERT INTO Reservation (Reservation_ID, Guest_ID, Room_ID, CheckInDate, CheckOutDate, NumberOfGuests) "
            + "VALUES (1005, 5, 105, TO_DATE('2024-10-05', 'YYYY-MM-DD'), TO_DATE('2024-10-09', 'YYYY-MM-DD'), 1)",
            
            // Insert data into Bill table
            "INSERT INTO Bill (Bill_ID, Reservation_ID, Amount, PaymentStatus, BillingDate) "
            + "VALUES (2001, 1001, 750.00, 'Paid', TO_DATE('2024-09-24', 'YYYY-MM-DD'))",
            
            "INSERT INTO Bill (Bill_ID, Reservation_ID, Amount, PaymentStatus, BillingDate) "
            + "VALUES (2002, 1002, 480.00, 'Unpaid', TO_DATE('2024-10-07', 'YYYY-MM-DD'))",
            
            "INSERT INTO Bill (Bill_ID, Reservation_ID, Amount, PaymentStatus, BillingDate) "
            + "VALUES (2003, 1003, 800.00, 'Paid', TO_DATE('2024-10-06', 'YYYY-MM-DD'))",
            
            "INSERT INTO Bill (Bill_ID, Reservation_ID, Amount, PaymentStatus, BillingDate) "
            + "VALUES (2004, 1004, 880.00, 'Paid', TO_DATE('2024-10-07', 'YYYY-MM-DD'))",
            
            "INSERT INTO Bill (Bill_ID, Reservation_ID, Amount, PaymentStatus, BillingDate) "
            + "VALUES (2005, 1005, 450.00, 'Unpaid', TO_DATE('2024-10-09', 'YYYY-MM-DD'))",
            
            // Insert data into Staff table
            "INSERT INTO Staff (Staff_ID, Hotel_ID, Name, Position, PhoneNumber) "
            + "VALUES (3001, 1, 'Emily White', 'Manager', '416-555-9876')",
            
            "INSERT INTO Staff (Staff_ID, Hotel_ID, Name, Position, PhoneNumber) "
            + "VALUES (3002, 1, 'Paul Green', 'Receptionist', '416-555-3210')",
            
            "INSERT INTO Staff (Staff_ID, Hotel_ID, Name, Position, PhoneNumber) "
            + "VALUES (3003, 2, 'Anna Brown', 'Housekeeper', '905-555-2345')",
            
            "INSERT INTO Staff (Staff_ID, Hotel_ID, Name, Position, PhoneNumber) "
            + "VALUES (3004, 2, 'Tom Black', 'Concierge', '905-555-1230')",
            
            "INSERT INTO Staff (Staff_ID, Hotel_ID, Name, Position, PhoneNumber) "
            + "VALUES (3005, 3, 'Rachel Adams', 'Chef', '416-555-1122')",
            
            // Insert data into FullTime_Staff table
            "INSERT INTO FullTime_Staff (Staff_ID, Salary) "
            + "VALUES (3001, 65000.00)",
            
            "INSERT INTO FullTime_Staff (Staff_ID, Salary) "
            + "VALUES (3002, 45000.00)",
            
            "INSERT INTO FullTime_Staff (Staff_ID, Salary) "
            + "VALUES (3004, 52000.00)",
            
            // Insert data into PartTime_Staff table
            "INSERT INTO PartTime_Staff (Staff_ID, HourlyRate) "
            + "VALUES (3003, 16.50)",
            
            "INSERT INTO PartTime_Staff (Staff_ID, HourlyRate) "
            + "VALUES (3005, 22.50)",
            
            // Insert data into Service table
            "INSERT INTO Service (Service_ID, Hotel_ID, Name, Price, Description) "
            + "VALUES (4001, 1, 'Spa', 100.00, 'Heated indoor/outdoor pool')",
            
            "INSERT INTO Service (Service_ID, Hotel_ID, Name, Price, Description) "
            + "VALUES (4002, 1, 'Gym', 50.00, '24-hour fitness center')",
            
            "INSERT INTO Service (Service_ID, Hotel_ID, Name, Price, Description) "
            + "VALUES (4003, 2, 'Breakfast Buffet', 20.00, 'All-you-can-eat breakfast buffet')",
            
            "INSERT INTO Service (Service_ID, Hotel_ID, Name, Price, Description) "
            + "VALUES (4004, 2, 'Room Service', 30.00, '24-hour room service')",
            
            "INSERT INTO Service (Service_ID, Hotel_ID, Name, Price, Description) "
            + "VALUES (4005, 3, 'Laundry', 15.00, 'Same-day laundry service')",
            
            // Insert data into Uses table
            "INSERT INTO Uses (Guest_ID, Service_ID) "
            + "VALUES (1, 4001)",
            
            "INSERT INTO Uses (Guest_ID, Service_ID) "
            + "VALUES (2, 4002)",
            
            "INSERT INTO Uses (Guest_ID, Service_ID) "
            + "VALUES (3, 4003)",
            
            "INSERT INTO Uses (Guest_ID, Service_ID) "
            + "VALUES (4, 4004)",
            
            "INSERT INTO Uses (Guest_ID, Service_ID) "
            + "VALUES (5, 4005)"
        };

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Loop through the queries and execute them one by one
            for (String query : populateQueries) {
                stmt.executeUpdate(query);
            }

            System.out.println("Tables populated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

