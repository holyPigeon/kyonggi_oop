package kyonggi_oop.domain.user.repository;

import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserRepository implements UserRepository{

    private List<User> users;

    private MemoryUserRepository() {
        init();
    }

    public static MemoryUserRepository getInstance() {
        return new MemoryUserRepository();
    }

    @Override
    public void init() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            String number = String.format("%02d", i);
            UserRequest userRequest = UserRequest.of("2023000" + number, "abc0" + number);
            users.add(User.create(userRequest));
        }
        this.users = users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void removeByStudentId(String studentId) {
        User findUser = findByStudentId(studentId);
        users.remove(findUser);
    }

    @Override
    public User findByStudentId(String studentId) {
        return users.stream()
                .filter(user -> user.getStudentId().equals(studentId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 사용자입니다."));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
