package ICPC2022;

// https://open.kattis.com/problems/loneknight

import java.io.*;
import java.util.*;
/*
 * Logistically to solve:
 * 
 * BFS Knight positions in starting board if size < 3x3 to see if it can exit first board.
 * Can't leave 1x1, 3x3 if in middle.
 * 
 * BFS Knight positions in ending board if size < 3x3.
 * There will need to be some logic to account for 2xN where N is very large.
 * Flag reachable 3x3 or bigger boards as 2 in visited arr and continue.
 * 
 */

public class LoneKnight {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Board[][] boards;
    public static void main(String[] args) throws IOException {
        int[] nq = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> xsSet = new HashSet<>();
        Set<Integer> ysSet = new HashSet<>();
        for (int i = 0; i < nq[0]; i++) {
            int[] xy = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            xsSet.add(xy[0]);
            ysSet.add(xy[1]);
        }
        List<Integer> xs = new ArrayList<>(xsSet);
        List<Integer> ys = new ArrayList<>(ysSet);
        // These started erroring randomly
        // Collections.sort(xs);
        // Collections.sort(ys);
        boards = new Board[xs.size() + 1][ys.size() + 1];

        // Set up all boards
        for (int i = 0; i < xs.size() + 1; i++) {
            for (int j = 0; j < ys.size() + 1; j++) {
                Board b = new Board(
                    i == 0 ? Integer.MIN_VALUE : xs.get(i - 1),
                    i == xs.size() ? Integer.MAX_VALUE : xs.get(i),
                    j == 0 ? Integer.MIN_VALUE : ys.get(j - 1),
                    j == ys.size() ? Integer.MAX_VALUE : ys.get(j),
                    i, j
                );
                boards[i][j] = b;
            }
        }

        // Each query
        for (int i = 0; i < nq[1]; i++) {
            int[] pos = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Queue<Coord> Q = new LinkedList<Coord>();

            // Check if two sets of rooks between K and T
            Board start = boards[FindBoardXFromPos(pos[0])][FindBoardYFromPos(pos[1])];
            Board end = boards[FindBoardXFromPos(pos[2])][FindBoardYFromPos(pos[3])];
            int maxX = Math.max(start.x, end.x);
            int minX = Math.min(start.x, end.x);
            int maxY = Math.max(start.y, end.y);
            int minY = Math.min(start.y, end.y);
            if (DoubleRookBetweenX(minX, maxX) || DoubleRookBetweenY(minY, maxY)) {
                System.out.println(0);
                continue;
            }

            // BFS Explore from knight position until you hit a board bigger than or equal to 3x3
            boolean validStart = false;
            Q.add(new Coord(start.x, start.y, pos[0], pos[1]));
            while (!Q.isEmpty()) {
                Coord c = Q.poll();
                if (c.boardX < 0 || c.boardX >= boards.length) continue;
                if (c.boardY < 0 || c.boardY >= boards[0].length) continue;
                if (xs.contains(c.knightX) || ys.contains(c.knightY)) continue;

                Board curr = boards[c.boardX][c.boardY];
                if (curr.localVisited[curr.xHigh - c.knightX][curr.yHigh - c.knightY]) continue;
                curr.localVisited[curr.xHigh - c.knightX][curr.yHigh - c.knightY] = true;
                
                if (curr.GetLengthX() == 3 && curr.GetLengthY() == 3) {
                    if (pos[0] == curr.xLow + 2 && pos[1] == curr.yLow + 2) { // Board size 3x3 and start in middle
                        System.out.println("In middle of 3x3");
                        break;
                    }
                    else {
                        validStart = true;
                        break;
                    }
                }
                if (curr.GetLengthX() >= 3 && curr.GetLengthY() >= 3) {
                    validStart = true;
                    break;
                }
                if (curr.GetLengthX() == 1 && curr.GetLengthY() == 1) break;
                if (curr.GetLengthX() == 1) {
                    Q.add(new Coord(c.boardX + 1, c.boardY, c.knightX + 2, c.knightY + 1));
                    Q.add(new Coord(c.boardX + 1, c.boardY, c.knightX + 2, c.knightY - 1));
                    Q.add(new Coord(c.boardX - 1, c.boardY, c.knightX - 2, c.knightY + 1));
                    Q.add(new Coord(c.boardX - 1, c.boardY, c.knightX - 2, c.knightY - 1));
                    continue;
                }
                if (curr.GetLengthY() == 1) {
                    Q.add(new Coord(c.boardX, c.boardY + 1, c.knightX + 1, c.knightY + 2));
                    Q.add(new Coord(c.boardX, c.boardY + 1, c.knightX + 1, c.knightY - 2));
                    Q.add(new Coord(c.boardX, c.boardY - 1, c.knightX - 1, c.knightY + 2));
                    Q.add(new Coord(c.boardX, c.boardY - 1, c.knightX - 1, c.knightY - 2));
                    continue;
                }
                if (curr.GetLengthX() == 2 && curr.GetLengthY() == 2) {
                    Q.add(new Coord(c.boardX + 1, c.boardY, c.knightX + 2, c.knightY + 1));
                    Q.add(new Coord(c.boardX + 1, c.boardY, c.knightX + 2, c.knightY - 1));
                    Q.add(new Coord(c.boardX - 1, c.boardY, c.knightX - 2, c.knightY + 1));
                    Q.add(new Coord(c.boardX - 1, c.boardY, c.knightX - 2, c.knightY - 1));
                    Q.add(new Coord(c.boardX, c.boardY + 1, c.knightX + 1, c.knightY + 2));
                    Q.add(new Coord(c.boardX, c.boardY + 1, c.knightX + 1, c.knightY - 2));
                    Q.add(new Coord(c.boardX, c.boardY - 1, c.knightX - 1, c.knightY + 2));
                    Q.add(new Coord(c.boardX, c.boardY - 1, c.knightX - 1, c.knightY - 2));
                    continue;
                }
                if (curr.GetLengthX() == 2) {
                    
                }
                break;
            }
            Q.clear();
            if (!validStart) { System.out.println(0); continue; }
            else System.out.println(1);
        }
    }

    public static boolean DoubleRookBetweenX(int min, int max) {
        for (int j = min; j < max; j++) {
            if (boards[j][0].GetLengthX() == 0) { // Board is of x size 0
                return true;
            }
        }
        return false;
    }

    public static boolean DoubleRookBetweenY(int min, int max) {
        for (int j = max; j > min; j--) {
            if (boards[0][j].GetLengthY() == 0) { // Board is of y size 0
                return true;
            }
        }
        return false;
    }

    public static int FindBoardXFromPos(int x) {
        for (int i = 0; i < boards.length; i++) {            
            if (x <= boards[i][0].xHigh && x >= boards[i][0].xLow) {
                return i;
            }
        }
        return -1;
    }

    public static int FindBoardYFromPos(int y) {
        for (int j = 0; j < boards[0].length; j++) {
            if (y <= boards[0][j].yHigh && y >= boards[0][j].yLow) {
                return j;
            }
        }
        return -1;
    }

    public static class Coord {
        int boardX;
        int boardY;
        int knightX;
        int knightY;
        public Coord(int boardX, int boardY, int knightX, int knightY) {
            this.boardX = boardX;
            this.boardY = boardY;
        }
    }

    public static class Board {
        int xLow;
        int xHigh;
        int yLow;
        int yHigh;
        int x;
        int y;
        public boolean[][] localVisited;

        public Board(int x1, int x2, int y1, int y2, int x, int y) {
            xLow = x1;
            xHigh = x2;
            yLow = y1;
            yHigh = y2;
            this.x = x;
            this.y = y;
            localVisited = new boolean[GetLengthX()][GetLengthY()];
        }

        public int GetLengthX() {
            return xHigh - xLow - 1;
        }

        public int GetLengthY() {
            return yHigh - yLow - 1;
        }
    }
}
