package kyonggi_oop.dto.response;

import kyonggi_oop.domain.SeatUsage;
import kyonggi_oop.domain.user.User;

import java.time.LocalDateTime;

public class UserStatusResponse {

    private String studentId;
    private int seatNumber;

    private LocalDateTime seatUsageStartTime;

    private LocalDateTime seatUsageEndTime;

    private UserStatusResponse(User user, SeatUsage seatUsage) {
        this.studentId = user.getStudentId();
        this.seatNumber = seatUsage.getSeat().getNumber();
        this.seatUsageStartTime = seatUsage.getStartTime();
        this.seatUsageEndTime = seatUsage.getEndTime();
    }

    public static UserStatusResponse of(User user, SeatUsage seatUsage) {
        return new UserStatusResponse(user, seatUsage);
    }

    public String getStudentId() {
        return studentId;
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
