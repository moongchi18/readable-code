package cleancode.minesweeper.tobe.minesweeper.board.cell;

public interface Cell {

    boolean isLandMine();

    boolean hasLandMineCount();

    CellSnapshot getSnapshot();

    public void flag();

    public void open();

    public boolean isChecked();


    public boolean isOpened();
}
