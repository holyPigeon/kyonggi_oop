package kyonggi_oop.service.library;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.response.UserStatusResponse;
import kyonggi_oop.repository.seat.SeatRepository;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private User user;
    private SeatUsage seatUsage;
    private final SeatRepository seatRepository;

    private LibraryServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public static LibraryServiceImpl getInstance(SeatRepository seatRepository) {
        return new LibraryServiceImpl(seatRepository);
    }

    @Override
    public UserStatusResponse getUserStatusResponse() {
        return UserStatusResponse.of(user, isUsingSeat(), seatUsage);
    }

    @Override
    public void login(User user) {
        this.user = user;
    }

    @Override
    public void useSeat(Seat seat) {
        if (isUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 이미 이용하고 있습니다.");
        }
        if (!seat.isAvailable()) {
            throw new IllegalStateException("이미 이용중인 좌석입니다.");
        }
        this.seatUsage = SeatUsage.create(user, seat);
        seatRepository.updateSeatAvailability(seat, false);
    }

    @Override
    public void returnSeat() {
        if (!isUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 이용하고 있지 않습니다.");
        }
        seatRepository.updateSeatAvailability(getCurrentSeat(), true);
        this.seatUsage = null;

    }

    @Override
    public void changeSeat(Seat seat) {
        if (!seat.isAvailable()) {
            throw new IllegalStateException("이미 이용중인 좌석입니다.");
        }
        seatRepository.updateSeatAvailability(getCurrentSeat(), true);
        this.seatUsage = SeatUsage.create(user, seat);
        seatRepository.updateSeatAvailability(seat, false);
    }

    @Override
    public List<Seat> findAvailableSeats() {
        return seatRepository.findAvailableSeats();
    }

    @Override
    public Seat getCurrentSeat() {
        if (!isUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 이용하고 있지 않습니다.");
        }
        return seatUsage.getSeat();
    }

    @Override
    public boolean isUsingSeat() {
        return seatUsage != null;
    }

    @Override
    public Seat findSeatBySeatNumber(int seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }
}
