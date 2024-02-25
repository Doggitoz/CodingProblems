package ICPC2023;
import java.util.*;
import java.io.*;

public class itemselection {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] nmspq = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nmspq[0];
        int m = nmspq[1];
        int s = nmspq[2] - 1;
        int p = nmspq[3];
        int q = nmspq[4];
        
        boolean[] wantedItems = new boolean[n];
        boolean[] selected = new boolean[n];
        for (int i = 0; i < p; i++) {
            selected[Integer.parseInt(in.readLine()) - 1] = true;
        }
        for (int i = 0; i < q; i++) {
            wantedItems[Integer.parseInt(in.readLine()) - 1] = true;
        }

        int[] pageOps = new int[n/m + 1];
        for (int i = 0; i < pageOps.length; i++) {
            int falsePos = 0;
            int falseNeg = 0;
            int wanted = 0;
            int unwanted = 0;
            for (int j = i * m; j < (i + 1) * m; j++) {
                if (j >= n) break;
                if (wantedItems[j]) {
                    wanted++;
                    if (!selected[j]) {
                        falseNeg++;
                    }
                }
                else {
                    unwanted++;
                    if (selected[j]) {
                        falsePos++;
                    }
                }
            }
            pageOps[i] = Math.min(Math.min(wanted + 1, unwanted + 1), falsePos + falseNeg);
        }
        int leftBound = -1;
        int rightBound = -1;
        for (int i = 0; i <= s; i++) {
            if (pageOps[i] != 0) {
                leftBound = i;
                break;
            }
        }
        for (int i = pageOps.length - 1; i >= s; i--) {
            if (pageOps[i] != 0) {
                rightBound = i;
                break;
            }
        }
        if (leftBound == -1) leftBound = s;
        if (rightBound == -1) rightBound = s;

        int sumOps = 0;
        for (int i = 0; i < pageOps.length; i++) {
            sumOps += pageOps[i];
        }
        int rightMoves = (rightBound - s);
        int leftMoves = (s - leftBound);

        int moves = 2 * (Math.min(rightMoves, leftMoves)) + Math.max(rightMoves, leftMoves);

        System.out.println(sumOps + moves);
    }
}