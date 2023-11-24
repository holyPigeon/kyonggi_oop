package kyonggi_oop.domain.user;

import java.util.Arrays;

public enum Role {
    STUDENT("학생"),
    ADMIN("관리자");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public static Role findByName(String name) {
        return Arrays.stream(values())
                .filter(role -> role.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 권한입니다."));
    }

    public String getName() {
        return name;
    }
}
