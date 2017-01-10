package hw3;

/**
 * This class represents a standard playing card. It has 2 instance variables suit and rank.
 * @author Han Zhu
 *
 */
public class Card {
	private String suit;
	private int rank;
	
	/**
	 * Constructor. The card is defined by a random number between 0 and 52 (exclusive).
	 * @param randNum a random int between 0 and 52.
	 */
	public Card(int randNum) {
		rank = randNum % 13 + 1;
		
		switch (randNum / 13) {
		case 0: suit = "Spades"; 
				break;
		case 1: suit = "Hearts";
				break;
		case 2: suit = "Clubs";
				break;
		case 3: suit = "Diamonds";
				break;
		}
	}

	/**
	 * Getter method for suit.
	 * @return the card's suit.
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * Getter method for rank. Different values of aces are not considered here. Face cards are treated as 10 in order to calculate scores.
	 * @return the rank of the card under the above conditions.
	 */
	public int getRankAsInt() {
		if (rank <= 10) {
			return rank;
		} else {
			return 10;
		}
	}

	/**
	 * Getter method for rank.
	 * @return the card's rank in String because "Ace", "Jack"... makes more sense than "1", "11"... when they are printed out.
	 */
	public String getRankAsString() {
		switch (rank) {
		case 1: return "Ace";
		case 2: return "Two";
		case 3: return "Three";
		case 4: return "Four";
		case 5: return "Five";
		case 6: return "Six";
		case 7: return "Seven";
		case 8: return "Eight";
		case 9: return "Nine";
		case 10: return "Ten";
		case 11: return "Jack";
		case 12: return "Queen";
		case 13: return "King";
		}
		return null;
	}
}
