// Dynamic Programming
// Jan 12 2023

// Given an amount and the denominations of coins available,
// determine how many ways change can be made for amount. 
// There is a limitless supply of each coin type.

import java.util.*;
import java.io.*;

public class TheCoinChangeProblem {

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
            for (int j = 0; j < coinValues.length; j++) {
                //if (j)
            }
        }


        System.out.println(dynam[totalChange]);
    }
}
