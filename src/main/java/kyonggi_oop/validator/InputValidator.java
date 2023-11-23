package kyonggi_oop.validator;

import kyonggi_oop.exception.ErrorMessage;

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
            throw new IllegalArgumentException(ErrorMessage.STUDENT_ID_INPUT_LENGTH_IS_NOT_VALID.getMessage());
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
            throw new IllegalArgumentException(ErrorMessage.PASSWORD_INPUT_LENGTH_IS_NOT_VALID.getMessage());
        }
    }

    private static void validateIsPasswordInputRightFormat(String input) {
        if (!isRightFormat(input)) {
            throw new IllegalArgumentException(ErrorMessage.PASSWORD_INPUT_IS_NOT_RIGHT_FORMAT.getMessage());
        }
    }

    private static boolean isRightFormat(String input) {
        String regex = ErrorMessage.PASSWORD_INPUT_REGEX.getRegEx(); // 영어와 숫자를 각각 한 개 이상 포함한다.
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
            throw new IllegalArgumentException(ErrorMessage.MENU_INPUT_IS_NOT_IN_RANGE.getMessage());
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
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_BLANK.getMessage());
        }
    }

    private static void validateIsDigit(String input) {
        if (!isDigit(input)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_NOT_DIGIT.getMessage());
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
