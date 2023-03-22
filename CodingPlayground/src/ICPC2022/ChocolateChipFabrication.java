package ICPC2022;

// https://open.kattis.com/problems/chocolatechipfabrication

import java.util.*;
import java.io.*;

public class ChocolateChipFabrication {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static boolean[][] visited;
    public static int[] nm;
    public static void main(String[] args) throws IOException {
        nm = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[nm[0]][nm[1]];
        Queue<Tuple> one = new LinkedList<>();
        Queue<Tuple> two = new LinkedList<>();
        for (int i = 0; i < nm[0]; i++) {
            String row = reader.readLine();
            // Add all empties and edge X's to queue
            for (int j = 0; j < nm[1]; j++) {
                if (row.charAt(j) == '-') {
                    visited[i][j] = true;
                    one.add(new Tuple(i, j));
                }
                else if (i == 0 || i == nm[0] - 1 || j == 0 || j == nm[1] - 1) {
                    visited[i][j] = true;
                    two.add(new Tuple(i, j));
                }
            }
        }
        // Add all X's adjacent to empties
        while (!one.isEmpty()) {
            AddNeighbors(one.poll(), two);
        }
        int bakes = 0;
        // While at least one of the two queues has stuff in it, there is still cookie dough to chipify
        while (!one.isEmpty() || !two.isEmpty()) {
            bakes++;
            if (bakes % 2 == 0) {
                while (!one.isEmpty()) {
                    AddNeighbors(one.poll(), two);
                }
            }
            else {
                while(!two.isEmpty()) {
                    AddNeighbors(two.poll(), one);
                }
            }
        }
        System.out.println(bakes);
    }

    public static void AddNeighbors(Tuple curr, Queue<Tuple> Q) {
        if (CheckValidSquare(new Tuple(curr.x + 1, curr.y))) Q.add(new Tuple(curr.x + 1, curr.y));
        if (CheckValidSquare(new Tuple(curr.x - 1, curr.y))) Q.add(new Tuple(curr.x - 1, curr.y));
        if (CheckValidSquare(new Tuple(curr.x, curr.y + 1))) Q.add(new Tuple(curr.x, curr.y + 1));
        if (CheckValidSquare(new Tuple(curr.x, curr.y - 1))) Q.add(new Tuple(curr.x, curr.y - 1));
    }

    public static boolean CheckValidSquare(Tuple t) {
        if (t.x < 0 || t.x >= nm[0] || t.y < 0 || t.y >= nm[1]) return false;
        if (visited[t.x][t.y]) return false;
        visited[t.x][t.y] = true;
        return true;
    }
    
    public static class Tuple {
        int x;
        int y;
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}