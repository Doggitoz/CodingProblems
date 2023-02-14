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

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    static ArrayList<ArrayList<Character>>[][] dynam;

    public static int[][] solution(int num_buns, int num_required) {
        // Initialize dynamic programming array
        dynam = new ArrayList[num_buns+1][num_required+1];
        for (int i = 0; i <= num_buns; i++) {
            for (int j = 0; j <= num_required; j++) {
                dynam[i][j] = new ArrayList<ArrayList<Character>>();
            }
        }

        for (int totalBuns = 1; totalBuns <= num_buns; totalBuns++) {
            for (int reqBuns = 1; reqBuns <= num_required; reqBuns++) {
                ArrayList<ArrayList<Character>> keyAssignments = new ArrayList<>();

                // We don't care about these cases, since we'll never have fewer bunnies than required
                if (reqBuns > totalBuns) {
                    continue;
                }

                // BASE CASE: If required buns is 1, then all bunnies have the same key, [A]
                if (reqBuns == 1) {
                    ArrayList<Character> a = new ArrayList<Character>();
                    a.add('A');
                    for (int k = 0; k < totalBuns; k++) {
                        keyAssignments.add(a);
                    }
                }
                // BASE CASE: If required buns is the same as current buns, assign them each a new key
                else if (reqBuns == totalBuns) {
                    for (int k = 0; k < totalBuns; k++) {
                        ArrayList<Character> charList = new ArrayList<Character>();
                        charList.add((char)(k + 65));
                        keyAssignments.add(charList);
                    }
                }
                // ALL OTHER CASES
                else {
                    // Initialize a temporary list to keep track of the new collection
                    ArrayList<ArrayList<Integer>> keysets = new ArrayList<ArrayList<Integer>>();
                    for (int arr = 0; arr < totalBuns; arr++) {
                        keysets.add(new ArrayList<Integer>());
                    }

                    ArrayList<ArrayList<Character>> dynamicBuild = dynam[totalBuns - 1][reqBuns - 1];
                    int lastIndexOfFirst = 10 - NumKeys(dynamicBuild) - 1;

                    // Fill in first row with 0 -> (10 - (dynam[totalBuns - 1][reqBuns - 1]) number of keys)
                    for (int key = 0; key <= lastIndexOfFirst; key++) {
                        System.out.printf("Adding %d to bunny 0\n", key);
                        keysets.get(0).add(key);
                    }

                    // Match each rows final entires 
                    for (int bunIndex = 1; bunIndex < totalBuns; bunIndex++) {
                        for (char chI = 0; chI < dynamicBuild.get(bunIndex-1).size(); chI++) {
                            // Convert character to key ints
                            int currNewInt = 65 - dynamicBuild.get(bunIndex-1).get(chI);
                            keysets.get(bunIndex).add(lastIndexOfFirst + 1 + currNewInt);
                            System.out.printf("Adding %d to bunny %d\n", lastIndexOfFirst + 1 + currNewInt, bunIndex);
                        }
                    }
                    
                    keyAssignments = Reduce(keysets, reqBuns);
                }
                System.out.printf("totalbuns: %d, reqbuns: %d, list: %s\n", totalBuns, reqBuns, keyAssignments.toString());
                dynam[totalBuns][reqBuns] = keyAssignments;
            }
        }

        // Convert from charater representation back into int representation
        ArrayList<ArrayList<Character>> selection = dynam[num_buns][num_required];
        int[][] keys = CharToInt(selection, num_buns, num_required, 0);

        return keys;
    }


    // Convert from charater representation back into int representation
    public static int[][] CharToInt(ArrayList<ArrayList<Character>> selection, int num_buns, int num_required, int startIndex) {
        int numKeysPerBunny = selection.get(0).size();
        int[][] keys = new int[num_buns][numKeysPerBunny];
        for (int i = 0; i < num_buns; i++) {
            for (int j = 0; j < numKeysPerBunny; j++) {
                int charToInt = (int) selection.get(i).get(j);
                keys[i][j] = (charToInt - 65 + startIndex); //Convert from char back into integer
            }
        }
        return keys;
    }

    // Simplify the passed in integer list by grouping together key sets that are uniform across the lists
    public static ArrayList<ArrayList<Character>> Reduce(ArrayList<ArrayList<Integer>> unreduced, int buns) {
        ArrayList<ArrayList<Character>> reduced = new ArrayList<ArrayList<Character>>();

        for (int i = 0; i < buns; i++) {
            reduced.add(new ArrayList<Character>());
        }

        boolean[] used = new boolean[10];
        Arrays.fill(used, true);
        int charCount = 0;

        // MARKS ALL UNIFORM KEYS AS USED
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < buns; j++) {
                if (!unreduced.get(j).contains(i)) {
                    used[i] = false;
                    break;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            if (used[i]) {
                continue;
            }
            ArrayList<Integer> intersection = new ArrayList<Integer>();
            for (int j = 0; j < buns; j++) {
                ArrayList<Integer> currBun = unreduced.get((j + i) % buns);
                // If the current bunny is missing the identifier key
                if (!currBun.contains(i)){
                    continue;
                }
                //First instance found of this key
                if (intersection.size() == 0) {
                    for (Integer intg : currBun) {
                        if (!used[intg]) {
                            intersection.add(intg);
                        }
                    }
                }
                else {
                    // Remove all keys that aren't unioned with the next bunny
                    ArrayList<Integer> markToRemove = new ArrayList<Integer>();
                    for (Integer intg : intersection) {
                        if (!currBun.contains(intg)) {
                            markToRemove.add(intg);
                        }
                    }
                    for (Integer intg : markToRemove) {
                        intersection.remove(intersection.indexOf(intg));
                    }
                }
               
            }

            // Mark selected intersection as used
            for (Integer intg : intersection) {
                used[intg] = true;
            }

            // Add character to each set bunnies where applicable
            for (int j = 0; j < buns; j++) {
                if (unreduced.get(j).contains(i)) {
                    reduced.get(j).add((char)(65 + charCount));
                }
            }
            charCount++;
        }
        
        // If all keys are duplicated across all bunnies, reduce to one key each
        if (reduced.get(0).size() == 0) {
            for (int j = 0; j < buns; j++) {
                reduced.get(j).add((char)(65 + charCount));
            }
        }

        return reduced;
    }

    // Return the number of unique keys that exist in character list form
    public static int NumKeys(ArrayList<ArrayList<Character>> list) {
        ArrayList<Character> chars = new ArrayList<Character>();

        for (ArrayList<Character> charList : list) {
            for (Character ch : charList) {
                if (!chars.contains(ch)) {
                    chars.add(ch);
                }
            }
        }

        return chars.size();
    }

    public static void main(String[] args) {
        //Test case one:
        // int[][] solutionOne = solution(2, 1);
        // for (int i = 0; i < solutionOne.length; i++) {
        //     System.out.println(Arrays.toString(solutionOne[i]));
        // }
        //Expected outcome: [[0], [0]]

        // Test case two:
        // int[][] solutionTwo = solution(5, 3);
        // for (int i = 0; i < solutionTwo.length; i++) {
        // System.out.println(Arrays.toString(solutionTwo[i]));
        // }
        // Expected outcome: [[0, 1, 2, 3, 4, 5], [0, 1, 2, 6, 7, 8], [0, 3, 4, 6, 7,
        // 9], [1, 3, 5, 6, 8, 9], [2, 4, 5, 7, 8, 9]]

        // Test case three:
        // int[][] solutionThree = solution(3, 2);
        // for (int i = 0; i < solutionThree.length; i++) {
        // System.out.println(Arrays.toString(solutionThree[i]));
        // }
        // Expected outcome: [[0, 1], [0, 2], [1, 2],]

        //Test case four:
        int[][] solutionFour = solution(3, 2);
        for (int i = 0; i < solutionFour.length; i++) {
            System.out.println(Arrays.toString(solutionFour[i]));
        }

        //Reduce test case:
        // ArrayList<ArrayList<Integer>> reduceTestOne = new ArrayList<ArrayList<Integer>>();
        // ArrayList<Integer> Adder = new ArrayList<Integer>() {
        //     {
        //         add(0);
        //         add(1);
        //         add(2);
        //         add(5);
        //         add(6);
        //         add(3);
        //         add(4);
        //     }
        // };
        // reduceTestOne.add(Adder);
        // Adder = new ArrayList<Integer>() {
        //     {
        //         add(7);
        //         add(8);
        //         add(2);
        //         add(3);
        //         add(4);
        //         add(0);
        //         add(1);
        //     }
        // };
        // reduceTestOne.add(Adder);
        // Adder = new ArrayList<Integer>() {
        //     {
        //         add(0);
        //         add(1);
        //         add(2);
        //         add(3);
        //         add(5);
        //         add(7);
        //         add(9);
        //     }
        // };
        // reduceTestOne.add(Adder);
        // Adder = new ArrayList<Integer>() {
        //     {
        //         add(0);
        //         add(1);
        //         add(2);
        //         add(4);
        //         add(6);
        //         add(8);
        //         add(9);
        //     }
        // };
        // reduceTestOne.add(Adder);
        // ArrayList<ArrayList<Character>> reduced = Reduce(reduceTestOne, reduceTestOne.size());
        // for (ArrayList<Character> arr : reduced) {
        //     System.out.println(arr.toString());
        // }
        // System.out.println(NumKeys(reduced));

    }
}