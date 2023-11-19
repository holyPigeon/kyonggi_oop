package kyonggi_oop.repository.seat;

import kyonggi_oop.domain.seat.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatRepository {

    private List<Seat> seats;

    private SeatRepository(List<Seat> seats) {
        this.seats = new ArrayList<>(seats);
    }

    public static SeatRepository create(List<Seat> seats) {
        return new SeatRepository(seats);
    }

    // 데이터 직접 입력을 위한 임시 주석
    //    public SeatManager() {
//        init();
//    }

//    public void init() {
//        List<Seat> seats = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            seats.add(new Seat(i, RoomType.CREATIVE_FACTORY));
//        }
//        for (int i = 11; i <= 20; i++) {
//            seats.add(new Seat(i, RoomType.ROOM_1));
//        }
//        for (int i = 21; i <= 30; i++) {
//            seats.add(new Seat(i, RoomType.ROOM_2));
//        }
//        this.seats = seats;
//    }

    public Seat findBySeatNumber(int seatNumber) {
        return seats.stream()
                .filter(seat -> seat.getNumber() == seatNumber)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 좌석입니다."));
    }

    public List<Seat> findAvailableSeats() {
        return seats.stream()
                .filter(Seat::isAvailable)
                .toList();
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }
}
