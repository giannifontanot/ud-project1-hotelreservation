package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    ReservationService reservationService;

    public AdminResource() {
        reservationService = ReservationService.getInstance();
    }


    public Customer getCustomer(String email) {
        return CustomerService.getInstance().getCustomer(email);
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
        Collection customersList = CustomerService.getInstance().getAllCustomers();
        return customersList;
    }

    public void displayAllReservations() {
    }
}
