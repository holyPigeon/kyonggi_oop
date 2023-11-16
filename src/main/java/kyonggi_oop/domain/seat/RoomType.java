package kyonggi_oop.domain.seat;

import java.util.Arrays;

public enum RoomType {
    CREATIVE_FACTORY("창의 팩토리"),
    ROOM_1("제 1열람실"),
    ROOM_2("제 2열람실"),
    NONE("없음");

    private final String name;

    RoomType(String name) {
        this.name = name;
    }

    public static RoomType findByName(String name) {
        return Arrays.stream(values())
                .filter(room -> room.name.equals(name))
                .findAny()
                .orElse(RoomType.NONE);
    }

    public String getName() {
        return name;
    }
}
