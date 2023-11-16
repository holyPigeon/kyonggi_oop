package kyonggi_oop.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

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
