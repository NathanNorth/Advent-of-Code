package tech.nathann.aoc;

import java.util.List;

public class Day3 {
    public static final List<String> inputLines = Input.getInputLinesWeb(3);

    //4375225
    public static void main(String[] args) {
        System.out.println("Part one result: " + part1());
        System.out.println("Part two result: " + part2());

        System.out.println(part2Max());
        System.out.println(part2Min());
    }

    private static int part1() {
        int[] avgMax = new int[length];
        int[] avgMin = new int[length];
        for(int c = 0; c < length; c++) {
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

        int num1 = Integer.parseInt(toString(avgMax), 2);
        int num2 = Integer.parseInt(toString(avgMin), 2);
        return num1 * num2;
    }

    private static int part2() {
        return part2Min() * part2Max();
    }


    private static final int length = 12;

    private static int part3Max() {
        String returnable = "";
        for(int c = 0; c < length; c++) {
            int numZeros = getZeroes(c, returnable);
            int numOnes = inputLines.size() - numZeros;

            //we have only one left
            if(numZeros == -1) {
                for(String s: inputLines) {
                    if(s.startsWith(returnable)) return Integer.parseInt(s, 2);
                }
            }

            if(numZeros >= numOnes) {
                returnable += "0";
            }
            else {
                returnable += "1";
            }
        }
        return Integer.parseInt(returnable, 2);
    }

    private static int part3Min() {
        String returnable = "";
        for(int c = 0; c < length; c++) {
            int numZeros = getZeroes(c, returnable);
            int numOnes = inputLines.size() - numZeros;

            //we have only one left
            if(numZeros == -1) {
                for(String s: inputLines) {
                    if(s.startsWith(returnable)) return Integer.parseInt(s, 2);
                }
            }

            if(numZeros <= numOnes) {
                returnable += "0";
            }
            else {
                returnable += "1";
            }
        }
        return Integer.parseInt(returnable, 2);
    }

    private static int getZeroes(int c, String filter) {
        int returnable = 0;
        int skipped = 0;
        for(String s: inputLines) {
            if(!s.startsWith(filter)) {
                skipped++;
                continue;
            }

            int num = Integer.parseInt(s.charAt(c) + "");
        }
        //if we have only one value left
        if(skipped == inputLines.size() - 1) return -1;
        else return returnable;
    }


    private static int part2Min() {
        String filter = "";
        String lastVal = "";
        for(int c = 0; c < length; c++) {
            int total0 = 0;
            int total1 = 0;
            int skipped = 0;
            for(int r =  0; r < inputLines.size(); r++) {
                if(!inputLines.get(r).startsWith(filter)){
                    skipped++;
                    continue; //ignore stuff that doesnt mach filter
                }
                lastVal = inputLines.get(r);
                int num = Integer.parseInt(inputLines.get(r).charAt(c) + "");
                if(num ==0) total0++;
                else total1++;
            }
            if(skipped == inputLines.size() - 1) {
                System.out.println("lastval " + lastVal + " filter " + filter);
                return Integer.parseInt(lastVal, 2);
            }
            if(total0 <= total1) {
                filter += "0";
            }
            else {
                filter += "1";
            }
        }
        System.out.println("lastval " + lastVal + " filter " + filter);
        return Integer.parseInt(filter, 2);
    }

    private static int part2Max() {
        String filter = "";
        String lastVal = "";
        for(int c = 0; c < length; c++) {
            int total0 = 0;
            int total1 = 0;
            int skipped = 0;
            for(int r =  0; r < inputLines.size(); r++) {
                if(!inputLines.get(r).startsWith(filter)){
                    skipped++;
                    continue; //ignore stuff that doesnt mach filter
                }
                lastVal = inputLines.get(r);
                int num = Integer.parseInt(inputLines.get(r).charAt(c) + "");
                if(num ==0) total0++;
                else total1++;
            }
            if(skipped == inputLines.size() - 1) {
                System.out.println("lastval " + lastVal + " filter " + filter);
                return Integer.parseInt(lastVal, 2);
            }
            if(total0 > total1) {
                filter += "0";
            }
            else {
                filter += "1";
            }
        }
        System.out.println("lastval " + lastVal + " filter " + filter);
        return Integer.parseInt(filter, 2);
    }

    private static String toString(int[] in) {
        String returnable = "";
        for(int i = 0; i < in.length; i++) {
            returnable += in[i];
        }
        return returnable;
    }
}
