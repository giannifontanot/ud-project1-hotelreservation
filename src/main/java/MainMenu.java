import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import api.HotelResource;
import model.IRoom;
import service.CustomerService;

public class MainMenu {

    // Just one scanner is used along the application
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    // Main method
    public static void main(String[] args) throws ParseException {
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

        //Cycle for options
        do {
            //Paint the menu
            UserMenu(gName, gLast, gEmail);
            //Capture the User Input
            gUserOption = Integer.parseInt(scanner.nextLine());
            System.out.println("\n     Option Selected: " + gUserOption);

            //Verify if the user is null or not
            if (gEmail != null && gUserOption >= 3) {
                gUserOption++;
                System.out.println(gUserOption);
            }

            switch (gUserOption) {
                case 1:
                    // Find and reserve a room
                    PrintMessage("Reserve a Room");
                    String sdate1 = ScanMessage("To serve you best we need the following information." +
                            "\nCheckin Date (MM-DD-YYYY):", scanner);
                    String sdate2 = ScanMessage("Checkout Date (MM-DD-YYYY):", scanner);
                    Date date1 = new SimpleDateFormat("mm-dd-yyyy").parse(sdate1);
                    Date date2 = new SimpleDateFormat("mm-dd-yyyy").parse(sdate2);
                    System.out.println(date1);
                    Collection<IRoom> roomsList = hotelresource.findARoom(date1,date2);
                    System.out.println(roomsList);
                    break;
                case 2:
                    // See my Reservations
                    PrintMessage("List of My Reservations");
                    break;
                case 3:


                    // Create an Account
                    PrintMessage("3 - Login / Create an Account");
                    String email = ScanMessage("Please type your email:", scanner);
                    if (hotelresource.getCustomer(email) == null) {

                        String name = ScanMessage("Your email does not exist in our database. \nPlease write your name:", scanner);
                        String last = ScanMessage("Please write your Last Name:", scanner);

                        hotelresource.createACustomer(name, last, email);
                        System.out.println(CustomerService.getInstance().getAllCustomers());
                        gEmail = email;
                        gName = name;
                        gLast = last;
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
                                break;
                            case 2:
                                // See All Customers
                                break;
                            case 3:
                                // Add a Room
                                break;
                            case 4:
                                // See All Rooms
                                break;
                            case 5:
                                // See All Reservations
                                break;
                            case 6:
                                // Back to main menu
                                break;
                        }

                    } while (adminOption <= 5);
                    break;
                case 5:
                    // Exit
                    PrintMessage("Good to see you here. Bye!");
                    break;
            }

//            if(gUserOption!=3 && gUserOption!=6) {
//                PrintMessage("Press Any Key to Continue or 5 to Exit");
//                gUserOption = Integer.parseInt(ScanMessage("", scanner));
//            }
        } while (gUserOption <= 4);
    }


    static void PrintMessage(String message) {
        System.out.println(" -----------------------------");
        System.out.println(" " + message);
        System.out.println(" -----------------------------");

    }

    static String ScanMessage(String message, Scanner scanner1) {
        System.out.println(message);
        return scanner1.nextLine();
    }

    static void UserMenu(String gName, String gLast, String gEmail) {

        int counter = 0;
        System.out.println(" **************************************");
        System.out.println("     Hotel Reservation Application");
        System.out.println(" **************************************");
        if (gEmail != null) {
            System.out.println("          * Welcome " + gName + "! *");
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

}
