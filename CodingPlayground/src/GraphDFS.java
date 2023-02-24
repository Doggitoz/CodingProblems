import java.util.*;
import java.io.*;

public class GraphDFS {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 2);
        g.addEdge(2, 1);
        g.addEdge(4, 3);
        g.addEdge(3, 2);
        g.DFS(4);
    }

    public static class Graph {
        int V; //Num vertices
        LinkedList<Integer> adj[];
        LinkedList<Integer> pointFrom[];

        @SuppressWarnings("unchecked") Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        // Make a connection between two nodes. Directional, v -> w
        void addEdge(int v, int w) {
            System.out.printf("Adding edge from %d to %d\n", v, w);
            adj[v].add(w);
        }

        void DFSUtil(int v, boolean[] visited) {
            System.out.println(v);
            visited[v] = true;

            //Recur for all vertices adjacent
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    DFSUtil(n, visited);
                }
            }
        }

        void DFS(int start) {
            boolean[] visited = new boolean[V];

            DFSUtil(start, visited);
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    DFSUtil(i, visited);
                }
            }
        }
        void ReduceCycles() {

        }
    }
}
