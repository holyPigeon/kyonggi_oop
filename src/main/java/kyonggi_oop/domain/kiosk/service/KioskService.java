package kyonggi_oop.domain.kiosk.service;

import kyonggi_oop.domain.kiosk.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.controller.dto.response.UserStatusResponse;

import java.util.List;

public interface KioskService {

    UserStatusResponse getUserStatusResponse();

    void useSeat(Seat seat);

    void returnSeat();

    void changeSeat(Seat seat);

    void addSeats(List<Seat> seats);

    void addUsers(List<User> users);

    List<Seat> findAvailableSeats();

    List<Seat> findAllSeats();

    List<User> findAllUsers();

    SeatUsage getCurrentSeatUsage();

    boolean isUsingSeat();

    Seat findSeatBySeatNumber(int seatNumber);

    void setUser(User user);
}
