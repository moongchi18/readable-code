package cleancode.studycafe.before.model.pass;

import cleancode.studycafe.before.exception.AppException;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권", 1),
    WEEKLY("주 단위 이용권", 2),
    FIXED("1인 고정석", 3);

    private final String description;
    private final int value; // refactor: 이용권 타입별 value 부여

    StudyCafePassType(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public static StudyCafePassType findPassTypeFrom(String userInput) {
        try {
            int passTypeValue = Integer.parseInt(userInput);

            for (StudyCafePassType studyCafePassType : StudyCafePassType.values()) {
                if (studyCafePassType.value == passTypeValue) {
                    return studyCafePassType;
                }
            }
        } catch (Exception e){
            throw new AppException("숫자를 입력해주세요"); // userInput to passTypeValue 변환 실패하는 경우
        }

        throw new AppException("잘못된 입력입니다.");
    }
    public static boolean isHourly(StudyCafePassType studyCafePassType) {
        return studyCafePassType == HOURLY;
    }
    public static boolean isWeekly(StudyCafePassType studyCafePassType) {
        return studyCafePassType == WEEKLY;
    }
    public static boolean isFixed(StudyCafePassType studyCafePassType) {
        return studyCafePassType == FIXED;
    }

    public static boolean isSamePassType(StudyCafePassType first, StudyCafePassType second) {
        return first == second;
    }

    public static boolean isAvailableLock(StudyCafePassType studyCafePassType) {
        return studyCafePassType == FIXED;
    }

    public static boolean isNotAvailableLock(StudyCafePassType studyCafePassType) {
        return !isAvailableLock(studyCafePassType);
    }
}
