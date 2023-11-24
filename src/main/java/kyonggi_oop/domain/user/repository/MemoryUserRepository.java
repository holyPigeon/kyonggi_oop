package kyonggi_oop.domain.user.repository;

import kyonggi_oop.controller.dto.request.UserJoinRequest;
import kyonggi_oop.domain.user.User;

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
            users.add(User.create(UserJoinRequest.of("202300001", "abc001", "관리자"))); // 관리자 계정 추가
        for (int i = 2; i <= 30; i++) {
            String number = String.format("%02d", i);
            UserJoinRequest userJoinRequest = UserJoinRequest.of("2023000" + number, "abc0" + number, "학생");
            users.add(User.create(userJoinRequest));
        }
        this.users = users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeByStudentId(String studentId) {
        User findUser = findByStudentId(studentId);
        users.remove(findUser);
    }

    @Override
    public List<User> findAll() {
        return users;
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
