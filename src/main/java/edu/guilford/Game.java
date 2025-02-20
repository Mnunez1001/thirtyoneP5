package edu.guilford;

import java.util.*;

public class Game {
    private List<Player> players;
    private Deck deck;
    private Stack<Card> discardPile;
    private Queue<Card> stockPile;
    private Random rand;

    public Game(int numPlayers) {
        players = new ArrayList<>(); // Creates a list to store the players in the game.
        deck = new Deck(); // Creates a new deck of cards
        discardPile = new Stack<>(); // Creates a stack to store the discard pile
        stockPile = new LinkedList<>(); // Creates a queue to store the stock pile
        rand = new Random(); // Creates a new random number generator

        for (int i = 0; i < numPlayers; i++) { // This loop creates a number of Player objects equal to numPlayers.
            players.add(new Player("Player " + (i + 1))); // Each player is given a name like "Player 1", "Player 2",
                                                          // etc.

        }
        startNewRound(); // Starts a new round of the game.
    }

    private void startNewRound() {
        deck.build(); // Builds a new deck of cards.
        deck.shuffle(); // Shuffles the deck.
        stockPile.clear(); // Clears the stock pile.
        discardPile.clear(); // Clears the discard pile.

        for (Player player : players) { // This loop deals 3 cards to each player.
            player.getHand().reset(); // Resets the player's hand.
            for (int i = 0; i < 3; i++) {
                player.getHand().addCard(deck.deal());
            }
        }

        while (deck.size() > 0) { // This loop deals the remaining cards in the deck to the stock pile.
            stockPile.offer(deck.deal());
        }

        discardPile.push(stockPile.poll()); // The top card of the stock pile is moved to the discard pile.
    }

    /**
     * players.stream()
     * 
     * This creates a stream from the players list.
     * A stream is a way to process data in a functional style.
     * .filter(p -> p.getLives() > 0)
     * 
     * This filters the stream to include only players who have more than 0 lives.
     * Each p represents a Player object in the list.
     * p.getLives() > 0 checks if the player is still alive.
     * .count()
     * 
     * This counts how many players still have lives remaining.
     * > 1
     * 
     * The game should continue as long as there is more than one player still
     * alive.
     * Once only one player is left, the game stops.
     */

    public void play() {
        while (players.stream().filter(p -> p.getLives() > 0).count() > 1) { // This loop continues until only one
                                                                             // player has lives remaining.
            System.out.println("Starting a new round...");
            startNewRound();

            boolean roundOver = false;
            int roundsPlayed = 0;

            while (!roundOver) { // This loop continues until the round is over.
                for (Player player : players) {
                    if (player.getLives() <= 0)
                        continue; // If the player has no lives remaining, skip their turn.

                    System.out.println(player.getName() + "'s turn:");
                    System.out.println("Current hand: " + player.getHand());

                    ensureDiscardPileNotEmpty(); // Ensures the discard pile is not empty.
                    Card topDiscard = discardPile.peek(); // looks at the top card of the discard pile.
                    System.out.println("Top of discard pile: " + topDiscard);

                    Card drawnCard;
                    if (shouldTakeDiscard(player, topDiscard)) { // If the player should take the top card of the
                                                                 // discard pile.
                        ensureDiscardPileNotEmpty();
                        drawnCard = discardPile.pop();
                        System.out.println(player.getName() + " picks up " + drawnCard + " from the discard pile.");
                    } else {
                        drawnCard = stockPile.poll(); // If the player should draw from the stock pile.
                        System.out.println(player.getName() + " draws from the stockpile.");
                    }

                    player.getHand().addCard(drawnCard);
                    discardCard(player); // Discards a card from the player's hand.

                    // Check for 31 immediately after discarding
                    if (player.getHand().getTotalValue() == 31) {
                        System.out.println(player.getName() + " reaches 31! Round ends immediately!");
                        roundOver = true;

                        // All other players lose a life
                        for (Player otherPlayer : players) {
                            if (otherPlayer != player && otherPlayer.getLives() > 0) {
                                otherPlayer.loseLife();
                                System.out.println(otherPlayer.getName() + " loses a life because " + player.getName()
                                        + " reached 31!");
                            }
                        }

                        break; // End the turn loop
                    }

                    roundsPlayed++;

                    // Check if the player should knock
                    if (roundsPlayed >= players.size() && player.getHand().getTotalValue() >= 25
                            && shouldKnock(player)) {
                        System.out.println(player.getName() + " knocks!");
                        roundOver = true;
                        resolveRound(player);
                        break;
                    }
                }
            }
        }

        /**
         * players.stream()
         * 
         * Creates a stream from the list of players.
         * A stream allows us to process data using functional operations.
         * .filter(p -> p.getLives() > 0)
         * 
         * Filters the stream to only include players who still have lives.
         * p -> p.getLives() > 0 checks if a player is still alive.
         * .findFirst()
         * 
         * Returns the first player from the filtered list.
         * Since only one player should be left when this runs, this will be the winner.
         * .orElse(null)
         * 
         * If no player is found (which shouldn't happen in a correctly functioning
         * game), it returns null.
         */

        Player winner = players.stream().filter(p -> p.getLives() > 0).findFirst().orElse(null);
        if (winner != null) {
            System.out.println(winner.getName() + " is the winner!");
        }
    }

    private boolean shouldTakeDiscard(Player player, Card topDiscard) { // Determines if the player should take the top
                                                                        // card of the discard pile.
        Hand hand = player.getHand();
        Card.Suit preferredSuit = getPreferredSuit(hand);

        if (topDiscard.getSuit() == preferredSuit) { // If the top card of the discard pile is the preferred suit.
                                                     // The player should take the card.
            return true;
        }
        return false;
    }

    private void discardCard(Player player) { // Discards a card from the player's hand.
        Hand hand = player.getHand();
        Card.Suit preferredSuit = getPreferredSuit(hand); // Gets the preferred suit for the player.
        Card cardToDiscard = null;

        for (Card card : hand.getHand()) { // This loop goes through the player's hand to find a card to discard.
            if (card.getSuit() != preferredSuit) { // If the card is not the preferred suit. it is selected to be
                                                   // discarded.
                cardToDiscard = card;
                break;
            }
        }

        // If all cards are of the preferred suit, discard the card with the lowest
        // value
        if (cardToDiscard == null) {
            cardToDiscard = hand.getHand().get(0);
            for (Card card : hand.getHand()) {
                if (card.getValue() < cardToDiscard.getValue()) {
                    cardToDiscard = card;
                }
            }
        }

        hand.removeCard(cardToDiscard);

        // Randomly choose where to discard the card (50/50 chance)
        if (rand.nextBoolean()) { // true 50% of the time
            discardPile.push(cardToDiscard);
            System.out.println(player.getName() + " discards " + cardToDiscard + " to the discard pile.");
        } else {
            stockPile.offer(cardToDiscard);
            System.out.println(player.getName() + " discards " + cardToDiscard + " to the stockpile.");
        }
    }

    /**
     * Creates a HashMap (suitCount) to store the number of times each suit appears
     * in the hand.
     * The key is a Card.Suit (like HEARTS, CLUBS), and the value is the count of
     * that suit.
     * Loops through each card in the player's hand.
     * card.getSuit() gets the suit of the card.
     * suitCount.getOrDefault(card.getSuit(), 0) + 1:
     * If the suit is already in the map, increase its count.
     * If the suit isn't in the map, set its count to 1.
     * suitCount.entrySet().stream() → Converts the HashMap into a stream of entries
     * ((suit, count) pairs).
     * .max(Map.Entry.comparingByValue()) → Finds the entry with the highest count.
     * .get() → Retrieves the winning (suit, count) pair.
     * .getKey() → Extracts and returns the suit.
     */
    private Card.Suit getPreferredSuit(Hand hand) {
        Map<Card.Suit, Integer> suitCount = new HashMap<>();
        for (Card card : hand.getHand()) {
            suitCount.put(card.getSuit(), suitCount.getOrDefault(card.getSuit(), 0) + 1);
        }
        return suitCount.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    private boolean shouldKnock(Player player) {
        return player.getHand().getTotalValue() >= 25; // The player should knock if their hand value is 25 or more.
    }

    private void resolveRound(Player knocker) { // Resolves the round after a player knocks.
        //int knockerScore = knocker.getHand().getTotalValue();
        //System.out.println(knocker.getName() + " has a score of " + knockerScore);

        // map, which holds players and their hand values
        // Collect scores for all players still in the game
        Map<Player, Integer> playerScores = new HashMap<>();
        for (Player player : players) {
            if (player.getLives() <= 0)
                continue;

            int playerScore = player.getHand().getTotalValue();
            playerScores.put(player, playerScore);
            System.out.println(player.getName() + " has a score of " + playerScore);
        }

        /**
         * playerScores.values() Get all scores.
         * .stream() Turn it into a stream for processing. allows us to perform operations on the scores.
         * .min(Integer::compareTo) Find the minimum score.
         * .orElse(Integer.MAX_VALUE) Return the minimum, or a large fallback value if
         * empty.
         */

        // Find the lowest score(s)
        int lowestScore = playerScores.values().stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
        List<Player> lowestPlayers = new ArrayList<>();
        for (Map.Entry<Player, Integer> entry : playerScores.entrySet()) {
            if (entry.getValue() == lowestScore) {
                lowestPlayers.add(entry.getKey());
            }
        }

        // Apply life loss rules
        if (lowestPlayers.contains(knocker) && lowestPlayers.size() == 1) {
            // Knocker is the lowest and alone -> loses 2 lives
            knocker.loseLife();
            knocker.loseLife();
            System.out.println(knocker.getName() + " has the lowest hand and loses 2 lives!");
        } else {
            // All players with the lowest hand lose 1 life
            for (Player player : lowestPlayers) {
                player.loseLife();
                System.out.println(player.getName() + " has the lowest hand and loses 1 life!");
            }
        }

        System.out.println("End of round. Player lives:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getLives() + " lives remaining.");
        }
    }

    private void ensureDiscardPileNotEmpty() {
        if (discardPile.isEmpty()) {
            if (!stockPile.isEmpty()) {
                discardPile.push(stockPile.poll());
                System.out.println(
                        "Discard pile was empty. Moved " + discardPile.peek() + " from stockpile to discard pile.");
            }
        }
    }

}
