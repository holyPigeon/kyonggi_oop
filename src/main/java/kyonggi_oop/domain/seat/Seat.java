package kyonggi_oop.domain.seat;

import kyonggi_oop.controller.dto.request.SeatRequest;

public class Seat {

    private final int number;
    private boolean isAvailable;
    private final RoomType roomType;

    private Seat(SeatRequest seatRequest) {
        this.number = seatRequest.getNumber();
        this.isAvailable = true;
        this.roomType = seatRequest.getRoomType();
    }

    public static Seat create(SeatRequest seatRequest) {
        return new Seat(seatRequest);
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
