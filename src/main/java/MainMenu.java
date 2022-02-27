import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import api.AdminResource;
import api.HotelResource;
import model.*;

import static UI.ConsoleColors.*;
import static UI.PrintToConsole.*;

public class MainMenu {

    // Just one scanner is used along the application
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

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
            try {
                gUserOption = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                gUserOption = 0;
                PrintMessage(ANSI_RED, "Input error...", 1);
            }


            //Verify if the user is null or not
            if (gEmail != null && gUserOption >= 3) {
                gUserOption++;
                System.out.println(gUserOption);
            }

            switch (gUserOption) {
                case 1:
                    // Verify if a user is already logged in
                    if (gEmail == null) {
                        PrintMessage(ANSI_YELLOW, "Please login first: Option 3", 2);
                        break;
                    }
                    // Find and reserve a room
                    PrintMessage(ANSI_YELLOW, "Reserve a Room", 1);

                    boolean error1Parsed = true;
                    boolean error2Parsed = true;
                    Date date1 = null;
                    Date date2 = null;
                    do {
                        try {
                            String sdate1 = ScanMessage("To serve you best we need the following information:" +
                                    "\nCheck In Date (MM-DD-YYYY):", scanner);
                            date1 = new SimpleDateFormat("MM-dd-yyyy").parse(sdate1);
                            error1Parsed = false;
                        } catch (Exception e) {
                            PrintMessage(ANSI_RED, "Error When Reading the Date", 1);
                            error1Parsed = true;
                        }
                    } while (error1Parsed);

                    do {
                        try {
                            String sdate2 = ScanMessage("Check Out Date (MM-DD-YYYY):", scanner);
                            date2 = new SimpleDateFormat("MM-dd-yyyy").parse(sdate2);
                            error2Parsed = false;
                        } catch (Exception e) {
                            PrintMessage(ANSI_RED, "Error When Reading the Date", 1);
                            error2Parsed = true;
                        }
                    } while (error2Parsed);

                    Customer customer = hotelresource.getCustomer(gEmail);
                    Collection<IRoom> availableRooms = hotelresource.findARoom(date1, date2);
                    ParseRooms(availableRooms);
                    String roomSelected = "";
                    boolean errorListParsed = true;
                    do {
                        roomSelected = ScanMessage("Select a room from the previous list.", scanner);
                        if(findRoomInList(roomSelected,availableRooms)) {
                            errorListParsed = false;
                        } else {
                            PrintMessage(ANSI_RED, "Please type the Room Number as in the list", 1);
                            errorListParsed = true;
                        }
                    } while (errorListParsed);

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
                        PrintMessage(ANSI_YELLOW, "Please login first: Option 3", 2);
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

                        //Capture the User Input
                        try {
                            adminOption = Integer.parseInt(ScanMessage("Select one option:", scanner));
                        } catch (Exception e) {
                            adminOption = 0;
                            PrintMessage(ANSI_RED, "Input error...", 1);
                        }


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
                                PrintMessage(ANSI_YELLOW, "Admin: See All Customers", 1);
                                Collection<Customer> customersList = adminresource.getAllCustomers();
                                ParseCustomers(customersList);
                                break;
                            case 3:

                                // Add a Room
                                PrintMessage(ANSI_YELLOW, "Admin: Add a Room", 1);
                                String roomnumber = ScanMessage("Room number:", scanner);

                                String roomtype = "";
                                boolean errorTypeParsed = true;
                                do {
                                        roomtype = ScanMessage("Room Type: (S)ingle or (D)ouble", scanner);
                                    if(roomtype.equals("S") || roomtype.equals("D")) {

                                        errorTypeParsed = false;
                                    } else {
                                        PrintMessage(ANSI_RED, "Error When Reading the Room Type", 1);
                                        errorTypeParsed = true;
                                    }
                                } while (errorTypeParsed);

                                String roompay = "";
                                boolean errorPayParsed = true;
                                do {
                                    roompay = ScanMessage("Room Price: (F)ree or (P)ay", scanner);
                                    if(roompay.equals("F") || roompay.equals("P")) {
                                        errorPayParsed = false;
                                    } else {
                                        PrintMessage(ANSI_RED, "Error When Reading the Room Price", 1);
                                        errorPayParsed = true;
                                    }
                                } while (errorPayParsed);

                                String roomprice = "0";
                                Double dRoomprice = 0.00;
                                boolean errorPriceParsed = true;
                                if(roompay.equals("P")) {
                                    do {
                                        try {
                                            roomprice= ScanMessage("Cost:", scanner);
                                            dRoomprice =  Double.parseDouble(roomprice);
                                            errorPriceParsed = false;
                                        } catch (Exception e) {
                                            PrintMessage(ANSI_RED, "Error When Reading the Cost", 1);
                                            errorPriceParsed = true;
                                        }
                                    } while (errorPriceParsed);
                                }

                                //When the room is free, then we use a different constructor
                                IRoom newRoom = null;
                                if(roompay.equals("F")) {
                                    newRoom = new FreeRoom(roomnumber, (roomtype.equals("S") ? RoomType.SINGLE : RoomType.DOUBLE));
                                }else{
                                    newRoom = new Room(roomnumber, dRoomprice, (roomtype.equals("S") ? RoomType.SINGLE : RoomType.DOUBLE));
                                }
                                adminresource.addRoom(newRoom);
                                ParseRoom(newRoom);
                                break;
                            case 4:
                                // See All Rooms
                                PrintMessage(ANSI_YELLOW, "Admin: See All Rooms", 1);
                                Collection<IRoom> roomsList = adminresource.getAllRooms();
                                ParseRooms(roomsList);
                                TimeUnit.SECONDS.sleep(1);
                                PrintMessage(ANSI_YELLOW, "Back to Admin Menu", 2);
                                break;
                            case 5:
                                // See All Reservations
                                PrintMessage(ANSI_YELLOW, "Admin: See All Reservations", 1);
                                Collection<Reservation> reservationList1 = adminresource.displayAllReservations();
                                ParseAllReservations(reservationList1);
                                TimeUnit.SECONDS.sleep(1);
                                PrintMessage(ANSI_YELLOW, "Back to Admin Menu", 2);
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
        } while (gUserOption <= 4);
    }

    private static boolean findRoomInList(String roomSelected, Collection<IRoom> availableRooms){
        for(IRoom room:availableRooms){
            if(roomSelected.equals(room.getRoomNumber())){
                return true;
            }
        }
        return false;
    }
}
