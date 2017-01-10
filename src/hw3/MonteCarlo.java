package hw3;

/**
 * This class represents the Monte Carlo simulation for testing the winning strategy.
 * @author Han Zhu
 *
 */
public class MonteCarlo {
	private int trials;
	public int[] results;
	public double timesBusted;
	
	/**
	 * Constructor. Set the number of trials. Initiate results as an empty array of size 3 because there are 3 possible outcomes. And finally run the experiment.
	 * @param trials
	 */
	public MonteCarlo(int trials) {
		this.trials = trials;
		results = new int[3];
		runExperiment();
	}
	
	/**
	 * Run the game once. Also keep track of the times player busted to calculate the "bust ratio".
	 * @return
	 */
	public int runTrial() {
		BlackjackEC game = new BlackjackEC();
		
		game.dealStartingHands();
		
		game.letAutoPlayerPlay();
		
		if (game.doesBust) {
			timesBusted++;
			
			return 2;
		}

		game.letDealerPlay();

		return game.compareScore();
	}
	
	/**
	 * Run the simulation with a loop.
	 */
	public void runExperiment() {
		while (trials > 0) {
			int result = runTrial();
			results[result]++;
			trials--;
		}
	}
}
