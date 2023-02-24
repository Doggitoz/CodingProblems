import java.util.PriorityQueue;

/*
 * GENERAL PLAN: 
 * Given point P and point E, find north distance from each and combine them. This is the scale factor for all NE and NW shots
 * For both east and west, find distance. This will be the scale factor for E and W
 * Now, for each NE and NW direction, keep incrementing E and W to get bigger and bigger slope.
 * Once this distance is > max distance, reset E/W and incremement N. (IMPORTANT TO STILL ADD THE FIRST INSTANCE OF OVER MAX DISTANCE I THINK)
 * Repeat this process until first iteration of N is too big
 * 
 * Repeat this process for south now. This should handle all 360 radial angles possible
 */



public class Solution {
    public static int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {
        PriorityQueue<Coord> Q = new PriorityQueue<Coord>();

        // LOGIC TO ADD ALL INITIAL 
        // Add all primary cardinal directions (weird edge cases w how my formula works)
        //Q.add(new Coord(distance, your_position[0], your_position[1], new DirVect(0, 1)));
        //Q.add(new Coord(distance, your_position[0], your_position[1], new DirVect(0, -1)));
        //Q.add(new Coord(distance, your_position[0], your_position[1], new DirVect(1, 0)));
        //Q.add(new Coord(distance, your_position[0], your_position[1], new DirVect(-1, 0)));

        // Will need to add one case that is just shooting straight at the enemy
        DirVect straightToEnemy = new DirVect((int)(trainer_position[0]-your_position[0]), (int)(trainer_position[1]-your_position[0]));
        Q.add(new Coord(distance, your_position[0], your_position[1], straightToEnemy));

        // Calculate the distance required for wall bounce logic
        int distanceNorth = dimensions[1] - your_position[1] + dimensions[1] - trainer_position[1];
        int distanceSouth = your_position[1] + trainer_position[1];

        System.out.printf("Distance north: %d\nDistance south: %d\n", distanceNorth, distanceSouth);

        int numDirections = 0;

        while (!Q.isEmpty()) {
            Coord curr = Q.poll();

            // Distance from this point to enemy is too much for range
            if (Distance(curr, your_position[0], your_position[1]) > curr.range) {
                continue;
            }

            Coord hit = null;

            // If ray hits you
            if (HitsPoint(curr, your_position[0], your_position[1], hit)) {
                continue;
            }

            // If ray hits enemy
            if (HitsPoint(curr, trainer_position[0], trainer_position[1], hit)) {
                numDirections++;
                continue;
            }

            // Calculate new bounce location, flip vector, add to Q

        }


        return numDirections;
    }

    public static boolean HitsPoint(Coord a, int x, int y, Coord b) {
        // https://stackoverflow.com/questions/328107/how-can-you-determine-a-point-is-between-two-other-points-on-a-line-segment

        float crossProduct = (y - a.y) * (b.x - a.x) - (x - a.x) * (b.y - a.y);

        if (Math.abs(crossProduct) != 0) {
            return false;
        }

        float dotProduct = (x - a.x) * (b.x - a.x) + (y - a.y) * (b.y - a.y);
        if (dotProduct < 0) {
            return false;
        }

        float squaredLengthba = (b.x - a.x)*(b.x - a.x) + (b.y - a.y)*(b.y - a.y);
        if (dotProduct > squaredLengthba) {
            return false;
        }

        //Prevents initial location from triggering hit
        if (a.x == x && a.y == y) {
            return false;
        }

        return true;
    }

    //public static Coord Bounce()

    public static double Distance(Coord a, Coord b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    public static double Distance(Coord a, int x, int y) {
        return Math.sqrt(Math.pow(x - a.x, 2) + Math.pow(y - a.y, 2));
    }

    static class Coord implements Comparable<Coord> {
        float range;
        float x;
        float y;
        DirVect dir;

        public Coord(float range, int x, int y, DirVect dir) {
            this.range = range;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public int compareTo(Solution.Coord o) {
            return 0;
        }
    }

    static class DirVect {
        int x;
        int y;

        public DirVect(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        // Test case one: [3,2], [1,1], [2,1], 4

        //System.out.println(solution(new int[]{3, 2}, new int[]{1, 1}, new int[]{2, 1}, 4));

        // Expected outcome: 7

        // Distance test:
        // Coord a = new Coord(0, 1, 1, new DirVect(0, 0));
        // Coord b = new Coord(0, 2, 3, new DirVect(0, 0));
        // System.out.println(Distance(a, b));

        // Hit point test:
        // Coord a = new Coord(0, 1, 1, new DirVect(1, 0));
        // Coord b = new Coord(0, 3, 1, new DirVect(0, 0));
        // System.out.println(HitsPoint(a, 1, 1, b));
    }

}