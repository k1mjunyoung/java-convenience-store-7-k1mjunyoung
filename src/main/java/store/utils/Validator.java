package store.utils;

import java.util.Arrays;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;

public class Validator {
    private static final String REGEX_PATTERN = "\\[[가-힣a-zA-Z]+-[0-9]+\\]";

    public static ErrorType isValidItemInput(String input) {
        try {
            if (StringUtils.isBlank(input)) {
                return ErrorType.INVALID_INPUT_ERROR;
            }

            List<String> itemInputs = Arrays.stream(input.split(Constant.COMMA)).toList();
            if (itemInputs.isEmpty()) {
                return ErrorType.INVALID_INPUT_ERROR;
            }
            if (!checkRegex(itemInputs)) {
                return ErrorType.INVALID_FORMAT_ERROR;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return ErrorType.INVALID_FORMAT_ERROR;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ErrorType.INVALID_INPUT_ERROR;
        }

        return ErrorType.VALID_INPUT;
    }

    private static Boolean checkRegex(List<String> itemInputs) {
        for (String itemInput : itemInputs) {
            if (!itemInput.matches(REGEX_PATTERN)) {
                return false;
            }
        }

        return true;
    }
}
