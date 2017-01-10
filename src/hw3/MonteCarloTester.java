package hw3;

/**
 * This class runs the Monte Carlo simulation, prints the results in the order of "winning, push, losing", and prints the bust ratio.
 * @author Han Zhu
 *
 */
public class MonteCarloTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int trials = 1000;
		
		MonteCarlo tester = new MonteCarlo(trials);
		
		for (int i = 0; i < 3; i++) {
			System.out.println(tester.results[i]);
		}
		
		System.out.println(tester.timesBusted / trials);
	}

}
