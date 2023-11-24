package kyonggi_oop.view.inputView;

import kyonggi_oop.controller.dto.request.UserLoginRequest;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;

import java.util.List;

public interface InputView {

    List<Seat> readSeatRequests();

    List<User> readUserJoinRequests();

    UserLoginRequest readStudentIdAndPassword();

    int readMenuForStudent();

    int readMenuForAdmin();

    int readSeatNumber();
}
