import java.util.*;
import java.io.*;

public class Djikstras {
    // Djikstras finds the shortest path from one node to another node --- O(E log(V))
    
    static void DjikstrasAlg(Node[] nodes, int n, int s) {
        PriorityQueue<Edge> Q = new PriorityQueue<>();
        // Visited stores the lowest number it takes to reach node at index. Init at -1 to indicate never visited
        int[] visited = new int[n]; 
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        // Flag starting node as 0
        visited[s - 1] = 0;

        // Add all connected edges from starting graph to queue
        while (!nodes[s - 1].isEmpty()) {
            Q.add(nodes[s - 1].popEdge());
        }
        
        while(!Q.isEmpty()) {
            Edge e = Q.poll();
            // Both the node from and to are visited already, so continue
            if (visited[e.from - 1] > -1 && visited[e.to - 1] > -1) {
                continue;
            }
            // stores the node label of the unvisited node
            int next = (visited[e.from - 1] > -1) ? e.to : e.from;

            // record the visited length and add all of its adjacent edges
            visited[next - 1] = e.length;
            while(!nodes[next - 1].isEmpty()) {
                Edge ne = nodes[next - 1].popEdge();
                if (visited[ne.from - 1] > -1 && visited[ne.to - 1] > -1) {
                    continue;
                }
                ne.length += visited[next - 1];
                Q.add(ne);
            }
        }
        // Every node should now be empty
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < visited.length; i++) {
            if (i == s - 1) {
                continue;
            }
            str.append(visited[i]);
            str.append(' ');
        }
        System.out.println(str.toString());
    }
    
    static class Node{
        int label;
        ArrayList<Edge> edges;
        
        public Node(int label) {
            this.label = label;
            this.edges = new ArrayList<>();
        }
        
        public void addEdge(Edge e) {
            this.edges.add(e);
        }
        
        public boolean isEmpty() {
            return this.edges.isEmpty();
        }
        
        public Edge popEdge() {
            return this.edges.remove(this.edges.size() - 1);
        }
        
    }
    
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int length;
        
        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }
        
        public int compareTo(Edge e) {
            return this.length - e.length;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        Node[] nodes = new Node[3000];
        for (int tk = 0; tk < t; tk++) {
            String[] parts = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            for (int e = 0; e < m; e++) {
                parts = reader.readLine().trim().split(" ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                int z = Integer.parseInt(parts[2]);
                if (nodes[x-1] == null) {
                    nodes[x-1] = new Node(x);
                }
                if (nodes[y-1] == null) {
                    nodes[y-1] = new Node(y);
                }
                Edge edge = new Edge(x, y, z);
                nodes[x-1].addEdge(edge);
                nodes[y-1].addEdge(edge);
            }
            int s = Integer.parseInt(reader.readLine().trim());
            DjikstrasAlg(nodes, n, s);
            if (tk + 1 == t) {
                continue;
            }
            //SAFETY CHECK FOR NODES
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] == null) {
                    break;
                }
                while (!nodes[i].isEmpty()) {
                    nodes[i].popEdge();
                }
            }
        }
    }
}
