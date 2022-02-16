package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

//All of the service classes use static references to create singleton objects.

/*
The ReservationService contains for or while loops that are used to iterate over and process data in order to do the following:

Search for available rooms
Search for recommended rooms
 */

/*
The ReservationService contains at least one example of using each of the following method access modifiers:

public
private
default
 */
public class ReservationService {
    public void addRoom(IRoom room){

    }

    public IRoom getARoom(String roomId){
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate){
        return null;
    }

    public Collection<IRoom> findRooms(Date checkinDate, Date checkoutDate){
        return null;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        return null;
    }

    public void printAllReservation(){

    }
}
