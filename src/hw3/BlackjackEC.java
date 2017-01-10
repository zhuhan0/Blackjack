package hw3;

/**
 * This class represents a simple Blackjack game. Most of the methods here are from Blackjack.java. Methods that were designed for a human player such as 
 * letPlayerHit() and System.out.println() are removed. A new method letAutoPlayerPlay() was added.s
 * @author Han Zhu
 *
 */
public class BlackjackEC {
	private Deck deck;
	private Participant dealer;
	private Participant player;
	public boolean doesBust;
	
	/**
	 * Constructor. Create a deck of cards, a dealer and a player. Set doesBust to false. If the player busts in the game, doesBust will become true.
	 */
	public BlackjackEC() {
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
		
		Card playerFirstCard = deck.dealCard();
		player.getHand().add(playerFirstCard);
		deck.addCard(playerFirstCard);

		Card playerSecondCard = deck.dealCard();
		player.getHand().add(playerSecondCard);
		deck.addCard(playerSecondCard);
		
		getScore(player);
	}
	
	/**
	 * Calculate the most advantageous score according to the number of aces in the hand. The score is also printed. If there is an ace and the score does not
	 * bust neither when ace is 1 nor 11, then both scores are printed. If either participant busts, doesBust will be set to true. 
	 * @param part the player or dealer.
	 * @return the score if no ace; the score closer to 21 if there is at least an ace.
	 */
	public int getScore(Participant part) {
		int numOfAces = part.getNumOfAces();
		if (numOfAces == 0) {
			int score = part.getScore();
			
			if (score > 21) {
				doesBust = true;
			}
			
			return score;
		} else {
			int score = part.getScore();
			int scoreWithAces = part.getScoreWithAces(numOfAces);
			if (scoreWithAces > 21) {
				
				if (score > 21) {
					doesBust = true;
				}
				
				return score;
			} else {				
				return scoreWithAces;
			}
		}
	}
	
	/**
	 * This method lets dealer play. As long as dealer's score is lower than 17, he keeps taking a card. The card dealer got and his score are printed.
	 */
	public void letDealerPlay() {
		int dealerScore = getScore(dealer);
		
		while (dealerScore < 17) {			
			Card hitCard = deck.dealCard();
			dealer.getHand().add(hitCard);
			deck.addCard(hitCard);
			
			dealerScore = getScore(dealer);
		}		
	}
	
	/**
	 * If the player didn't bust, this method will be called in the interface. It will compare the scores of player and dealer and announce the winner. Also if
	 * dealer busts, player automatically wins.
	 */
	public int compareScore() {
		int playerScore = getScore(player);
		int dealerScore = getScore(dealer);
		
		if (dealerScore > 21) {
			return 0;
		} else if (playerScore > dealerScore) {
			return 0;
		} else if (playerScore == dealerScore) {
			return 1;
		} else {
			return 2;
		}
	}
	
	/**
	 * This method implements an automated player to test the winning strategy.
	 */
	public void letAutoPlayerPlay() {
		Card dealerUpCard = dealer.getHand().get(1);
		int playerScore = getScore(player);
		int numOfAces = player.getNumOfAces();
		
		if (dealerUpCard.getRankAsInt() <= 6) {
			if (numOfAces == 0) {
				while (playerScore < 12) {				
					Card hitCard = deck.dealCard();
					player.getHand().add(hitCard);
					deck.addCard(hitCard);
	
					playerScore = getScore(player);
				}
			} else {
				while (playerScore < 16) {				
					Card hitCard = deck.dealCard();
					player.getHand().add(hitCard);
					deck.addCard(hitCard);
					
					playerScore = getScore(player);
				}
			}
		} else {
			if (numOfAces == 0) {
				while (playerScore < 16) {				
					Card hitCard = deck.dealCard();
					player.getHand().add(hitCard);
					deck.addCard(hitCard);
	
					playerScore = getScore(player);
				}
			} else {
				while (playerScore < 17) {				
					Card hitCard = deck.dealCard();
					player.getHand().add(hitCard);
					deck.addCard(hitCard);
					
					playerScore = getScore(player);
				}
			}
		}
			
		if (playerScore > 21) {
			doesBust = true;
		}
	}
}

