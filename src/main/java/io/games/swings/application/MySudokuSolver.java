package io.games.swings.application;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 5, 2014
 */

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import io.games.swings.base.UtilityBaseFrame;
import io.generator.Sudoku;
import io.generator.SudokuGenerator;
import io.generator.Sudoku.SudokuLevel;

public class MySudokuSolver {
	static Logger log = Logger.getLogger(MySudokuSolver.class.getName());

	public static void main(String[] args) {
		log.info("Starting MySudokuSolver");
		Sudoku sudoku = SudokuGenerator.generatePlayableRandomSudoku(SudokuLevel.EASY);
		Integer[][] matrix = sudoku.getMatrix();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(" "+ matrix[i][j]);
			}	
			System.out.println("");
		}
		MySudokuSolver myUtilityApplication = new MySudokuSolver();
		myUtilityApplication.initiateFrame();
	}

		public void initiateFrame() {
			UtilityBaseFrame myApplicationFrame = new UtilityBaseFrame();
			myApplicationFrame.setTitle("MySudokuSolver");
			myApplicationFrame.buildFrame();
			myApplicationFrame.setVisible(true);
			log.info("Added MySudokuSolver to the frame");

			SudokuUtilityPanel loginBasePanel = new SudokuUtilityPanel();
			loginBasePanel.buildPanel("SudokuPanel");
			loginBasePanel.setMainFrame(myApplicationFrame);
			myApplicationFrame.addTabbedPanel(loginBasePanel);

			myApplicationFrame.setSelected("SudokuPanel");

			// myApplicationFrame.setExtendedState(myApplicationFrame
					// .getExtendedState() | JFrame.MAXIMIZED_BOTH);
		}}
