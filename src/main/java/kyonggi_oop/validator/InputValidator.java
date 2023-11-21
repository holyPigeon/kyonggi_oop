package kyonggi_oop.validator;

import java.util.regex.Pattern;

public class InputValidator {

    private InputValidator() {

    }

    /*
    학번 입력값 검증
     */
    public static void validateStudentIdInput(String input) {
        validateIsBlank(input);
        validateIsStudentIdInputLengthValid(input);
        validateIsDigit(input);
    }

    private static void validateIsStudentIdInputLengthValid(String input) {
        if (input.length() != 9) {
            throw new IllegalArgumentException("[ERROR] 학번 입력값을 9자리이어야 합니다.");
        }
    }

    /*
    비밀번호 입력값 검증
     */
    public static void validatePasswordInput(String input) {
        validateIsBlank(input);
        validateIsPasswordInputLengthValid(input);
        validateIsPasswordInputRightFormat(input);
    }

    private static void validateIsPasswordInputLengthValid(String input) {
        if (input.length() < 4 || input.length() > 12) {
            throw new IllegalArgumentException("[ERROR] 비밀번호 입력값은 4~12자리이어야 합니다.");
        }
    }

    private static void validateIsPasswordInputRightFormat(String input) {
        if (!isRightFormat(input)) {
            throw new IllegalArgumentException("[ERROR] 비밀번호 입력값은 영어와 숫자를 각각 한 개 이상 포함해야 합니다.");
        }
    }

    private static boolean isRightFormat(String input) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$"; // 영어와 숫자를 각각 한 개 이상 포함한다.
        return Pattern.compile(regex)
                .matcher(input)
                .matches();
    }

    /*
    메뉴 입력값 검증
     */
    public static void validateMenuInput(String input) {
        validateIsBlank(input);
        validateIsDigit(input);
        validateIsMenuInputInRange(input);
    }

    private static void validateIsMenuInputInRange(String input) {
        if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 4) {
            throw new IllegalArgumentException("[ERROR] 메뉴 입력값은 1~4 범위의 정수이어야 합니다.");
        }
    }

    /*
    좌석 번호 입력값 검증
     */
    public static void validateSeatNumberInput(String input) {
        validateIsBlank(input);
        validateIsDigit(input);
    }

    /*
    공통 검증
     */
    private static void validateIsBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값은 비어있지 않아야 합니다.");
        }
    }

    private static void validateIsDigit(String input) {
        if (!isDigit(input)) {
            throw new IllegalArgumentException("[ERROR] 입력값은 정수이어야 합니다.");
        }
    }

    private static boolean isDigit(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
