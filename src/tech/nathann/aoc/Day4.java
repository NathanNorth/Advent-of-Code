package tech.nathann.aoc;

import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static final List<String> inputLines = Input.getInputLinesWeb(4);

    public static void main(String[] args) {
        System.out.println("Part one" +  partOne());
        System.out.println(partTwo());
    }

    private static int partOne() {
        List<Board> boards = new ArrayList<>();
        for(int i = 2; i <inputLines.size(); i += 6) {
            boards.add(new Board(inputLines, i));
        }

        String[] numbers = inputLines.get(0).split(",");
        for(String num: numbers) {
            for(Board b: boards) {
                b.addNum(Integer.parseInt(num));
            }

            for(Board b: boards) {
                if(b.isBingo()) return b.score(Integer.parseInt(num));
            }

        }

        return -1;
    }

    private static int partTwo() {
        List<Board> boards = new ArrayList<>();
        for(int i = 2; i <inputLines.size(); i += 6) {
            boards.add(new Board(inputLines, i));
        }

        boolean[] hasWon = new boolean[100];
        String[] numbers = inputLines.get(0).split(",");
        int lastGuy = -1;
        for(String num: numbers) {
            for(Board b: boards) {
                b.addNum(Integer.parseInt(num));
            }

            for(int i = 0; i < 100; i++) {
                if(boards.get(i).isBingo()) {
                    hasWon[i] = true;
                    if(i == lastGuy)
                        return boards.get(i).score(Integer.parseInt(num));
                }
            }
            if(countWins(hasWon) == 99) {
                for(int i = 0; i < 100; i++) {
                    if(!hasWon[i]) lastGuy = i;
                }
            }

        }

        return -1;
    }

    public static int countWins(boolean[] win) {
        int count = 0;
        for(int i = 0; i < win.length; i++) {
            if(win[i]) count++;
        }
        return count;
    }
}
class Board {
    int[][] nums = new int[5][5];
    boolean[][] selected = new boolean[5][5];

    public Board(List<String> in, int startAt) {
        for(int r = startAt; r < startAt + 5; r++) {
            String parsable = in.get(r).replace("  ", " ");
            if(parsable.charAt(0) == ' ') parsable = parsable.substring(1);
            String[] digits = parsable.split(" ");
            for(int c = 0; c < digits.length; c++) {
                nums[r - startAt][c] = Integer.parseInt(digits[c]);
            }
        }
        System.out.println("Array finished!");
    }

    public void addNum(int num) {
        for(int r = 0; r < 5; r++) {
            for(int c = 0; c < 5; c++) {
                if(nums[r][c] == num) {
                    selected[r][c] = true;
                }
            }
        }
    }

    public boolean isBingo() {
        for(int r = 0; r < 5; r++) {
            if(rowIsBingo(r)) return true;
        }
        for(int c = 0; c < 5; c++) {
            if(colIsBingo(c)) return true;
        }
        return false;
    }

    private boolean rowIsBingo(int row) {
        boolean returnable = true;
        for(int c = 0; c < 5; c++) {
            returnable = returnable && selected[row][c];
        }
        return returnable;
    }

    private boolean colIsBingo(int col) {
        boolean returnable = true;
        for(int r = 0; r < 5; r++) {
            returnable = returnable && selected[r][col];
        }
        return returnable;
    }

    public int score(int finalNum) {
        int sumUnmarked = 0;
        for(int r = 0; r < 5; r++) {
            for(int c = 0; c < 5; c++) {
                if(!selected[r][c]) {
                    sumUnmarked += nums[r][c];
                }
            }
        }
        return sumUnmarked * finalNum;
    }
}