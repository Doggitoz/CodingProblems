package ICPC2023;
import java.io.*;

public class orderedproblemset {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        //input
        int n = Integer.parseInt(in.readLine());
        int[] problems = new int[n];

        for (int i = 0; i < n; i++) {
            problems[i] = Integer.parseInt(in.readLine());
        }

        //solving
        boolean hasValid = false;

        for (int i = 2; i < problems.length + 1; i++) { //defines sections
            if (n % i != 0) continue;

            boolean valid = true;
            int sectionSize = n/i;
            for (int j = 0; j < i-1; j++) { //Check each section if it works (j is the section number)
                int max = 0;
                int min = n + 1;

                //finds max of first section
                for (int k = j * (n/i); k < j * (n/i) + sectionSize; k++) { //find max of section
                    max = Math.max(problems[k], max);
                }
                
                //finds min of the second section  
                for (int k = (j) * (n/i); k < j * (n/i) + sectionSize; k++) {
                    min = Math.min(problems[k + n/i], min);
                }
                if (max >= min) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                System.out.println(i);
                hasValid = true;
            }
        }
        if (!hasValid) System.out.println(-1);

    } //j * n/i = the start of a section
}