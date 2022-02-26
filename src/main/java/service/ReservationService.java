package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

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
    /*
    The constructor is private so the class cannot be instantiated
    */
    private ReservationService() {
    }

    /*
    Static reference to the class
     */
    public static ReservationService reservationServiceSingleton = null;

    /*
    Storage for reservations, Rooms, and Free Rooms;
     */
    Collection<Reservation> reservations = new HashSet<>();
    Collection<IRoom> roomsList = new HashSet<>();
    Collection<IRoom> freeRoomsList = new HashSet<>();

    /*
    Method that returns the instance when it exists
     */
    public static ReservationService getInstance() {
        if (Objects.isNull(reservationServiceSingleton)) {
            reservationServiceSingleton = new ReservationService();
        }
        return reservationServiceSingleton;
    }

    /*
    Add a room to the storage
     */
    public void addRoom(IRoom room) {
        roomsList.add(room);
    }

    /*
    Find a room when you provide the room number
     */
    public IRoom getARoom(String roomNumber) {

        for (IRoom room : roomsList) {
            if (roomNumber.equals(room.getRoomNumber())) {
                return room;
            }
        }
        return null;
    }

    public Collection<IRoom> getAllRooms() {
        return roomsList;
    }

    /*
        Create new Reservation
     */
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        Reservation reservation =  new Reservation(customer, room, checkinDate, checkoutDate);
        reservations.add(reservation);
        return reservation;
    }

    /*
    Find rooms using a range of dates
     */
    public Collection<IRoom> findRooms(Date checkinDate, Date checkoutDate) {

// Read All rooms and subtract the rooms that already exist in the reservations
        Collection roomsAvailable = new HashSet<IRoom>();
        Collection roomsReserved = new HashSet<IRoom>();

        //Rooms already in reservations
        for (Reservation reservation : reservations) {
            roomsReserved.add(reservation.getRoom());
        }
        // All rooms in the hotel
        for (IRoom room : roomsList) {
            roomsAvailable.add(room);
        }

        // Find out the rooms available
        roomsList.removeAll(roomsReserved);
        return roomsList;
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        System.out.println("reservations");
        System.out.println(reservations);
        Collection reservationList = new HashSet<Reservation>();
        for(Reservation reservation:reservations){
            if(customerEmail.equals(reservation.getCustomer().getEmail())){
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }

    public void printAllReservation() {

    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);

    }

    public Collection<Reservation> getAllReservations() {
        return reservations;
    }
}
