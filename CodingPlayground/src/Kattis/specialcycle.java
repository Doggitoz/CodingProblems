package Kattis;

//import java.io.*;
import java.util.*;

public class specialcycle {
    public static void main(String[] args) {
        //int[] Nodes;
    }

    // BFS to find smallest cycle given a cycle.    
    public static ArrayList<Integer> GetSimpleCycle(int start, int end) {
        return null;
    }


    static class Node {
        int label;
        ArrayList<Edge> edges;

        Node(int label) {

        }

        void AddEdge(Node o) {
            
        }

    }

    static class Edge {
        Node n1;
        Node n2;
        boolean special;
        Edge(Node n1, Node n2, boolean special) {
            this.n1 = n1;
            this.n2 = n2;
            this.special = special;
        }
    }
}
