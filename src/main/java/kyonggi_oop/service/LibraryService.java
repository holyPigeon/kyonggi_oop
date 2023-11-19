package kyonggi_oop.service;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.response.UserStatusResponse;
import kyonggi_oop.repository.seat.SeatRepository;

import java.util.List;

public class LibraryService {

    private final SeatRepository seatRepository = SeatRepository.getInstance();
    private User user;
    private SeatUsage seatUsage;

    private LibraryService() {

    }

    public static LibraryService create() {
        return new LibraryService();
    }

    public UserStatusResponse getUserStatusResponse() {
        return UserStatusResponse.of(user, isUsingSeat(), seatUsage);
    }

    public void login(User user) {
        this.user = user;
    }

    public void useSeat(Seat seat) {
        if (!seat.isAvailable()) {
            throw new IllegalStateException("이미 사용중인 좌석입니다.");
        }
        this.seatUsage = SeatUsage.create(user, seat);
        seatRepository.updateSeatIsAvailable(seat, false);
    }

    public void returnSeat() {
        if (!isUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 사용하고 있지 않습니다.");
        }
        seatRepository.updateSeatIsAvailable(getCurrentSeat(), true);
        this.seatUsage = null;

    }

    public void changeSeat(Seat seat) {
        if (!isUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 사용하고 있지 않습니다.");
        }
        if (!seat.isAvailable()) {
            throw new IllegalStateException("이미 사용중인 좌석입니다.");
        }
        seatRepository.updateSeatIsAvailable(getCurrentSeat(), true);
        this.seatUsage = SeatUsage.create(user, seat);
        seatRepository.updateSeatIsAvailable(seat, false);
    }

    public Seat getCurrentSeat() {
        if (!isUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 사용하고 있지 않습니다.");
        }
        return seatUsage.getSeat();
    }

    private boolean isUsingSeat() {
        return seatUsage != null;
    }


    public Seat findSeatBySeatNumber(int seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }

    public List<Seat> findAvailableSeats() {
        return seatRepository.findAvailableSeats();
    }
}
