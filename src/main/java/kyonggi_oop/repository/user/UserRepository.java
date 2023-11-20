package kyonggi_oop.repository.user;

import kyonggi_oop.domain.user.User;

import java.util.List;

public interface UserRepository {

    void init();

    void add(User user);

    void removeByStudentId(String studentId);

    User findByStudentId(String studentId);

    List<User> getUsers();
}
