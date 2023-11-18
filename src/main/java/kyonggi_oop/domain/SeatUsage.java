package kyonggi_oop.domain;

import kyonggi_oop.domain.seat.Seat;
import kyonggi_oop.domain.user.User;

import java.time.LocalDateTime;

public class SeatUsage {

    private User user;
    private Seat seat;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private SeatUsage(User user, Seat seat) {
        this.user = user;
        this.seat = seat;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusHours(4);
    }

    public static SeatUsage create(User user, Seat seat) {
        return new SeatUsage(user, seat);
    }

    public User getUser() {
        return user;
    }

    public Seat getSeat() {
        return seat;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
