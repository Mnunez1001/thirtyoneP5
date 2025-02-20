package edu.guilford;

public class Player {
    private Hand hand;
    private int lives;
    private String name; // Useful for identifying players
    private boolean canKnock; // Tracks if the player is eligible to knock
    private Card.Suit preferredSuit; // Suit player is focusing on

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.lives = 3; // Players start with 3 lives
        this.canKnock = false;
        this.preferredSuit = null; // Will be determined based on initial hand
    }

    public Hand getHand() {
        return hand;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public String getName() {
        return name;
    }

    public boolean canKnock() {
        return canKnock;
    }

    public void allowKnocking() {
        this.canKnock = true;
    }

    public Card.Suit getPreferredSuit() {
        return preferredSuit;
    }

    public void setPreferredSuit(Card.Suit suit) {
        this.preferredSuit = suit;
    }

    @Override
    public String toString() {
        return name + " | Lives: " + lives + "\nHand:\n" + hand.toString();
    }
}
