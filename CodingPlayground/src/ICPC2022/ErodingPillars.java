package ICPC2022;

import java.util.*;
import java.io.*;

@SuppressWarnings({"unchecked"})
public class ErodingPillars {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Jump> jumps;
    static Pillar[] pillars;
    static boolean[] severedFromStart;
    static int[] pointer;
    static LinkedList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        System.out.println(String.format("%.15f", solve()));
    }
    public static double solve() throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        jumps = new ArrayList<>((n + 1) * (n + 1));
        pillars = new Pillar[n + 1];
        adj = new LinkedList[n + 1];
        pointer = new int[n + 1];
        
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
                if (j > i) {
                    jumps.add(jmp);
                }
            }
            if (i == 0) {
                Collections.sort(jumps);
                max = jumps.get(jumps.size()).dist;
            }
        }
        
        Collections.sort(jumps);
        for (int i = 0; i < jumps.size(); i++) {
            Jump j = jumps.get(i);
            adj[j.p1].add(j.p2);
            adj[j.p2].add(j.p1);
        }
        return -1;
    }

    static boolean DFS(int node) {
        
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
