package ICPC2022;

import java.util.*;
import java.io.*;

public class ThreeDice {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] charRecorded;
    static boolean[] solved;
    static boolean[][] validDice;
    static String[] words;
    static DynamicArray[] dices;
    static int numSolved = 0;
    public static void main(String[] args) throws IOException {
        try {
            solve();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("0");
        }
    }

    public static void solve() throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        charRecorded = new boolean[26];
        validDice = new boolean[26][3];
        words = new String[n];
        dices = new DynamicArray[4];
        for (int i = 0; i < validDice.length; i++) {
            Arrays.fill(validDice[i], true);
        }
        for (int i = 0; i < 4; i++) {
            dices[i] = new DynamicArray();
        }
        
        // Grab all words, record all characters, and detect any duplicate letters in words
        int totalChars = 0;
        for (int i = 0; i < n; i++) {
            words[i] = reader.readLine().trim();
            char[] letters = words[i].toCharArray();
            for (Character c : letters) {
                if (!charRecorded[c - 'a']) {
                    totalChars++;
                    charRecorded[c - 'a'] = true;
                }
            }
            if (letters[0] == letters[1] || letters[0] == letters[2] || letters[1] == letters[2]) {
                System.out.println(0);
                return;
            }
        }
        
        // If more than 18 characters recorded, invalid solution
        if (totalChars > 18) {
            System.out.println(0);
            return;
        }

        solved = new boolean[26];
        String first = words[0];
        addToDice(first.charAt(0), 1);
        addToDice(first.charAt(1), 2);
        addToDice(first.charAt(2), 3);
        int numSolved = 3;
        ArrayList<Character> batch = new ArrayList<>();
        while (numSolved < totalChars) { 
            boolean foundSolved = false;
            for (int j = 0; j < 26; j++) {
                if (solved[j]) continue;
                char c = (char)(j + 'a');

                // Not in the words
                if (!charRecorded[j]) continue;

                // Can't be solved right now or is impossible to solve
                int diceToAdd = getSolvedDice(c);
                if (diceToAdd == -1) {
                    System.out.println(0);
                    return;
                }
                if (diceToAdd == 0) continue;

                // Has either two or three possibilites
                batch.add(c);
                foundSolved = true;
                continue;
            }

            if (foundSolved) {
                for (Character c : batch) {
                    int dice = getSolvedDice(c);
                    addToDice(c, dice);
                    numSolved++;
                }
                batch.clear();
                continue;
            }

            boolean completed = false;
            for (int j = 0; j < n; j++) {
                ArrayList<Character> unsolvedInWord = new ArrayList<>();
                String word = words[j];
                for (Character c : word.toCharArray()) {
                    if (!solved[(c - 'a')] && charRecorded[(c - 'a')]) {
                        unsolvedInWord.add(c);
                    }
                }
                if (unsolvedInWord.size() == 2) {
                    int ind1 = unsolvedInWord.get(0) - 'a';
                    if (validDice[ind1][0]) {
                        if (validDice[ind1][1]) {
                            addToDice(unsolvedInWord.get(0), 1);
                            addToDice(unsolvedInWord.get(1), 2);
                        }
                        else {
                            addToDice(unsolvedInWord.get(0), 1);
                            addToDice(unsolvedInWord.get(1), 3);
                        }
                    }
                    else {
                        addToDice(unsolvedInWord.get(0), 2);
                        addToDice(unsolvedInWord.get(1), 3);
                    }
                    numSolved += 2;
                    completed = true;
                    break;
                }
            }

            if (completed) continue;

            // Guaranteed to be here if all characters in word are unchecked
            for (int j = 0; j < n; j++) {
                ArrayList<Character> unsolvedInWord = new ArrayList<>();
                String word = words[j];
                for (Character c : word.toCharArray()) {
                    if (!solved[(c - 'a')] && charRecorded[(c - 'a')]) {
                        unsolvedInWord.add(c);
                    }
                }
                if (unsolvedInWord.size() == 0)
                    continue;
                addToDice(word.charAt(0), 1);
                addToDice(word.charAt(1), 2);
                addToDice(word.charAt(2), 3);
                numSolved += 3;
            }
        }
        

        // Fill the rest of the dice and output
        for (int i = 1; i < 4; i++) {
            while (dices[i].size < 6) {
                char c = '?';
                for (int j = 0; j < 26; j++) {
                    if (!solved[j]) {
                        c = (char)(j + 'a');
                        break;
                    }
                }
                addToDice(c, i);
            }
            for (int j = 0; j < dices[i].size; j++) {
                System.out.print(dices[i].arr[j]);
            }
            System.out.print(" ");
        }

    }

    public static void addToDice(char c, int dice) {
        dices[dice].append(c);
        solved[c - 'a'] = true;
        removeValidDices(c, dice);
    }

    public static int getSolvedDice(char c) {
        ArrayList<Integer> validPossibilities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (validDice[c - 'a'][i]) validPossibilities.add(i + 1);
        }
        if (validPossibilities.size() > 1) return 0;
        if (validPossibilities.size() < 1) return -1;
        return validPossibilities.get(0);
    }

    public static void removeValidDices(char c, int dice) {
        String s = String.valueOf(c);
        for (int i = 0; i < words.length; i++) {
            if (words[i].contains(s)) {
                for (Character currChar : words[i].toCharArray()) {
                    if (currChar == c) continue;
                    validDice[currChar - 'a'][dice - 1] = false;
                }
            }
        }
    }

    static class DynamicArray {
        
        char[] arr = new char[10];
        // Don't hide arr access behind getters. THIS IS MUCH MUCH SLOWER
        // No setters, getters. Just add.

        int idx = 0;
        int size = 0;

        public void append(char x) {
            if (idx == arr.length) {
                this.grow();
            }
            this.arr[idx] = x;
            idx++;
            size++;
        }

        public void grow() {
            char[] temp = this.arr;
            this.arr = new char[temp.length * 2];
            System.arraycopy(temp, 0, this.arr, 0, temp.length);
            // Not a method, this is a VM call. All System calls are VM calls.
            // These are handled natively by Java
        }
    }
}
