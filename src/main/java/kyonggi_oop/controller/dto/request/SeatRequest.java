package kyonggi_oop.controller.dto.request;

import kyonggi_oop.domain.seat.RoomType;

public class SeatRequest {

    private final int number;
    private final RoomType roomType;

    public SeatRequest(String number, String roomName) {
        this.number = Integer.parseInt(number);
        this.roomType = RoomType.findByName(roomName);
    }

    public static SeatRequest of(String number, String roomName) {
        return new SeatRequest(number, roomName);
    }

    public int getNumber() {
        return number;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}


