package hw3;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a collection of standard playing cards. It keeps track of cards dealt and deals new cards.
 * @author Han Zhu
 *
 */
public class Deck {
	private ArrayList<String> cardsDealt;
	private Random rand;
	
	/**
	 * Constructor. Initialize cardsDealt as an empty ArrayList and a new Random object. At first I made cardsDealt as an ArrayList of Card. But I realized a 
	 * newly dealt Card will never be the same object as previous ones, although it might have the same rank and suit. Therefore I switched to an ArrayList of
	 * concatenated strings of the card's rank and suit.
	 */
	public Deck() {
		this.cardsDealt = new ArrayList<String>();
		this.rand = new Random();
	}
	
	/**
	 * Deals one new card which is not already dealt. Check whether the card is dealt through a while loop. If cardDealt does not contain the card, break out of
	 * the loop and return.
	 * @return a new card.
	 */
	public Card dealCard() {
		Card newCard = new Card(rand.nextInt(52));
		String newCardAsString = newCard.getRankAsString() + newCard.getSuit();

		while (cardsDealt.contains(newCardAsString)) {
			newCard = new Card(rand.nextInt(52));
			newCardAsString = newCard.getRankAsString() + newCard.getSuit();
		}
		return newCard;
	}
	
	/**
	 * Adds a newly dealt card to the cardsDealt.
	 * @param newCard
	 */
	public void addCard(Card newCard) {
		cardsDealt.add(newCard.getRankAsString() + newCard.getSuit());
	}

	/**
	 * Getter method of cardsDealt for debugging.
	 * @return cardsDealt
	 */
	public ArrayList<String> getCardsDealt() {
		return cardsDealt;
	}
}
