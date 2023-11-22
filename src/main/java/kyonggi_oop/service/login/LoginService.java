package kyonggi_oop.service.login;

import kyonggi_oop.domain.user.User;

public interface LoginService {

    void login();

    void logout();

    boolean isLoggedIn();

    boolean isRegisteredUser(User user);
}
