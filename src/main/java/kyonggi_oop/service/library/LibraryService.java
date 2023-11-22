package kyonggi_oop.service.library;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.response.UserStatusResponse;

import java.util.List;

public interface LibraryService {

    UserStatusResponse getUserStatusResponse();

    void useSeat(Seat seat);

    void returnSeat();

    void changeSeat(Seat seat);

    List<Seat> findAvailableSeats();

    SeatUsage getCurrentSeatUsage();

    boolean isUsingSeat();

    Seat findSeatBySeatNumber(int seatNumber);

    void setUser(User user);
}
