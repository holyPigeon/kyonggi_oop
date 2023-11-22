package kyonggi_oop.exception;

public enum ErrorMessage {

    /*
    학번 입력값 검증
     */
    LOGIN_FAILED("[ERROR] 로그인에 실패하였습니다."),
    STUDENT_ID_INPUT_LENGTH_IS_NOT_VALID("[ERROR] 학번 입력값을 9자리이어야 합니다."),
    NOT_EXISTING_USER("[ERROR] 존재하지 않는 사용자입니다."),

    /*
    비밀번호 입력값 검증
     */
    PASSWORD_INPUT_LENGTH_IS_NOT_VALID("[ERROR] 비밀번호 입력값은 4~12자리이어야 합니다."),
    PASSWORD_INPUT_IS_NOT_RIGHT_FORMAT("[ERROR] 비밀번호 입력값은 영어와 숫자를 각각 한 개 이상 포함해야 합니다."),

    PASSWORD_INPUT_REGEX("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$"),

    /*
    메뉴 입력값 검증
     */
    MENU_INPUT_IS_NOT_IN_RANGE("[ERROR] 메뉴 입력값은 1~4 범위의 정수이어야 합니다."),

    /*
    좌석 이용 관련 검증
     */
    SELECTED_ALREADY_USING_SEAT("[ERROR] 다른 사람이 이용중인 좌석입니다."),
    ALREADY_USING_SEAT("[ERROR] 이미 좌석을 이용중입니다."),
    NOT_EXISTING_SEAT("[ERROR] 존재하지 않는 좌석입니다."),
    SEAT_USAGE_DOES_NOT_EXIST("[ERROR] 현재 사용자가 좌석을 이용하고 있지 않습니다."),

    /*
    공통 검증
     */
    INPUT_IS_BLANK("[ERROR] 입력값은 비어있지 않아야 합니다."),
    INPUT_IS_NOT_DIGIT("[ERROR] 입력값은 정수이어야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
