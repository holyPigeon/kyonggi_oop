package kyonggi_oop.controller.dto.response;

import kyonggi_oop.domain.kiosk.SeatUsage;
import kyonggi_oop.domain.user.User;

import java.time.LocalDateTime;

public class UserStatusResponse {

    private final String studentId;
    private final boolean isUsingSeat;
    private int seatNumber;
    private LocalDateTime seatUsageStartTime;
    private LocalDateTime seatUsageEndTime;

    private UserStatusResponse(User user, boolean isUsingSeat, SeatUsage seatUsage) {
        this.studentId = user.getStudentId();
        this.isUsingSeat = isUsingSeat;
        if (isUsingSeat) {
            this.seatNumber = seatUsage.getSeat().getNumber();
            this.seatUsageStartTime = seatUsage.getStartTime();
            this.seatUsageEndTime = seatUsage.getEndTime();
        }
    }

    public static UserStatusResponse of(User user, boolean isUsingSeat, SeatUsage seatUsage) {
        return new UserStatusResponse(user, isUsingSeat, seatUsage);
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean isUsingSeat() {
        return isUsingSeat;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public LocalDateTime getSeatUsageStartTime() {
        return seatUsageStartTime;
    }

    public LocalDateTime getSeatUsageEndTime() {
        return seatUsageEndTime;
    }
}
