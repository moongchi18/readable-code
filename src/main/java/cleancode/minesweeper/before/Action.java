package cleancode.minesweeper.before;

public enum Action {
    OPEN("1"),
    FLAG("2");

    public final String value;

    Action(String value) {
        this.value = value;
    }
}
