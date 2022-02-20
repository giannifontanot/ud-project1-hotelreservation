package api;

import model.Customer;
import model.IRoom;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    ReservationService reservationService = ReservationService.getInstance();

    AdminResource() {

    }


    public Customer getCustomer(String email) {

        return null;
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);

        }
    }

    public Collection<IRoom> getAllRooms() {
        return null;

    }

    public Collection<Customer> getAllCustomers() {
        return null;
    }

    public void displayAllReservations() {
    }
}
