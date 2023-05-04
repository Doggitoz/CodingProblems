import java.io.*;
//import java.util.*;

public class SubstringDiff {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static final int UPPER_MASK = (1 << 16) - 1;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(reader.readLine().trim());
        int[][] cache = new int[1501][1501];
        for (int t = 0; t < T; t++) {
            String[] split = reader.readLine().trim().split(" ");
            int k = Integer.parseInt(split[0]);
            String s1 = split[1], s2 = split[2];
            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    int prev = cache[i - 1][j - 1];
                    int len = prev & UPPER_MASK;
                    int err = Math.abs(prev >> 16) & UPPER_MASK;
                    if (s1.charAt(i - 1) != s2.charAt(j - 1) && len == 0) {
                        cache[i][j] = 0;
                        continue;
                    }
                    if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                        cache[i][j] = prev + 1 + (1 << 16);
                        continue;
                    }
                    if (err <= k) {
                        cache[i][j] = prev + 1;
                        continue;
                    }
                    int skips = 1;
                    while (s1.charAt(i - len + skips) != s2.charAt(j - len + skips)) {
                        skips++;
                    }
                    cache[i][j] = prev - skips + 1 - ((skips - 1) << 16);
                    continue;
                }
            }
        }
    }

}