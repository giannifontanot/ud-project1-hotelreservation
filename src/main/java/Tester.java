import model.*;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class Tester {

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BABY_BLUE = "\u001B[34m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws ParseException {
//        // Create new Free Room
//        Room myFreeRoom = new FreeRoom("1", RoomType.SINGLE);
//        System.out.println(myFreeRoom.getRoomPrice());
//
//        // Add new customer
//
//        // Add the new customer to the CustomerService
//        CustomerService.getInstance().addCustomer("Luis","Perez","luis@gmail.com");
//        System.out.println(CustomerService.getInstance().getAllCustomers());
//        // Print all customers
        // Seed the Storage first
        Seeds seeds = new Seeds();
        seeds.seedData();

//        Date date1 = new SimpleDateFormat("mm-dd-yyyy").parse("10-26-2020");
//        Date date2 = new SimpleDateFormat("mm-dd-yyyy").parse("10-27-2020");
//
//        Collection availableRooms = ReservationService.getInstance().findRooms(date1, date2);
//
////        System.out.println(ReservationService.getInstance().getAllRooms());
////        System.out.println(ReservationService.getInstance().getAllReservations());
//
//        ParseRooms(availableRooms);
//        System.out.println("Reservations: ");
//        ParseReservations(ReservationService.getInstance().getCustomerReservations("hp@gmail.com"));

        //ReservationService.getInstance().findRooms());
    }

    public static void ParseRooms(Collection<IRoom> rooms) {
        for (IRoom room : rooms) {
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║ Room Number: " + ANSI_RED + room.getRoomNumber() + ANSI_RESET+ "\t Type: " + room.getRoomType()+"\t║");
            System.out.println("║ Price:\t$" + room.getRoomPrice()+"\t\t\t\t\t║");
            System.out.println("╚═══════════════════════════════════╝");
        }
    }

    public static void ParseReservations(Collection<Reservation> reservationsList) {
        for (Reservation reservation : reservationsList) {

            Customer customer = reservation.getCustomer();
            IRoom room = reservation.getRoom();


            System.out.println(ANSI_YELLOW +"╔═══════════════════════════════════════════════════╗"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║                                                   ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║            YOUR RESERVATION DETAILS               ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║                                                   ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║ "+ ANSI_RESET + " Cusomer name: " + ANSI_BABY_BLUE + customer.getFirstName() + " " + customer.getLastName() + ANSI_RESET+ ANSI_YELLOW+ "\t\t\t\t\t\t\t║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║ "+ ANSI_RESET + " email: "+ ANSI_BABY_BLUE +customer.getEmail() + ANSI_RESET + ANSI_YELLOW+ "\t\t\t\t\t\t\t\t║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║                                                   ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║ "+ ANSI_RESET + " Check In:\t" + ANSI_GREEN+reservation.getCheckinDate()+ ANSI_RESET+"      " +ANSI_YELLOW+"  ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║ "+ ANSI_RESET + " Check Out:\t" + ANSI_GREEN+reservation.getCheckoutDate()+ ANSI_RESET+"       "+ ANSI_YELLOW+" ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║                                                   "+ ANSI_YELLOW+"║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║ "+ ANSI_RESET + " Room: "+ ANSI_RED +room.getRoomNumber() + ANSI_RESET+
                    "\t Type: " + ANSI_CYAN +room.getRoomType()+ ANSI_RESET + "  \t Price: "+ ANSI_PURPLE +room.getRoomPrice()+ ANSI_RESET+
                    ANSI_YELLOW +"       ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║                                                   ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║        **Thank you for booking with us**          ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"║                                                   ║"+ ANSI_RESET);
            System.out.println(ANSI_YELLOW +"╚═══════════════════════════════════════════════════╝"+ ANSI_RESET);
        }
    }
}
