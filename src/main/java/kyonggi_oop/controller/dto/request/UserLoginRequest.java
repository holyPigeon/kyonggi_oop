package kyonggi_oop.controller.dto.request;

public class UserLoginRequest {

    private final String studentId;
    private final String password;

    private UserLoginRequest(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public static UserLoginRequest of(String studentId, String password) {
        return new UserLoginRequest(studentId, password);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }
}
