package kyonggi_oop.view;

import kyonggi_oop.domain.seat.RoomType;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static List<Seat> readSeats() {
        System.out.println("좌석 데이터를 입력합니다.");
        String input = scanner.next();
        return Arrays.stream(input.split("\n"))
                .map(line -> {
                    String[] parts = line.split(", ");
                    int roomNumber = Integer.parseInt(parts[1]);
                    RoomType roomType = RoomType.findByName(parts[0]);
                    return new Seat(roomNumber, roomType);
                }).toList();
    }

    public static List<User> readUsers() {
        System.out.println("사용자 데이터를 입력합니다.");
        String input = scanner.nextLine();
        return Arrays.stream(input.split("\n"))
                .map(line -> {
                    String[] parts = line.split(", ");
                    String studentId = parts[0];
                    String password = parts[1];
                    return new User(studentId, password);
                }).toList();
    }

    public static User readStudentIdAndPassword() {
        System.out.println("학번과 비밀번호를 입력해주세요. (e.g. 202301234, abc123");
        String input = scanner.next();
        String[] split = input.split(input);
        return new User(split[0], split[1]);
    }

    public static int readMenu() {
        System.out.println();
        System.out.println("<메뉴>");
        System.out.println("메뉴를 선택하세요.");
        System.out.println("1. 좌석 이용   2.좌석 이동   3.좌석 반납   4.로그아웃");

        return Integer.parseInt(scanner.next());
    }

    public static int readSeatNumber() {
        System.out.println("이용할 좌석 번호를 입력해주세요.");

        return Integer.parseInt(scanner.next());
    }
}
