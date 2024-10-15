package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.gamelevel.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class GameBoardTest {
    @ParameterizedTest
    @MethodSource("ofGameLevels")
    @DisplayName("게임판 초기화 테스트")
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

    private static Stream<Arguments> ofGameLevels(){
        return Stream.of(
            Arguments.arguments(new Advanced()),
            Arguments.arguments(new Middle()),
            Arguments.arguments(new Beginner()),
            Arguments.arguments(new VeryBeginner())
        );
    }
}
