package NAQ23;

import java.util.ArrayList;

public class LongestIncreasingMatrixPath {

    public int longestIncreasingPath(int[][] matrix) {
        ArrayList<Element> elements = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Element e = new Element(matrix[i][j], i, j);
                elements.add(e);
            }
        }

        elements.sort(Element::compareTo);

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (Element e : elements) {
            CheckNeighbor(e, e.row + 1, e.col, dp, matrix);
            CheckNeighbor(e, e.row - 1, e.col, dp, matrix);
            CheckNeighbor(e, e.row, e.col + 1, dp, matrix);
            CheckNeighbor(e, e.row, e.col - 1, dp, matrix);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }

        return max + 1;
    }

    private static void CheckNeighbor(Element e, int i, int j, int dp[][], int[][] matrix) {
        if (i >= matrix.length || i < 0) return;
        if (j >= matrix[0].length || j < 0) return;
        if (e.value >= matrix[i][j]) return;
        if (dp[e.row][e.col] + 1 > dp[i][j])
            dp[i][j] = dp[e.row][e.col] + 1;
    }

    private static class Element implements Comparable<Element> {

        public int value;
        public int row;
        public int col;

        public Element(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }

        @Override
        public int compareTo(Element o) {
            return this.value - o.value;
        }
        
    }
}
