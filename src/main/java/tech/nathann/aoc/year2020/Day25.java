package tech.nathann.aoc.year2020;

import tech.nathann.aoc.Input;

import java.util.List;

public class Day25 {
    public static final List<String> inputLines = Input.getInputLinesWeb(25, 2020);

    public static void main(String[] args) {
        System.out.println("Part one result: " + part1());
    }

    private static int part1() {
        int key1 = Integer.parseInt(inputLines.get(0));
        int key2 = Integer.parseInt(inputLines.get(1));

        int num1 = getCount(key1);
        int num2 = getCount(key2);

        System.out.println(num1);
        System.out.println(num2);

        int key2Trans = 1;
        for(int i = 0; i < num1; i++) {
            key2Trans = key2Trans * num1;
            key2Trans = key2Trans % 20201227;
        }

        for(int i = 0; i < num2; i++) {
            key1 = key1 * subject;
            key1 = key1 % 20201227;
        }

        System.out.println(key1);
        System.out.println(key2);

        return key2;
    }

    static final int subject = 7;
    private static int getCount(int publicKey) {
        int returnable = 0;
        int mod = 1;
        while(mod != publicKey) {
            mod = mod * subject;
            mod = mod % 20201227;
            returnable++;
        }
        return returnable;
    }
}
