package NAQ22;

import java.util.*;
import java.io.*;

public class MazeMan {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] nm = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        char[][] maze = new char[nm[0]][nm[1]];
        for (int i = 0; i < nm[0]; i++) {
            char[] row = reader.readLine().trim().toCharArray();
            maze[i] = row;
        }
        boolean[][] visited = new boolean[nm[0]][nm[1]];
        solve(nm[0], nm[1], maze, visited);
    }

    public static void solve(int n, int m, char[][] maze, boolean[][] visited) {
        int entranceCount = 0;
        int unreachable = 0;

        Queue<Coord> entrances = new PriorityQueue<Coord>();
        Queue<Coord> search = new PriorityQueue<Coord>();
        // Top wall
        for (int i = 0; i < m; i++) {
            if (maze[0][i] == 'X') continue;
            entrances.add(new Coord(0, i));
        }
        // Left wall
        for (int i = 0; i < n; i++) {
            if (maze[i][0] == 'X') continue;
            entrances.add(new Coord(i, 0));
        }
        // Bottom wall
        for (int i = 0; i < m; i++) {
            if (maze[n - 1][i] == 'X') continue;
            entrances.add(new Coord(n-1, i));
        }
        // Right wall
        for (int i = 0; i < n; i++) {
            if (maze[i][m - 1] == 'X') continue;
            entrances.add(new Coord(i, m-1));
        }

        while (!entrances.isEmpty()) {
            Coord entr = entrances.poll();
            search.add(entr);
            boolean hasHitDot = false;
            int numSearches = 0;
            while (!search.isEmpty()) {
                Coord c = search.poll();
                if (c.x < 0 || c.x >= n) continue;
                if (c.y < 0 || c.y >= m) continue;
                if (maze[c.x][c.y] != '.' &&  maze[c.x][c.y] != ' ' && numSearches != 0) continue;
                if (visited[c.x][c.y]) continue;

                if (maze[c.x][c.y] == '.') hasHitDot = true;

                visited[c.x][c.y] = true;
                numSearches++;
                search.add(new Coord(c.x + 1, c.y));
                search.add(new Coord(c.x - 1, c.y));
                search.add(new Coord(c.x, c.y + 1));
                search.add(new Coord(c.x, c.y - 1));
                
            }
            if (hasHitDot) {
                entranceCount++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == false && maze[i][j] == '.') {
                    unreachable++;
                }
            }
        }

        System.out.printf("%d %d", entranceCount, unreachable);
    }

    public static class Coord {
        int x;
        int y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
