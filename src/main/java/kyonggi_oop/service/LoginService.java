package kyonggi_oop.service;

import kyonggi_oop.domain.user.User;
import kyonggi_oop.dto.request.UserRequest;
import kyonggi_oop.repository.user.UserRepository;
import kyonggi_oop.view.OutputView;

import java.util.function.Predicate;

public class LoginService {
    UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User tryLogin(UserRequest userRequest) {
        User user;

        while (true) {
            user = new User(userRequest);
            if (isRegisteredUser(user)) {
                OutputView.printLoginSuccessMessage();
                break;
            }
            OutputView.printLoginFailMessage();
        }

        return user;
    }

    public boolean isRegisteredUser(User user) {
        return userRepository.getUsers()
                .stream()
                .anyMatch(isStudentIdAndPasswordAllMatch(user));
    }

    private static Predicate<User> isStudentIdAndPasswordAllMatch(User user) {
        return savedUser -> isStudentIdMatch(user, savedUser) && isPasswordMatch(user, savedUser);
    }

    private static boolean isPasswordMatch(User user, User savedUser) {
        return savedUser.getPassword().equals(user.getPassword());
    }

    private static boolean isStudentIdMatch(User user, User savedUser) {
        return savedUser.getStudentId().equals(user.getStudentId());
    }
}
