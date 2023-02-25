import java.io.*;
import java.util.*;

public class Prims {
    // Prims is good at large graphs, but only works on connected graphs

    // Node[] nodes assumes a preconstructed array of nodes from numbers 1 -> nodes.length
    public static void PrimsAlg(Node[] nodes, int start) {
        boolean[] visited = new boolean[nodes.length];
        visited[start - 1] = true;
        int minSum = 0;

        // A priority queue is a heap, which sorts all elements. 
        // This utilizes the Edge compareTo() method, which in our case, sorts from lowest weight to highest
        PriorityQueue<Edge> queue = new PriorityQueue<>(); 

        // Add all edges from the starting node to the queue
        for (Edge e : nodes[start - 1].adj) {
            queue.add(e);
        }
        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            // Lowest edge points to a previously visited node
            if (visited[e.node.label - 1]) {
                continue;
            }
            visited[e.node.label - 1] = true;

            // Add weight of just added edge
            minSum += e.weight;

            // Add all edges from the newly added node to queue
            for (Edge f : e.node.adj) {
                queue.add(f);
            }
        }
        System.out.println(minSum);
    }


    public static class Node {
        int label;
        ArrayList<Edge> adj;
        ArrayList<Integer> weights;

        public Node(int label) {
            this.label = label;
            this.adj = new ArrayList<>();
        }

        // Creates a directed edge from this to Node node
        public void addAdjacency(Node node, int weight) {
            this.adj.add(new Edge(node, weight));
        }
    }

    public static class Edge implements Comparable<Edge> {
        Node node;
        int weight;

        public Edge(Node node, int weight) {
            this.node = node;
            this.weight = weight;    
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight; 
            // if this weight is > o.weight, return > 0. 
            // if this weight is < o.weight, return < 0.
            // if the weights are equal, return 0.
        }

    }
    
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        int[] nm = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        Node[] nodes = new Node[nm[0]];
        for (int i = 0; i < nm[0]; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (int i = 0; i < nm[1]; i++) {
            int[] parts = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            // Creates an edge from a to b, and from b to a. This essential accomplishes an undirected graph
            nodes[parts[0]-1].addAdjacency(nodes[parts[1]-1], parts[2]);
            nodes[parts[1]-1].addAdjacency(nodes[parts[0]-1], parts[2]);
        }
        int start = Integer.parseInt(reader.readLine());
        PrimsAlg(nodes, start);
    }
}
