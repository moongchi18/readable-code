package cleancode.studycafe.before;


import cleancode.studycafe.before.exception.AppException;
import cleancode.studycafe.before.io.InputHandler;
import cleancode.studycafe.before.io.OutputHandler;
import cleancode.studycafe.before.io.StudyCafeFileHandler;
import cleancode.studycafe.before.model.pass.StudyCafeLockerPass;
import cleancode.studycafe.before.model.pass.StudyCafePass;
import cleancode.studycafe.before.model.pass.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.printInitialMessage(); // refactor: 초기 출력 메시지 병합

            StudyCafePassType selectedPassType = inputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> seatOptions = findSeatOptionsFor(selectedPassType);
            outputHandler.showPassListForSelection(seatOptions);

            StudyCafePass selectedPass = inputHandler.getSelectPass(seatOptions);

            if(StudyCafePassType.isNotAvailableLock(selectedPassType)){
                outputHandler.showPassOrderSummary(selectedPass, null);
                return;
            }

            StudyCafeLockerPass foundLockerOption = findLockOptionFor(selectedPass);
            outputHandler.askLockerPass(foundLockerOption);

            boolean isUsingLocker = inputHandler.getLockerSelection();
            foundLockerOption.updateUsingLocker(isUsingLocker);

            outputHandler.showPassOrderSummary(selectedPass, foundLockerOption);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeLockerPass findLockOptionFor(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(StudyCafeLockerPass.of(null,0,0));
    }

    private static List<StudyCafePass> findSeatOptionsFor(StudyCafePassType selectedPassType) {
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
                            .filter(studyCafePass -> studyCafePass.isSamePassType(selectedPassType))
                            .toList();
    }

}
