import model.*;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seeds {
    public static void main(String[] args) {

    }

    public void seedData() throws ParseException {
        CustomerService.getInstance().addCustomer("Hugo", "Perez", "hp@gmail.com");
        CustomerService.getInstance().addCustomer("Paco", "Lopez", "pl@gmail.com");
        CustomerService.getInstance().addCustomer("Luis", "Martinez", "lm@gmail.com");

        IRoom Room001 = new Room("001", 120.99, RoomType.SINGLE);
        IRoom Room002 = new Room("002", 149.90, RoomType.DOUBLE);
        IRoom Room003 = new Room("003", 200.00, RoomType.DOUBLE);
        IRoom Room004 = new Room("004", 120.00, RoomType.DOUBLE);
        IRoom Room005 = new Room("005", 119.49, RoomType.DOUBLE);
        IRoom Room006 = new FreeRoom("006", RoomType.DOUBLE);


        ReservationService.getInstance().addRoom(Room001);
        ReservationService.getInstance().addRoom(Room002);
        ReservationService.getInstance().addRoom(Room003);
        ReservationService.getInstance().addRoom(Room004);
        ReservationService.getInstance().addRoom(Room005);
        ReservationService.getInstance().addRoom(Room006);

        Date date1 = new SimpleDateFormat("mm-dd-yyyy").parse("01-01-2022");
        Date date2 = new SimpleDateFormat("mm-dd-yyyy").parse("01-10-2022");
        Date date3 = new SimpleDateFormat("mm-dd-yyyy").parse("01-20-2022");
        Date date4 = new SimpleDateFormat("mm-dd-yyyy").parse("01-31-2022");
        Customer customer1 = CustomerService.getInstance().getCustomer("hp@gmail.com");
        IRoom Room1 = ReservationService.getInstance().getARoom("001");
        IRoom Room2 = ReservationService.getInstance().getARoom("002");
        Reservation reservation1 = ReservationService.getInstance().reserveARoom(customer1, Room1, date1, date2);
        Reservation reservation2 = ReservationService.getInstance().reserveARoom(customer1, Room2, date3, date4);
        ReservationService.getInstance().addReservation(reservation1);
        ReservationService.getInstance().addReservation(reservation2);
//        System.out.println(ReservationService.getInstance().getAllReservations());
//        System.out.println(CustomerService.getInstance().getAllCustomers());
//        System.out.println(ReservationService.getInstance().getAllRooms());
    }
}
