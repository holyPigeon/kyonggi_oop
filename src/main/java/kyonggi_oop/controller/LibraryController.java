package kyonggi_oop.controller;

import kyonggi_oop.service.LibraryService;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.view.InputView;
import kyonggi_oop.view.OutputView;

public class LibraryController {

    public void run() {

        // 로그인 진행 후 학번 입력값 받음
        String studentId = "202301234";
        User user = new User("202301234");
        LibraryService libraryService = new LibraryService(user);

        int menu;
        do {
            menu = InputView.readMenu();
            try {
                switch (menu) {
                    case 1 -> useSeat(libraryService);
                    case 2 -> {
                        user.getSeat();
                        changeSeat(libraryService);
                    }
                    case 3 -> returnSeat(libraryService);
                    case 4 -> logout();
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } while (menu != 4);
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
        Seat usedSeat = libraryService.changeSeat(findSeat);
        OutputView.printSeatChangedMessage(usedSeat.getNumber(), findSeat.getNumber());
    }

    private void returnSeat(LibraryService libraryService) {
        OutputView.printReturnSeatMessage();
        Seat usedSeat = libraryService.returnSeat();
        OutputView.printSeatReturnedMessage(usedSeat.getNumber());
    }

    private void logout() {
    }
}
