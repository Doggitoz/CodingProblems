package ICPC2022;

//import java.util.*;
import java.io.*;

public class ErodingPillars {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static double[][] distanceTo;
    // static Pillar[] pillars;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());

        distanceTo = new double[n + 1][n + 1];
        // pillars = new Pillar[n + 1];
        // pillars[0] = new Pillar(0, 0);
        
        for (int i = 1; i <= n; i++) {
            //int[] coords = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //Pillar p = new Pillar(coords[0], coords[1]);
        }
    }
}
