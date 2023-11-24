package kyonggi_oop.view.inputView;

import kyonggi_oop.controller.dto.request.SeatRequest;
import kyonggi_oop.controller.dto.request.UserJoinRequest;
import kyonggi_oop.controller.dto.request.UserLoginRequest;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.validator.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleInputView() {

    }

    public static ConsoleInputView getInstance() {
        return new ConsoleInputView();
    }

    @Override
    public UserLoginRequest readStudentIdAndPassword() {
        System.out.println();
        System.out.println("학번과 비밀번호를 입력해주세요. (e.g. 관리자 계정: 202300001,abc001 / 학생 계정: 202300002,abc002)");
        String input = scanner.next();
        String[] split = input.split(",");
        InputValidator.validateStudentIdInput(split[0]);
        InputValidator.validatePasswordInput(split[1]);

        return UserLoginRequest.of(split[0], split[1]);
    }

    @Override
    public int readMenuForStudent() {
        System.out.println();
        System.out.println("<메뉴>");
        System.out.println("메뉴를 선택하세요.");
        System.out.println("1.좌석 이용   2.좌석 이동   3.좌석 반납   4.로그아웃");
        String input = scanner.next();
        InputValidator.validateMenuInputForStudent(input);

        return Integer.parseInt(input);
    }

    @Override
    public int readMenuForAdmin() {
        System.out.println();
        System.out.println("<메뉴>");
        System.out.println("관리자 모드 이용중입니다. 메뉴를 선택하세요.");
        System.out.println("1.좌석 조회   2.사용자 조회   3.좌석 추가   4.사용자 추가   5.로그아웃");
        String input = scanner.next();
        InputValidator.validateMenuInputForAdmin(input);

        return Integer.parseInt(input);
    }

    @Override
    public int readSeatNumber() {
        System.out.println("이용할 좌석 번호를 입력해주세요.");
        String input = scanner.next();
        InputValidator.validateSeatNumberInput(input);

        return Integer.parseInt(input);
    }

    /*
    관리자 모드에서의 좌석 및 사용자 데이터 추가
    */
    @Override
    public List<Seat> readSeatRequests() {
        System.out.println("좌석 데이터를 입력하고, 입력 종료 시에는 \"입력 종료\"를 입력합니다.");

        List<Seat> seats = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine().trim()).equals("입력 종료")) {
            if (!line.isEmpty()) {
                String[] parts = line.split(",");
                SeatRequest seatRequest = SeatRequest.of(parts[1], parts[0]);
                seats.add(Seat.create(seatRequest));
            }
        }

        return seats;
    }

    @Override
    public List<User> readUserJoinRequests() {
        System.out.println("사용자 데이터를 입력하고, 입력 종료 시에는 \"입력 종료\"를 입력합니다.");

        List<User> users = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine().trim()).equals("입력 종료")) {
            if (!line.isEmpty()) {
                String[] parts = line.split(",");
                String studentId = parts[0].trim();
                String password = parts[1].trim();
                String roleName = parts[2].trim();
                UserJoinRequest userJoinRequest = UserJoinRequest.of(studentId, password, roleName);
                users.add(User.create(userJoinRequest));
            }
        }

        return users;
    }
}
