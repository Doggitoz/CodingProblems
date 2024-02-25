package Advent2022;

import java.util.*;

import java.io.*;


public class DayFour {

    public static void main(String[] args) throws IOException {
        List<String> lines = Util.MyFileReader.ReadFile("advent-2022", "four");
        partOne(lines);
        partTwo(lines);
    }

    static void partOne(List<String> lines) {
        int sum = 0;
        for (String l : lines) {
            String[] split = l.split(",");
            int[] p1 = Arrays.stream(split[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] p2 = Arrays.stream(split[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (checkIfInInterval(p1[0], p2[0], p2[1]) && checkIfInInterval(p1[1], p2[0], p2[1])) sum++; 
            else if (checkIfInInterval(p2[0], p1[0], p1[1]) && checkIfInInterval(p2[1], p1[0], p1[1])) sum++;
        }
        System.out.println(sum);
    }

    static boolean checkIfInInterval(int check, int lower, int upper) {
        return check >= lower && check <= upper;
    }

    static void partTwo(List<String> lines) {
        int sum = 0;
        for (String l : lines) {
            String[] split = l.split(",");
            int[] p1 = Arrays.stream(split[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] p2 = Arrays.stream(split[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (checkIfInInterval(p1[0], p2[0], p2[1]) || checkIfInInterval(p1[1], p2[0], p2[1])) sum++; 
            else if (checkIfInInterval(p2[0], p1[0], p1[1]) || checkIfInInterval(p2[1], p1[0], p1[1])) sum++;
        }
        System.out.println(sum);
    }

}
