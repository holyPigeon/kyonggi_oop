package kyonggi_oop.controller;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.repository.seat.SeatRepository;
import kyonggi_oop.repository.user.UserRepository;
import kyonggi_oop.service.LibraryService;
import kyonggi_oop.service.LoginService;
import kyonggi_oop.view.InputView;
import kyonggi_oop.view.OutputView;

public class LibraryController {

    public void run() {

        SeatRepository seatRepository = new SeatRepository(InputView.readSeats());
        LibraryService libraryService = new LibraryService(seatRepository);
        libraryService.login(tryLogin());

        int menu;
        while ((menu = readMenu(libraryService)) != 4) {
            selectMenuWithExceptionHandling(libraryService, menu);
        }
    }

    /*
    사용자 로그인
     */
    private static User tryLogin() {
        LoginService loginService = prepareLoginService();
        User user;

        while (true) {
            user = new User(InputView.readStudentIdAndPassword());
            if (loginService.isRegisteredUser(user)) {
                OutputView.printLoginSuccessMessage();
                break;
            }
            OutputView.printLoginFailMessage();
        }

        return user;
    }

    private static LoginService prepareLoginService() {
        UserRepository userRepository = new UserRepository(InputView.readUsers());
        return new LoginService(userRepository);
    }

    /*
    메뉴 입력값 입력
     */
    private static int readMenu(LibraryService libraryService) {
        return InputView.readMenu(libraryService.getUserStatusResponse());
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
            case 1 -> useSeat(libraryService);
            case 2 -> changeSeat(libraryService);
            case 3 -> returnSeat(libraryService);
            case 4 -> logout();
        }
    }

    private void useSeat(LibraryService libraryService) {
        OutputView.printUseSeatMessage(libraryService.findAvailableSeats());
        Seat findSeat = libraryService.findSeatBySeatNumber(InputView.readSeatNumber());
        libraryService.useSeat(findSeat);
        OutputView.printSeatUsedMessage(findSeat.getNumber());
    }

    private void changeSeat(LibraryService libraryService) {
        OutputView.printChangeSeatMessage(libraryService.findAvailableSeats());
        Seat findSeat = libraryService.findSeatBySeatNumber(InputView.readSeatNumber());
        libraryService.changeSeat(findSeat);
        OutputView.printSeatChangedMessage(findSeat.getNumber());
    }

    private void returnSeat(LibraryService libraryService) {
        OutputView.printReturnSeatMessage();
        int usedSeatNumber = libraryService.getCurrentSeat().getNumber();
        libraryService.returnSeat();
        OutputView.printSeatReturnedMessage(usedSeatNumber);
    }

    private void logout() {
    }
}
