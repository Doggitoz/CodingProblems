// Dynamic Programming
// Jan 12 2023

import java.util.*;
import java.io.*;

public class TheCoinChangeProblemIncorrect {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] parts = in.readLine().trim().split(" ");
        int changeAmount = Integer.parseInt(parts[0]);
        //int numCoins = Integer.parseInt(parts[1]);
        int[] coins = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        Solve(changeAmount, coins);
    }

    public static void Solve(int totalChange, int[] coinValues) {
        int[] dynam = new int[totalChange + 1]; 
        
        dynam[0] = 0;

        for (int i = 1; i <= totalChange; i++) {
            int lowestPrev = Integer.MAX_VALUE;

            for (int j = 0; j < coinValues.length; j++) {
                if (i - coinValues[j] >= 0) {
                    if (dynam[i - coinValues[j]] < lowestPrev) {
                        lowestPrev = dynam[i - coinValues[j]];
                    }
                }
            }
            if (lowestPrev < Integer.MAX_VALUE) {
                dynam[i] = lowestPrev + 1;
            }
        }
        System.out.println(dynam[totalChange]);
    }
}
