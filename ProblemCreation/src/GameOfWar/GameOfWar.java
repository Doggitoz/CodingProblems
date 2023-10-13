package GameOfWar;

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
 * - They will always place the lowest cards won onto the bottom of their deck first.
 * 
 * One of the main problems with War is the large amount of time it can take. Bob and Sarah want to make sure that the
 * order of the deck will result in a game that does not take too long. Given the permutation of the deck at the start of the
 * game, determine if the winner is Bob or Sarah, or neither if the game takes more than 10^5 turns.
 */

/*
 * INPUT
 * You are given an integer array of size 52 that contains the numerical value of each card in the starting deck. Index 0 is the top card.
 * 
 * OUTPUT
 * Output "Bob" if Bob would win or "Sarah" if Sarah would win. If the game would take more than 10^5 turns, output "Neither".
 */

public class GameOfWar {
    public String solve(int[] deck) {
        Queue<Integer> Bob = new LinkedList<>();
        Queue<Integer> Sarah = new LinkedList<>();
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) Bob.add(deck[i]);
            else Sarah.add(deck[i]);
        }
        PriorityQueue<Integer> stored = new PriorityQueue<>();
        for (int i = 0; i < Math.pow(10, 5); i++) {
            if (Sarah.isEmpty()) return "Bob";
            if (Bob.isEmpty()) return "Sarah";
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
        return "Nothing";
    }
}
