package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellsTest {
    @ParameterizedTest
    @MethodSource("ofCheckedCells")
    @DisplayName("주어진 Cells가 모두 체크(열림 또는 깃발)된 상태임을 확인한다.(모두 체크된 상태)")
    void isAllCheckedTrue(List<Cell> list) {
        // @TODO isAllChecked 메서드는 단순히 체크하는게 아니라 게임 승리 조건 판단으로 보임
        // given
        Cells cells = Cells.of(list);

        // when
        boolean isChecked = cells.isAllChecked();

        // then
        assertThat(isChecked).isTrue();
    }

    @ParameterizedTest
    @MethodSource("ofCells")
    @DisplayName("주어진 Cells가 모두 체크(열림 또는 깃발)된 상태임을 확인한다.(일부 또는 전체가 체크되지 않은 상태)")
    void isAllCheckedFalse(List<Cell> list) {
        // @TODO isAllChecked 메서드는 단순히 체크하는게 아니라 게임 승리 조건 판단으로 보임
        // given
        Cells cells = Cells.of(list);

        // when
        boolean isChecked = cells.isAllChecked();

        // then
        assertThat(isChecked).isFalse();
    }

//  ================= private =====================
private static Stream<Arguments> ofCheckedCells(){
//      isChecked 메서드가 변경되면 아래 내용도 변경되어야함
    EmptyCell checkedEmptyCell = new EmptyCell();
    checkedEmptyCell.open();
    LandMineCell checkedLandMineCell = new LandMineCell();
    checkedLandMineCell.flag();
    NumberCell checkedNumberCell = new NumberCell(1);
    checkedNumberCell.open();

    return Stream.of(
        Arguments.arguments(List.of(checkedEmptyCell)),
        Arguments.arguments(List.of(checkedLandMineCell)),
        Arguments.arguments(List.of(checkedNumberCell)),
        Arguments.arguments(List.of(checkedEmptyCell, checkedLandMineCell)),
        Arguments.arguments(List.of(checkedEmptyCell, checkedNumberCell)),
        Arguments.arguments(List.of(checkedLandMineCell, checkedNumberCell)),
        Arguments.arguments(List.of(checkedEmptyCell, checkedLandMineCell, checkedNumberCell))
    );
}

    private static Stream<Arguments> ofCells(){
        EmptyCell emptyCell = new EmptyCell();
        LandMineCell landMineCell = new LandMineCell();
        NumberCell numberCell = new NumberCell(1);

//      isChecked 메서드가 변경되면 아래 내용도 변경되어야함
        EmptyCell checkedEmptyCell = new EmptyCell();
        checkedEmptyCell.open();
        LandMineCell checkedLandMineCell = new LandMineCell();
        checkedLandMineCell.flag();
        NumberCell checkedNumberCell = new NumberCell(1);
        checkedNumberCell.open();

        return Stream.of(
            Arguments.arguments(List.of(emptyCell)),
            Arguments.arguments(List.of(landMineCell)),
            Arguments.arguments(List.of(numberCell)),
            Arguments.arguments(List.of(emptyCell, landMineCell, numberCell, checkedEmptyCell, checkedLandMineCell, checkedLandMineCell))
        );
    }
}
