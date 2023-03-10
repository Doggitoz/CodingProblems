import java.util.*;
import java.io.*;

public class LoneKnight {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        Collections.sort(xs);
        Collections.sort(ys);
        Board[][] boards = new Board[xs.size() + 2][ys.size() + 2];

        for (int i = 0; i < xs.size() + 1; i++) {
            for (int j = 0; j < ys.size() + 1; j++) {
                Board b = new Board(
                    i == 0 ? Integer.MIN_VALUE : xs.get(i - 1),
                    i == xs.size() ? Integer.MAX_VALUE : xs.get(i),
                    j == 0 ? Integer.MIN_VALUE : ys.get(j - 1),
                    j == ys.size() ? Integer.MAX_VALUE : ys.get(j)
                );
            }
        }
    }

    public static class Board {
        int xLow;
        int xHigh;
        int yLow;
        int yHigh;

        public Board(int x1, int x2, int y1, int y2) {
            xLow = x1;
            xHigh = x2;
            yLow = y1;
            yHigh = y2;
        }

        public int GetLengthX() {
            return xHigh - xLow - 1;
        }

        public int GetLengthY() {
            return yHigh - yLow - 1;
        }
    }
}
