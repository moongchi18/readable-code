package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.order.StudyCafePassOrder;

import java.util.List;

public class StudyCafeIOHandler {
    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();


    public void showWelcomeMessage() {
        outputHandler.showWelcomeMessage();
    }

    public void showAnnouncement() {
        outputHandler.showAnnouncement();
    }

    public void showPassOrder(StudyCafePassOrder order) {
        outputHandler.showPassOrderSummary(order);
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }

    public StudyCafePassType askPassTypeSelecting() {
        askPassTypeSelection();
        StudyCafePassType studyCafePassType = getPassTypeSelectingUserAction();
        return studyCafePassType;
    }

    private void askPassTypeSelection() {
        outputHandler.askPassTypeSelection();
    }

    private StudyCafePassType getPassTypeSelectingUserAction() {
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafeSeatPass askPassSelecting(List<StudyCafeSeatPass> passCandidates) {
        showPassListForSelection(passCandidates);
        return getSelectPass(passCandidates);
    }

    private void showPassListForSelection(List<StudyCafeSeatPass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
    }

    private StudyCafeSeatPass getSelectPass(List<StudyCafeSeatPass> passCandidates) {
        return inputHandler.getSelectPass(passCandidates);
    }

    public boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate) {
        outputHandler.askLockerPass(lockerPassCandidate);
        return getLockerSelection();
    }

    public boolean getLockerSelection() {
        return inputHandler.getLockerSelection();
    }

}
