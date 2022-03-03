package model;

public class FreeRoom extends Room{
    private String roomNumber;
    private RoomType enumeration;
    final Double freeCost = 0.0;

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.00, enumeration);
        this.roomNumber = roomNumber;
        this.enumeration = enumeration;
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + freeCost +
                ", enumeration=" + enumeration +
                '}';
    }
}
