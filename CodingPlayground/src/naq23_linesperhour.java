import java.io.*;
import java.util.*;

public class naq23_linesperhour {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] parts = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = 0;
        PriorityQueue<Integer> problems = new PriorityQueue<>();

        for (int i = 0; i < parts[0]; i++) {
            int loc = Integer.parseInt(in.readLine());
            problems.add(loc);
        }

        while (!problems.isEmpty() && sum <= parts[1] * 5) {
            if (problems.peek() + sum > parts[1] * 5) break;
            sum += problems.poll();
        }

        System.out.println(parts[0] - problems.size());

    }
}