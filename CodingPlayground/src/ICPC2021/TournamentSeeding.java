package ICPC2021;

import java.util.*;
import java.io.*;

public class TournamentSeeding {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> seeding = new PriorityQueue<>();
        int[] nk = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < Math.pow(2, nk[0]); i++) {
            seeding.add(Integer.parseInt(reader.readLine().trim()));
        }
        for (int i = 0; i < nk[0]; i++) {
            //int numRemoved
            for (int j = 0; j < Math.pow(2, nk[0]-1-i); i++) {

            }
        }
    }
}
