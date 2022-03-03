package model;

import java.util.Objects;

public class Room implements IRoom {
    //Store and process data for an application in a Collection.
//The collection type chosen for rooms ensures that two rooms cannot be booked at the same time.
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomNumber(), room.getRoomNumber()) && Objects.equals(price, room.price) && enumeration == room.enumeration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), price, enumeration);
    }

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setEnumeration(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", enumeration=" + enumeration +
                '}';
    }
}
