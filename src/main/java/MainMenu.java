import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.MessageColor;
import service.CustomerService;
import service.ReservationService;

public class MainMenu {

    // Just one scanner is used along the application
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String NONE = "";

    // Main method
    public static void main(String[] args) throws ParseException, InterruptedException {
        // Global User
        String gEmail = null;
        String gName = null;
        String gLast = null;

        // Global Option
        int gUserOption = 5;
        int gAdminOption = 6;

        // Seed the Storage first
        Seeds seeds = new Seeds();
        seeds.seedData();

        //Create the HotelResource
        HotelResource hotelresource = new HotelResource();

        //Create the AdminResource
        AdminResource adminresource = new AdminResource();

        //Cycle for options
        do {
            //Paint the menu
            UserMenu(gName, gLast, gEmail);
            //Capture the User Input
            gUserOption = Integer.parseInt(scanner.nextLine());
            //System.out.println("\n     Option Selected: " + gUserOption);

            //Verify if the user is null or not
            if (gEmail != null && gUserOption >= 3) {
                gUserOption++;
                System.out.println(gUserOption);
            }

            switch (gUserOption) {
                case 1:
                    // Verify if a user is already logged in
                    if (gEmail == null) {
                        PrintMessage(ANSI_RED, "Please login first: Option 3", 2);
                        break;
                    }
                    // Find and reserve a room
                    PrintMessage(ANSI_YELLOW, "Reserve a Room", 1);
                    String sdate1 = ScanMessage("To serve you best we need the following information." +
                            "\nCheckin Date (MM-DD-YYYY):", scanner);
                    String sdate2 = ScanMessage("Checkout Date (MM-DD-YYYY):", scanner);
                    Date date1 = new SimpleDateFormat("MM-dd-yyyy").parse(sdate1);
                    Date date2 = new SimpleDateFormat("MM-dd-yyyy").parse(sdate2);
                    System.out.println(date1);

                    Customer customer = hotelresource.getCustomer(gEmail);
                    System.out.println("gEmail: " + gEmail);

                    System.out.println("customer: " + customer);
                    Collection<IRoom> availableRooms = hotelresource.findARoom(date1, date2);
                    ParseRooms(availableRooms);
                    String roomSelected = ScanMessage("What room do you want to reserve?", scanner);

                    for (IRoom room : availableRooms) {
                        if (room.getRoomNumber().equals(roomSelected)) {
                            //Create a reservation
                            Reservation reservation1 = hotelresource.bookARoom(customer, room, date1, date2);
                            PrintMessage(ANSI_YELLOW, "Reservation created successfully!!", 2);
                            break;
                        }
                    }


                    break;
                case 2:
                    // Verify if a user is already logged in
                    if (gEmail == null) {
                        PrintMessage(ANSI_RED, "Please login first: Option 3", 2);
                        break;
                    }
                    // See my Reservations
                    PrintMessage(ANSI_YELLOW, "List of Your Reservations", 1);
                    Collection<Reservation> reservationList = hotelresource.getCustomerReservations(gEmail);
                    ParseReservations(reservationList);
                    PrintMessage(ANSI_YELLOW,
                            "You have (" + reservationList.size() + ") reservation" + (reservationList.size() > 1 ? "s" : ""), 2);
                    break;
                case 3:


                    // Create an Account
                    PrintMessage(ANSI_YELLOW, "3 - Login / Create an Account", 1);
                    String email = ScanMessage("Please type your email:", scanner);
                    Customer customerFound = hotelresource.getCustomer(email);
                    if (customerFound == null) {

                        String name = ScanMessage("Your email does not exist in our database. \nPlease write your name:", scanner);
                        String last = ScanMessage("Please write your Last Name:", scanner);

                        hotelresource.createACustomer(name, last, email);

                        gEmail = email;
                        gName = name;
                        gLast = last;

                    } else {
                        PrintMessage(ANSI_YELLOW, "User Found!", 2);
                        gEmail = customerFound.getEmail();
                        gName = customerFound.getFirstName();
                        gLast = customerFound.getLastName();
                    }
                    break;
                case 4:
                    // Admin
                    System.out.println("4 - Admin Option Selected: " + gUserOption);
                    int adminOption = 7;
                    do {
                        AdminMenu();
                        adminOption = Integer.parseInt(ScanMessage("Select one option:", scanner));
                        System.out.println("\n     Option Selected: " + adminOption);
                        switch (adminOption) {
                            case 1:
                                // See One Customer
                                PrintMessage(ANSI_YELLOW, "Admin: See One Customer", 1);
                                String customeremail = ScanMessage("Type the email of the customer:", scanner);
                                Customer customer1 = adminresource.getCustomer(customeremail);

                                // Adding the Reservations
                                Collection<Reservation> customerreservations1 = hotelresource.getCustomerReservations(customeremail);
                                ParseCustomer(customer1, customerreservations1);
                                break;
                            case 2:
                                // See All Customers
                                PrintMessage(ANSI_YELLOW, "Admin: See All Customer", 1);
                                Collection<Customer> customersList = adminresource.getAllCustomers();
                                ParseCustomers(customersList);
                                break;
                            case 3:
                                // Add a Room
                                PrintMessage(ANSI_YELLOW, "Admin: Add a Room", 1);
                                break;
                            case 4:
                                // See All Rooms
                                PrintMessage(ANSI_YELLOW, "Admin: See All Rooms", 1);
                                break;
                            case 5:
                                // See All Reservations
                                PrintMessage(ANSI_YELLOW, "Admin: See All Reservations", 1);
                                break;
                            case 6:
                                // Back to main menu
                                PrintMessage(ANSI_YELLOW, "Admin: Back to Main Menu", 1);
                                break;
                        }

                    } while (adminOption <= 5);
                    break;
                case 5:
                    // Exit
                    PrintMessage(ANSI_YELLOW, "Good to see you here. Bye!", 2);
                    break;
            }

//            if(gUserOption!=3 && gUserOption!=6) {
//                PrintMessage(ANSI_YELLOW,"Press Any Key to Continue or 5 to Exit");
//                gUserOption = Integer.parseInt(ScanMessage("", scanner));
//            }
        } while (gUserOption <= 4);
    }



    static void PrintMessage(String color, String message, int sleep) throws InterruptedException {
        String sEnd = "";
        if (color.equals("NONE")) {
            sEnd = "";
        } else {
            sEnd = ANSI_RESET;
        }

        System.out.println(ANSI_YELLOW + " -----------------------------" + ANSI_RESET);
        System.out.println("  " + color + message + sEnd);
        System.out.println(ANSI_YELLOW + " -----------------------------" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(sleep);
    }

    static String ScanMessage(String message, Scanner scanner1) {
        System.out.println(message);
        return scanner1.nextLine();
    }

    static void UserMenu(String gName, String gLast, String gEmail) {

        int counter = 0;
        System.out.print("\033[H\033[2J");

        System.out.println(" **************************************");
        System.out.println("     Hotel Reservation Application");
        System.out.println(" **************************************");
        if (gEmail != null) {
            System.out.println("          * Welcome " + ANSI_BLUE + gName + ANSI_RESET + "! *");
            System.out.println(" **************************************");
        }
        System.out.println("     " + (++counter) + ") Find and Reserve a Room");
        System.out.println("     " + (++counter) + ") See my Reservations");
        if (gEmail == null) {
            System.out.println("     " + (++counter) + ") Login / Create an Account");
        }
        System.out.println("     " + (++counter) + ") Admin");
        System.out.println("     " + (++counter) + ") Exit");
        System.out.println("\n     Select an Option from the Menu...");
    }

    static void AdminMenu() {

        System.out.println("1. See One Customer");
        System.out.println("2. See All Customers");
        System.out.println("3. Add a Room");
        System.out.println("4. See All Rooms");
        System.out.println("5. See All Reservations");
        System.out.println("6. Back to Main Menu");
    }

    public static void ParseRooms(Collection<IRoom> rooms) {
        for (IRoom room : rooms) {
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║ Room Number: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET + "\t Type: " + room.getRoomType() + "\t║");
            System.out.println("║ Price:\t$" + room.getRoomPrice() + "\t\t\t\t\t║");
            System.out.println("╚═══════════════════════════════════╝");
        }
    }

    private static void ParseCustomers(Collection<Customer> customersList) throws InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        int counter = 1;
        System.out.println(ANSI_YELLOW + "╔═══════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                  CUSTOMER LIST                    ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);


            for (Customer customer : customersList) {
                System.out.println(ANSI_YELLOW + "║   --------------  CUSTOMER " + (counter++) + "  " +
                        "------------------  " +
                        "║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Customer name: " + ANSI_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET + ANSI_YELLOW + fillSpaces(18,customer.getFirstName().length()+ customer.getLastName().length())+  "║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " email: " + ANSI_BLUE + customer.getEmail() + ANSI_RESET + ANSI_YELLOW + fillSpaces(9,customer.getEmail().length())+"║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            }

        System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(1);
        PrintMessage(ANSI_YELLOW,"Back to Admin Menu",2);
    }


    public static void ParseReservations(Collection<Reservation> reservationsList) throws InterruptedException, ParseException {
        for (Reservation reservation : reservationsList) {

            Customer customer = reservation.getCustomer();
            IRoom room = reservation.getRoom();

            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");


            System.out.println(ANSI_YELLOW + "╔═══════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║            YOUR RESERVATION DETAILS               ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Customer name: " + ANSI_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET + ANSI_YELLOW + fillSpaces(18,customer.getFirstName().length()+ customer.getLastName().length())+  "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " email: " + ANSI_BLUE + customer.getEmail() + ANSI_RESET + ANSI_YELLOW + fillSpaces(9,customer.getEmail().length())+"║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Check In: " + ANSI_GREEN + sdf.format(reservation.getCheckinDate()) + ANSI_RESET + "\t\tCheck Out: " + ANSI_GREEN + sdf.format(reservation.getCheckoutDate()) + ANSI_RESET + "  " + ANSI_YELLOW + " ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   " + ANSI_YELLOW + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Room: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET +
                    "\t Type: " + ANSI_CYAN + room.getRoomType() + ANSI_RESET + "  \t Price: " + ANSI_PURPLE + room.getRoomPrice() + ANSI_RESET +
                    ANSI_YELLOW + "       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║       ** Thank you for booking with us **         ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void ParseCustomer(Customer customer, Collection<Reservation> reservationList) throws InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        int counter = 1;
        System.out.println(ANSI_YELLOW + "╔═══════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                  CUSTOMER DATA                    ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Customer name: " + ANSI_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET + ANSI_YELLOW + fillSpaces(18,customer.getFirstName().length()+ customer.getLastName().length())+  "║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " email: " + ANSI_BLUE + customer.getEmail() + ANSI_RESET + ANSI_YELLOW + fillSpaces(9,customer.getEmail().length())+"║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);

        if(reservationList.size() > 0) {
            for (Reservation reservation : reservationList) {
                IRoom room = reservation.getRoom();
                System.out.println(ANSI_YELLOW + "║  ---------------  RESERVATION " + (counter++) + "  ---------------  " +
                        "║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Check In: " + ANSI_GREEN + sdf.format(reservation.getCheckinDate()) + ANSI_RESET + "\t\tCheck Out: " + ANSI_GREEN + sdf.format(reservation.getCheckoutDate()) + ANSI_RESET + "  " + ANSI_YELLOW + " ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   " + ANSI_YELLOW + "║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Room: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET +
                        "\t Type: " + ANSI_CYAN + room.getRoomType() + ANSI_RESET + "  \t Price: " + ANSI_PURPLE + room.getRoomPrice() + ANSI_RESET +
                        ANSI_YELLOW + "       ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);

            }
        }else{
                System.out.println(ANSI_YELLOW + "║        ------- No Reservations Yet --------       ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);

        }
        System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(1);
        PrintMessage(ANSI_YELLOW,"Back to Admin Menu",2);
    }

    public static StringBuilder fillSpaces(int used,int stringLength){
        StringBuilder space = new StringBuilder(" ");
        for(int i=0;i<50-(used+stringLength);i++){
            space.append(" ");
        }
        return space;
    }
}
