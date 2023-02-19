import java.util.PriorityQueue;

public class Solution {
    public static int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {
        PriorityQueue<Coord> Q = new PriorityQueue<Coord>();
        return -1;
    }

    public boolean hitsPoint(Coord c, int x, int y) {
        return false;
    }

    class Coord {
        float range;
        int x;
        int y;
        DirVect dir;

        public Coord(float range, int x, int y, DirVect dir) {
            this.range = range;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    class DirVect {
        int x;
        int y;

        public DirVect(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

    }

}