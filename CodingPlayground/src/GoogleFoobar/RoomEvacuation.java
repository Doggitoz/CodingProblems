/*
 * You are now the fire marshal. It is not a fun job to have. You have a layout of a room in the building as a 2D grid. 
 * There are known locations that people will occupy, there are known locations that people cannot walk into or out of, 
 * and there are known locations that are exits. You know that two or more people cannot occupy the same cell of the 2D 
 * grid at the same time. You know how quickly everyone needs to evacuate the room in seconds. Asssume that the occupants 
 * can only move in one of the four cardinal directions (i.e., North, South, East, or West), and can make one move per second. 
 * You can assume that although only one person can stand in the exit at a time, a person in the exit is safe, and of course 
 * anyone past the exit is safe.
 * 
 * Given the layout of the room and the desired time to evacuate, determine how many people can get out safely.
 */


import java.io.*;
import java.util.*;

public class RoomEvacuation {
    
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] nmt = Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[][] grid = new String[nmt[0]][nmt[1]];
        for (int i = 0; i < nmt[0]; i++) {
            grid[i] = reader.readLine().trim().split("");
        }
        Solve(nmt[0], nmt[1], nmt[2], grid);
    }

    public static void Solve(int n, int m, int t, String[][] grid) {
        System.out.println("");
        List<Person> people = new ArrayList<Person>();
        List<Coord> exits = new ArrayList<Coord>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j].equals("P")) {
                    Person newPerson = new Person(j, i);
                    people.add(newPerson);
                }
                if (grid[i][j].equals("E")) {
                    Coord coord = new Coord(j, i, 0);
                    exits.add(coord);
                }
            }
        }
        for (int i = 0; i < people.size(); i++) {
            int[][] visited = new int[n][m];
            PriorityQueue<Coord> Q = new PriorityQueue<Coord>();
            Coord start = new Coord(people.get(i).x, people.get(i).y, 0);
            Q.add(start);

            while (!Q.isEmpty()) {
                Coord coord = Q.poll();

                // If new grid spot is out of bounds, continue
                if (coord.x < 0 || m <= coord.x)
                    continue;
                if (coord.y < 0 || n <= coord.y)
                    continue;

                if (grid[coord.y][coord.x].equals("#")) {
                    continue;
                }
                // If slot is lower or not visited, set new value. Otherwise, continue
                if (visited[coord.y][coord.x] >= coord.steps || visited[coord.y][coord.x] == 0) {
                    visited[coord.y][coord.x] = coord.steps;
                }
                else {
                    continue;
                }

                Q.add(new Coord(coord.x, coord.y + 1, coord.steps + 1));
                Q.add(new Coord(coord.x, coord.y - 1, coord.steps + 1));
                Q.add(new Coord(coord.x - 1, coord.y, coord.steps + 1));
                Q.add(new Coord(coord.x + 1, coord.y, coord.steps + 1));
            }

            int minimum = Integer.MAX_VALUE;
            for (int j = 0; j < exits.size(); j++) {
                if (visited[exits.get(j).y][exits.get(j).x] < minimum) {
                    minimum = visited[exits.get(j).y][exits.get(j).x];
                }
            }
            people.get(i).movesToEscape = minimum;
        }

        Collections.sort(people);
        //ArrayList<Person> conflicts = new ArrayList<Person>();
        for (int i = 0; i < people.size(); i++) {
            System.out.printf("Person %d has %d moves to escape\n", i + 1, people.get(i).movesToEscape);
        }

    }

    public static class Person implements Comparable<Person> {
        int x;
        int y;
        int movesToEscape;

        public Person(int x, int y) {
            movesToEscape = 0;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Person p) {
            System.out.println(this.movesToEscape < p.movesToEscape ? 1 : 0);
            //return Integer.compare(this.movesToEscape, p.movesToEscape);
            return this.movesToEscape - p.movesToEscape;
        }

    }

    public static class Coord implements Comparable<Coord> {
        int x;
        int y;
        int steps;
        public Coord(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }

        public int compareTo(Coord c) {
            return this.steps - c.steps;
        }
    }
}

/*
 * Test case:
 * 
 * 
4 5 3
.....
..P#.
..PPE
..P.E
 * 
 * Result: 4
 */
