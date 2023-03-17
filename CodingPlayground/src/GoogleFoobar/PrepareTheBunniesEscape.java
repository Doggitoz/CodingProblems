/*
 * You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny workers, but once they're free of the work 
 * duties the bunnies are going to need to escape Lambda's space station via the escape pods as quickly as possible. Unfortunately, the halls of the space 
 * station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies. Fortunately, Commander Lambda has put you in charge of a 
 * remodeling project that will give you the opportunity to make things a little easier for the bunnies. Unfortunately (again), you can't just remove all 
 * obstacles between the bunnies and the escape pods - at most you can remove one wall per escape pod path, both to maintain structural integrity of the station 
 * and to avoid arousing Commander Lambda's suspicions. 
 * 
 * You have maps of parts of the space station, each starting at a work area exit and ending at the door to an escape pod. The map is 
 * represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls. The door out of the station is at the top left (0,0) and 
 * the door into an escape pod is at the bottom right (w-1,h-1). 
 * 
 * Write a function solution(map) that generates the length of the shortest path from the station door to the escape pod, where you are 
 * allowed to remove one wall as part of your remodeling plans. The path length is the total number of nodes you pass through, counting both the 
 * entrance and exit nodes. The starting and ending positions are always passable (0). The map will always be solvable, though you may or may not need to 
 * remove a wall. The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.
 * 
 * Completed in: 2 hrs, 57 mins, 29 secs
 */

import java.util.PriorityQueue;
import java.util.Arrays;

public class PrepareTheBunniesEscape {
    public static int solution(int[][] map) {
        // Your code here
        int h = map.length;
        int w = map[0].length;

        PriorityQueue<Coord> Q = new PriorityQueue<Coord>();

        for (int i = 0; i < h; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("------------------------------");

        // Add start slot to queue
        Q.add(new Coord(0, 0, 1, 1));
        VisitedSlot[][] visited = new VisitedSlot[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                VisitedSlot vs = new VisitedSlot(0, false);
                visited[i][j] = vs;
            }
        }

        // Process search priority queue
        while (!Q.isEmpty()) {
            Coord coord = Q.poll();

            int numRemovals = coord.removals;

            // If new grid spot is out of bounds, continue
            if (coord.x < 0 || h <= coord.x)
                continue;
            if (coord.y < 0 || w <= coord.y)
                continue;

            // If searching spot with a 1
            if (map[coord.x][coord.y] == 1) {
                if (numRemovals == 0) // No removals, can't break wall, invalid search
                    continue;
                numRemovals--;
            }

            // If grid spot has been visited before with a removal
            if (visited[coord.x][coord.y].visitedWithRemoval) {
                continue;
            }

            // If one a spot with a removal, mark it visited with removals
            if (numRemovals > 0) {
                visited[coord.x][coord.y].visitedWithRemoval = true;
            }

            // If searched spot has more moves than stored, overwrite
            if (visited[coord.x][coord.y].v >= coord.steps || visited[coord.x][coord.y].v == 0) {
                visited[coord.x][coord.y].v = coord.steps;
            } else {
                if (visited[coord.x][coord.y].visitedWithRemoval != true) {
                    continue;
                }
            }

            // Search all adjacent grid slots
            Q.add(new Coord(coord.x, coord.y + 1, coord.steps + 1, numRemovals));
            Q.add(new Coord(coord.x, coord.y - 1, coord.steps + 1, numRemovals));
            Q.add(new Coord(coord.x - 1, coord.y, coord.steps + 1, numRemovals));
            Q.add(new Coord(coord.x + 1, coord.y, coord.steps + 1, numRemovals));
        }

        // for (int i = 0; i < h; i++) {
        // for (int j = 0; j < w; j++) {
        // System.out.printf("%d, ", visited[i][j].v);
        // }
        // System.out.print("\n");
        // }

        return visited[h - 1][w - 1].v;
    }

    static class VisitedSlot {
        int v;
        boolean visitedWithRemoval;

        public VisitedSlot(int v, boolean visitedWithRemoval) {
            this.v = v;
            this.visitedWithRemoval = visitedWithRemoval;
        }
    }

    static class Coord implements Comparable<Coord> {

        int x;
        int y;
        int steps;
        int removals;

        public Coord(int x, int y, int steps, int removals) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.removals = removals;
        }

        public int compareTo(Coord c) {
            return this.steps - c.steps;
        }
    }

    public static void main(String[] args) {
        // Test case one:
        int[][] caseOne = { { 0, 1, 1, 0 }, { 0, 0, 0, 1 }, { 1, 1, 0, 0 }, { 1, 1, 1, 0 } };
        System.out.println(solution(caseOne));
        // Expected outcome: 7

        // Test case two:
        int[][] caseTwo = { { 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0 } };
        System.out.println(solution(caseTwo));
        // Expected outcome: 11

        // Test case three:
        int[][] caseThree = { { 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0 } };
        System.out.println(solution(caseThree));
        // Expected outcome: 0
        // Case where it's impossible

        // Test case four:
        int[][] caseFour = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 } };
        System.out.println(solution(caseFour));
        // Expected outcome: 31
        // Case where the grid is REALLY BIG

        // Test case five:
        int[][] caseFive = { { 0, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 0 } };
        System.out.println(solution(caseFive));
        // Expected outcome: 8
        // Case where the path can be reached in the same number of moves both going
        // through a wall and not going through a wall

        // Test case six:
        int[][] caseSix = { { 0, 0, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1, 1 },
                { 0, 0, 0, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0 } };
        System.out.println(solution(caseSix));
        // Expected outcome: 11, Possible failure outcome: 15
        // Case where the path can be reached in the same number of moves both going
        // through a wall and not going through a wall, but requires a move later

        int[][] caseSeven = { { 0, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0 },
                { 0, 0, 0, 0, 1, 0 },
                { 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 0 } };
        System.out.println(solution(caseSeven));
        // Expected outcome: 11, Possible failure outcome: 15
        // Previous case but rotated

        int[][] caseEight = { { 0, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 0 },
                { 1, 0, 0, 0, 1, 0 },
                { 1, 0, 0, 0, 1, 0 },
                { 1, 0, 0, 0, 1, 0 },
                { 1, 0, 0, 0, 1, 0 } };
        System.out.println(solution(caseEight));
        // Expected outcome: 11
        // A case with a big empty area to get stuck in

        int[][] caseNine = { { 0, 0, 0, 0 },
                { 1, 1, 1, 0 },
                { 1, 1, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 1, 1, 1 },
                { 0, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 0, 1, 0 },
                { 1, 0, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 1, 1, 1 },
                { 0, 0, 0, 0 } };
        System.out.println(solution(caseNine));
        // Expected outcome: 21
        // Double walls and intricate exploration

        int[][] caseTen = { { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        System.out.println(solution(caseTen));
        // Expected outcome: 39
        // Found this shit on the internet idfk

        int[][] caseEleven = { { 0 } };
        System.out.println(solution(caseEleven));
        // Expected outcome: 1
        // It's literally size one

        int[][] caseTwelve = {
                { 0, 0, 1, 1 },
                { 1, 0, 0, 1 },
                { 1, 0, 0, 1 },
                { 1, 1, 1, 0 }
        };
        System.out.println(solution(caseTwelve));
        // Expected outcome: 0
        // It's impossible, but fancier
    }
}
