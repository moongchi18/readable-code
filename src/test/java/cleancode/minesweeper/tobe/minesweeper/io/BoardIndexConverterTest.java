package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BoardIndexConverterTest {

    BoardIndexConverter converter = new BoardIndexConverter();

    @ParameterizedTest
    @CsvSource({
        "a1",
        "z2147483647",
    })
    @DisplayName("사용자 입력을 게임의 숫자좌표로 변환에 성공한다.")
    public void convertSuccess(String userInput) {
        // given

        // when
        int selectedRowIndex = converter.getSelectedRowIndex(userInput);
        int selectedColIndex = converter.getSelectedColIndex(userInput);

        // then
        assertThat(selectedRowIndex).isGreaterThanOrEqualTo(0);
        assertThat(selectedColIndex).isGreaterThanOrEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource(value = {
//        "null", NPE 발생
        "10",
        "!-1",
//        "ㅁa", NumberFormatException 발생
    }, nullValues = "null")
    @DisplayName("사용자 입력을 게임의 숫자좌표로 변환에 실패한다.")
    public void convertError(String userInput) {
        // given

        // when

        // then
        assertThatThrownBy(() -> converter.getSelectedRowIndex(userInput))
            .isInstanceOf(GameException.class)
            .hasMessage("잘못된 입력입니다.");
        assertThatThrownBy(() -> converter.getSelectedColIndex(userInput))
            .isInstanceOf(GameException.class)
            .hasMessage("잘못된 입력입니다.");
    }
}
