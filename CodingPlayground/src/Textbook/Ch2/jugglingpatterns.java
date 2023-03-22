package Textbook.Ch2;

// Priority Queue problem
// https://open.kattis.com/problems/jugglingpatterns

import java.io.*;
import java.util.*;

/*
 * Invalid if average of integers != an integer
 * Also invalid if two balls fall in the same hand at the same time
 */

public class jugglingpatterns {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        while (sc.hasNextLine()) {
            PriorityQueue<Beat> LeftHand = new PriorityQueue<>();
            PriorityQueue<Beat> RightHand = new PriorityQueue<>();
            int[] beats = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            int sum = 0;
            for (int i = 1; i <= beats.length; i++) {
                Beat b = new Beat(beats[i-1], i + beats[i-1]);
                if (i % 2 == 0) RightHand.add(b);
                else LeftHand.add(b);
                sum += beats[i-1];
            }
            if (((double)sum / (double)beats.length) % 1 != 0) {
                System.out.println("invalid # of balls");
                continue;
            }
            boolean valid = true;
            
            if (valid) System.out.printf("valid with %d balls", beats.length);
        }
    }

    public static class Beat implements Comparable<Beat>{
        int toss;
        int land;

        public Beat (int toss, int land) {
            this.toss = toss;
            this.land = land;
        }

        public int compareTo(Beat o) {
            return this.land - o.land;
        }

    }
}
