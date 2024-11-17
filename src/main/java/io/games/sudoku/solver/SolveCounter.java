package io.games.sudoku.solver;

/**
 * ProjectName: SudokuSolver
 * @author amit_kshirsagar
 * @date Feb 28, 2014
 */

import org.apache.log4j.Logger;

public class SolveCounter {
	static Logger log = Logger.getLogger(SolveCounter.class.getName());

	static boolean instanceContinue = true;

	/**
	 * @return the instanceContinue
	 */
	public static boolean isInstanceContinue() {
		return instanceContinue;
	}

	/**
	 * @param instanceContinue
	 *            the instanceContinue to set
	 */
	public static void setInstanceContinue() {
		instanceContinue = true;
	}

	public static void setInstanceStop() {
		instanceContinue = false;
	}

	static int iterationCounter = 0;

	public static void incrementIteration() {
		iterationCounter++;
	}
}
