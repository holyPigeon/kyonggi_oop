package kyonggi_oop.service;

import kyonggi_oop.domain.user.User;
import kyonggi_oop.domain.user.UserManager;

import java.util.Scanner;
import java.util.function.Predicate;

public class LoginService {
    UserManager userManager;

    public LoginService(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean isRegisteredUser(User user) {
        return userManager.getUsers()
                .stream()
                .anyMatch(isStudentIdAndPasswordAllMatch(user));
    }

    private static Predicate<User> isStudentIdAndPasswordAllMatch(User user) {
        return savedUser -> savedUser.getStudentId().equals(user.getStudentId()) && savedUser.getPassword().equals(user.getPassword());
    }
}
