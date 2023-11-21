package kyonggi_oop.service.library;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.response.UserStatusResponse;
import kyonggi_oop.repository.seat.SeatRepository;
import kyonggi_oop.validator.SeatValidator;

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
    public void useSeat(Seat selectedSeat) {
        SeatValidator.validateWhenUsingSeat(seatUsage, selectedSeat);
        this.seatUsage = SeatUsage.create(user, selectedSeat);
        seatRepository.updateSeatAvailability(selectedSeat, false);
    }

    @Override
    public void changeSeat(Seat selectedSeat) {
        SeatValidator.validateWhenChangingSeat(selectedSeat);
        seatRepository.updateSeatAvailability(getCurrentSeat(), true);
        this.seatUsage = SeatUsage.create(user, selectedSeat);
        seatRepository.updateSeatAvailability(selectedSeat, false);
    }

    @Override
    public void returnSeat() {
        SeatValidator.validateWhenReturningSeat(seatUsage);
        seatRepository.updateSeatAvailability(getCurrentSeat(), true);
        this.seatUsage = null;

    }

    @Override
    public List<Seat> findAvailableSeats() {
        return seatRepository.findAvailableSeats();
    }

    @Override
    public Seat getCurrentSeat() {
        SeatValidator.validateWhenGettingSeat(seatUsage);
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
