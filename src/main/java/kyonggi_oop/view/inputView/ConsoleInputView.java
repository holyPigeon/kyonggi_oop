package kyonggi_oop.view.inputView;

import kyonggi_oop.dto.request.UserRequest;
import kyonggi_oop.validator.InputValidator;

import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleInputView() {

    }

    public static ConsoleInputView getInstance() {
        return new ConsoleInputView();
    }

    /*
     좌석 및 사용자 초기 데이터 입력 로직 임시 비활성
     */
//    public static List<Seat> readSeats() {
//        System.out.println("좌석 데이터를 입력하고, 입력 종료 시에는 \"입력 종료\"를 입력합니다.");
//
//        List<Seat> seats = new ArrayList<>();
//        String line;
//        while (!(line = scanner.nextLine().trim()).equals("입력 종료")) {
//            String[] parts = line.split(",");
//            int roomNumber = Integer.parseInt(parts[1]);
//            RoomType roomType = RoomType.findByName(parts[0]);
//            seats.add(Seat.create(roomNumber, roomType));
//        }
//
//        return seats;
//    }
//
//    public static List<User> readUsers() {
//        System.out.println("사용자 데이터를 입력하고, 입력 종료 시에는 \"입력 종료\"를 입력합니다.");
//
//        List<User> users = new ArrayList<>();
//        String line;
//        while (!(line = scanner.nextLine().trim()).equals("입력 종료")) {
//            String[] parts = line.split(",");
//            String studentId = parts[0].trim();
//            String password = parts[1].trim();
//            UserRequest userRequest = UserRequest.of(studentId, password);
//            users.add(new User(userRequest));
//        }
//
//        return users;
//    }

    @Override
    public UserRequest readStudentIdAndPassword() {
        System.out.println("학번과 비밀번호를 입력해주세요. (e.g. 202300001,abc001)");
        String input = scanner.next();
        String[] split = input.split(",");
        InputValidator.validateStudentIdInput(split[0]);
        InputValidator.validatePasswordInput(split[1]);

        return UserRequest.of(split[0], split[1]);
    }

    @Override
    public int readMenu() {
        System.out.println();
        System.out.println("<메뉴>");
        System.out.println("메뉴를 선택하세요.");
        System.out.println("1.좌석 이용   2.좌석 이동   3.좌석 반납   4.로그아웃");
        String input = scanner.next();
        InputValidator.validateMenuInput(input);

        return Integer.parseInt(input);
    }

    @Override
    public int readSeatNumber() {
        System.out.println("이용할 좌석 번호를 입력해주세요.");
        String input = scanner.next();
        InputValidator.validateSeatNumberInput(input);

        return Integer.parseInt(input);
    }
}
