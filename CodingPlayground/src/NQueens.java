//Forward tracking
// Jan 12 2023

// The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

import java.util.*;
import java.io.*;

public class NQueens {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        solveNQueens(n);
    }

    static String[][] queenPlacement;
    static int boardDimension;
    static int[][] attackedBoard;

    public static String[][] solveNQueens(int n) {
        attackedBoard = new int[n][n];
        queenPlacement = new String[n][n];
        boardDimension = n;

        for (int i = 0; i < n; i++) {
            Arrays.fill(attackedBoard[i], 0);
            Arrays.fill(queenPlacement[i], ".");
        }

        if (PlaceQueen(0)) {
            System.out.println("works");
        } else {
            System.out.println("doesn't work");
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(queenPlacement[i]).replaceAll(",", ""));
        }

        return queenPlacement;
    }

    public static boolean PlaceQueen(int col) {
        if (col >= boardDimension)
            return true;
        for (int row = 0; row < boardDimension; row++) {
            if(CheckIfAttacked(row, col)) {
                CreateQueen(row, col);
                queenPlacement[row][col] = "X";
                if (PlaceQueen(col + 1)) {
                    return true;
                }
                RemoveQueen(row, col);
                queenPlacement[row][col] = ".";
            }
        }
        return false;
    }

    public static boolean CheckIfAttacked(int row, int col) {
        return (attackedBoard[row][col] == 0);
    }

    public static void CreateQueen(int row, int col) {
        for (int offset = 1; col + offset < boardDimension; offset++) {
            if (attackedBoard[row][col+offset] == 0) {
                attackedBoard[row][col+offset] = col + 1;
            }
            if (row+offset < boardDimension && attackedBoard[row+offset][col+offset] == 0) {
                attackedBoard[row+offset][col+offset] = col + 1;
            }
            if (row-offset >= 0 && attackedBoard[row-offset][col+offset] == 0) {
                attackedBoard[row-offset][col+offset] = col + 1;
            }
        }
    }

    public static void RemoveQueen(int row, int col) {
        for (int offset = 1; col + offset < boardDimension; offset++) {
            if (attackedBoard[row][col+offset] == col + 1) {
                attackedBoard[row][col+offset] = 0;
            }
            if (row+offset < boardDimension && attackedBoard[row+offset][col+offset] == col+1) {
                attackedBoard[row+offset][col+offset] = 0;
            }
            if (row-offset >= 0 && attackedBoard[row-offset][col+offset] == col+1) {
                attackedBoard[row-offset][col+offset] = 0;
            }
        }
    }
}
