package Advent2022;

import java.util.*;
// import java.util.stream.IntStream;
// import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DayOne {
    public static void main(String[] args) throws IOException {
        String path = "c:\\Users\\cpWhe\\Documents\\GitHub\\CodingProblems\\CodingPlayground\\test-cases\\advent-2022\\one.txt";
        // Cool arrays stream thing but it wasnt working
        // System.out.println(Paths.get(path));
        // OptionalInt max= Arrays.stream(Files.readString(Paths.get(path))
        //     .split("\n\n"))
        //     .mapToInt(s -> {
        //         return Arrays.stream(s.split("\n"))
        //             .filter(st -> st.equals(""))
        //             .map(String::trim)
        //             .mapToInt(Integer::parseInt)
        //             .sum();
        //         })
        //     .max();
        // System.out.println(max.getAsInt());
        List<String> lines = Files.readAllLines(Paths.get(path));
        partOne(lines);
        partTwo(lines);
    }

    static void partOne(List<String> lines) {
        int max = 0;
        int sum = 0;
        for (String l : lines) {
            if (l.equals("")) {sum = 0; continue;}
            sum += Integer.parseInt(l.trim());
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }

    static void partTwo(List<String> lines) {
        PriorityQueue<Integer> elves = new PriorityQueue<>();
        int sum = 0;
        for (String l : lines) {
            if (l.equals("")) {
                elves.add(-1 * sum);
                sum = 0; 
                continue;
            }
            sum += Integer.parseInt(l.trim());
        }
        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += elves.poll() * -1;
        }
        System.out.println(sum);
    }
}
