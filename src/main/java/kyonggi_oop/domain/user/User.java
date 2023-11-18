package kyonggi_oop.domain.user;

import kyonggi_oop.domain.seat.Seat;

public class User {

    private final String studentId;
    private final String password;
    private Seat seat;

    public User(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public void useSeat(Seat seat) {
        this.seat = seat;
    }

    public Seat returnSeat() {
        Seat usedSeat = getSeat();
        seat = null;

        return usedSeat;
    }

    public boolean isUsingSeat() {
        return seat != null;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public Seat getSeat() {
        if (!isUsingSeat()) {
            throw new IllegalStateException("사용자가 좌석을 사용하고 있지 않습니다.");
        }
        return seat;
    }
}
