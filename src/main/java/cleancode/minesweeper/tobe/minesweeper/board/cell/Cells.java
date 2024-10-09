package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Arrays;
import java.util.List;

public class Cells {

    private final List<Cell> cells;

    public Cells(List<Cell> cellList) {
        this.cells = cellList;
    }

    public static Cells of(List<Cell> cellList) {
        return new Cells(cellList);
    }

    public static Cells from(Cell[][] cellArrays){
        List<Cell> cellList = Arrays.stream(cellArrays)
                .flatMap(Arrays::stream)
                .toList();
        return of(cellList);
    }

    public boolean isAllChecked() {
        return cells.stream()
                .allMatch(Cell::isChecked);
    }
}
