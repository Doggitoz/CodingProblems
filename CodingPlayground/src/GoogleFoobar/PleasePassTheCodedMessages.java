package GoogleFoobar;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * You need to pass a message to the bunny workers, but to avoid detection, the code you agreed to use is... obscure, to say the least. 
 * The bunnies are given food on standard-issue plates that are stamped with the numbers 0-9 for easier sorting, and you need to combine sets of plates to create the 
 * numbers in the code. The signal that a number is part of the code is that it is divisible by 3. You can do smaller numbers like 15 and 45 easily, but bigger numbers 
 * like 144 and 414 are a little trickier. Write a program to help yourself quickly create large numbers for use in the code, given a limited number of plates to work with.
 *
 * You have L, a list containing some digits (0 to 9). Write a function solution(L) which finds the largest number that can be made from some or all of these digits and is 
 * divisible by 3. If it is not possible to make such a number, return 0 as the solution. L will contain anywhere from 1 to 9 digits.  
 * The same digit may appear multiple times in the list, but each element in the list may only be used once.
 * 
 * Completed in: 10 hrs, 21 mins, 57 secs.
 */

public class PleasePassTheCodedMessages {

    static int biggestVal = 0;

    public static int solution(int[] l) {
        // Your code here
        biggestVal = 0;
        Arrays.sort(l);

        // Reverse array into descending
        for (int i = 0; i < l.length / 2; i++) {
            int temp = l[i];
            l[i] = l[l.length - i - 1];
            l[l.length - i - 1] = temp;
        }

        generateSubsets(l);

        return biggestVal;
    }

    public static void generateSubsets(int[] set) {
        // Modified from
        // https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
        int n = set.length;
        ArrayList<Integer> list;

        for (int i = 0; i < (1 << n); i++) {
            list = new ArrayList<>();

            for (int j = 0; j < n; j++) {

                if ((i & (1 << j)) > 0)
                    list.add(set[j]);
            }
            if (list.size() == 0)
                continue;

            int[] passArr = new int[list.size()];
            for (int k = 0; k < list.size(); k++) {
                passArr[k] = list.get(k);
            }
            permuteUnique(passArr, 0);
        }
    }

    public static void permuteUnique(int[] nums, int index) {
        // Modified from
        // https://leetcode.com/problems/permutations-ii/solutions/2773174/permutations-2-java-solution-using-hashset-in-permutations-1/
        if (index == nums.length) {
            String strInt = "";
            for (int i = 0; i < nums.length; i++) {
                strInt = strInt + String.valueOf(nums[i]);
            }
            int val = Integer.parseInt(strInt);
            if (val > biggestVal && val % 3 == 0) {
                biggestVal = val;
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            permuteUnique(nums, index + 1);
            swap(i, index, nums);
        }
    }

    public static void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        // Test case one:
        int[] caseOne = { 3, 1, 4, 1 };
        System.out.println(caseOne);
        // Expected outcome: 4311

        // Test case two:
        int[] caseTwo = { 3, 1, 4, 1, 5, 9 };
        System.out.println(solution(caseTwo));
        // Expected outcome: 94311

        // Test case three:
        int[] caseThree = { 1, 2, 3, 6, 9, 2, 8, 2 };
        System.out.println(solution(caseThree));
    }

}
