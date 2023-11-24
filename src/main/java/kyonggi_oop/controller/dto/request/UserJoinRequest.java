package kyonggi_oop.controller.dto.request;

import kyonggi_oop.domain.user.Role;

public class UserJoinRequest {

    private final String studentId;
    private final String password;
    private final Role role;

    private UserJoinRequest(String studentId, String password, String roleName) {
        this.studentId = studentId;
        this.password = password;
        this.role = Role.findByName(roleName);
    }

    public static UserJoinRequest of(String studentId, String password, String roleName) {
        return new UserJoinRequest(studentId, password, roleName);
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
