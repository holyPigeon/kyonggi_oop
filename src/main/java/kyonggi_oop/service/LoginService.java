//package kyonggi_oop.domain;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class LoginService {
//    UserManager userManager;
//
//    public LoginService(UserManager userManager) {
//        this.userManager = userManager;
//    }
//
//    public boolean isRegisteredUser() {
//    }
//
//    void readStudent() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("학번 입력: ");
//        String studentId = scanner.next();
//        User findUser = userManager.findByStudentId(studentId);
//        if (studentIdList.contains(studentId)) {
//            System.out.println("로그인 되었습니다.");
//            runMenu();
//        } else if (!studentIdList.contains(studentId)) {
//            studentIdList.add(studentId);
//            System.out.println("회원가입 되었습니다.");
//        }
//    }
//
//    void runMenu() {
//        System.out.println("1. 좌석등록  2.좌석반납  3.좌석이동");
//    }
//}
