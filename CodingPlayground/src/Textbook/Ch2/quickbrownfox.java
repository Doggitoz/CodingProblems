package Textbook.Ch2;

// Direct accessing table problem
// https://open.kattis.com/problems/quickbrownfox

import java.io.*;

public class quickbrownfox {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            String line = reader.readLine();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c >= 65 && c <= 90) {
                    freq[c - 'A']++;
                }
                if (c >= 97 && c <= 122) {
                    freq[c - 'a']++;
                }
            }

            String missing = "";

            for (int j = 0; j < freq.length; j++) {
                if (freq[j] == 0) {
                    missing = missing + (char)(j + 97);
                }
            }

            if (missing.length() == 0) {
                System.out.println("pangram");
            }
            else {
                System.out.printf("Missing %s\n", missing);
            }
        }
    }
}
