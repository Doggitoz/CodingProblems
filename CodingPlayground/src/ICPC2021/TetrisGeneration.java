package ICPC2021;

import java.io.*;

public class TetrisGeneration {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; i++) {
            System.out.println(solve());
        }
    }

    public static int solve() throws IOException {
        int[] sequence = new int[26];
        String s = reader.readLine().trim();
        boolean hasFilled = false;
        for (int j = 0; j < s.length(); j++) {
            int indx = s.charAt(j) - 'A';
            sequence[indx]++;
            if (sequence[indx] == 3) return 0;
            if (!hasFilled && sequence[indx] == 2) {
                hasFilled = true;
                for (int k = 0; k < sequence.length; k++) {
                    if (sequence[k] == 0) sequence[k] = 1;
                }
            }
            if (j - 7 >= 0) {
                sequence[s.charAt(j - 7) - 'A']--;
            }
        }
        return 1;
    }
}
