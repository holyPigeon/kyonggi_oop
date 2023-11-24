package kyonggi_oop.config;

import kyonggi_oop.controller.KioskController;
import kyonggi_oop.domain.seat.repository.MemorySeatRepository;
import kyonggi_oop.domain.seat.repository.SeatRepository;
import kyonggi_oop.domain.user.repository.MemoryUserRepository;
import kyonggi_oop.domain.user.repository.UserRepository;
import kyonggi_oop.domain.kiosk.service.KioskService;
import kyonggi_oop.domain.kiosk.service.KioskServiceImpl;
import kyonggi_oop.domain.login.service.LoginService;
import kyonggi_oop.domain.login.service.LoginServiceImpl;
import kyonggi_oop.view.inputView.ConsoleInputView;
import kyonggi_oop.view.inputView.InputView;
import kyonggi_oop.view.outputView.ConsoleOutputView;
import kyonggi_oop.view.outputView.OutputView;

public class AppConfig implements Config {

    public static AppConfig getInstance() {
        return LazyHolder.instance;
    }

    @Override
    public SeatRepository seatRepository() {
        return LazyHolder.seatRepository;
    }

    @Override
    public UserRepository userRepository() {
        return LazyHolder.userRepository;
    }

    @Override
    public LoginService loginService() {
        return LazyHolder.loginService;
    }

    @Override
    public KioskService kioskService() {
        return LazyHolder.KIOSK_SERVICE;
    }

    @Override
    public KioskController kioskController() {
        return LazyHolder.KIOSK_CONTROLLER;
    }

    private static class LazyHolder {

        private static final AppConfig instance = new AppConfig();
        private static final InputView inputView = createInputView();
        private static final OutputView outputView = createOutputView();
        private static final SeatRepository seatRepository = createSeatRepository();
        private static final UserRepository userRepository = createUserRepository();
        private static final LoginService loginService = createLoginService();
        private static final KioskService KIOSK_SERVICE = createKioskService();
        private static final KioskController KIOSK_CONTROLLER = createKioskController();

        private static KioskController createKioskController() {
            return new KioskController(inputView, outputView, loginService, KIOSK_SERVICE);
        }

        private static LoginService createLoginService() {
            return LoginServiceImpl.getInstance(userRepository);
        }

        private static KioskService createKioskService() {
            return KioskServiceImpl.getInstance(seatRepository, userRepository);
        }

        private static SeatRepository createSeatRepository() {
            return MemorySeatRepository.getInstance();
        }

        private static UserRepository createUserRepository() {
            return MemoryUserRepository.getInstance();
        }

        private static InputView createInputView() {
            return ConsoleInputView.getInstance();
        }

        private static OutputView createOutputView() {
            return ConsoleOutputView.getInstance();
        }
    }
}
