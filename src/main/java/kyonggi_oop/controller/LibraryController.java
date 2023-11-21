package kyonggi_oop.controller;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.service.library.LibraryService;
import kyonggi_oop.service.login.LoginService;
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

        libraryService.login(tryLogin());

        int menu;
        while ((menu = readMenu(libraryService)) != 4) {
            selectMenuWithExceptionHandling(libraryService, menu);
        }
    }

    /*
    사용자 로그인
     */
    private User tryLogin() {
        User user;

        while (true) {
            user = new User(inputView.readStudentIdAndPassword());
            if (loginService.isRegisteredUser(user)) {
                outputView.printLoginSuccessMessage();
                break;
            }
            outputView.printLoginFailMessage();
        }

        return user;
    }

    /*
    메뉴 입력값 입력
     */
    private int readMenu(LibraryService libraryService) {
        outputView.printUserStatusMessage(libraryService.getUserStatusResponse());
        return inputView.readMenu();
    }

    private void selectMenuWithExceptionHandling(LibraryService libraryService, int menu) {
        try {
            selectMenu(libraryService, menu);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*
    메뉴 선택
    */
    private void selectMenu(LibraryService libraryService, int menu) {
        switch (menu) {
            case 1 -> {
                if (libraryService.isUsingSeat()) {
                    throw new IllegalStateException("사용자가 좌석을 이미 이용하고 있습니다.");
                }
                useSeat(libraryService);
            }
            case 2 -> {
                if (!libraryService.isUsingSeat()) {
                    throw new IllegalStateException("사용자가 좌석을 이용하고 있지 않습니다.");
                }
                changeSeat(libraryService);
            }
            case 3 -> returnSeat(libraryService);
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
        int usedSeatNumber = libraryService.getCurrentSeat().getNumber();
        libraryService.returnSeat();
        outputView.printSeatReturnedMessage(usedSeatNumber);
    }

    private void logout() {
    }
}
