package tech.nathnn.aoc;

import java.util.List;

public class Day2 {
    public static List<String> inputLines = Input.getInputLinesWeb(2);

    public static void main(String[] args) {
        System.out.println("Part one result: " + part1());
        System.out.println("Part two result: " + part2());
    }

    private static int part1() {
        int posX = 0;
        int posY = 0;
        for(String s: inputLines) {
            String[] arr = s.split(" ");
            char dir = arr[0].charAt(0);
            int amount = Integer.parseInt(arr[1]);
            switch (dir) {
                case 'f' -> posX += amount;
                case 'u' -> posY -= amount;
                case 'd' -> posY += amount;
            }
        }
        return posX * posY;
    }

    private static int part2() {
        int posX = 0;
        int posY = 0;
        int aim = 0;
        for(String s: inputLines) {
            String[] arr = s.split(" ");
            char dir = arr[0].charAt(0);
            int amount = Integer.parseInt(arr[1]);
            switch (dir) {
                case 'f' -> {
                    posX += amount;
                    posY += amount * aim;
                }
                case 'u' -> aim -= amount;
                case 'd' -> aim += amount;
            }
        }
        return posX * posY;
    }
}
