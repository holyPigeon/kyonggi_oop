package kyonggi_oop.validator;

import kyonggi_oop.domain.kiosk.SeatUsage;
import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.exception.ErrorMessage;

public class SeatValidator {

    private SeatValidator() {

    }

    public static void validateIsAlreadyUsingSeat(Seat selectedSeat) {
        if (!selectedSeat.isAvailable()) {
            throw new IllegalStateException(ErrorMessage.SELECTED_ALREADY_USING_SEAT.getMessage());
        }
    }

    public static void validateIsUserAlreadyUsingSeat(SeatUsage seatUsage) {
        if (isUsingSeat(seatUsage)) {
            throw new IllegalStateException(ErrorMessage.ALREADY_USING_SEAT.getMessage());
        }
    }

    public static void validateIsSeatUsageExist(SeatUsage seatUsage) {
        if (!isUsingSeat(seatUsage)) {
            throw new IllegalStateException(ErrorMessage.SEAT_USAGE_DOES_NOT_EXIST.getMessage());
        }
    }

    private static boolean isUsingSeat(SeatUsage seatUsage) {
        return seatUsage != null;
    }
}
