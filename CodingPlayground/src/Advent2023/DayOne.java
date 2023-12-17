package Advent2023;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayOne {
    public static void main(String[] args) {
        List<String> lines = Util.MyFileReader.ReadFile("advent-2023", "one");
        PartOne(lines);
    }

    public static void PartOne(List<String> lines) {
        int sum = 0;
        for (String str : lines) {
            char first = 'f';
            char last = 'f';
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    first = str.charAt(i);
                    break;
                }
            }
            for (int i = str.length() - 1; i >= 0; i--) {
                if (Character.isDigit(str.charAt(i))) {
                    last = str.charAt(i);
                    break;
                }
            }
            sum += Integer.parseInt(Character.toString(first) + Character.toString(last));
        }
        System.out.println(sum);
    }

    public static void PartTwo(List<String> lines) {
        int sum = 0;
        String pattern = "one|two|three|four|five|six|seven|eight|nine";

        for (int i = 0; i < lines.size(); i++) {
            String str = lines.get(i);
            System.out.println(str);
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (c == 't') {
                    str = insert(str, 't', j);
                    j++;
                }

            }
            Matcher matcher = Pattern.compile("")
        }

        System.out.println(sum);
    }

    public static String insert(String str, char c, int pos) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(pos, c);
        return sb.toString();
    }
}
