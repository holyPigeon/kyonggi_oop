package kyonggi_oop.domain.seat.repository;

import kyonggi_oop.domain.seat.Seat;

import java.util.List;

public interface SeatRepository {

    void init();

    void addSeat(Seat seat);

    List<Seat> findAll();

    List<Seat> findAvailableSeats();

    Seat findBySeatNumber(int seatNumber);

    void updateSeatAvailability(Seat seat, boolean isAvailable);

    List<Seat> getSeats();

}
