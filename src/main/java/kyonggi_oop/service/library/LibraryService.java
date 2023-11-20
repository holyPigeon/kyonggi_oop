package kyonggi_oop.service.library;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.response.UserStatusResponse;

import java.util.List;

public interface LibraryService {

    UserStatusResponse getUserStatusResponse();

    void login(User user);

    void useSeat(Seat seat);

    void returnSeat();

    void changeSeat(Seat seat);

    List<Seat> findAvailableSeats();

    Seat getCurrentSeat();

    boolean isUsingSeat();

    Seat findSeatBySeatNumber(int seatNumber);
}
