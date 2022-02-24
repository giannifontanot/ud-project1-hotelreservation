import model.*;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class Tester {
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

        Date date1 = new SimpleDateFormat("mm-dd-yyyy").parse("10-26-2020");
        Date date2 = new SimpleDateFormat("mm-dd-yyyy").parse("10-27-2020");
        Collection availableRooms = ReservationService.getInstance().findRooms(date1,date2);

//        System.out.println(ReservationService.getInstance().getAllRooms());
//        System.out.println(ReservationService.getInstance().getAllReservations());

        ParseRooms(availableRooms);

    }

    public static void ParseRooms(Collection rooms){
        for(IRoom room:rooms){
            room.getRoomNumber();
        }
        System.out.println();
    }
}
