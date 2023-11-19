package kyonggi_oop.dto.request;

public class UserRequest {

    private final String studentId;
    private final String password;

    private UserRequest(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public static UserRequest of(String studentId, String password) {
        return new UserRequest(studentId, password);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }
}
