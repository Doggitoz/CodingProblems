import java.util.*;
import java.io.*;


public class Knapsack {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(reader.readLine().trim());
        int[] prevCol = new int[2001];
        int[] currCol = new int[2001];
        for (int t = 0; t < T; t++) {
            int[] nk = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = nk[0]; int k = nk[1];
            int[] arr = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr);
            //solve
            for (int j = 1; j <= n; j++) {
                int[] tmp = currCol;
                currCol = tmp;
                //solve for rows
                for (int i = 1; i <= k; i++) {
                    if (arr[j - 1] > i) { //Stone is too large to fit into our current backpack
                        currCol[i] = prevCol[i];
                    } else {
                        currCol[i] = Math.max(
                            prevCol[i], 
                            currCol[i - arr[j - 1]] + arr[j - 1]
                        );
                    }
                }
            }
            System.out.printf("%d\n", currCol[k]);
            if (t + 1 < T) {
                for (int i = 1; i <= k; i++) {
                    prevCol[i] = 0;
                    currCol[i] = 0;
                }
            }
        }
    }
}
