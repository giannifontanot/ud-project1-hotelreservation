package UI;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static UI.ConsoleColors.*;

public class PrintToConsole {
    public static void PrintMessage(String color, String message, int sleep) throws InterruptedException {
        String sEnd = "";
        if (color.equals("NONE")) {
            sEnd = "";
        } else {
            sEnd = ANSI_RESET;
        }

        System.out.println(YELLOW_BOLD + " -----------------------------" + ANSI_RESET);
        System.out.println("  " + color + message + sEnd);
        System.out.println(YELLOW_BOLD + " -----------------------------" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(sleep);
    }

    public static String ScanMessage(String message, Scanner scanner1) {
        System.out.println(WHITE_BRIGHT + message + ANSI_RESET);
        return scanner1.nextLine();
    }

    public static void UserMenu(String gName, String gLast, String gEmail) {

        int counter = 0;
        System.out.print("\033[H\033[2J");

        System.out.println(" ");
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
        System.out.println("\n " + WHITE_BRIGHT + "Select an Option from the Menu..." + ANSI_RESET);
    }

    public static void AdminMenu() {
        System.out.println(" **************************************");
        System.out.println("               Admin Menu");
        System.out.println(" **************************************");
        System.out.println("     1. See One Customer");
        System.out.println("     2. See All Customers");
        System.out.println("     3. Add a Room");
        System.out.println("     4. See All Rooms");
        System.out.println("     5. See All Reservations");
        System.out.println("     6. Back to Main Menu");
    }

    public static void ParseRooms(Collection<IRoom> rooms) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        for (IRoom room : rooms) {
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║ Room Number: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET + "\t Type: " + room.getRoomType() + "\t║");
            System.out.println("║ Price:\t$" + formatter.format(room.getRoomPrice()) + "\t\t\t\t\t║");
            System.out.println("╚═══════════════════════════════════╝");
        }
    }

    public static void ParseRoom(IRoom room) throws InterruptedException {
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println(ANSI_YELLOW + "╔═══════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                     NEW ROOM                      ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Room: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET +
                "\t Type: " + ANSI_CYAN + room.getRoomType() + ANSI_RESET + "  \t Price: " + ANSI_PURPLE + formatter.format(room.getRoomPrice()) + ANSI_RESET +
                ANSI_YELLOW + "       ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(1);
        PrintMessage(ANSI_YELLOW, "Back to Admin Menu", 2);
    }

    public static void ParseCustomers(Collection<Customer> customersList) throws InterruptedException {

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
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Customer name: " + ANSI_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET + ANSI_YELLOW + fillSpaces(18, customer.getFirstName().length() + customer.getLastName().length()) + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " email: " + ANSI_BLUE + customer.getEmail() + ANSI_RESET + ANSI_YELLOW + fillSpaces(9, customer.getEmail().length()) + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        }

        System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(1);
        PrintMessage(ANSI_YELLOW, "Back to Admin Menu", 2);
    }


    public static void ParseReservations(Collection<Reservation> reservationsList) throws InterruptedException, ParseException {
        for (Reservation reservation : reservationsList) {

            Customer customer = reservation.getCustomer();
            IRoom room = reservation.getRoom();

            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            NumberFormat formatter = new DecimalFormat("#0.00");

            System.out.println(ANSI_YELLOW + "╔═══════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║            YOUR RESERVATION DETAILS               ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Customer name: " + ANSI_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET + ANSI_YELLOW + fillSpaces(18, customer.getFirstName().length() + customer.getLastName().length()) + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " email: " + ANSI_BLUE + customer.getEmail() + ANSI_RESET + ANSI_YELLOW + fillSpaces(9, customer.getEmail().length()) + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Check In: " + ANSI_GREEN + sdf.format(reservation.getCheckinDate()) + ANSI_RESET + "\t\tCheck Out: " + ANSI_GREEN + sdf.format(reservation.getCheckoutDate()) + ANSI_RESET + "  " + ANSI_YELLOW + " ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   " + ANSI_YELLOW + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Room: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET +
                    "\t Type: " + ANSI_CYAN + room.getRoomType() + ANSI_RESET + "  \t Price: " + ANSI_PURPLE + formatter.format(room.getRoomPrice()) + ANSI_RESET +
                    ANSI_YELLOW + "      ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║       ** Thank you for booking with us **         ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static void ParseAllReservations(Collection<Reservation> reservationsList) throws InterruptedException {
        int counter = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println(ANSI_YELLOW + "╔═══════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║            RESERVATIONS IN THIS HOTEL             ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        for (Reservation reservation : reservationsList) {
            System.out.println(ANSI_YELLOW + "║     ------------- RESERVATION " + (counter++) + "---------------    ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);

            Customer customer = reservation.getCustomer();
            IRoom room = reservation.getRoom();

            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Customer name: " + ANSI_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET + ANSI_YELLOW + fillSpaces(18, customer.getFirstName().length() + customer.getLastName().length()) + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " email: " + ANSI_BLUE + customer.getEmail() + ANSI_RESET + ANSI_YELLOW + fillSpaces(9, customer.getEmail().length()) + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Check In: " + ANSI_GREEN + sdf.format(reservation.getCheckinDate()) + ANSI_RESET + "\t\tCheck Out: " + ANSI_GREEN + sdf.format(reservation.getCheckoutDate()) + ANSI_RESET + "  " + ANSI_YELLOW + " ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   " + ANSI_YELLOW + "║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Room: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET +
                    "\t Type: " + ANSI_CYAN + room.getRoomType() + ANSI_RESET + "  \t Price: " + ANSI_PURPLE + formatter.format(room.getRoomPrice()) + ANSI_RESET +
                    ANSI_YELLOW + "      ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
    }

    public static void ParseCustomer(Customer customer, Collection<Reservation> reservationList) throws InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        NumberFormat formatter = new DecimalFormat("#0.00");
        int counter = 1;
        System.out.println(ANSI_YELLOW + "╔═══════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                  CUSTOMER DATA                    ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Customer name: " + ANSI_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET + ANSI_YELLOW + fillSpaces(18, customer.getFirstName().length() + customer.getLastName().length()) + "║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " email: " + ANSI_BLUE + customer.getEmail() + ANSI_RESET + ANSI_YELLOW + fillSpaces(9, customer.getEmail().length()) + "║" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);

        if (reservationList.size() > 0) {
            for (Reservation reservation : reservationList) {
                IRoom room = reservation.getRoom();
                System.out.println(ANSI_YELLOW + "║  ---------------  RESERVATION " + (counter++) + "  ---------------  " +
                        "║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Check In: " + ANSI_GREEN + sdf.format(reservation.getCheckinDate()) + ANSI_RESET + "\t\tCheck Out: " + ANSI_GREEN + sdf.format(reservation.getCheckoutDate()) + ANSI_RESET + "  " + ANSI_YELLOW + " ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   " + ANSI_YELLOW + "║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ " + ANSI_RESET + " Room: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET +
                        "\t Type: " + ANSI_CYAN + room.getRoomType() + ANSI_RESET + "  \t Price: " + ANSI_PURPLE + formatter.format(room.getRoomPrice()) + ANSI_RESET +
                        ANSI_YELLOW + "      ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);

            }
        } else {
            System.out.println(ANSI_YELLOW + "║        ------- No Reservations Yet --------       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║                                                   ║" + ANSI_RESET);

        }
        System.out.println(ANSI_YELLOW + "╚═══════════════════════════════════════════════════╝" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(1);
        PrintMessage(ANSI_YELLOW, "Back to Admin Menu", 2);
    }

    public static StringBuilder fillSpaces(int used, int stringLength) {
        StringBuilder space = new StringBuilder(" ");
        for (int i = 0; i < 50 - (used + stringLength); i++) {
            space.append(" ");
        }
        return space;
    }
}
