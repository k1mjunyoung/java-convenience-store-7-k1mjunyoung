package store.utils;

import java.util.Arrays;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;

public class Validator {
    private static final String REGEX_PATTERN = "\\[[가-힣a-zA-Z]+-[0-9]+\\]";
    private static final String REGEX_BRAKETS_WITH_SPACE = "[\\[\\] ]";
    private static final String SPACE = "";
    private static final String DASH = "-";
    private static final String COMMA = ",";
    private static final Integer ITEM_COUNT_INDEX = 1;

    public static ErrorType isValidItemInput(String input) {
        ErrorType errorType = ErrorType.VALID_INPUT;

        try {
            if (StringUtils.isBlank(input)) {
                return ErrorType.INVALID_INPUT_ERROR;
            }

            List<String> itemInputs = Arrays.stream(input.split(COMMA)).toList();

            if (itemInputs.isEmpty()) {
                return ErrorType.INVALID_FORMAT_ERROR;
            }

            for (String itemInput : itemInputs) {
                errorType = checkRegex(itemInput);

                String tempInput = itemInput.replaceAll(REGEX_BRAKETS_WITH_SPACE, SPACE);
                List<String> splitedItemInputs = Arrays.stream(tempInput.split(DASH)).toList();

                Integer.valueOf(splitedItemInputs.get(ITEM_COUNT_INDEX));
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return ErrorType.INVALID_FORMAT_ERROR;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ErrorType.INVALID_INPUT_ERROR;
        }

        return errorType;
    }

    private static ErrorType checkRegex(String input) {
        if (!input.matches(REGEX_PATTERN)) {
            return ErrorType.INVALID_FORMAT_ERROR;
        }

        return ErrorType.VALID_INPUT;
    }
}
