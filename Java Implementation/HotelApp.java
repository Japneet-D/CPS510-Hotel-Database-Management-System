import java.util.Scanner;

public class HotelApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBManager dbManager = new DBManager(); 
        GuestManager guestManager = new GuestManager();
        StaffManager staffManager = new StaffManager();
        HotelManager hotelManager = new HotelManager();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Create Tables");
            System.out.println("2. Drop Tables");
            System.out.println("3. Populate Tables");
            System.out.println("4. List Guests with 5-Star Hotel Bookings");
            System.out.println("5. List Hotels with No Part-Time Staff");
            System.out.println("6. List High-Paying Full-Time Staff");
            System.out.println("7. List All Staff Members with Employment Type");
            System.out.println("8. List Hotels with Available Rooms");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    /*guestManager.viewGuests();*/
                    dbManager.createTables(); 
                    break;
                case 2:
                    /*roomManager.viewAvailableRooms();*/
                    dbManager.dropTables();
                    break;
                case 3:
                    /*reservationManager.viewReservations();*/
                    dbManager.populateTables();
                    break;
                case 4:
                    guestManager.listGuestsWith5StarHotelBookings();
                    break;
                case 5:
                    hotelManager.listHotelsWithNoPartTimeStaff();
                    break;
                case 6:
                    staffManager.listHighPayingFullTimeStaff();
                    break;
                case 7:
                    staffManager.listAllStaffWithEmploymentType();
                    break;
                case 8:
                    hotelManager.listHotelsWithAvailableRooms();
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

