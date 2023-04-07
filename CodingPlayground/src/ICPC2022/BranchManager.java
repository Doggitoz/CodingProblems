    package ICPC2022;

    import java.util.*;
    import java.io.*;


    // Store byte array, a final for CLOSED and GUARANTEED (1, 2 respectively).
    // As you traverse up from the leaf, set all nodes to 2, any child that is lower, traverse down and mark as 1
    @SuppressWarnings({"unchecked"})
    public class BranchManager {
        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        static PriorityQueue<Integer>[] children;
        static int[] parent;
        static byte[] status;
        static final int CLOSED = 1;
        static final int GUARANTEED = 2;
        public static void main(String[] args) throws IOException {
            System.out.println(solve());
        }

        public static int solve() throws IOException {
            int[] nm = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            parent = new int[nm[0] + 1];
            children = new PriorityQueue[nm[0] + 1];
            status = new byte[nm[0] + 1];
            for (int i = 1; i <= nm[0]; i++) {
                children[i] = new PriorityQueue<>();
            }
            for (int i = 1; i < nm[0]; i++) {
                int[] road = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                parent[road[1]] = road[0];
                children[road[0]].add(road[1]);
            }


            // For each query, explore up from the leaf to the root
            int numReached = 0;
            for (int i = 0; i < nm[1]; i++) {
                int q = Integer.parseInt(reader.readLine().trim());
                if (status[q] == CLOSED) return numReached;
                int prev = q;
                int curr = parent[q];
                while (curr != 0) {
                    if (status[curr] == CLOSED) return numReached;
                    status[prev] = GUARANTEED;
                    while (!children[curr].isEmpty() && children[curr].peek() < prev) {
                        closeOff(children[curr].poll());
                    }
                    if (status[curr] == GUARANTEED) break;
                    prev = curr;
                    curr = parent[curr];
                }
                numReached++;
            }
            return numReached;
        }

        static void closeOff(int head) {
            Deque<Integer> stack = new LinkedList<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                int curr = stack.pollLast();
                while (!children[curr].isEmpty()) {
                    stack.add(children[curr].poll());
                }
                status[curr] = CLOSED;
            }
        }
    }
