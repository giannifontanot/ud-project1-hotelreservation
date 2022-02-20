package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public Customer getCustomer(String email){
return null;
    }

    public void createACustomer(String email, String name, String lastName){

    }

    public IRoom getRoom(String roomNumber){
return null;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkinDate, Date checkoutDate){
return null;

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
return null;
    }

    public Collection<IRoom> findARoom(Date checkin, Date checkoutDate){
return null;
    }
}
