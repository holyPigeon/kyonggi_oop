package kyonggi_oop.domain.seat.repository;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.controller.dto.request.SeatRequest;
import kyonggi_oop.exception.ErrorMessage;

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
            SeatRequest seatRequest = SeatRequest.of(String.valueOf(i), "창의 팩토리");
            seats.add(Seat.create(seatRequest));
        }
        for (int i = 11; i <= 20; i++) {
            SeatRequest seatRequest = SeatRequest.of(String.valueOf(i), "제1열람실");
            seats.add(Seat.create(seatRequest));
        }
        for (int i = 21; i <= 30; i++) {
            SeatRequest seatRequest = SeatRequest.of(String.valueOf(i), "제2열람실");
            seats.add(Seat.create(seatRequest));
        }
        this.seats = seats;
    }

    @Override
    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    @Override
    public List<Seat> findAll() {
        return seats;
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
                .orElseThrow(() -> new IllegalStateException(ErrorMessage.NOT_EXISTING_SEAT.getMessage()));
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
