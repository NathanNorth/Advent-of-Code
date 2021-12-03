package tech.nathnn.aoc;

import java.util.List;

public class Day3 {
    public static final List<String> inputLines = Input.getInputLinesWeb(3);

    public static void main(String[] args) {
        System.out.println("Part one result: " + part1());
        System.out.println("Part two result: " + part2());
    }

    private static int part1() {
        int[] avgMax = new int[12];
        int[] avgMin = new int[12];
        for(int c = 0; c < 12; c++) {
            int total0 = 0;
            int total1 = 0;
            for(int r =  0; r < inputLines.size(); r++) {
                int num = Integer.parseInt(inputLines.get(r).charAt(c) + "");
                if(num ==0) total0++;
                else total1++;
            }
            if(total0 > total1) {
                avgMax[c] = 0;
                avgMin[c] = 1;
            }
            else {
                avgMax[c] = 1;
                avgMin[c] = 0;
            }
        }

        int num1 =Integer.parseInt(toString(avgMax), 2);
        int num2 =Integer.parseInt(toString(avgMin), 2);
        return num1 * num2;
    }

    private static int part2() {
        int[] avgMax = new int[12];
        int[] avgMin = new int[12];
        for(int c = 0; c < 12; c++) {
            int total0 = 0;
            int total1 = 0;
            for(int r =  0; r < inputLines.size(); r++) {
                int num = Integer.parseInt(inputLines.get(r).charAt(c) + "");
                if(num ==0) total0++;
                else total1++;
            }
            if(total0 > total1) {
                avgMax[c] = 0;
                avgMin[c] = 1;
            }
            else {
                avgMax[c] = 1;
                avgMin[c] = 0;
            }
        }

        int num1 =Integer.parseInt(toString(avgMax), 2);
        int num2 =Integer.parseInt(toString(avgMin), 2);
        return num1 * num2;
    }

    private static String toString(int[] in) {
        String returnable = "";
        for(int i = 0; i < in.length; i++) {
            returnable += in[i];
        }
        return returnable;
    }
}
