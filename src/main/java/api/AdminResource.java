package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
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

    public void addRoom(IRoom room) {
        reservationService.addRoom(room);

    }

    public Collection<IRoom> getAllRooms() {
        return ReservationService.getInstance().getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        Collection customersList = CustomerService.getInstance().getAllCustomers();
        return customersList;
    }

    public Collection<Reservation> displayAllReservations() {
        Collection<Reservation> reservationsList = ReservationService.getInstance().getAllReservations();
        return reservationsList;
    }
}
