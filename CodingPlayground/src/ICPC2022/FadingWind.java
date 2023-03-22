package ICPC2022;

// https://open.kattis.com/problems/fadingwind

import java.io.*;

public class FadingWind {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        int h = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int v = Integer.parseInt(line[2]);
        int s = Integer.parseInt(line[3]);
        int distance = 0;
        while (h > 0) {
            v += s;
            v -= Math.max(1, Math.floor((double)v / (double)10));
            if (v >= k) h++;
            if (0 < v && v < k) h--;
            if (h == 0) v = 0;
            if (v <= 0) { h = 0; v = 0; }
            distance += v;
            if (s > 0) s--;
        }
        System.out.println(distance);
    }
}
