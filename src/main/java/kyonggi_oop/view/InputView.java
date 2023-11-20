package kyonggi_oop.view;

import kyonggi_oop.dto.request.UserRequest;

public interface InputView {

    UserRequest readStudentIdAndPassword();

    int readMenu();

    int readSeatNumber();
}
