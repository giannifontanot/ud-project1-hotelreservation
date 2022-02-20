import model.FreeRoom;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

public class Seeds {
    public static void main(String[] args) {
        CustomerService.getInstance().addCustomer("Hugo","Perez","hp@gmail.com");
        CustomerService.getInstance().addCustomer("Paco","Lopez","pl@gmail.com");
        CustomerService.getInstance().addCustomer("Luis","Martinez","lm@gmail.com");

        IRoom Room001 = new  Room("001",100.00, RoomType.SINGLE);
        IRoom Room002 = new  Room("002",100.00, RoomType.DOUBLE);
        IRoom Room003 = new FreeRoom("003", RoomType.DOUBLE);
        ReservationService.getInstance().addRoom(Room001);
        ReservationService.getInstance().addRoom(Room002);
        ReservationService.getInstance().addRoom(Room003);

        System.out.println(CustomerService.getInstance().getAllCustomers());
        System.out.println(ReservationService.getInstance().getAllRooms());
    }
}
