package ICPC2023;

import java.io.*;

public class abcstring {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String s = in.readLine();
        char[] c = s.toCharArray();
        int[] abc = new int[3];
        int count = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = c[i] - 'A';
            if (abc[index] == 0) {
                count++;
            }
            abc[index]++;
            max = Math.max(max, abc[index]);
            if (count == 3) {
                for (int j = 0; j < 3; j++) {
                    if (abc[j] == 1) {
                        count--;
                    }
                }
                abc[0]--;
                abc[1]--;
                abc[2]--;
            }
        }
        System.out.println(max);
    }

}