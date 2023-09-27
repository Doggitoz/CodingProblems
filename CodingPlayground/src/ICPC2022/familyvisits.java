package ICPC2022;

import java.io.*;
import java.util.*;

public class familyvisits {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] parts = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] mess = new int[parts[0]];
        int[] clean = new int[parts[0]];
        for (int i = 0; i < parts[0]; i++) {
            int[] mc = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            mess[i] = mc[0];
            clean[i] = mc[1];
        }
        int daysCleaned = 0;
        int prevDay = 0;
        for (int i = 0; i < parts[1]; i++) {
            int day = Integer.parseInt(in.readLine());
            int solve = solve(prevDay + 1, day, mess, clean);
            if (solve == -1) {
                System.out.println(solve);
                return;
            }
            daysCleaned += solve;
            prevDay = day;
        }
        System.out.println(daysCleaned);
    }

    public static int solve(int low, int high, int[] mess, int[] clean) {
        // If one day
        if (high == low) {
            if (mess[high] > clean[high]) return -1;
            return mess[high] == 0 ? 0 : 1;
        }

        // Generate total mess over period
        int messTotal = 0;
        for (int i = low; i <= high; i++) {
            messTotal += mess[i - 1];
        }

        // If forced to clean final day
        int addSum = 0;
        if (mess[high - 1] != 0) {
            addSum = 1;
            if (mess[high - 1] > clean[high - 1]) return -1;
            // Remove clean of final day
            messTotal -= clean[high - 1];
            // Remove high from bound of dp
            high = high - 1;
            if (messTotal <= 0) return 1;
        }

        // messTotal + 1 so we have a 0 mess row, end - start + 2 so we have a nonclean day
        int[][] dp = new int[messTotal + 1][high - low + 2];

        for (int i = 1; i < messTotal + 1; i++) {
            dp[i][0] = -1;
        }
        
        for (int i = 1; i < messTotal + 1; i++) {
            for (int j = 1; j < high - low + 2; j++) {
                int cleanDay = clean[high - j];
                int opOne = dp[i][j-1];
                int opTwo = dp[Math.max(0, i - cleanDay)][j-1];
                if (opOne == -1 && opTwo == -1) dp[i][j] = -1;
                else if (opOne == -1) dp[i][j] = opTwo + 1;
                else if (opTwo == -1) dp[i][j] = opOne;
                else dp[i][j] = Math.min(opOne, opTwo + 1); 
            }
        }

        return dp[messTotal][high - low + 1] + addSum;
    }
}