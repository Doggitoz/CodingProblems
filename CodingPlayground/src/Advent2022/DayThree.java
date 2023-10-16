package Advent2022;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class DayThree {

    public static void main(String[] args) throws IOException {
        String path = "c:\\Users\\cpWhe\\Documents\\GitHub\\CodingProblems\\CodingPlayground\\test-cases\\advent-2022\\three.txt";
        List<String> lines = Files.readAllLines(Paths.get(path));
        partOne(lines);
        partTwo(lines);
    }

    static void partOne(List<String> lines) {
        int sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            boolean[] visited = new boolean[126];
            String line = lines.get(i);
            char[] items = line.toCharArray();
            for (int j = 0; j < items.length/2; j++) {
                visited[items[j]] = true;
            }
            for (int j = items.length/2; j < items.length; j++) {
                if (visited[items[j]] == true) {
                    sum += items[j] > 96 ? items[j] - 'a' + 1 : items[j] - 'A' + 27;
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    static void partTwo(List<String> lines) {
        int sum = 0;
        for (int i = 0; i < lines.size()/3; i++) {
            int[] counter = new int[126];
            for (int j = 0; j < 3; j++) {
                boolean[] visited = new boolean[126];
                String line = lines.get(i*3 + j);
                for (Character c : line.toCharArray()) {
                    if (!visited[c]) {
                        counter[c]++;
                    }
                    visited[c] = true;
                }
            }
            for (int j = 0; j < 126; j++) {
                if (counter[j] == 3) {
                    sum += priorityFromItem((char)j);
                }
            }
        }
        System.out.println(sum);
    }

    static int priorityFromItem(char item) {
        return item > 96 ? item - 'a' + 1 : item - 'A' + 27;
    }
}
