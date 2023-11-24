package kyonggi_oop.domain.kiosk.service;

import kyonggi_oop.domain.kiosk.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.controller.dto.response.UserStatusResponse;
import kyonggi_oop.domain.seat.repository.SeatRepository;
import kyonggi_oop.domain.user.repository.UserRepository;
import kyonggi_oop.validator.SeatValidator;

import java.util.List;

public class KioskServiceImpl implements KioskService {

    private User user;
    private SeatUsage seatUsage;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    private KioskServiceImpl(SeatRepository seatRepository, UserRepository userRepository) {
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }

    public static KioskServiceImpl getInstance(SeatRepository seatRepository, UserRepository userRepository) {
        return new KioskServiceImpl(seatRepository, userRepository);
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
    public void addSeats(List<Seat> seats) {
        seats.forEach(seatRepository::addSeat);
    }

    @Override
    public void addUsers(List<User> users) {
        users.forEach(userRepository::addUser);
    }

    @Override
    public List<Seat> findAvailableSeats() {
        return seatRepository.findAvailableSeats();
    }

    @Override
    public List<Seat> findAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
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
