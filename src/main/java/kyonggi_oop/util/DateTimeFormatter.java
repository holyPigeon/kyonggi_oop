package kyonggi_oop.util;

import java.time.LocalDateTime;

public class DateTimeFormatter {

    private DateTimeFormatter() {

    }

    public static String format(LocalDateTime localDateTime) {
        return java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
