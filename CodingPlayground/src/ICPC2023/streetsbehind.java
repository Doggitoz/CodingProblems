package ICPC2023;
import java.util.*;
import java.io.*;

public class streetsbehind {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            int[] nkab = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int numLoops = 0;
            double n = nkab[0];
            double k = nkab[1];
            int total = (int) (n + k);
            double a = nkab[2];
            double b = nkab[3];
            double dist = b - a;
            while (k > 0) {
                int newSerious = (int)Math.floor((b * n) / a - n);
                if (newSerious == 0) {
                    numLoops = -1;
                    break;
                }
                double newN = Math.ceil(((newSerious + 1)*a)/dist);
                if (newN > total) {
                    newN = total;
                }
                double amtToAdd = newN - n;
                if (amtToAdd % newSerious != 0) {
                    amtToAdd += newSerious - (amtToAdd % newSerious);
                }
                int steps = (int)(amtToAdd / newSerious);
                k -= amtToAdd;
                n += amtToAdd;
                numLoops += steps;
            }
            System.out.println(numLoops);
        }
    }
}