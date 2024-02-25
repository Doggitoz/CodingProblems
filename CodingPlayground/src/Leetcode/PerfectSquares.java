package Leetcode;
import java.util.*;

class PerfectSquares {
    public int numSquares(int n) {
        ArrayList<Integer> squares = new ArrayList<Integer>();
        int counter = 2;
        int num = 1;
        do {
            squares.add(num);
            num = counter * counter;
            counter++;
        } while (num <= n);

        int[][] dp = new int[n + 1][squares.size() + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][1] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 2; j < squares.size() + 1; j++) {
                int curr = squares.get(j - 1);
                if (curr > i) {
                    dp[i][j] = dp[i][j - 1];
                }
                else if (curr == i) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = Math.min(dp[i - curr][j] + 1, dp[i][j-1]);
                }
                
            }
        }
        return dp[n][squares.size()];
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(4));
    }
}