package kyonggi_oop.domain.login.service;

import kyonggi_oop.controller.dto.request.UserLoginRequest;
import kyonggi_oop.domain.user.User;

public interface LoginService {

    User tryLogin(UserLoginRequest userLoginRequest);

    void logout();

    boolean isLoggedIn();

    boolean isRegisteredUser(UserLoginRequest userLoginRequest);
}
