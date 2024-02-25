package Util;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public class UtilAlternatives {
    public static void main(String[] args) {
        int[] arr = new int[10];
        ArrayList<Integer> al = new ArrayList<>();
        // Essentially doing new Integer[10]
        // Will do al.add(10) as al.add(Integer.of(10))
        // Arraylists almost quadruple our memory footprint
        // Arraylists leave a lot of garbage around


        // Convert a 2d matrix into a 1d matrix
        int[][] mat = new int[10][12];
        int[] mat2 = new int[mat.length * mat[0].length];
        //mat2[12 * row + col];
        //mat2[idx % 12 = row number, idx / 12 = column number]
    }

    // Arraylist implementation using primitives
    static class DynamicArray {
        
        int[] arr = new int[10];
        // Don't hide arr access behind getters. THIS IS MUCH MUCH SLOWER
        // No setters, getters. Just add.

        int idx = 0;

        // could use a constructor

        public void append(int x) {
            if (idx == arr.length) {
                this.grow();
            }
            this.arr[idx] = x;
            idx++;
        }

        public void grow() {
            int[] temp = this.arr;
            this.arr = new int[temp.length * 2];
            System.arraycopy(temp, 0, this.arr, 0, temp.length);
            // Not a method, this is a VM call. All System calls are VM calls.
            // These are handled natively by Java
        }

        // Could remove and insert an element by array shifting. Could add these methods?
        // This could be done using an array copy, similar to grow.
    }

    static class LinkedList {

        static Node head;
        static int size = 0;

        static class Node {
            //Whatever data type you want
            int data;
            Node next;

            Node(int d) {
                data = d;
                next = null;
            }
        }

        public static void insert(int data) {
            Node newNode = new Node(data);
            size++;

            if (head == null) {
                head = newNode;
                return;
            }
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
            return;
        }

        public static int poll() {
            if (size == 0) throw new RuntimeException("LinkedList is empty");
            size--;
            Node temp = head;
            if (size > 1) {
                head = head.next;
            }
            else {
                head = null;
            }
            return temp.data;
        }

        public static int peek() {
            return head.data;
        }

    }
}
