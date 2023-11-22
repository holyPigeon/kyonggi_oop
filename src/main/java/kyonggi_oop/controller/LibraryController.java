package kyonggi_oop.controller;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.exception.ErrorMessage;
import kyonggi_oop.service.library.LibraryService;
import kyonggi_oop.service.login.LoginService;
import kyonggi_oop.validator.SeatValidator;
import kyonggi_oop.view.inputView.InputView;
import kyonggi_oop.view.outputView.OutputView;

public class LibraryController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LoginService loginService;
    private final LibraryService libraryService;

    public LibraryController(InputView inputView,
                             OutputView outputView,
                             LoginService loginService,
                             LibraryService libraryService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.loginService = loginService;
        this.libraryService = libraryService;
    }

    public void run() {
        while (!loginService.isLoggedIn()) {
            try {
                User user = tryLogin();
                loginService.login();
                libraryService.setUser(user);

                while (loginService.isLoggedIn()) {
                    selectMenuWithExceptionHandling();
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void selectMenuWithExceptionHandling() {
        try {
            selectMenu(libraryService, readMenu(libraryService));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*
    사용자 로그인
     */
    private User tryLogin() {
        User user = new User(inputView.readStudentIdAndPassword());
        if (!loginService.isRegisteredUser(user)) {
            throw new IllegalStateException(ErrorMessage.LOGIN_FAILED.getMessage());
        }
        outputView.printLoginSuccessMessage();

        return user;
    }

    /*
    메뉴 입력값 입력
     */
    private int readMenu(LibraryService libraryService) {
        outputView.printUserStatusMessage(libraryService.getUserStatusResponse());
        return inputView.readMenu();
    }

    /*
    메뉴 선택
    */
    private void selectMenu(LibraryService libraryService, int menu) {
        SeatUsage seatUsage;
        try {
            seatUsage = libraryService.getCurrentSeatUsage();
        } catch (IllegalStateException exception) {
            seatUsage = null;
        }

        switch (menu) {
            case 1 -> {
                SeatValidator.validateIsUserAlreadyUsingSeat(seatUsage);
                useSeat(libraryService);
            }
            case 2 -> {
                SeatValidator.validateIsSeatUsageExist(seatUsage);
                changeSeat(libraryService);
            }
            case 3 -> {
                SeatValidator.validateIsSeatUsageExist(seatUsage);
                returnSeat(libraryService);
            }
            case 4 -> logout();
        }
    }

    private void useSeat(LibraryService libraryService) {
        outputView.printUseSeatMessage(libraryService.findAvailableSeats());
        Seat findSeat = libraryService.findSeatBySeatNumber(inputView.readSeatNumber());
        libraryService.useSeat(findSeat);
        outputView.printSeatUsedMessage(findSeat.getNumber());
    }

    private void changeSeat(LibraryService libraryService) {
        outputView.printChangeSeatMessage(libraryService.findAvailableSeats());
        Seat findSeat = libraryService.findSeatBySeatNumber(inputView.readSeatNumber());
        libraryService.changeSeat(findSeat);
        outputView.printSeatChangedMessage(findSeat.getNumber());
    }

    private void returnSeat(LibraryService libraryService) {
        outputView.printReturnSeatMessage();
        int usedSeatNumber = libraryService.getCurrentSeatUsage().getSeat().getNumber();
        libraryService.returnSeat();
        outputView.printSeatReturnedMessage(usedSeatNumber);
    }

    private void logout() {
        outputView.printLogoutMessage();
        loginService.logout();
    }
}
