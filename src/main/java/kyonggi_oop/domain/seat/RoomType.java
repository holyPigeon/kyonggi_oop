package kyonggi_oop.domain.seat;

public enum RoomType {
    CREATIVE_FACTORY("창의 팩토리"),
    ROOM_1("제 1열람실"),
    ROOM_2("제 2열람실");

    private final String name;

    RoomType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
