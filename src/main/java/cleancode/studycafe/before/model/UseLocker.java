package cleancode.studycafe.before.model;

import java.util.stream.Stream;

public enum UseLocker {

    YES(1),
    NO(2),
    NOT_FOUND(-1)
    ;

    private final int value;

    UseLocker(int value) {
        this.value = value;
    }
    public static UseLocker of(String userInput) {
        int userInputValue = Integer.parseInt(userInput);
        return Stream.of(values())
                .filter(val  -> val.value == userInputValue)
                .findFirst()
                .orElse(NOT_FOUND);
    }

    public static boolean isUsingLocker(UseLocker useLocker) {
        return useLocker == YES;
    }
}
