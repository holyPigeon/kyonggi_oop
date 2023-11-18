package kyonggi_oop.service;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.repository.seat.SeatRepository;
import kyonggi_oop.domain.user.User;

import java.util.List;

public class LibraryService {

    SeatRepository seatRepository;
    User user;

    public LibraryService(User user, List<Seat> seats) {
        seatRepository = new SeatRepository(seats);
        this.user = user;
    }

    public void useSeat(Seat seat) {
        seat.setAvailable(false);
        user.useSeat(seat);
    }

    public Seat returnSeat() {
        Seat usedSeat = user.returnSeat();
        usedSeat.setAvailable(true);

        return usedSeat;
    }

    public Seat changeSeat(Seat seat) {
        Seat usedSeat = user.getSeat();
        user.useSeat(seat);
        seat.setAvailable(false);
        usedSeat.setAvailable(true);

        return usedSeat;
    }

    public Seat findSeatBySeatNumber(int seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }

    public List<Seat> findAvailableSeats() {
        return seatRepository.findAvailableSeats();
    }
}
