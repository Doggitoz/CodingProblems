import java.util.*;
import java.io.*;

public class CycleDetection {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        boolean isCycle = false;
        Graph g = new Graph(7);
        g.addEdge(1, 4);
        g.addEdge(5, 6);
        g.addEdge(6, 2);
        g.addEdge(2, 1);
        g.addEdge(3, 2);
        if (isCycle) {
            g.addEdge(4, 5);
            g.addEdge(4, 3);
        }
        System.out.println(g.isCyclic());
    }

    public static class Graph {
        int V; //Num vertices
        List<List<Integer>> adj;
        LinkedList<Integer> pointFrom[];

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(V); //This has issues if you dont start with node = 0
            for (int i = 0; i < v; i++) {
                adj.add(new LinkedList<>());
            }
        }

        // Make a connection between two nodes. Directional, source -> dest
        void addEdge(int source, int dest) {
            adj.get(source).add(dest);
        }

        
        boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
            if (recStack[i]) {
                return true;
            }
            if (visited[i]) {
                return false;
            }
            visited[i] = true;
            recStack[i] = true;
            List<Integer> children = adj.get(i);

            for (Integer c: children) {
                if (isCyclicUtil(c, visited, recStack)) {
                    return true;
                }
            }
            recStack[i] = false;

            return false;
        }

        boolean isCyclic() {
            boolean[] visited = new boolean[V];
            boolean[] recStack = new boolean[V];


            for (int i = 0; i < V; i++) {
                if (isCyclicUtil(i, visited, recStack)) {
                    return true;
                }
            }
            return false;
        }

        
    }
}
