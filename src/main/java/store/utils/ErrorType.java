package store.utils;

public enum ErrorType {
    //input
    VALID_INPUT(null),
    INVALID_INPUT_ERROR("잘못된 입력입니다. 다시 입력해 주세요."),
    INVALID_FORMAT_ERROR("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.")
    ;

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
