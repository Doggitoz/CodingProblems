package ICPC2022Qualifiers;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class MovieNight {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer>[] children; // Max size will be (n + 1) + n/2 (if we care about initializing)
    static ArrayList<Integer> roots;
    static long[] dp;
    static int[] childIndex;
    final static long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        children = new ArrayList[2 * n]; // Any reduced cycle will be at index > n
        roots = new ArrayList<>();
        dp = new long[2 * n];
        childIndex = new int[2 * n];

        // Initialize jagged array
        for (int i = 1; i < 2 * n; i++) {
            children[i] = new ArrayList<>();
        }

        // Establish all parent and child relations
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(reader.readLine().trim());
            children[parent].add(i);
        }

        // Cycle reduction (creates new nodes and collects all children from cycle nodes)
        DFS(n);

        // Sums all disconnected graphs
        long product = 1;
        for (Integer i : roots) {
            product = mul(product, dp[i]);
        }

        product = sub(product, 1);
        System.out.print(product);
    }

    private static long mul(long a, long b) {
        return (a * b) % MOD;
    }

    private static long sub(long a, long b) {
        if (a < b) {
            return (a + MOD - b) % MOD;
        }
        return (a - b) % MOD;
    }

    private static long add(long a, long b) {
        return (a + b) % MOD;
    }

    static void DFS(int n) {
        int cyclesFound = 0;
        boolean[] visited = new boolean[children.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < visited.length; i++) {
            if (i > n + cyclesFound) break;
            if (!visited[i]) stack.push(i);
            visited[i] = true;
            int dfsRoot = i;
            while (!stack.isEmpty()) {
                int curr = stack.peek();

                // Is a leaf node
                if (children[curr].isEmpty()) {
                    dp[curr] = 2;
                    stack.pop();
                    continue;
                }
                
                // Search all children for unvisited
                boolean skip = false;
                for (int j = childIndex[curr]; j < children[curr].size(); j++) {
                    int child = children[curr].get(j);
                    childIndex[curr]++;
                    // Cycle found, reduce to one node
                    if (child == dfsRoot) {
                        cyclesFound++;
                        ArrayList<Integer> childrenOfCycle = new ArrayList<>();
                        int nodeToIgnore = dfsRoot;
                        while (!stack.isEmpty()) {
                            int currCycleNode = stack.pop();
                            for (Integer cycleChild : children[currCycleNode]) {
                                if (cycleChild == nodeToIgnore) continue;
                                childrenOfCycle.add(cycleChild);
                            }
                            nodeToIgnore = currCycleNode;
                            visited[currCycleNode] = true;
                        }
                        children[n + cyclesFound] = childrenOfCycle;
                        roots.add(n + cyclesFound);
                        stack.push(n + cyclesFound);
                        visited[n + cyclesFound] = true;
                        skip = true;
                        break;
                    }

                    if (visited[child]) continue;
                    skip = true;
                    stack.push(child);
                    visited[child] = true;
                    break;
                }
                if (skip) continue;

                // If node has no more children to search, calculate its dp
                if (children[curr].size() == 1) {
                    dp[curr] = add(dp[children[curr].get(0)], 1);
                }
                else {
                    long product = 1;
                    for (Integer child : children[curr]) {
                        product = mul(product, dp[child]);
                    }
                    dp[curr] = add(product, 1);
                }
                stack.pop();

            }
        }
    }
}
