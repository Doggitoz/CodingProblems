package NAQ23;
import java.io.*;
import java.util.*;

public class contestadvancement {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] nkc = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] teams = new int[nkc[0]];
        Queue<Integer> backup = new LinkedList<>();
        int teamsDrafted = 0;
        for (int i = 0; i < nkc[0]; i++) {
            if (teamsDrafted == nkc[1]) break;
            int parts[] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (teams[parts[1]] + 1 > nkc[2])
                backup.add(parts[0]);
            else {
                teams[parts[1]]++;
                System.out.println(parts[0]);
                teamsDrafted++;
            }
        }

        while (!backup.isEmpty()) {
            if (teamsDrafted == nkc[1]) break;
            System.out.println(backup.poll());
            teamsDrafted++;
        }
    }
}