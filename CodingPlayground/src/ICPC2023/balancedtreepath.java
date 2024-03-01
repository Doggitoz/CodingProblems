package ICPC2023;
import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class balancedtreepath {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        ArrayList<Integer>[] ja = new ArrayList[n];
        char[] val = in.readLine().toCharArray();

        // Init jagged array
        for (int i = 0; i < n; i++) {
            ja[i] = new ArrayList<>();
        }

        // Populate jagged array
        for (int i = 0; i < n - 1; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ja[row[0]-1].add(row[1]-1);
            ja[row[1]-1].add(row[0]-1);
        }

        int paths = 0;

        // DFS
        for (int i = 0; i < n; i++) {
            paths += DFS(val, new LinkedList<Character>(), ja, new boolean[n], i);
        }
        System.out.println(paths);
    }

    static int DFS(char[] val, LinkedList<Character> stack, ArrayList<Integer>[] ja, boolean[] visited, int n) {
        visited[n] = true;
        char removed = 'a';
        // If we received a closing char
        if (val[n] == ')' || val[n] == '}' || val[n] == ']') {
            if (stack.isEmpty()) return 0;
            // If does not match top of stack, return 0.
            switch (val[n]) {
                case ')':
                    if (stack.peekLast() != '(') return 0;
                    break;
                case ']':
                    if (stack.peekLast() != '[') return 0;
                    break;
                case '}':
                    if (stack.peekLast() != '{') return 0;
                    break;
            }
            removed = stack.removeLast();
        }
        else {
            stack.add(val[n]);
        }

        int sumOfChildren = 0;
        if (stack.isEmpty()) sumOfChildren++; // Add one for combo w/o children
        for (int i = 0; i < ja[n].size(); i++) {
            if (visited[ja[n].get(i)]) continue;
            sumOfChildren += DFS(val, stack, ja, visited, ja[n].get(i));
        }

        if (removed == 'a') stack.removeLast();
        else stack.add(removed);

        return sumOfChildren;
    }
}