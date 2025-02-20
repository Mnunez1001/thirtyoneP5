package edu.guilford;

import java.util.Random;

/**
 * Represents a playing card with a suit and rank.
 * Implements Comparable to allow for card comparison based on suit or rank.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 * @see java.util.Random
 * 
 */
public class Card implements Comparable<Card> {

    /**
     * Enum representing the four suits in a standard deck of cards.
     */
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    // public enum Rank {
    // ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
    // KING

    // }

    /**
     * Enum representing the ranks in a standard deck of cards,
     * with assigned values used for scoring purposes.
     */
    public enum Rank {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

        private final int value;

        /**
         * Constructor for Rank, assigning a specific value to each rank.
         * 
         * @param value The point value of the rank.
         */
        Rank(int value) {
            this.value = value;
        }

        /**
         * Gets the point value associated with the rank.
         * 
         * @return The point value of the rank.
         */
        public int getValue() {
            return value;
        }
    }

    // instance variables

    /**
     * The suit of the card.
     */
    private Suit suit;

    /**
     * The rank of the card.
     */
    private Rank rank;

    /**
     * The sort method for comparing cards based on suit or rank.
     */
    public static final int SORT_BY_SUIT = 1;
    public static final int SORT_BY_RANK = 2;
    private static int sortMethod = SORT_BY_RANK;

    /**
     * Constructs a Card with a specified suit and rank.
     * 
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Constructs a random Card with a random suit and rank.
     */
    public Card() {
        // random Card
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    /**
     * Gets the suit of the card.
     * 
     * @return The suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the rank of the card.
     * 
     * @return The rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets the sorting method for comparing cards.
     * 
     * @param sortMethod The sorting method to use (SORT_BY_SUIT or SORT_BY_RANK).
     */
    public static void setSortMethod(int sortMethod) {
        Card.sortMethod = sortMethod;
    }

    /**
     * Gets the value of the card based on its rank.
     * 
     * @return The value of the card.
     */
    public int getValue() {
        return rank.getValue();
    }

    /**
     * Returns a string representation of the card, e.g., "ACE of SPADES".
     * 
     * @return A string representing the card's rank and suit.
     */
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Compares this card with another card based on the current sort method (suit
     * or rank).
     * 
     * @param otherCard The other card to compare to.
     * @return A positive number if this card is greater, a negative number if
     *         lesser, and 0 if equal.
     */
    @Override
    public int compareTo(Card otherCard) {
        if (sortMethod == SORT_BY_SUIT) {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            } else {
                if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                    return 1;
                } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                    return -1;
                }
            }
        } else {
            if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                return 1;
            } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                return -1;
            } else {
                if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                    return 1;
                } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                    return -1;
                }
            }
        }

        return 0;
    }

}