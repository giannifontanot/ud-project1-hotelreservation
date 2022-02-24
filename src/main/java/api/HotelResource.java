package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

import service.CustomerService;
import service.ReservationService;

public class HotelResource {

    CustomerService customerServiceInstance;
    ReservationService reservationServiceInstance;

    public HotelResource() {
        customerServiceInstance = CustomerService.getInstance();
    }


    public Customer getCustomer(String email) {
        return customerServiceInstance.getCustomer(email);
    }

    public void createACustomer(String email, String name, String lastName) {

    }

    public IRoom getRoom(String roomNumber) {
        return null;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkinDate, Date checkoutDate) {
        return null;

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        return null;
    }

    public Collection<IRoom> findARoom(Date checkinDate, Date checkoutDate) {

        return reservationServiceInstance.findRooms(checkinDate, checkoutDate);

    }
}
