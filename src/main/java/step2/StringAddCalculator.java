package step2;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    public static final String SEPARATOR = ",|:";
    public static final String CUSTOM = "//(.)\n(.*)";
    public static final int CUSTOMSEPARATOR = 1;
    public static final int VALUE = 2;
    public static final Pattern PATTERN = Pattern.compile(CUSTOM);


    static int splitAndSum(String param) {
        if (param == null || param.isEmpty()) {
            return 0;
        }

        if (param.contains("//") && param.contains("\n")) {
            return customSeparator(param);
        }

        return separator(param);
    }

    static int separator(String param) {
        return validation(param.split(SEPARATOR));
    }

    static int customSeparator(String param) {
        int result = 0;
        Matcher matcher = PATTERN.matcher(param);
        if (matcher.find()) {
            String customDelimiter = matcher.group(CUSTOMSEPARATOR);
            String[] tokens = matcher.group(VALUE).split(customDelimiter);
            result = validation(tokens);
        }
        return result;
    }

    static int validation(String[] splitParam) {
        Arrays.stream(splitParam).filter(s -> Integer.parseInt(s) < 0).forEachOrdered(s -> {
            throw new RuntimeException("음수는 지원이 되지 않습니다.");
        });
        return sum(splitParam);
    }

    static int sum(String[] splitParam) {
        return Arrays.stream(splitParam).mapToInt(Integer::parseInt).sum();
    }
}
