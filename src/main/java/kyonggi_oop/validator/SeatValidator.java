package kyonggi_oop.validator;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.seat.Seat;

public class SeatValidator {

    private SeatValidator() {

    }

    public static void validateWhenGettingSeat(SeatUsage seatUsage) {
        validateIsUserNotUsingSeat(seatUsage);
    }

    public static void validateWhenUsingSeat(SeatUsage seatUsage, Seat selectedSeat) {
        validateIsUserAlreadyUsingSeat(seatUsage);
        validateIsAlreadyUsingSeat(selectedSeat);
    }

    public static void validateWhenChangingSeat(Seat selectedSeat) {
        validateIsAlreadyUsingSeat(selectedSeat);
    }

    public static void validateWhenReturningSeat(SeatUsage seatUsage) {
        validateIsUserNotUsingSeat(seatUsage);
    }

    private static void validateIsAlreadyUsingSeat(Seat selectedSeat) {
        if (!selectedSeat.isAvailable()) {
            throw new IllegalStateException("[ERROR] 다른 사람이 이용중인 좌석입니다.");
        }
    }

    private static void validateIsUserAlreadyUsingSeat(SeatUsage seatUsage) {
        if (isUsingSeat(seatUsage)) {
            throw new IllegalStateException("[ERROR] 이미 좌석을 이용중입니다.");
        }
    }

    private static void validateIsUserNotUsingSeat(SeatUsage seatUsage) {
        if (!isUsingSeat(seatUsage)) {
            throw new IllegalStateException("[ERROR] 현재 좌석을 이용하고 있지 않습니다.");
        }
    }


    private static boolean isUsingSeat(SeatUsage seatUsage) {
        return seatUsage != null;
    }
}
