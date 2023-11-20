package kyonggi_oop.service.login;

import kyonggi_oop.domain.user.User;
import kyonggi_oop.repository.user.UserRepository;

import java.util.function.Predicate;

public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static LoginServiceImpl getInstance(UserRepository userRepository) {
        return new LoginServiceImpl(userRepository);
    }

    @Override
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
