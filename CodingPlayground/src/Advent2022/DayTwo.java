package Advent2022;

import java.util.*;
// import java.util.stream.IntStream;
// import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DayTwo {
    public static void main(String[] args) throws IOException {
        String path = "c:\\Users\\cpWhe\\Documents\\GitHub\\CodingProblems\\CodingPlayground\\test-cases\\advent-2022\\two.txt";
        List<String> lines = Files.readAllLines(Paths.get(path));
        partOne(lines);
        partTwo(lines);
    }

    public static void partOne(List<String> lines) {
        int dist = 'A' - 'X';
        int sum = 0;
        for (String l : lines) {
            char[] c = l.replace(" ", "").toCharArray();
            int curr = c[0] - c[1];
            sum += c[1] - 'X' + 1;
            if (curr == dist) sum += 3;
            else if (curr == dist + 2 || curr == dist - 1) sum += 6;
        }
        System.out.println(sum);
    }

    public static void partTwo(List<String> lines) {
        int sum = 0;
        for (String l : lines) {
            char[] c = l.replace(" ", "").toCharArray();
            if (c[1] == 'Z') sum += ((c[0] - 'A' + 3) + 1) % 3 + 1 + 6;
            else if (c[1] == 'Y') sum += (c[0] - 'A') + 1 + 3;
            else sum += ((c[0] - 'A' + 3) - 1) % 3 + 1;
        }
        System.out.println(sum);
    }
}
