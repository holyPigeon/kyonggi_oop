package kyonggi_oop.repository.user;

import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final UserRepository instance = new UserRepository();
    private List<User> users;

    private UserRepository() {
        init();
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public void init() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            String number = String.format("%02d", i);
            UserRequest userRequest = UserRequest.of("2023000" + number, "abc0" + number);
            users.add(User.create(userRequest));
        }
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
