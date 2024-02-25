package ICPC2023;

import java.io.*;

public class champernowneverification {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int champ = 1;
        if (n == 1) {
            System.out.println(1);
            return;
        }
        // Iterate through first 9 champernowne numbers. n > 10 -> champ > 10^9
        for (int i = 2; i < 10; i++) {
            champ = champ * 10 + i;
            if (n == champ) { // If equal, we are done.
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}