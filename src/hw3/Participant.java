package hw3;

import java.util.ArrayList;

/**
 * This class represents a participant in a Blackjack game. In this implementation, there is only one player and the dealer.
 * @author Han Zhu
 *
 */
public class Participant {
	private ArrayList<Card> hand;
	
	/**
	 * Constructor. Initialize participant with an empty hand.
	 */
	public Participant() {
		this.hand = new ArrayList<Card>();
	}

	/**
	 * Getter method for the participant's hand.
	 * @return the participant's hand.
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	/**
	 * Calculates the participant's current score when there is no ace or ace is treated as 1.
	 * @return the participant's current score under the above conditions.
	 */
	public int getScore() {
		int score = 0;
		
		for (Card c: hand) {
			score += c.getRankAsInt();
		}
		
		return score;
	}
	
	/**
	 * Calculates the number of aces in the participant's hand. At first I used boolean hasAce() but then I realized there could be multiple aces. In that case, 
	 * not all aces can be treated as 11.
	 * @return the number of aces in the player's hand.
	 */
	public int getNumOfAces() {
		int numOfAces = 0;
		
		for (Card c: hand) {
			if (c.getRankAsInt() == 1) {
				numOfAces++;
			}
		}
		
		return numOfAces;
	}
	
	/**
	 * Calculates the participant's current score when there is 1 or more aces. When there is 1 ace, treat it as 11 (getScore() will return the score when ace
	 * is treated as 1). When there are more than 1 aces, treat only 1 of them as 11, so that the hand does not bust (Again, getScore() will handle the cases
	 * where all aces are treated as 1).
	 * @param numOfAces the number of aces in hand.
	 * @return the participant's current score.
	 */
	public int getScoreWithAces(int numOfAces) {
		int score = 0;
		
		if (numOfAces == 1) {
			for (Card c: hand) {
				if (c.getRankAsInt() == 1) {
					score = score + 11;
				} else {
					score += c.getRankAsInt();
				}
			}
		} else {
			for (Card c: hand) {
				if (c.getRankAsInt() != 1) {
					score += c.getRankAsInt();
				}
			}
			score += (11 + numOfAces - 1);
		}
		
		return score;
	}
}
