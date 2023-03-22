package Textbook.Ch2;

// Priority Queue problem
// https://open.kattis.com/problems/knigsoftheforest

import java.io.*;
import java.util.*;

public class knigsoftheforest {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] kn = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        PriorityQueue<Moose> moose = new PriorityQueue<>((o1, o2) -> o1.compareYear(o2));
        int[] data = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        moose.add(new Moose(data[0], data[1], true));
        for (int i = 0; i < kn[1] + kn[0] - 2; i++) {
            data = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            moose.add(new Moose(data[0], data[1], false));
        }
        PriorityQueue<Moose> tournament = new PriorityQueue<Moose>();
        for (int i = 2011; i < 2011 + kn[1]; i++) {
            while (!moose.isEmpty() && moose.peek().year == i) {
                tournament.add(moose.poll());
            }
            if (tournament.poll().isKarl) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("unknown");
    }

    public static class Moose implements Comparable<Moose> {
        int year;
        int strength;
        boolean isKarl;

        public Moose(int year, int strength, boolean isKarl) {
            this.year = year;
            this.strength = strength;
            this.isKarl = isKarl;
        }

        public int compareYear(Moose o) {
            return this.year - o.year;
        }

        public int compareTo(Moose o) {
            return o.strength - this.strength;
        }
    }
}
