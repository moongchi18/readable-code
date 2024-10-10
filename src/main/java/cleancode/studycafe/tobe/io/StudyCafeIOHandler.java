package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

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

    public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        outputHandler.showPassOrderSummary(selectedPass, lockerPass);
    }

    public void showPassOrderSummary(StudyCafePass selectedPass) {
        outputHandler.showPassOrderSummary(selectedPass);
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

    public StudyCafePass askPassSelecting(List<StudyCafePass> passCandidates) {
        showPassListForSelection(passCandidates);
        return getSelectPass(passCandidates);
    }

    private void showPassListForSelection(List<StudyCafePass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
    }

    private StudyCafePass getSelectPass(List<StudyCafePass> passCandidates) {
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
