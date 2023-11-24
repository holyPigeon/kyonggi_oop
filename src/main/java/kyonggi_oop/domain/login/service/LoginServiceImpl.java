package kyonggi_oop.domain.login.service;

import kyonggi_oop.controller.dto.request.UserLoginRequest;
import kyonggi_oop.domain.user.User;
import kyonggi_oop.domain.user.repository.UserRepository;
import kyonggi_oop.exception.ErrorMessage;

import java.util.function.Predicate;

public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private boolean isLoggedIn;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.isLoggedIn = false;
    }

    public static LoginServiceImpl getInstance(UserRepository userRepository) {
        return new LoginServiceImpl(userRepository);
    }

    @Override
    public User tryLogin(UserLoginRequest userLoginRequest) {
        if (!isRegisteredUser(userLoginRequest)) {
            throw new IllegalStateException(ErrorMessage.LOGIN_FAILED.getMessage());
        }
        login();

        return userRepository.findByStudentId(userLoginRequest.getStudentId());
    }

    private void login() {
        isLoggedIn = true;
    }

    @Override
    public void logout() {
        isLoggedIn = false;
    }

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public boolean isRegisteredUser(UserLoginRequest userLoginRequest) {
        return userRepository.getUsers()
                .stream()
                .anyMatch(isStudentIdAndPasswordAllMatch(
                        userLoginRequest.getStudentId(),
                        userLoginRequest.getPassword())
                );
    }

    private static Predicate<User> isStudentIdAndPasswordAllMatch(String studentId, String password) {
        return savedUser -> isStudentIdMatch(studentId, savedUser) && isPasswordMatch(password, savedUser);
    }

    private static boolean isPasswordMatch(String password, User savedUser) {
        return savedUser.getPassword().equals(password);
    }

    private static boolean isStudentIdMatch(String studentId, User savedUser) {
        return savedUser.getStudentId().equals(studentId);
    }
}
