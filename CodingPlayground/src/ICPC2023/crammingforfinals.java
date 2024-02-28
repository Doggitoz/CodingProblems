package ICPC2023;

import java.io.*;
import java.util.*;

public class crammingforfinals {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        HashMap<Integer, ArrayList<Interval>> rows = new HashMap<>();
        int[] rcdn = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int ds = rcdn[2] * rcdn[2];

        int[] offsets = new int[rcdn[2] + 1];

        for (int i = 0; i <= rcdn[2]; i++) {
            offsets[i] = (int)Math.floor(Math.sqrt(ds - Math.pow(i,2)));
        }

        for (int s = 0; s < rcdn[3]; s++) {
            int[] pos = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = -rcdn[2]; i <= rcdn[2]; i++) {
                if (pos[0] + i < 1) continue;
                if (pos[0] + i > rcdn[0]) continue;
                Interval interval = new Interval(pos[1] - offsets[Math.abs(i)], 
                    pos[1] + offsets[Math.abs(i)]);
                if (interval.start < 1) interval.start = 1;
                if (interval.end > rcdn[1]) interval.end = rcdn[1];
                if (rows.containsKey(pos[0] + i)) {
                    rows.get(pos[0] + i).add(interval);
                }
                else {
                    ArrayList<Interval> a = new ArrayList<>();
                    a.add(interval);
                    rows.put(pos[0] + i, a);
                }
            }
        }
        System.out.println("test");
    }

    public static class Interval {
        public int start;
        public int end;
        public Interval(int a, int b) {
            start = a;
            end = b;
        }
    }
}
