package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;

import java.util.List;

public class CellSignFinder {
    public String findCellSignFrom(CellSnapshot snapshot) {
        List<CellSignProvidable> cellSignProvidables = cellSignProviders();
        return cellSignProvidables.stream()
                .filter(provider -> provider.supports(snapshot))
                .findFirst()
                .map(provider -> provider.provide(snapshot))
                .orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다."));
    }

    private static List<CellSignProvidable> cellSignProviders() {
        return List.of(
                new EmptyCellSignProvider(),
                new FlagCellSignProvider(),
                new LandMineCellSignProvider(),
                new NumberCellSignProvider(),
                new UncheckedCellSignProvider()
        );
    }
}
