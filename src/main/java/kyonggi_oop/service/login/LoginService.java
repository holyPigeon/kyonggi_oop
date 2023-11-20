package kyonggi_oop.service.login;

import kyonggi_oop.domain.user.User;

public interface LoginService {

    boolean isRegisteredUser(User user);
}
