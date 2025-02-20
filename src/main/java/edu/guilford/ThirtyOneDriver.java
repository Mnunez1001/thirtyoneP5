package edu.guilford;

public class ThirtyOneDriver {
    public static void main(String[] args) {

        // Test Hand and Card class

        System.out.println("-----Test Hand and Card class-----");
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.FIVE));

        System.out.println("\n" + hand);
        System.out.println("Hand Value: " + hand.getTotalValue());


        // Test Deck class
        System.out.println("-----Test Deck class-----");

        Deck deck = new Deck();
        deck.shuffle();
        Card card1 = deck.deal();
        System.out.println("\nDealt card: " + card1);
        System.out.println("Remaining cards in deck: " + deck.size());


        System.out.println("\n-----Welcome to Thirty-One!-----");

        // Create a game with 3 players.
        Game game = new Game(3);

        // Start the game
        game.play();

        System.out.println("Game Over. Thanks for playing!");




        
    }


    
}