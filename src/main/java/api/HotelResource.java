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

    public void createACustomer(String name, String lastName, String email) {
        CustomerService.getInstance().addCustomer(name, lastName, email);
    }

    public IRoom getRoom(String roomNumber) {
        return null;
    }

    public Reservation bookARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {

        return ReservationService.getInstance().reserveARoom(customer, room, checkinDate, checkoutDate);

    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {

        return ReservationService.getInstance().getCustomerReservations(customerEmail);

    }

    public Collection<IRoom> findARoom(Date checkinDate, Date checkoutDate) {

        return ReservationService.getInstance().findRooms(checkinDate, checkoutDate);

    }
}
