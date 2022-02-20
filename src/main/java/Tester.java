import model.*;
import service.CustomerService;

public class Tester {
    public static void main(String[] args) {
        // Create new Free Room
        Room myFreeRoom = new FreeRoom("1", RoomType.SINGLE);
        System.out.println(myFreeRoom.getRoomPrice());

        // Add new customer

        // Add the new customer to the CustomerService
        CustomerService.getInstance().addCustomer("Luis","Perez","luis@gmail.com");
        System.out.println(CustomerService.getInstance().getAllCustomers());
        // Print all customers
    }
}
