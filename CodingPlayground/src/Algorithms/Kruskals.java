package Algorithms;

import java.util.*;
import java.io.*;

public class Kruskals {
    // Kruskals is faster on smaller graphs. Kruskals also works on disconnected graphs, whereas Prims does not.

    public static int solve(int gNodes, ArrayList<Edge> edges) {
        // Sort the edges using Edges.compareTo
        Collections.sort(edges);
        int minSum = 0;
        Node[] nodes = new Node[gNodes];

        // Create a node for each index 0 -> num of graph nodes
        for (int n = 0; n < gNodes; n++) {
            nodes[n] = new Node(n);
        }

        
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            Node fromRoot = nodes[e.from - 1].find();
            Node toRoot = nodes[e.to - 1].find();
            if (fromRoot == toRoot) {
                continue;
            }
            minSum += e.weight;
            if (fromRoot.label < toRoot.label) {
                nodes[e.to - 1].union(fromRoot);
            } else {
                nodes[e.from - 1].union(toRoot);
            }
        }
        
        return minSum;
    }
    
    public static class Node {
        int label;
        Node parent;
        
        public Node(int label) {
            this.label = label;
        }
        
        public Node find() {
            if (this.parent == null) {
                return this;
            }
            this.parent = this.parent.find();
            return this.parent;
        }
        
        public void union(Node root) {
            if (this.parent == null) {
                this.parent = root;
                return;
            }
            this.parent.union(root);
            this.parent = root;
        }
    }

    
    private static class Edge implements Comparable<Edge> { 
        int from;
        int to;
        int weight;
        
        
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        // Return the smaller of the two edge weights
        public int compareTo(Edge e) {
            if (this.weight == e.weight) {
                int thisTieBreak = this.from + this.to + this.weight;
                int eTieBreak = e.from + e.to + e.weight;
                if (thisTieBreak <= eTieBreak) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return this.weight - e.weight;
        }
        
        public String toString() {
            return String.format("(%d, %d, %d)", this.from, this.to, this.weight);
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] parts = reader.readLine().trim().split(" ");
        int gNodes = Integer.parseInt(parts[0]); // Num of graph nodes
        int gEdges = Integer.parseInt(parts[1]); // Num of graph edges
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < gEdges; i++) {
            parts = reader.readLine().trim().split(" ");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            edges.add(new Edge(from, to, weight));
        }
        System.out.printf("%d\n", Kruskals.solve(gNodes, edges));
        
    }
}
