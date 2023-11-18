package kyonggi_oop.domain.seat;

public class Seat {

    private final int number;
    private boolean isAvailable;
    private final RoomType roomType;

    public Seat(int number, RoomType roomType) {
        this.number = number;
        this.isAvailable = true;
        this.roomType = roomType;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}
