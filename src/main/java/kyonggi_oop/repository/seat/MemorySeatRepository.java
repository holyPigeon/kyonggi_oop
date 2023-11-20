package kyonggi_oop.repository.seat;

import kyonggi_oop.domain.seat.RoomType;
import kyonggi_oop.domain.seat.Seat;

import java.util.ArrayList;
import java.util.List;

public class MemorySeatRepository implements SeatRepository {

    private List<Seat> seats;

    private MemorySeatRepository() {
        init();
    }

    public static MemorySeatRepository getInstance() {
        return new MemorySeatRepository();
    }

    @Override
    public void init() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            seats.add(Seat.create(i, RoomType.CREATIVE_FACTORY));
        }
        for (int i = 11; i <= 20; i++) {
            seats.add(Seat.create(i, RoomType.ROOM_1));
        }
        for (int i = 21; i <= 30; i++) {
            seats.add(Seat.create(i, RoomType.ROOM_2));
        }
        this.seats = seats;
    }

    @Override
    public List<Seat> findAvailableSeats() {
        return seats.stream()
                .filter(Seat::isAvailable)
                .toList();
    }

    @Override
    public Seat findBySeatNumber(int seatNumber) {
        return seats.stream()
                .filter(seat -> seat.getNumber() == seatNumber)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 좌석입니다."));
    }

    @Override
    public void updateSeatAvailability(Seat seat, boolean isAvailable) {
        seat.setAvailable(isAvailable);
    }

    @Override
    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }
}
