package kyonggi_oop.service;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.seat.SeatManager;
import kyonggi_oop.domain.user.User;

import java.util.List;
import java.util.Optional;

public class LibraryService {

    SeatManager seatManager;
    User user;

    public LibraryService(User user, List<Seat> seats) {
        seatManager = new SeatManager(seats);
        this.user = user;
    }

    public void useSeat(Seat seat) {
        seat.setAvailable(false);
        user.setSeat(seat);
    }

    public Seat returnSeat() {
        Seat usedSeat = user.returnSeat();
        usedSeat.setAvailable(true);

        return usedSeat;
    }

    public Seat changeSeat(Seat seat) {
        Seat usedSeat = user.getSeat();
        user.setSeat(seat);
        seat.setAvailable(false);
        usedSeat.setAvailable(true);

        return usedSeat;
    }

    public Seat findSeatBySeatNumber(int seatNumber) {
        return seatManager.findBySeatNumber(seatNumber);
    }

    public List<Seat> findAvailableSeats() {
        return seatManager.findAvailableSeats();
    }
}
