package kyonggi_oop.controller;

import kyonggi_oop.domain.kiosk.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.exception.ErrorMessage;
import kyonggi_oop.domain.kiosk.service.KioskService;
import kyonggi_oop.domain.login.service.LoginService;
import kyonggi_oop.validator.SeatValidator;
import kyonggi_oop.view.inputView.InputView;
import kyonggi_oop.view.outputView.OutputView;

public class KioskController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LoginService loginService;
    private final KioskService kioskService;

    public KioskController(InputView inputView,
                           OutputView outputView,
                           LoginService loginService,
                           KioskService kioskService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.loginService = loginService;
        this.kioskService = kioskService;
    }

    public void run() {
        while (!loginService.isLoggedIn()) {
            try {
                User user = tryLogin();
                loginService.login();
                kioskService.setUser(user);

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
            selectMenu(kioskService, readMenu(kioskService));
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
    private int readMenu(KioskService kioskService) {
        outputView.printUserStatusMessage(kioskService.getUserStatusResponse());
        return inputView.readMenu();
    }

    /*
    메뉴 선택
    */
    private void selectMenu(KioskService kioskService, int menu) {
        SeatUsage seatUsage;
        try {
            seatUsage = kioskService.getCurrentSeatUsage();
        } catch (IllegalStateException exception) {
            seatUsage = null;
        }

        switch (menu) {
            case 1 -> {
                SeatValidator.validateIsUserAlreadyUsingSeat(seatUsage);
                useSeat(kioskService);
            }
            case 2 -> {
                SeatValidator.validateIsSeatUsageExist(seatUsage);
                changeSeat(kioskService);
            }
            case 3 -> {
                SeatValidator.validateIsSeatUsageExist(seatUsage);
                returnSeat(kioskService);
            }
            case 4 -> logout();
        }
    }

    private void useSeat(KioskService kioskService) {
        outputView.printUseSeatMessage(kioskService.findAvailableSeats());
        Seat findSeat = kioskService.findSeatBySeatNumber(inputView.readSeatNumber());
        kioskService.useSeat(findSeat);
        outputView.printSeatUsedMessage(findSeat.getNumber());
    }

    private void changeSeat(KioskService kioskService) {
        outputView.printChangeSeatMessage(kioskService.findAvailableSeats());
        Seat findSeat = kioskService.findSeatBySeatNumber(inputView.readSeatNumber());
        kioskService.changeSeat(findSeat);
        outputView.printSeatChangedMessage(findSeat.getNumber());
    }

    private void returnSeat(KioskService kioskService) {
        outputView.printReturnSeatMessage();
        int usedSeatNumber = kioskService.getCurrentSeatUsage().getSeat().getNumber();
        kioskService.returnSeat();
        outputView.printSeatReturnedMessage(usedSeatNumber);
    }

    private void logout() {
        outputView.printLogoutMessage();
        loginService.logout();
    }
}
