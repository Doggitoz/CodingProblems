import java.util.Arrays;

//Problem from Google foo.bar challenge
/*
 * As a henchman on Commander Lambda's space station, you're expected to be resourceful, smart, and a quick thinker. It's not easy building a doomsday device and ordering the bunnies around at the same time, after all! In order to make sure that everyone is sufficiently quick-witted, Commander Lambda has installed new flooring outside the henchman dormitories. It looks like a chessboard, and every morning and evening you have to solve a new movement puzzle in order to cross the floor. That would be fine if you got to be the rook or the queen, but instead, you have to be the knight. Worse, if you take too much time solving the puzzle, you get "volunteered" as a test subject for the LAMBCHOP doomsday device!

To help yourself get to and from your bunk every day, write a function called solution(src, dest) which takes in two parameters: the source square, on which you start, and the destination square, which is where you need to land to solve the puzzle.  The function should return an integer representing the smallest number of moves it will take for you to travel from the source square to the destination square using a chess knight's moves (that is, two squares in any direction immediately followed by one square perpendicular to that direction, or vice versa, in an "L" shape).  Both the source and destination squares will be an integer between 0 and 63, inclusive, and are numbered like the example chessboard below:

-------------------------
| 0| 1| 2| 3| 4| 5| 6| 7|
-------------------------
| 8| 9|10|11|12|13|14|15|
-------------------------
|16|17|18|19|20|21|22|23|
-------------------------
|24|25|26|27|28|29|30|31|
-------------------------
|32|33|34|35|36|37|38|39|
-------------------------
|40|41|42|43|44|45|46|47|
-------------------------
|48|49|50|51|52|53|54|55|
-------------------------
|56|57|58|59|60|61|62|63|
-------------------------

-- Java cases --
Input:
Solution.solution({3, 1, 4, 1})
Output:
    4311

    Input:
Solution.solution({3, 1, 4, 1, 5, 9})
Output:
    94311

 */

public class KnightTour {
    public static int[][] minimumMoves = new int[8][8];

    public static int solution(int src, int dest) {
        // Your code here

        // initialize board as -1 moves (unchecked)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                minimumMoves[i][j] = -1;
            }
        }

        // coordiantes for stuff
        Coordinate start = new Coordinate(src);
        Coordinate end = new Coordinate(dest);

        // set starting point as 0
        minimumMoves[start.y][start.x] = 0;

        // KnightsMove(0, start.y, start.x);

        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(minimumMoves[i]));
        }

        return minimumMoves[end.y][end.x];
    }

    public static void KnightsMove(int moveNum, int x, int y) {
        if (x > 7 || x < 0) {
            return;
        }
        if (y > 7 || y < 0) {
            return;
        }
        if (moveNum > 6) {
            return;
        }

        if (minimumMoves[x][y] == -1) {
            minimumMoves[x][y] = moveNum;
        }
        if (moveNum < minimumMoves[x][y]) {
            minimumMoves[x][y] = moveNum;
        }

        KnightsMove(moveNum + 1, x + 1, y + 2);
        KnightsMove(moveNum + 1, x - 1, y + 2);
        KnightsMove(moveNum + 1, x + 1, y - 2);
        KnightsMove(moveNum + 1, x - 1, y - 2);

        KnightsMove(moveNum + 1, x + 2, y + 1);
        KnightsMove(moveNum + 1, x + 2, y - 1);
        KnightsMove(moveNum + 1, x - 2, y + 1);
        KnightsMove(moveNum + 1, x - 2, y - 1);

    }

    public static void main(String[] args) {
        System.out.println(solution(0, 1));
    }

}

class Coordinate {
    int x;
    int y;

    public Coordinate(int slot) {
        this.x = slot % 8;
        this.y = (int) Math.ceil(slot / 8);
    }

    public void printCoordinates() {
        System.out.printf("(%d, %d)\n", x + 1, y + 1);
    }
}