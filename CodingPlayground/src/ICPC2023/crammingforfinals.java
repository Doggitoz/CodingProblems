package ICPC2023;

import java.io.*;
import java.util.*;

public class crammingforfinals {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] rcdn = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solve(rcdn[0], rcdn[1], rcdn[2], rcdn[3]));
    }

    public static int solve(int rows, int cols, int radius, int n) throws IOException {
        int radius_squared = radius * radius;
        LinkedList<Interval> intervals = new LinkedList<>();

        int[] offsets = new int[radius + 1];

        for (int i = 0; i <= radius; i++) {
            offsets[i] = (int)Math.floor(Math.sqrt(radius_squared - Math.pow(i,2)));
        }

        for (int s = 0; s < n; s++) {
            int[] pos = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = -radius; i <= radius; i++) {
                int row = pos[0] - i;
                if (row < 1 || row > rows) continue;
                Interval iv = new Interval(row, pos[1] - offsets[Math.abs(i)], pos[1] + offsets[Math.abs(i)]);
                intervals.add(iv);
            }
        }

        Collections.sort(intervals);

        int currRow = 1;
        int min = Integer.MAX_VALUE;
        while (!intervals.isEmpty()) {
            if (intervals.peek().row != currRow) return 0;
            if (intervals.peek().a > 1) return 0;
            int[] nearby = new int[2 * radius * n + 1];
            int right = intervals.peek().b;
            while (!intervals.isEmpty() && intervals.peek().row == currRow) {
                Interval iv = intervals.poll();
                if (iv.a > right + 1) return 0;
                right = Math.max(right, iv.b);
                for (int i = iv.a; i <= iv.b; i++) {
                    if (i < 1) continue;
                    if (i > cols) break;
                    if (nearby[i] == -1) continue;
                    nearby[i]++;
                }
                // Remove the ability to sit with another student
                if (iv.b - iv.a + 1 == radius * 2 + 1) {
                    nearby[iv.a + radius] = -1;
                }
            }

            for (int i = 1; i <= cols; i++) {
                if (nearby[i] == 0) return 0;
                if (nearby[i] == -1) continue;
                min = Math.min(min, nearby[i]);
            }
            currRow++;
        }
        return min;
    }

    public static class Interval implements Comparable<Interval> {
        public int row;
        public int a;
        public int b;

        public Interval(int row, int a, int b) {
            this.a = a;
            this.b = b;
            this.row = row;
        }

        @Override
        public int compareTo(Interval o) {
            if (this.row == o.row) {
                if (this.a == o.a) {
                    return (this.b - this.a) - (o.b - o.a);
                }
                else {
                    return this.a - o.a;
                }
            }
            else {
                return this.row - o.row;
            }
        }
    }
}
