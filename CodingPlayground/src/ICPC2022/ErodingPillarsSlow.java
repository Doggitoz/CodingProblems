package ICPC2022;

import java.util.*;
import java.io.*;

@SuppressWarnings({"unchecked"})
public class ErodingPillarsSlow {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Jump> jumps;
    static Pillar[] pillars;
    static boolean[] severedFromStart;
    static boolean[][] connected;
    static LinkedList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        System.out.println(String.format("%.15f", solve()));
    }
    public static double solve() throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        jumps = new ArrayList<>((n + 1) * (n + 1));
        pillars = new Pillar[n + 1];
        severedFromStart = new boolean[n + 1];
        connected = new boolean[n + 1][n + 1];
        adj = new LinkedList[n + 1];
        
        // Record all pillar locations
        pillars[0] = new Pillar(0, 0, 0);
        adj[0] = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            int[] coords = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Pillar p = new Pillar(coords[0], coords[1], i);
            adj[i] = new LinkedList<>();
            pillars[i] = p;
        }

        // Calculate all distances between pillars
        double max = Double.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Jump jmp = new Jump(pillars[i], pillars[j]);
                if (jmp.dist > max) {
                    continue;
                }
                connected[i][j] = true;
                connected[j][i] = true;
                if (i != j && j != 0)
                    adj[i].add(j);
                if (j > i) {
                    jumps.add(jmp);
                }
            }
            if (i == 0) {
                Collections.sort(jumps);
                max = jumps.get(0).dist;
            }
        }
        
        Collections.sort(jumps);
        for (int i = 0; i < jumps.size(); i++) {
            Jump j = jumps.get(i);

            connected[j.p1][j.p2] = false;
            connected[j.p2][j.p1] = false;
            if (j.p1 == 0) {
                severedFromStart[j.p2] = true;
            }

            if (!valid(j.p1)) return j.dist;
            if (!valid(j.p2)) return j.dist;
        }
        return -1;
    }

    // DFS in "shells"
    // Add all adj from start at once
    // Process all adj from edges away 1
    // Add all adj from edges away 1
    static boolean valid(int start) {
        if (!severedFromStart[start]) return true;
        Deque<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[pillars.length];
        visited[start] = true;
        int size = adj[start].size();
        int numPaths = 0;
        for (int i = 0; i < size; i++) {
            int next = adj[start].poll(); 
            if (!connected[start][next]) continue;
            adj[start].add(next);
            stack.add(next);
            visited[next] = true;
            if (!severedFromStart[next]) {
                numPaths++;
                if (numPaths >= 2) return true;
            }
        }

        while (!stack.isEmpty()) {
            if (numPaths >= 2) return true;
            if (stack.size() == 1 && numPaths == 0) return false;
            Deque<Integer> newStack = new LinkedList<>();
            while (!stack.isEmpty()) {
                if (numPaths >= 2) return true;
                int curr = stack.pollLast();
                size = adj[curr].size();
                for (int i = 0; i < size; i++) {
                    int next = adj[curr].poll();
                    if (!connected[curr][next]) continue;
                    adj[curr].add(next);
                    if (visited[next]) continue;
                    newStack.add(next);
                    visited[next] = true;
                    if (!severedFromStart[next]) {
                        numPaths++;
                        if (numPaths >= 2) return true;
                    }
                } 
            }
            stack = newStack;
        }
        
        return false;
    }

    static double dist(Pillar p1, Pillar p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    // Tuple class
    static class Pillar {
        int x;
        int y;
        int num;
        public Pillar(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    // Edge class
    static class Jump implements Comparable<Jump> {
        int p1;
        int p2;
        double dist;

        public Jump(Pillar one, Pillar two) {
            p1 = one.num;
            p2 = two.num;
            dist = dist(one, two);
        }
        
        public int compareTo(Jump o) {
            return (int) Math.signum(o.dist - this.dist);
        }
    }
}
