package ICPC2022;

import java.util.*;
import java.io.*;

public class CreativeAccounting {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int[] profits;
    static int[][] dp;
    static int[] nhl;
    public static void main(String[] args) throws IOException {
        nhl = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        profits = new int[nhl[0]];

        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;

        for (int i = 0; i < nhl[0]; i++) {
            profits[i] = Integer.parseInt(reader.readLine().trim());
        }

        // For indexing dp array:
        // Row - row 0 = Min Window Size, so curr window size - min window size
        // Col - col 0 = Sum where the largest window would only incorporate
        // the first element. So day 0 = max window size - 1
        dp = new int[nhl[2] - nhl[1] + 1][nhl[0] + nhl[2] - 1];

        if (nhl[1] == nhl[2]) {
            dp[0][0] = profits[0];
        }
        else {
            dp[0][0] = 0;
        }

        // Construct first row of DP
        for (int i = 1 - nhl[2] + 1; i < nhl[0]; i++) {
            int index = i + nhl[2] - 1;
            int sum = dp[0][index - 1];
            if (i > 0) {
                sum -= profits[i - 1];
            }
            if (i + nhl[1] - 1 > -1 && i + nhl[1] - 1 < nhl[0]) {
                sum += profits[i + nhl[1] - 1];
            }
            dp[0][index] = sum;
        }

        // Populate full DP table
        for (int i = 1; i < dp.length; i++) {
            // w-size will be i + min size
            for (int j = 0; j < dp[0].length; j++) {
                int sum = dp[i - 1][j];
                int index = j + i + nhl[1] - nhl[2];
                if (index >= 0 && index < nhl[0]) {
                    sum += profits[index];
                }
                dp[i][j] = sum;
            }
        }

        // Solve for each window size
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < nhl[1] + i; j++) {
                int profitable = 0;
                for (int k = j; k < dp[0].length; k += nhl[1] + i) {
                    if (dp[i][k] > 0) profitable++;
                }
                minimum = Math.min(minimum, profitable);
                maximum = Math.max(maximum, profitable);
            }
        }
        System.out.printf("%d %d", minimum, maximum);
    }
}
