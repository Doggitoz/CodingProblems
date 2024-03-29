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
        // Generate total mess over period
        int[] messTotalByDay = new int[high - low + 2];
        messTotalByDay[0] = 0;
        for (int i = 1; i < messTotalByDay.length; i++) {
            messTotalByDay[i] = messTotalByDay[i - 1] + mess[low - 2 + i];
        }

        // messTotal + 1 so we have a 0 mess row, end - start + 2 so we have a nonclean day
        short[] curr = new short[messTotalByDay[messTotalByDay.length - 1] + 1];
        short[] prev = new short[messTotalByDay[messTotalByDay.length - 1] + 1];

        for (int i = 1; i < prev.length; i++) {
            prev[i] = -1;
        }
        
        // Work through columns instead of rows
        for (int i = 1; i < high - low + 2; i++) {
            // j = row/total mess
            for (int j = 1; j < curr.length; j++) {
                int cleanDay = clean[low + (i - 2)];
                int opOne = j < prev.length ? prev[j] : -1;
                int opTwo = prev[Math.max(0, j - cleanDay)];
                if (opOne == -1 && opTwo == -1) curr[j] = -1;
                else if (opOne == -1) curr[j] = (short)(opTwo + 1);
                else if (opTwo == -1) curr[j] = (short)(opOne);
                else curr[j] = (short)Math.min(opOne, opTwo + 1); 
            }

            //After work, prev <- curr, clear curr
            if (i == (high - low + 1)) break;
            prev = curr;
            curr = new short[messTotalByDay[i + 1] + 1];
        }

        return curr[curr.length - 1];
    }
}