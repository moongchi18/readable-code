package cleancode.studycafe.before.io;

import cleancode.studycafe.before.model.pass.StudyCafePass;
import cleancode.studycafe.before.model.pass.StudyCafePassType;
import cleancode.studycafe.before.model.UseLocker;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = readUserInput(); // refactor: 반복되는 부분 메서드로 분리

        return StudyCafePassType.findPassTypeFrom(userInput); // refactor: 이용권 타입 메서드 enum으로 분리
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
        String userInput = readUserInput();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = readUserInput();
        UseLocker selectedUserInput = UseLocker.of(userInput);
        return UseLocker.isUsingLocker(selectedUserInput);
    }

    private static String readUserInput() {
        return SCANNER.nextLine();
    }
}
