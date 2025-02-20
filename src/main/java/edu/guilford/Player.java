package edu.guilford;

/**
 * The Player class represents a player in the Thirty-One card game.
 * Each player has a hand of cards, a certain number of lives, a name,
 * the ability to knock, and a preferred suit for the game strategy.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 */
public class Player {

    /**
     * The player's hand of cards.
     */
    private Hand hand;

    /**
     * The number of lives the player has.
     */
    private int lives;

    /**
     * The name of the player.
     */
    private String name; // Useful for identifying players

    /**
     * Flag to indicate if the player can knock.
     */
    private boolean canKnock; // Tracks if the player is eligible to knock

    /**
     * The suit the player is focusing on.
     */
    private Card.Suit preferredSuit; // Suit player is focusing on

    /**
     * Constructs a Player with the given name.
     * Initializes the hand, lives, knocking status, and preferred suit.
     * 
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.lives = 3; // Players start with 3 lives
        this.canKnock = false;
        this.preferredSuit = null; // Will be determined based on initial hand
    }

    /**
     * Gets the player's hand.
     * 
     * @return The hand of the player.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Gets the number of lives the player has remaining.
     * 
     * @return The number of lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Reduces the player's lives by one if the player has any lives remaining.
     */
    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    /**
     * Checks if the player is still in the game.
     * 
     * @return true if the player has at least one life remaining, false otherwise.
     */
    public boolean isAlive() {
        return lives > 0;
    }

    /**
     * Gets the name of the player.
     * 
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the player is eligible to knock.
     * 
     * @return true if the player can knock, false otherwise.
     */
    public boolean canKnock() {
        return canKnock;
    }

    /**
     * Sets the player as eligible to knock.
     */
    public void allowKnocking() {
        this.canKnock = true;
    }

    /**
     * Gets the preferred suit of the player, representing the suit the player
     * is focusing on.
     * 
     * @return The preferred suit of the player.
     */
    public Card.Suit getPreferredSuit() {
        return preferredSuit;
    }

    /**
     * Sets the preferred suit of the player.
     * 
     * @param suit The suit to set as the preferred suit.
     */
    public void setPreferredSuit(Card.Suit suit) {
        this.preferredSuit = suit;
    }

    /**
     * Returns a string representation of the player, including their name, lives,
     * and the cards in their hand.
     * 
     * @return A string representing the player's status and hand.
     */
    @Override
    public String toString() {
        return name + " | Lives: " + lives + "\nHand:\n" + hand.toString();
    }
}
