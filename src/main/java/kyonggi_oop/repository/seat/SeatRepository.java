package kyonggi_oop.repository.seat;

import kyonggi_oop.domain.seat.Seat;

import java.util.List;

public interface SeatRepository {

    void init();

    List<Seat> findAvailableSeats();

    Seat findBySeatNumber(int seatNumber);

    void updateSeatAvailability(Seat seat, boolean isAvailable);

    List<Seat> getSeats();

}
