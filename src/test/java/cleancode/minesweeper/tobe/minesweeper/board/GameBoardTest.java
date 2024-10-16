package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GameBoardTest {
    @ParameterizedTest
    @MethodSource("ofGameLevels")
    @DisplayName("게임을 시작하면 게임레벨에 맞춰 게임보드를 초기화한다.")
    void initializeGame(GameLevel gameLevel) {
        // given
        GameBoard gameBoard = new GameBoard(gameLevel);

        // when
        gameBoard.initializeGame();

        // then
        System.out.println(gameLevel.getClass().getSimpleName()
            + " : rowSize : " + gameLevel.getRowSize()
            + ", colSize : " + gameLevel.getColSize()
            + ", mineCount : " + gameLevel.getLandMineCount()
        );
        assertThat(gameLevel.getRowSize()).isEqualTo(gameBoard.getRowSize());
        assertThat(gameLevel.getColSize()).isEqualTo(gameBoard.getColSize());
        assertThat(gameBoard.isInProgress()).isTrue();
//        @TODO gameLevel, gameBoard의 mine 개수 일치 확인
//        @TODO gameBoard의 board에서 mine 업데이트 확인
//        @TODO gameBoard의 board에서 numberCell 업데이트 확인
    }

    @ParameterizedTest
    @MethodSource("ofGameLevels")
    @DisplayName("선택된 cellPosition의 row와 col 유효성을 검사한다.")
    void isInvalidCellPosition(GameLevel gameLevel) {
        // given
        GameBoard gameBoard = new GameBoard(gameLevel);
        gameBoard.initializeGame();

        int levelRowSize = gameLevel.getRowSize();
        int levelColSize = gameLevel.getColSize();

        CellPosition cellPosition = CellPosition.of(levelRowSize - 1, levelColSize - 1);
        CellPosition invalidRowPosition = CellPosition.of(levelRowSize - 1, levelColSize);
        CellPosition invalidColPosition = CellPosition.of(levelRowSize, levelColSize - 1);
        CellPosition invalidPosition = CellPosition.of(levelRowSize, levelColSize);

        // when
        boolean isValidPosition = gameBoard.isInvalidCellPosition(cellPosition);
        boolean isInvalidRowPosition = gameBoard.isInvalidCellPosition(invalidRowPosition);
        boolean isInvalidColPosition = gameBoard.isInvalidCellPosition(invalidColPosition);
        boolean isInvalidPosition = gameBoard.isInvalidCellPosition(invalidPosition);

        // then
        System.out.println(gameBoard.getRowSize() + " " + gameBoard.getColSize());
        System.out.println(cellPosition.getRowIndex() + ", " + cellPosition.getColIndex());
        assertThat(isValidPosition).isFalse();
        assertThat(isInvalidRowPosition).isTrue();
        assertThat(isInvalidColPosition).isTrue();
        assertThat(isInvalidPosition).isTrue();

        assertThat(CellPosition.of(0,0)).isNotNull();
        assertThatThrownBy(() -> CellPosition.of(-1, 0))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CellPosition.of(0, -1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CellPosition.of(-1, -1))
            .isInstanceOf(IllegalArgumentException.class);
    }



//  ==================== private =======================
    private static Stream<Arguments> ofGameLevels(){
        return of(
            arguments(new Advanced()),
            arguments(new Middle()),
            arguments(new Beginner()),
            arguments(new VeryBeginner())
        );
    }
}
