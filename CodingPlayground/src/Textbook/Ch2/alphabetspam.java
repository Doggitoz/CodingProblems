package Textbook.Ch2;

// Direct accessing table problem
// https://open.kattis.com/problems/alphabetspam

import java.io.*;

public class alphabetspam {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String line = reader.readLine();
        int[] freq = new int[127];
        for (int i = 0; i < line.length(); i++) {
            freq[line.charAt(i)]++;
        }

        int Upper = 0;
        int Lower = 0;
        int Symbol = 0;
        int Whitespace = 0;

        for (int i = 0; i < freq.length; i++) {
            if (i < 33) continue;
            else if (i <= 64) Symbol+=freq[i];
            else if (i <= 90) Upper+=freq[i];
            else if (i <= 94) Symbol+=freq[i];
            else if (i <= 95) Whitespace+=freq[i];
            else if (i <= 96) Symbol+=freq[i];
            else if (i <= 122) Lower+=freq[i];
            else if (i <= 126) Symbol+=freq[i];
        }

        System.out.println((double)Whitespace/(double)line.length());
        System.out.println((double)Lower/(double)line.length());
        System.out.println((double)Upper/(double)line.length());
        System.out.println((double)Symbol/(double)line.length());
    }
}
