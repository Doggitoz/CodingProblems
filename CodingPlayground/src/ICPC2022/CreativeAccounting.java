package ICPC2022;

import java.util.*;
import java.io.*;

public class CreativeAccounting {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int[] profits;
    public static void main(String[] args) throws IOException {
        int[] nhl = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        profits = new int[nhl[0]];

        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;

        for (int i = 0; i < nhl[0]; i++) {
            profits[i] = Integer.parseInt(reader.readLine().trim());
        }
        
        // Where i is the value added to smallest index
        for (int i = 0; i < nhl[2] - nhl[1] + 1; i++) {
            int windowSize = nhl[1] + i;
            for (int j = 0; j < windowSize; j++) {
                int profitable = 0;
                for (int k = 0 - j; k < nhl[0]; k += windowSize) {
                    int sumOfDays = sumDays(k, k + windowSize);
                    if (sumOfDays > 0) profitable++;
                }
                minimum = Math.min(minimum, profitable);
                maximum = Math.max(maximum, profitable);
            }
        }

        System.out.printf("%d %d", minimum, maximum);
    }


    public static int sumDays(int startIndex, int endIndex) {
        int sum = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (i >= profits.length || i < 0) continue;
            sum += profits[i];
        }
        return sum;
    }
}
