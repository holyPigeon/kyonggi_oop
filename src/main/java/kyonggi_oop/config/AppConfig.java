package kyonggi_oop.config;

import kyonggi_oop.controller.LibraryController;
import kyonggi_oop.repository.seat.MemorySeatRepository;
import kyonggi_oop.repository.seat.SeatRepository;
import kyonggi_oop.repository.user.MemoryUserRepository;
import kyonggi_oop.repository.user.UserRepository;
import kyonggi_oop.service.library.LibraryService;
import kyonggi_oop.service.library.LibraryServiceImpl;
import kyonggi_oop.service.login.LoginService;
import kyonggi_oop.service.login.LoginServiceImpl;
import kyonggi_oop.view.inputView.ConsoleInputView;
import kyonggi_oop.view.outputView.ConsoleOutputView;
import kyonggi_oop.view.InputView;
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
    public LibraryService libraryService() {
        return LazyHolder.libraryService;
    }

    @Override
    public LibraryController libraryController() {
        return LazyHolder.libraryController;
    }

    private static class LazyHolder {

        private static final AppConfig instance = new AppConfig();
        private static final InputView inputView = createInputView();
        private static final OutputView outputView = createOutputView();
        private static final SeatRepository seatRepository = createSeatRepository();
        private static final UserRepository userRepository = createUserRepository();
        private static final LoginService loginService = createLoginService();
        private static final LibraryService libraryService = createLibraryService();
        private static final LibraryController libraryController = createLibraryController();

        private static LibraryController createLibraryController() {
            return new LibraryController(inputView, outputView, loginService, libraryService);
        }

        private static LoginService createLoginService() {
            return LoginServiceImpl.getInstance(userRepository);
        }

        private static LibraryService createLibraryService() {
            return LibraryServiceImpl.getInstance(seatRepository);
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
