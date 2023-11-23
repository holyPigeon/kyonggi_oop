package kyonggi_oop.domain.user.repository;

import kyonggi_oop.domain.user.User;

import java.util.List;

public interface UserRepository {

    void init();

    void add(User user);

    void removeByStudentId(String studentId);

    User findByStudentId(String studentId);

    List<User> getUsers();
}
