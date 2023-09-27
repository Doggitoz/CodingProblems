package ICPC2022;

import java.io.*;
import java.util.*;

public class champernownecount {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        long[] parts = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long remainder = 1;
        if(parts[1] == 1) {
            System.out.println(parts[0]);
            return;
        }
        long counter = 0;
        for (int i = 2; i <= parts[0]; i++) {
            remainder *= Math.pow(10, Integer.toString(i).length());
            remainder += i;
            remainder = remainder % parts[1];
            if (remainder == 0) counter++;
        }
        System.out.println(counter);
    }
}