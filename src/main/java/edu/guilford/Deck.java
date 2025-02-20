package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a standard deck of playing cards.
 * Provides functionality to build, shuffle, deal, and manage the deck.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 * @see java.util.ArrayList, java.util.Random
 */

public class Deck {

    /**
     * ArrayList of cards representing the deck.
     */
    private ArrayList<Card> deck = new ArrayList<Card>();

    /**
     * Random object used for shuffling the deck.
     */
    private Random rand = new Random();

    /**
     * Constructs a new Deck and builds a standard deck of 52 playing cards.
     */
    public Deck() {
        build();
    }

    /**
     * Gets the list of cards in the deck.
     * 
     * @return An ArrayList containing all the cards in the deck.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Clears the deck of all cards.
     */
    public void clear() {
        deck.clear();
    }

    /**
     * Builds a standard deck of 52 cards (4 suits, 13 ranks each).
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck by randomly rearranging the cards.
     */
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int loc = rand.nextInt(deck.size());
            tempDeck.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = tempDeck;
    }

    /**
     * Picks and removes a card from the deck at a specified index.
     * 
     * @param i The index of the card to pick.
     * @return The card picked from the deck.
     */
    public Card pick(int i) {
        Card picked = deck.remove(i);
        return picked;
    }

    /**
     * Deals the top card from the deck (removes and returns it).
     * 
     * @return The top card from the deck.
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * Gets the number of cards remaining in the deck.
     * 
     * @return The size of the deck.
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns a string representation of the deck with each card on a new line.
     * 
     * @return A string representation of the deck.
     */
    public String toString() {
        String deckString = "";
        for (Card card : deck) {
            deckString += card.toString() + "\n";
        }
        return deckString;
    }
}
