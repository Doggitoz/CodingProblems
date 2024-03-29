package GameOfWar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Bob and Sarah have decided to sit down and play a card game. The game they have decided upon is War.
 * The rules of War are as follows:
 * - On a turn, each player flips the top card of their deck.
 * - The winner takes both of the cards and places them on the bottom of their deck.
 * - In the case of a tie, both cards are stored and another card is flipped. The winner will take all of the stored cards.
 * - The winner is decided by who ends up with all of the cards by the end.
 * - The numerical value of face cards are Jack = 11, Queen = 12, King = 13, Ace = 14.
 * 
 * Bob and Sarah have also added a couple house rules:
 * - Bob will always be dealt to first at the start of the game.
 * - Bob will always play his card first in a turn.
 * - Cards will always be placed into the bottom of the winners deck in order played.
 * *** For each pair of cards played Bobs card will go to the bottom of the winners deck before Sarahs.
 * *** Make sure they dont full tie. i.e. "It is guaranteed that the game ends in X turns"
 * *** Specify how many cards there are in the deck. "52 cards in the deck each represented by a value 2-14"
 * 
 * One of the main problems with War is the large amount of time it can take. Bob and Sarah want to make sure that the
 * order of the deck will result in a game that takes less than 500 moves. Given the permutation of the deck at the start of the
 * game, determine the amount of turns the game will take to complete.
 */

/*
 * INPUT
 * REDO THIS LATER
 * 
 * OUTPUT
 * Output the number of turns before completion or -1 if more than 500.
 */

public class GameOfWar {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        GameOfWar g = new GameOfWar();
        System.out.println(g.solve(GameOfWarTest.fileToArray("three.txt")));
    }

    public int solve(int[] deck) {
        Queue<Integer> Bob = new LinkedList<>();
        Queue<Integer> Sarah = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            Bob.add(deck[i]);
        }
        for (int i = 26; i < 52; i++) {
            Sarah.add(deck[i]);
        }
        Queue<Integer> stored = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
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
