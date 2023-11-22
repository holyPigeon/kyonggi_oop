package kyonggi_oop.view.outputView;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.dto.response.UserStatusResponse;

import java.util.List;

public interface OutputView {

    void printLoginSuccessMessage();

    void printUserStatusMessage(UserStatusResponse userStatusResponse);

    void printUseSeatMessage(List<Seat> seats);

    void printSeatUsedMessage(int usingSeatNumber);

    void printChangeSeatMessage(List<Seat> seats);

    void printSeatChangedMessage(int changeSeatNumber);

    void printReturnSeatMessage();

    void printSeatReturnedMessage(int usedSeatNumber);

    void printLogoutMessage();
}
