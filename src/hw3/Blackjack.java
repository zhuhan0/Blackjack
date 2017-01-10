package hw3;

/**
 * This class represents a simplified Blackjack game.  
 * @author Han Zhu
 *
 */
public class Blackjack {
	private Deck deck;
	private Participant dealer;
	private Participant player;
	public boolean doesBust;
	
	/**
	 * Constructor. Create a deck of cards (actually an empty ArrayList of cards dealt), a dealer and a player. Set doesBust to false. If the player busts in the
	 * game, doesBust will become true.
	 */
	public Blackjack() {
		this.deck = new Deck();
		this.dealer = new Participant();
		this.player = new Participant();
		doesBust = false;
	}
	
	/**
	 * Deal starting hands to dealer and player. The dealer hides one of his cards. The player's starting score is printed.
	 */
	public void dealStartingHands() {
		Card dealerDownCard = deck.dealCard();
		dealer.getHand().add(dealerDownCard);
		deck.addCard(dealerDownCard);
		
		Card dealerUpCard = deck.dealCard();
		dealer.getHand().add(dealerUpCard);
		deck.addCard(dealerUpCard);

		System.out.println("Dealer's starting hand is " + dealerUpCard.getRankAsString() + " of " + dealerUpCard.getSuit() + " plus a hidden card.");
		
		Card playerFirstCard = deck.dealCard();
		player.getHand().add(playerFirstCard);
		deck.addCard(playerFirstCard);

		Card playerSecondCard = deck.dealCard();
		player.getHand().add(playerSecondCard);
		deck.addCard(playerSecondCard);

		System.out.println("Your starting hand is " + playerFirstCard.getRankAsString() + " of " + playerFirstCard.getSuit() + " and " + 
				playerSecondCard.getRankAsString() + " of " + playerSecondCard.getSuit() + ".");
		
		getScore(player);
	}
	
	/**
	 * Calculate the most advantageous score according to the number of aces in hand. The score is also printed. If there is an ace and the score does not bust
	 * even when it is treated as 11, then both scores are printed. If one of the participants busts, doesBust will be set to true. 
	 * @param part the player or dealer.
	 * @return the score if no ace; the score closer to 21 if there is at least an ace.
	 */
	public int getScore(Participant part) {
		int numOfAces = part.getNumOfAces();
		if (numOfAces == 0) {
			int score = part.getScore();
			
			System.out.println((part.equals(player)? "Your" : "Dealer's") + " current score is " + score + ".");
			
			if (score > 21) {
				doesBust = true;
			}
			
			return score;
		} else {
			int score = part.getScore();
			int scoreWithAces = part.getScoreWithAces(numOfAces);
			if (scoreWithAces > 21) {
				System.out.println((part.equals(player)? "Your" : "Dealer's") + " current score is " + score + ".");
				
				if (score > 21) {
					doesBust = true;
				}
				
				return score;
			} else {
				System.out.println((part.equals(player)? "Your" : "Dealer's") + " current score is " + score + " or " + scoreWithAces + ".");
				
				return scoreWithAces;
			}
		}
	}
	
	/**
	 * This method is called if the player wants to hit. He will get a new card from the deck. The card will be stored in cardsDealt as well. When the player
	 * gets the card, a statement is printed to notify him what the card is. And getScore() is called to print the player's score and check whether he busts.
	 */
	public void letPlayerHit() {
		Card hitCard = deck.dealCard();
		player.getHand().add(hitCard);
		deck.addCard(hitCard);
		
		System.out.println("You got " + hitCard.getRankAsString() + " of " + hitCard.getSuit() + ".");
		getScore(player);
	}
	
	/**
	 * This method prints dealer's down card.
	 */
	public void showDealerDownCard() {
		Card dealerDownCard = dealer.getHand().get(0);
		System.out.println("Dealer's down card is " + dealerDownCard.getRankAsString() + " of " + dealerDownCard.getSuit() + ".");
	}
	
	/**
	 * This method lets dealer play. As long as dealer's score is lower than 17, he keeps taking a card. The card dealer got and his score are printed.
	 */
	public void letDealerPlay() {
		int dealerScore = getScore(dealer);
		
		while (dealerScore < 17) {
			System.out.println("Dealer hits.");
			
			Card hitCard = deck.dealCard();
			dealer.getHand().add(hitCard);
			deck.addCard(hitCard);
			
			System.out.println("Dealer got " + hitCard.getRankAsString() + " of " + hitCard.getSuit() + ".");
			dealerScore = getScore(dealer);
		}
		
		System.out.println("Dealer stands.");
	}
	
	/**
	 * If the player didn't bust, this method will be called in the interface. It will compare the scores of player and dealer and announce the winner. Also if
	 * dealer busts, player automatically wins.
	 */
	public void compareScore() {
		int playerScore = getScore(player);
		int dealerScore = getScore(dealer);
		
		if (dealerScore > 21) {
			System.out.println("Dealer busts. You win! XD");
		} else if (playerScore > dealerScore) {
			System.out.println("Final score: " + playerScore + " - " + dealerScore + ". You win! XD");
		} else if (playerScore == dealerScore) {
			System.out.println("Final score: " + playerScore + " - " + dealerScore + ". Push.");
		} else {
			System.out.println("Final score: " + playerScore + " - " + dealerScore + ". Dealer wins. X(");
		}
	}
}
