package kyonggi_oop.domain.user.repository;

import kyonggi_oop.domain.user.User;

import java.util.List;

public interface UserRepository {

    void init();

    void addUser(User user);

    void removeByStudentId(String studentId);

    List<User> findAll();

    User findByStudentId(String studentId);

    List<User> getUsers();
}
