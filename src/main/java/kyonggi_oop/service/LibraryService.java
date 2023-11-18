package kyonggi_oop.service;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.response.UserStatusResponse;
import kyonggi_oop.repository.seat.SeatRepository;

import java.util.List;

public class LibraryService {

    SeatRepository seatRepository;
    User user;
    SeatUsage seatUsage;

    public LibraryService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public UserStatusResponse getUserStatusResponse() {
        return UserStatusResponse.of(user, seatUsage);
    }

    public void login(User user) {
        this.user = user;
    }

    public void useSeat(Seat seat) {
        this.seatUsage = SeatUsage.create(user, seat);
    }

    public void returnSeat() {
        this.seatUsage = null;
    }

    public void changeSeat(Seat seat) {
        if (isNotUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 사용하고 있지 않습니다.");
        }
        this.seatUsage = SeatUsage.create(user, seat);
    }

    public Seat getCurrentSeat() {
        if (isNotUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 사용하고 있지 않습니다.");
        }
        return seatUsage.getSeat();
    }

    private boolean isNotUsingSeat() {
        return seatUsage == null;
    }


    public Seat findSeatBySeatNumber(int seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }

    public List<Seat> findAvailableSeats() {
        return seatRepository.findAvailableSeats();
    }
}
