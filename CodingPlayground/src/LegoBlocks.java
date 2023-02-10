import java.util.*;
import java.io.*;

public class LegoBlocks {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final static long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        long[][] mat = new long[3][1001];

        // mat[0] is the base row of the wall
        mat[0][1] = 1; mat[0][2] = 2; mat[0][3] = 4; mat[0][4] = 8;
        for (int i = 5; i <= 1000; i++) {
            mat[0][i] = LegoBlocks.add(mat[0][i], mat[0][i - 1]);
            mat[0][i] = LegoBlocks.add(mat[0][i], mat[0][i - 2]);
            mat[0][i] = LegoBlocks.add(mat[0][i], mat[0][i - 3]);
            mat[0][i] = LegoBlocks.add(mat[0][i], mat[0][i - 4]);
        }

        for (int tk = 1; tk <= t; tk++) {
            int[] split = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = split[0], m = split[1]; 
            if (n == 1) {
                if (m <= 4) {
                    System.out.println("1");
                }
                else {
                    System.out.println("0");
                }
                continue;
            }
            // mat[1] is all combinations
            for (int nk = 1; nk <= n; nk++) {
                for (int i = 1; i <= m; i++) {
                    if (nk == 1) {
                        //Simple solution
                        mat[1][i] = mat[0][i];
                        continue;
                    }
                    mat[1][i] = LegoBlocks.mul(mat[1][i], mat[0][i]);
                }
            }
            // mat[2] is the solutions
            for (int i = 1; i <= m; i++) {
                mat[2][i] = LegoBlocks.sub(mat[1][i], LegoBlocks.sum(mat[2], mat[1], i));
            }
            System.out.printf("%d\n", mat[2][m]);
        }
    }

    private static long mul(long a, long b) {
        return (a * b) % LegoBlocks.MOD;
    }

    private static long add(long a, long b) {
        return (a + b) % LegoBlocks.MOD;
    }
    private static long sub(long a, long b) {
        if (a < b) {
            return (a + LegoBlocks.MOD - b) % LegoBlocks.MOD;
        }
        return (a - b) % LegoBlocks.MOD;
    }

    private static long sum(long[] S, long[] A, int w) {
        if (w <= 1) {
            return 0;
        }
        long sum_bads = 0;
        for (int i = 1; i < w; i++) {
            sum_bads = LegoBlocks.add(sum_bads, LegoBlocks.mul(S[i], A[w - i]));
        }
        return sum_bads;
    }
}
