package ICPC2022;

import java.io.*;
import java.util.*;

public class ColorTubes {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int[][] tubes;
    static ArrayList<String> moves;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        tubes = new int[n + 1][3];
        moves = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            int[] parts = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tubes[i][2] = parts[0];
            tubes[i][1] = parts[1];
            tubes[i][0] = parts[2];
        }

        for (int i = 0; i <= n; i++) {
            int color = tubes[i][2];
            if (color == 0) continue;
            SolveNextBall(color, i, 1);
            SolveNextBall(color, i, 0);
        }

        System.out.println(moves.size());
        for (int i = 0; i < moves.size(); i++) {
            System.out.println(moves.get(i));
        }

    }

    public static void SolveNextBall(int color, int i, int verticalPos) {
        while (tubes[i][verticalPos] != color) {
            int nextTube = NextInstance(color, i, -1);
            if (tubes[nextTube][0] == color) {
                // Top ball is right color (Up to 3 moves)
                for (int j = 0; j < verticalPos + 1; j++) {
                    if (tubes[i][j] == 0) continue;
                    MoveBall(i, NextInstance(0, i, i), tubes[i][j]);
                }
                MoveBall(nextTube, i, color);
            }
            else if (tubes[nextTube][1] == color) {
                // Middle ball is right color (Up to 4 moves)
                if (tubes[nextTube][0] != 0) // Move blocking ball
                    MoveBall(nextTube, NextInstance(0, nextTube, i), tubes[nextTube][0]);
                for (int j = 0; j < verticalPos + 1; j++) {
                    if (tubes[i][j] == 0) continue;
                    MoveBall(i, NextInstance(0, nextTube, i), tubes[i][j]);
                }
                MoveBall(nextTube, i, color);
            }
            else {
                // Bottom ball is right color (Up to 6 moves)
                for (int j = 0; j < 3; j++) {
                    if (tubes[nextTube][j] == 0) continue;
                    MoveBall(nextTube, NextInstance(0, nextTube, -1), tubes[nextTube][j]);
                }
            }
        }
    }

    public static int NextInstance(int color, int ignore, int ignoreTwo) {
        for (int i = 0; i < tubes.length; i++) {
            if (i == ignore) continue;
            if (i == ignoreTwo) continue;
            for (int j = 0; j < 3; j++) {
                if (tubes[i][j] == color) {
                    return i;
                }
            }
        }
        return -2;
    }

    public static void MoveBall(int from, int to, int color) {
        for (int i = 2; i >= 0; i--) {
            if (tubes[to][i] == 0) {
                tubes[to][i] = color;
                moves.add((from+1) + " " + (to+1));
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tubes[from][i] != 0) {
                tubes[from][i] = 0;
                break;
            }
        }
    }
}
