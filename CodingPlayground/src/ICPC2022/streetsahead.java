package ICPC2022;

import java.io.*;
import java.util.*;

public class streetsahead {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] nq = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<String, Integer> d = new Hashtable<String, Integer>();
        for (int i = 0; i < nq[0]; i++) {
            d.put(in.readLine(), i);
        }

        for (int i = 0; i < nq[1]; i++) {
            String[] roads = in.readLine().split(" ");
            System.out.println(Math.abs(d.get(roads[0]) - d.get(roads[1])) - 1);
        }
    }
}