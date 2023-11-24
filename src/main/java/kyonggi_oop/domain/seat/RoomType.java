package kyonggi_oop.domain.seat;

import java.util.Arrays;

public enum RoomType {
    CREATIVE_FACTORY("창의 팩토리"),
    ROOM_1("제1열람실"),
    ROOM_2("제2열람실");

    private final String name;

    RoomType(String name) {
        this.name = name;
    }

    public static RoomType findByName(String name) {
        return Arrays.stream(values())
                .filter(room -> room.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 열람실입니다."));
    }

    public String getName() {
        return name;
    }
}
