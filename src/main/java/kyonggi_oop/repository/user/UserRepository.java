package kyonggi_oop.repository.user;

import kyonggi_oop.domain.user.User;

import java.util.List;

public class UserRepository {

    private final List<User> users;

    public UserRepository(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        users.add(user);
    }

    public void removeByStudentId(String studentId) {
        User findUser = findByStudentId(studentId);
        users.remove(findUser);
    }

    public User findByStudentId(String studentId) {
        return users.stream()
                .filter(user -> user.getStudentId().equals(studentId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 사용자입니다."));
    }

    public List<User> getUsers() {
        return users;
    }
}
