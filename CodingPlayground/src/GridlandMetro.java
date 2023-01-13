// Search Algorithm
// Made during WinterBash

/* The city of Gridland is represented as an n x m matrix where the rows are numbered from 1 to n
and the columns are numbered from 1 to m.

Gridland has a network of train tracks that always run in straight horizontal lines along a row. 
In other words, the start and end points of a train track are (r, c1) and (r, c2), where  represents the row number,  
represents the starting column, and  represents the ending column of the train track.

The mayor of Gridland is surveying the city to determine the number of locations where lampposts can be placed. 
A lamppost can be placed in any cell that is not occupied by a train track.

Given a map of Gridland and its k train tracks, find and print the number of cells where the mayor can place lampposts. 
*/

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class GridlandMetro {
    
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int[] firstLine = Arrays.stream(in.readLine().split(" ")).mapToInt                                                                 (Integer::parseInt).toArray();
        int rows = firstLine[0];
        int collumns = firstLine[1];
        int numTrainTracks = firstLine[2];
        int[][] grid = new int[rows][collumns];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < collumns; j++) {
                grid[i][j] = 0;
            }
        }
        
        for(int i = 0; i < numTrainTracks; i++) {
            int[] y = Arrays.stream(in.readLine().split(" ")).mapToInt                                                                 (Integer::parseInt).toArray();
            int row = y[0] - 1;
            int pos1 = y[1] - 1;
            int pos2 = y[2] - 1;
            
            for (int j = pos1; j <= pos2; j++) {
                grid[row][j] = 1;
            }
        }
        BigInteger spots = new BigInteger("0");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < collumns; j++) {
                if (grid[i][j] == 0) {
                    spots = spots.add(BigInteger.ONE);
                }
            }
        }
        
        System.out.println(spots);
    }
    
    public static void solve() {
        
    }
    
}