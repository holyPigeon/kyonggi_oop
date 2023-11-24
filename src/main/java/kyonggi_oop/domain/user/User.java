package kyonggi_oop.domain.user;

import kyonggi_oop.controller.dto.request.UserJoinRequest;

public class User {

    private final String studentId;
    private final String password;
    private final Role role;

    private User(UserJoinRequest userJoinRequest) {
        this.studentId = userJoinRequest.getStudentId();
        this.password = userJoinRequest.getPassword();
        this.role = userJoinRequest.getRole();
    }

    public static User create(UserJoinRequest userJoinRequest) {
        return new User(userJoinRequest);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
