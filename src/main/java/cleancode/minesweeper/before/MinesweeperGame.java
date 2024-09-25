package cleancode.minesweeper.before;

import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {

    private static String[][] board = new String[8][10]; // 출력되는 게임판
    private static Integer[][] landMineCounts = new Integer[8][10]; // 해당 위치 주위의 지뢰 개수
    private static boolean[][] landMines = new boolean[8][10]; // 지뢰 여부
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Scanner scanner = new Scanner(System.in);

        // 게임판 초기화
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = "□";
            }
        }

//      지뢰 위치 설정
        for (int i = 0; i < 10; i++) {
            int col = new Random().nextInt(10);
            int row = new Random().nextInt(8);
            landMines[row][col] = true;
        }

//      주위 지뢰 개수 count
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                int count = 0;
                if (!landMines[i][j]) {
                    if (i - 1 >= 0 && j - 1 >= 0 && landMines[i - 1][j - 1]) {
                        count++;
                    }
                    if (i - 1 >= 0 && landMines[i - 1][j]) {
                        count++;
                    }
                    if (i - 1 >= 0 && j + 1 < 10 && landMines[i - 1][j + 1]) {
                        count++;
                    }
                    if (j - 1 >= 0 && landMines[i][j - 1]) {
                        count++;
                    }
                    if (j + 1 < 10 && landMines[i][j + 1]) {
                        count++;
                    }
                    if (i + 1 < 8 && j - 1 >= 0 && landMines[i + 1][j - 1]) {
                        count++;
                    }
                    if (i + 1 < 8 && landMines[i + 1][j]) {
                        count++;
                    }
                    if (i + 1 < 8 && j + 1 < 10 && landMines[i + 1][j + 1]) {
                        count++;
                    }
                    landMineCounts[i][j] = count;
                    continue;
                }
                landMineCounts[i][j] = 0;
            }
        }

//      게임시작
        while (true) {
//          턴 종료 후 게임판 다시 출력
            System.out.println("   a b c d e f g h i j");
            for (int i = 0; i < 8; i++) {
                System.out.printf("%d  ", i + 1);
                for (int j = 0; j < 10; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }

//          게임 종료 체크
//          @FIXME 승리조건 개선 필요, 지뢰 갯수와 깃발 갯수가 일치하고 지뢰에 깃발이 꽂혀야 승리로 판단 필요
            if (gameStatus == 1) {
                System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                break;
            }
            if (gameStatus == -1) {
                System.out.println("지뢰를 밟았습니다. GAME OVER!");
                break;
            }

//          새로운 턴 시작
            System.out.println();
            System.out.println("선택할 좌표를 입력하세요. (예: a1)");
            String input = scanner.nextLine();
            System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
            String input2 = scanner.nextLine();
            char c = input.charAt(0);
            char r = input.charAt(1);
            int col;

//          열 위치 문자를 숫자로 변환
            switch (c) {
                case 'a':
                    col = 0;
                    break;
                case 'b':
                    col = 1;
                    break;
                case 'c':
                    col = 2;
                    break;
                case 'd':
                    col = 3;
                    break;
                case 'e':
                    col = 4;
                    break;
                case 'f':
                    col = 5;
                    break;
                case 'g':
                    col = 6;
                    break;
                case 'h':
                    col = 7;
                    break;
                case 'i':
                    col = 8;
                    break;
                case 'j':
                    col = 9;
                    break;
                default:
                    col = -1; // @FIXME -1인 경우 예외 처리가 없는듯
                    break;
            }

//          행 숫자(1~) 보정(0~)
            int row = Character.getNumericValue(r) - 1;

//          @TODO row, col 검증 조건 추가 필요

//          깃발꽂기 처리
            if (input2.equals("2")) {
                board[row][col] = "⚑";
                boolean open = true; // 메서드 명과 같아서 혼동
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 10; j++) {
//                      게임판 전체 확인 완료 여부
//                      게임판 전체에 깃발 꽂으면 클리어?
                        if (board[i][j].equals("□")) {
                            open = false;
                        }
                    }
                }
                if (open) {
                    gameStatus = 1;
                }

//          오픈 행위 처리
            } else if (input2.equals("1")) {

//              지뢰인 경우
                if (landMines[row][col]) {
                    board[row][col] = "☼";
                    gameStatus = -1;
                    continue;
                } else {
//                  해당 위치 및 주변에 지뢰가 아닌 필드 열기
                    open(row, col);
                }
                boolean open = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (board[i][j].equals("□")) {
                            open = false;
                        }
                    }
                }
//              모든 필드를 오픈했고, 지뢰 필드를 열어서 실패하지 않았으므로 승리
                if (open) {
                    gameStatus = 1;
                }
            } else {
                System.out.println("잘못된 번호를 선택하셨습니다.");
            }
        }
    }

    private static void open(int row, int col) {
//      파라미터 검증
        if (row < 0 || row >= 8 || col < 0 || col >= 10) {
            return;
        }

//      유효성 검증
        if (!board[row][col].equals("□")) {
            return;
        }
        if (landMines[row][col]) {
            return;
        }

//      주변 지뢰 개수가 0이 아닌 경우 숫자 표시
        if (landMineCounts[row][col] != 0) {
            board[row][col] = String.valueOf(landMineCounts[row][col]);
            return;

//      주변 지뢰 개수가 0인 경우 확인 완료 표시
        } else {
            board[row][col] = "■";
        }

//      주변에 지뢰가 아닌 경우 함께 오픈됨
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

}
