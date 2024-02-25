package NAQ23;
import java.io.*;
import java.util.*;

public class waterjournal {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] nab = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean foundMin = false;
        boolean foundMax = false;
        for (int i = 0; i < nab[0] - 1; i++) {
            int d = Integer.parseInt(in.readLine());
            if (d == nab[1]) foundMin = true;
            if (d == nab[2]) foundMax = true;
        }

        if (!foundMin && !foundMax) System.out.println(-1);
        else if (!foundMin) System.out.println(nab[1]);
        else if (!foundMax) System.out.println(nab[2]);
        else {
            for (int i = nab[1]; i <= nab[2]; i++) {
                System.out.println(i);
            }
        }
    }
}