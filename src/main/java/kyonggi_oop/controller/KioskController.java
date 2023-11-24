package kyonggi_oop.controller;

import kyonggi_oop.domain.kiosk.SeatUsage;
import kyonggi_oop.domain.kiosk.service.KioskService;
import kyonggi_oop.domain.login.service.LoginService;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.Role;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.validator.SeatValidator;
import kyonggi_oop.view.inputView.InputView;
import kyonggi_oop.view.outputView.OutputView;

import java.util.List;

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
                kioskService.setUser(user);

                while (loginService.isLoggedIn()) {
                    selectMenuWithExceptionHandling(user);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void selectMenuWithExceptionHandling(User user) {
        try {
            if (user.getRole().equals(Role.STUDENT)) {
                outputView.printUserStatusMessage(kioskService.getUserStatusResponse());
                int menu = inputView.readMenuForStudent();
                selectMenuForStudent(kioskService, menu);
            }
            if (user.getRole().equals(Role.ADMIN)) {
                int menu = inputView.readMenuForAdmin();
                selectMenuForAdmin(menu);
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*
    사용자 로그인
     */
    private User tryLogin() {
        User user = loginService.tryLogin(inputView.readStudentIdAndPassword());
        outputView.printLoginSuccessMessage();
        return user;
    }

    /*
    메뉴 선택
    */
    private void selectMenuForStudent(KioskService kioskService, int menu) {
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

    private void selectMenuForAdmin(int menu) {
        switch (menu) {
            case 1 -> viewSeats();
            case 2 -> viewUsers();
            case 3 -> addSeats();
            case 4 -> addUsers();
            case 5 -> logout();
        }
    }

    private void viewSeats() {
        outputView.printAllSeatsMessage(kioskService.findAllSeats());
    }

    private void viewUsers() {
        outputView.printAllUsersMessage(kioskService.findAllUsers());
    }

    private void addSeats() {
        List<Seat> seats = inputView.readSeatRequests();
        kioskService.addSeats(seats);
    }

    private void addUsers() {
        List<User> users = inputView.readUserJoinRequests();
        kioskService.addUsers(users);
    }

    private void logout() {
        outputView.printLogoutMessage();
        loginService.logout();
    }
}
