package hw3;

import java.util.Scanner;

/**
 * This class represents the Blackjack game interface. It creates a Blackjack game, takes user input, and outputs game results.
 * @author Han Zhu
 *
 */
public class BlackjackInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome! The game now starts. Good luck!");
		
		Blackjack game = new Blackjack();
		
		game.dealStartingHands();
		
//		Player plays. 
		Scanner in = new Scanner(System.in);
		System.out.println("Hit? (Y/N)");
		String input = in.next().toLowerCase();
		
//		Check whether player wants to hit.
		while (input.equals("y")) {
			game.letPlayerHit();
			
//			Check whether player busts. End game and return if busted.
			if (game.doesBust) {
				System.out.println("You have busted! Dealer wins. X(");
				
				in.close();
				return;
			}
			
			System.out.println("Hit? (Y/N)");
			input = in.next().toLowerCase();
		}
		
//		Dealer plays.
		game.showDealerDownCard();
		game.letDealerPlay();
		
//		Announce winner. Game ends.
		game.compareScore();
		
		in.close();
	}

}
