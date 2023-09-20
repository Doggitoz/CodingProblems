package ICPC2022;

//import java.util.*;
import java.io.*;

public class HuntTheWumpus {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] wumpusLocations = new String[4];
        boolean[] wumpusHit = new boolean[4];

        int seed = Integer.parseInt(in.readLine());
        int i = 0;
        while (i < 4) {
            String pos = "";
            seed = seed + (int)Math.floor(seed / 13) + 15;
            pos = Integer.toString((seed % 100) / 10) + Integer.toString(seed % 10 / 10);
            boolean skip = false;
            for (String wPos : wumpusLocations) {
                if (wPos == null) continue;
                if (wPos.equals(pos)) skip = true;
            }
            if (skip) continue;
            wumpusLocations[i] = pos;
            i++;
        }

        i = 1;
        while (i <= 250) {
            String guess = in.readLine();
            int gX = (Integer.parseInt(guess) % 100) / 10;
            int gY = (Integer.parseInt(guess) % 10);
            int min = 20;
            for (int j = 0; j < 4; j++) {
                if (wumpusHit[j]) continue;
                int wX = (Integer.parseInt(wumpusLocations[j]) % 100) / 10;
                int wY = (Integer.parseInt(wumpusLocations[j]) % 10);
                int dist = Math.abs(gX - wX) + Math.abs(gY - wY);
                if (dist == 0) {
                    System.out.println("You hit a wumpus!");
                    wumpusHit[j] = true;
                }
                min = Math.min(min, dist);
            }

            for (int j = 0; j < 4; j++) {
                if (!wumpusHit[j]) break;
                if (j == 3) {
                    System.out.println(i);
                    return;
                }
            }
            System.out.println(min);
            i++;
        }
        System.out.println("250");
    }
}