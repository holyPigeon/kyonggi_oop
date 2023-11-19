package kyonggi_oop.domain.user;

import kyonggi_oop.dto.request.UserRequest;

public class User {

    private final String studentId;
    private final String password;

    public User(UserRequest userRequest) {
        this.studentId = userRequest.getStudentId();
        this.password = userRequest.getPassword();
    }

    public static User create(UserRequest userRequest) {
        return new User(userRequest);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }
}
