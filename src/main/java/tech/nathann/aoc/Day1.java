package tech.nathann.aoc;

import java.util.List;

public class Day1 {
    public static final List<String> inputLines = Input.getInputLinesWeb(1);

    public static void main(String[] args) {
        System.out.println("Part one result: " + part1());
        System.out.println("Part two result: " + part2());
    }

    private static int part1() {
        int lastNum = Integer.MAX_VALUE; //lol
        int returnable = 0;
        for(String s: inputLines) {
            int val = Integer.parseInt(s);
            if(val > lastNum) {
                returnable++;
            }
            lastNum = val;
        }
        return returnable;
    }

    private static int part2() {
        int lastSum = Integer.MAX_VALUE;
        int returnable = 0;
        for(int i = 0; i < inputLines.size(); i++) {
            //lmao
            try {
               int first = Integer.parseInt(inputLines.get(i));
               int second = Integer.parseInt(inputLines.get(i + 1));
               int third = Integer.parseInt(inputLines.get(i + 2));

               int sum = first + second + third;
               if(sum > lastSum) returnable++;
               lastSum = sum;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return returnable;
    }
}
