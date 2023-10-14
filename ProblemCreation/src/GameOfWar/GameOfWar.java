package GameOfWar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Bob and Sarah have decied to sit down and play a card game. The game they have decided upon is War.
 * The rules of War are as follows:
 * - Each player is dealt half of the deck.
 * - On a turn, each player flips the top card of their deck.
 * - The winner takes both of the cards and places them on the bottom of their deck.
 * - In the case of a tie, both cards are stored and another card is flipped. The winner will take all of the stored cards.
 * - The winner is decided by who ends up with all of the cards by the end.
 * - The numerical value of face cards are Jack = 11, Queen = 12, King = 13, Ace = 14.
 * 
 * Bob and Sarah have also added a couple house rules:
 * - Bob will always be dealt to first at the start of the game.
 * - Cards will always be placed into the bottom of the winners deck in ascending order.
 * 
 * One of the main problems with War is the large amount of time it can take. Bob and Sarah want to make sure that the
 * order of the deck will result in a game that does not take too long. Given the permutation of the deck at the start of the
 * game, determine how many moves the game will take to complete or if it is too long.
 */

/*
 * INPUT
 * You are given an integer array of size 52 that contains the numerical value of each card in the starting deck. Index 0 is the top card.
 * 
 * OUTPUT
 * Output the number of turns before completion.
 */

public class GameOfWar {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        GameOfWar g = new GameOfWar();
        GameOfWarTest gt = new GameOfWarTest();
        System.out.println(g.solve(gt.fileToArray("one.txt")));
    }

    public int solve(int[] deck) {
        Queue<Integer> Bob = new LinkedList<>();
        Queue<Integer> Sarah = new LinkedList<>();
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) Bob.add(deck[i]);
            else Sarah.add(deck[i]);
        }
        PriorityQueue<Integer> stored = new PriorityQueue<>();
        for (int i = 0; i < Math.pow(10, 5); i++) {
            if (Sarah.isEmpty() || Bob.isEmpty()) return i;
            int bob = Bob.poll();
            int sarah = Sarah.poll();
            stored.add(bob);
            stored.add(sarah);
            if (bob > sarah) {
                while(!stored.isEmpty()) {
                    Bob.add(stored.poll());
                }
            }
            else if (sarah > bob) {
                while(!stored.isEmpty()) {
                    Sarah.add(stored.poll());
                }
            }
        }
        return -1;
    }
}
