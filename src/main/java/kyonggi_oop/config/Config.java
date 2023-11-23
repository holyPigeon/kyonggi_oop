package kyonggi_oop.config;

import kyonggi_oop.controller.KioskController;
import kyonggi_oop.domain.seat.repository.SeatRepository;
import kyonggi_oop.domain.user.repository.UserRepository;
import kyonggi_oop.domain.kiosk.service.KioskService;
import kyonggi_oop.domain.login.service.LoginService;

public interface Config {

    SeatRepository seatRepository();

    UserRepository userRepository();

    LoginService loginService();

    KioskService kioskService();

    KioskController kioskController();
}