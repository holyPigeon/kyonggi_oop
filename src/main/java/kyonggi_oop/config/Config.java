package kyonggi_oop.config;

import kyonggi_oop.controller.LibraryController;
import kyonggi_oop.repository.seat.SeatRepository;
import kyonggi_oop.repository.user.UserRepository;
import kyonggi_oop.service.library.LibraryService;
import kyonggi_oop.service.login.LoginService;

public interface Config {

    SeatRepository seatRepository();

    UserRepository userRepository();

    LoginService loginService();

    LibraryService libraryService();

    LibraryController libraryController();
}