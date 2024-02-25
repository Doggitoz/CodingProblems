package DP;
import java.util.*;
import java.io.*;

public class TheCoinChangeProblem {
    
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int[] nm = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] coins = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.printf("%d\n", TheCoinChangeProblem.Solve(nm[0], nm[1], coins));
    }

    public static long Solve(int n, int m, int[] coins) {
        Arrays.sort(coins);
        long[][] dp = new long[n + 1][m + 1]; //Default value is 0. Doesn't matter because we're building bottom up. 
        //Top down this would not work

        //No change to make, no way to make it, ways to make change is 0
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = 0;
        }

        //We have no coins, can't make change, ways to make change is 0
        for(int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }


        //j = m, i = n
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (coins[j - 1] > i) 
                { //Case where the coin is bigger than the change amount
                    dp[i][j] = dp[i][j - 1];
                } 
                else if (coins[j - 1] == i) 
                { //Case where the coin amount is equal to the change amonut
                    dp[i][j] = 1 + dp[i][j - 1];
                } 
                else 
                { //Case where the coin amount is less than the change amount
                    dp[i][j] = dp[i - coins[j - 1]][j] + dp[i][j-1];
                }
            }
        }


        return dp[n][m];
    }
}