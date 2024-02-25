package ICPC2023;
import java.util.*;
import java.io.*;

public class streetsbehind {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            double[] nkab = Arrays.stream(in.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            int numLoops = 0;
            double n = nkab[0];
            double k = nkab[1];
            double a = nkab[2];
            double b = nkab[3];
            double dist = b - a;
            while (k > 0) {
                int newSerious = (int)Math.floor((b/a) * n - n);
                if (newSerious == 0) {
                    numLoops = -1;
                    break;
                }
                if (newSerious == 1) {
                    double newN = Math.ceil((2*a)/dist);
                    double stepsSkipped = newN - n;
                    k = k - stepsSkipped;
                    numLoops += stepsSkipped;
                    n = newN;
                }
                else {
                    n = n + newSerious;
                    k = k - newSerious;
                    numLoops++;
                }
            }
            System.out.println(numLoops);
        }
    }
}