package GoogleFoobar;
/* 
 * You need to free the bunny workers before Commander Lambda's space station explodes! Unfortunately, the Commander was very careful with the highest-value workers -- they 
 * all work in separate, maximum-security work rooms. The rooms are opened by putting keys into each console, then pressing the open button on each console simultaneously. 
 * When the open button is pressed, each key opens its corresponding lock on the work room. So, the union of the keys in all of the consoles must be all of the keys. 
 * The scheme may require multiple copies of one key given to different minions.
 * 
 * The consoles are far enough apart that a separate minion is needed for each one. Fortunately, you have already relieved some bunnies to aid you - and even better, you were 
 * able to steal the keys while you were working as Commander Lambda's assistant. The problem is, you don't know which keys to use at which consoles. The consoles are 
 * programmed to know which keys each minion had, to prevent someone from just stealing all of the keys and using them blindly. There are signs by the consoles saying how 
 * many minions had some keys for the set of consoles. You suspect that Commander Lambda has a systematic way to decide which keys to give to each minion such that they 
 * could use the consoles.
 * 
 * You need to figure out the scheme that Commander Lambda used to distribute the keys. You know how many minions had keys, and how many consoles are by each work room.  
 * You know that Command Lambda wouldn't issue more keys than necessary (beyond what the key distribution scheme requires), and that you need as many bunnies with keys as 
 * there are consoles to open the work room.
 * 
 * Given the number of bunnies available and the number of locks required to open a work room, write a function solution(num_buns, num_required) which returns a 
 * specification of how to distribute the keys such that any num_required bunnies can open the locks, but no group of (num_required - 1) bunnies can.
 * 
 * Each lock is numbered starting from 0. The keys are numbered the same as the lock they open (so for a duplicate key, the number will repeat, since it opens the same lock). 
 * For a given bunny, the keys they get is represented as a sorted list of the numbers for the keys. To cover all of the bunnies, the final solution is represented by a sorted 
 * list of each individual bunny's list of keys.  Find the lexicographically least such key distribution - that is, the first bunny should have keys 
 * sequentially starting from 0.
 * 
 * num_buns will always be between 1 and 9, and num_required will always be between 0 and 9 (both inclusive).  For example, if you had 3 bunnies and required only 1 of them 
 * to open the cell, you would give each bunny the same key such that any of the 3 of them would be able to open it, like so:
 * [
 *   [0],
 *   [0],
 *   [0],
 * ]
 * If you had 2 bunnies and required both of them to open the cell, they would receive different keys (otherwise they wouldn't both actually be required), and your solution would be as follows:
 * [
 *   [0],
 *   [1],
 * ]
 * Finally, if you had 3 bunnies and required 2 of them to open the cell, then any 2 of the 3 bunnies should have all of the keys necessary to open the cell, but no single bunny would be able to do it.  Thus, the solution would be:
 * [
 *   [0, 1],
 *   [0, 2],
 *   [1, 2],
 * ]
 * 
 * Set cover problem
 * 
*/

import java.util.Arrays;
import java.util.ArrayList;

public class FreeTheBunnyWorkersIncorrect {

    public static int[][] solution(int num_buns, int num_required) {
        // Your code here
        int[][] keys = new int[num_buns][10];

        if (num_required == 1) {
            for (int i = 0; i < keys.length; i++) {
                keys[i][0] = 1;
            }
            return binaryArraysToNumbers(keys, num_buns);
        }

        int dupes = num_buns - num_required;
        int[] offsets = new int[dupes + 1];
        System.out.println(offsets.length);

        for (int i = 0; i < 10; i++) {
            System.out.println("NEW ITERATION------------------------");
            System.out.println("Offsets: " + Arrays.toString(offsets));

            int offsetTotal = 0;
            // Assign proper key values for ith column
            for (int j = 0; j < offsets.length; j++) {
                offsetTotal += offsets[j];
                keys[j + offsetTotal][i] = 1;
            }

            // LOGIC FOR OFFSET CARRY OVER
            int totalOffset = 0;
            boolean carryover = false;
            for (int j = 0; j < offsets.length; j++) {

                totalOffset += offsets[j];
                // If carryover, just have all remaining values set to 0
                if (carryover) {
                    offsets[j] = 0;
                    continue;
                }

                // If gotten to last iteration without carryover triggering, increment final
                // offset
                if (j == offsets.length - 1) {
                    offsets[j]++;
                    break;
                }

                // Check if next offset will overflow, if so, increment current offset and
                // trigger carryover
                if (totalOffset + offsets[j + 1] >= dupes) {
                    carryover = true;
                    offsets[j]++;
                }
            }

            if (offsets[0] == dupes + 1) {
                System.out.println("Final");
                break;
            }

            for (int j = 0; j < keys.length; j++) {
                System.out.println(Arrays.toString(keys[j]));
            }
            System.out.println("New offsets: " + Arrays.toString(offsets));

            System.out.println("\n\n");
        }

        int length = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[0][i] == 1)
                length++;
        }
        System.out.println(length);

        return binaryArraysToNumbers(keys, num_buns);
    }

    public static int[][] binaryArraysToNumbers(int[][] keyFlags, int num_buns) {
        int[][] converted = new int[num_buns][];
        for (int i = 0; i < keyFlags.length; i++) {
            ArrayList<Integer> keys = new ArrayList<Integer>();
            for (int j = 0; j < 10; j++) {
                if (keyFlags[i][j] == 1) {
                    keys.add(j);
                }
            }
            int[] keyArr = new int[keys.size()];
            for (int j = 0; j < keys.size(); j++) {
                keyArr[j] = keys.get(j);
            }
            converted[i] = keyArr;
        }
        return converted;
    }

    public static void main(String[] args) {
        // Test case one:
        // int[][] solutionOne = solution(2, 1);
        // for (int i = 0; i < solutionOne.length; i++) {
        // System.out.println(Arrays.toString(solutionOne[i]));
        // }
        // Expected outcome: [[0], [0]]

        // Test case two:
        int[][] solutionTwo = solution(5, 3);
        for (int i = 0; i < solutionTwo.length; i++) {
            System.out.println(Arrays.toString(solutionTwo[i]));
        }
        // Expected outcome: [[0, 1, 2, 3, 4, 5], [0, 1, 2, 6, 7, 8], [0, 3, 4, 6, 7,
        // 9], [1, 3, 5, 6, 8, 9], [2, 4, 5, 7, 8, 9]]

        // TEst case three:
        // int[][] solutionThree = solution(3, 2);
        // for (int i = 0; i < solutionThree.length; i++) {
        // System.out.println(Arrays.toString(solutionThree[i]));
        // }
        // Expected outcome: [[0, 1], [0, 2], [1, 2],]
    }
}