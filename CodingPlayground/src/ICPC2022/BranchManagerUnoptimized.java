package ICPC2022;

import java.util.*;
import java.io.*;


// Store byte array, a final for CLOSED and GUARANTEED (1, 2 respectively).
// As you traverse up from the leaf, set all nodes to 2, any child that is lower, traverse down and mark as 1
@SuppressWarnings({"unused", "unchecked"})
public class BranchManagerUnoptimized {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static LinkedList<Integer>[] children;
    static int[] parent;
    static int[] chainParent;
    public static void main(String[] args) throws IOException {
        System.out.println(solve());
    }

    public static int solve() throws IOException {
        int[] nm = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] minBound = new int[nm[0] + 1];
        parent = new int[nm[0] + 1];
        children = new LinkedList[nm[0] + 1];
        chainParent = new int[nm[0] + 1];
        for (int i = 1; i <= nm[0]; i++) {
            children[i] = new LinkedList<>();
        }
        for (int i = 1; i < nm[0]; i++) {
            int[] road = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            parent[road[1]] = road[0];
            children[road[0]].add(road[1]);
        }

        // Tree Reduction
        compressTree(nm[0]);

        // For each query, explore up from the leaf to the root
        int numReached = 0;
        int[] debug = chainParent;
        for (int i = 0; i < nm[1]; i++) {
            int q = Integer.parseInt(reader.readLine().trim());
            int prev = q;
            int curr = parent[q];
            while (curr != 0) {
                if (chainParent[prev] != 0) {
                    if (chainParent[prev] < minBound[curr]) return numReached;
                    minBound[curr] = Math.max(minBound[curr], chainParent[prev]);
                }
                else {
                    if (prev < minBound[curr]) return numReached;
                    minBound[curr] = Math.max(minBound[curr], prev);
                }
                prev = curr;
                curr = parent[curr];
            }
            numReached++;
        }
        return numReached;
    }

    static void compressTree(int n) {
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(1);

        while (!Q.isEmpty()) {
            int curr = Q.poll();
            for (Integer child : children[curr]) {
                Q.add(child);
                if (children[curr].size() == 1) {
                    parent[child] = parent[curr];
                    if (chainParent[curr] == 0) {
                        chainParent[child] = curr;
                    }
                    else {
                        chainParent[child] = chainParent[curr];
                    }
                }
            }
        }
    }
}
