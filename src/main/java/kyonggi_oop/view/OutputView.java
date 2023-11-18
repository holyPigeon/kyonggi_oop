package kyonggi_oop.view;

import kyonggi_oop.domain.seat.Seat;

import java.util.List;

public class OutputView {

    private OutputView() {

    }

    public static void printLoginSuccessMessage() {
        System.out.println("로그인에 성공하였습니다.");
    }

    public static void printLoginFailMessage() {
        System.out.println("로그인에 실패하였습니다.");
    }

    public static void printUseSeatMessage(List<Seat> seats) {
        System.out.println("1. 좌석 이용을 선택합니다.");
        System.out.println("이용하고자 하는 좌석 번호를 입력해주세요.");
        printAvailableSeatsMessage(seats);
    }

    public static void printSeatUsedMessage(int usingSeatNumber) {
        System.out.println();
        System.out.println(usingSeatNumber + "번 좌석 이용중입니다.");
    }

    private static void printAvailableSeatsMessage(List<Seat> seats) {
        System.out.println();
        System.out.println("<이용 가능한 좌석 정보>");
        seats.forEach(seat ->
                System.out.println("위치: " + seat.getRoomType().getName() + " / 번호: " + seat.getNumber())
        );
    }

    public static void printChangeSeatMessage(List<Seat> seats) {
        System.out.println("2. 좌석 이동을 선택합니다.");
        System.out.println("이동하고자 하는 좌석 번호를 입력해주세요.");
        printAvailableSeatsMessage(seats);
    }

    public static void printSeatChangedMessage(int changeSeatNumber) {
        System.out.println();
        System.out.println(changeSeatNumber + "번 좌석으로 이동하였습니다." );
    }

    public static void printReturnSeatMessage() {
        System.out.println("3. 좌석 반납을 선택합니다.");
    }

    public static void printSeatReturnedMessage(int usedSeatNumber) {
        System.out.println();
        System.out.println(usedSeatNumber + "번 좌석 반납되었습니다.");
    }
}
