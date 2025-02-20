package edu.guilford;

import java.util.ArrayList;

/**
 * Represents a hand of cards held by a player in the game.
 * A hand is a collection of cards that a player holds.
 * The hand class is used to keep track of the cards a player has.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 * @see java.util.ArrayList
 */

public class Hand {

    /**
     * ArrayList of cards representing the hand.
     */
    private ArrayList<Card> hand;

    /**
     * Constructor for Hand class.
     * Initializes the hand as an empty ArrayList of cards.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Gets the list of cards in the hand.
     * 
     * @return the list of cards in the hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Adds a card to the hand.
     * 
     * @param card the card to add
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes a card from the hand.
     * 
     * @param card the card to remove
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Clears all cards from the hand.
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Gets the number of cards in the hand.
     * 
     * @return the number of cards in the hand
     */
    public int size() {
        return hand.size();
    }

    /**
     * Gets a card from the hand by index.
     * 
     * @param index the index of the card to retrieve
     * @return the card at the specified index
     */
    public Card getCard(int index) {
        return hand.get(index);
    }

    /**
     * Calculates the total value of the hand based on the highest value suit.
     * 
     * @return the total value of the hand
     */
    public int getTotalValue() {
        int maxValue = 0;
        int[] values = new int[Card.Suit.values().length];
        for (Card.Suit suit : Card.Suit.values()) {
            values[suit.ordinal()] = 0;
            for (Card card : hand) {
                if (card.getSuit() == suit) {
                    // add the value of the card to the value of the suit
                    switch (card.getRank()) {
                        case ACE:
                            values[suit.ordinal()] += 11;
                            break;
                        case TWO:
                            values[suit.ordinal()] += 2;
                            break;
                        case THREE:
                            values[suit.ordinal()] += 3;
                            break;
                        case FOUR:
                            values[suit.ordinal()] += 4;
                            break;
                        case FIVE:
                            values[suit.ordinal()] += 5;
                            break;
                        case SIX:
                            values[suit.ordinal()] += 6;
                            break;
                        case SEVEN:
                            values[suit.ordinal()] += 7;
                            break;
                        case EIGHT:
                            values[suit.ordinal()] += 8;
                            break;
                        case NINE:
                            values[suit.ordinal()] += 9;
                            break;
                        case TEN:
                        case JACK:
                        case QUEEN:
                        case KING:
                            values[suit.ordinal()] += 10;
                            break;
                    }

                }
            }
        }
        maxValue = values[0];
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    /**
     * Returns a string representation of the hand, listing each card.
     * 
     * @return a string representation of the hand
     */
    public String toString() {
        String handString = "";
        for (Card card : hand) {
            handString += card.toString() + "\n";
        }
        return handString;
    }

}
