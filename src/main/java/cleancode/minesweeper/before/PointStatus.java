package cleancode.minesweeper.before;

public enum PointStatus {
    FLAG("⚑"),
    OPEN_MINE_NOT_FOUND("■"),
//    OPEN_AROUND_MINE_COUNT(""),
    CLOSE("□"),
    MINE("☼");

    public final String symbol;

    PointStatus(String symbol) {
        this.symbol = symbol;
    }
}
