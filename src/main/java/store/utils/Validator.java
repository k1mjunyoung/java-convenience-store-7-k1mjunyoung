package store.utils;

import java.util.Arrays;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;

public class Validator {
    private static final String REGEXP_PATTERN = "\\[[가-힣a-zA-Z]+-[0-9]+\\]";
    private static final Integer ITEM_COUNT_INDEX = 1;

    public static ErrorType isValidItemInput(String input) {
        ErrorType errorType = ErrorType.VALID_INPUT;

        try {
            if (StringUtils.isBlank(input)) {
                return ErrorType.INVALID_INPUT_ERROR;
            }

            List<String> itemInputs = Arrays.stream(input.split(",")).toList();

            if (itemInputs.isEmpty()) {
                return ErrorType.INVALID_FORMAT_ERROR;
            }

            for (String itemInput : itemInputs) {
                errorType = checkRegex(itemInput);

                String tempInput = itemInput.replaceAll("[\\[\\] ]", "");
                List<String> splitedItemInputs = Arrays.stream(tempInput.split("-")).toList();

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
        if (!input.matches(REGEXP_PATTERN)) {
            return ErrorType.INVALID_FORMAT_ERROR;
        }

        return ErrorType.VALID_INPUT;
    }
}
