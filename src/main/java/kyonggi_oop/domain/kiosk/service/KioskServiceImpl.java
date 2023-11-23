package kyonggi_oop.domain.kiosk.service;

import kyonggi_oop.domain.kiosk.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.response.UserStatusResponse;
import kyonggi_oop.domain.seat.repository.SeatRepository;
import kyonggi_oop.validator.SeatValidator;

import java.util.List;

public class KioskServiceImpl implements KioskService {

    private User user;
    private SeatUsage seatUsage;
    private final SeatRepository seatRepository;

    private KioskServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public static KioskServiceImpl getInstance(SeatRepository seatRepository) {
        return new KioskServiceImpl(seatRepository);
    }

    @Override
    public UserStatusResponse getUserStatusResponse() {
        return UserStatusResponse.of(user, isUsingSeat(), seatUsage);
    }

    @Override
    public void useSeat(Seat selectedSeat) {
        SeatValidator.validateIsAlreadyUsingSeat(selectedSeat);
        this.seatUsage = SeatUsage.create(user, selectedSeat);
        seatRepository.updateSeatAvailability(selectedSeat, false);
    }

    @Override
    public void changeSeat(Seat selectedSeat) {
        seatRepository.updateSeatAvailability(getCurrentSeatUsage().getSeat(), true);
        this.seatUsage = SeatUsage.create(user, selectedSeat);
        seatRepository.updateSeatAvailability(selectedSeat, false);
    }

    @Override
    public void returnSeat() {
        SeatValidator.validateIsSeatUsageExist(seatUsage);
        seatRepository.updateSeatAvailability(getCurrentSeatUsage().getSeat(), true);
        this.seatUsage = null;

    }

    @Override
    public List<Seat> findAvailableSeats() {
        return seatRepository.findAvailableSeats();
    }

    @Override
    public SeatUsage getCurrentSeatUsage() {
        SeatValidator.validateIsSeatUsageExist(seatUsage);
        return seatUsage;
    }

    @Override
    public boolean isUsingSeat() {
        return seatUsage != null;
    }

    @Override
    public Seat findSeatBySeatNumber(int seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
