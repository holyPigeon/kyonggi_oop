package kyonggi_oop.view.outputView;

import kyonggi_oop.controller.dto.response.UserStatusResponse;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.view.util.DateTimeFormatter;

import java.util.List;

public class ConsoleOutputView implements OutputView {

    private ConsoleOutputView() {

    }

    public static ConsoleOutputView getInstance() {
        return new ConsoleOutputView();
    }

    @Override
    public void printLoginSuccessMessage() {
        System.out.println("로그인합니다.");
    }

    @Override
    public void printUserStatusMessage(UserStatusResponse userStatusResponse) {
        String seatNumberStatus = "\n<좌석 이용 현황>\n현재 " + userStatusResponse.getStudentId() + " 사용자가 ";

        if (!userStatusResponse.isUsingSeat()) {
            seatNumberStatus += "좌석을 이용하고 있지 않습니다.";
        }
        if (userStatusResponse.isUsingSeat()) {
            seatNumberStatus += userStatusResponse.getSeatNumber() + " 번 좌석 이용중입니다. (" +
                    DateTimeFormatter.format(userStatusResponse.getSeatUsageStartTime()) + " ~ " +
                    DateTimeFormatter.format(userStatusResponse.getSeatUsageEndTime()) + ")";
        }

        System.out.println(seatNumberStatus);
    }

    @Override
    public void printUseSeatMessage(List<Seat> seats) {
        System.out.println("1. 좌석 이용을 선택합니다.");
        System.out.println("이용하고자 하는 좌석 번호를 입력해주세요.");
        printAvailableSeatsMessage(seats);
    }

    @Override
    public void printSeatUsedMessage(int usingSeatNumber) {
        System.out.println(usingSeatNumber + "번 좌석 이용중입니다.");
    }

    private void printAvailableSeatsMessage(List<Seat> seats) {
        System.out.println();
        System.out.println("<이용 가능한 좌석 정보>");
        seats.forEach(seat ->
                System.out.println("위치: " + seat.getRoomType().getName() + " / 번호: " + seat.getNumber())
        );
    }

    @Override
    public void printAllSeatsMessage(List<Seat> seats) {
        System.out.println();
        System.out.println("<전체 좌석>");
        seats.forEach(seat ->
                System.out.println("위치: " + seat.getRoomType().getName() + " / 번호: " + seat.getNumber())
        );
    }

    @Override
    public void printAllUsersMessage(List<User> users) {
        System.out.println();
        System.out.println("<전체 사용자>");
        users.forEach(user ->
                System.out.println("학번: " + user.getStudentId() + " / 비밀번호: " + user.getPassword() +
                        " / 권한: " + user.getRole().getName())
        );
    }

    @Override
    public void printChangeSeatMessage(List<Seat> seats) {
        System.out.println("2. 좌석 이동을 선택합니다.");
        System.out.println("이동하고자 하는 좌석 번호를 입력해주세요.");
        printAvailableSeatsMessage(seats);
    }

    @Override
    public void printSeatChangedMessage(int changeSeatNumber) {
        System.out.println(changeSeatNumber + "번 좌석으로 이동하였습니다." );
    }

    @Override
    public void printReturnSeatMessage() {
        System.out.println("3. 좌석 반납을 선택합니다.");
    }

    @Override
    public void printSeatReturnedMessage(int usedSeatNumber) {
        System.out.println(usedSeatNumber + "번 좌석 반납되었습니다.");
    }

    @Override
    public void printLogoutMessage() {
        System.out.println("로그아웃합니다.");
    }
}
