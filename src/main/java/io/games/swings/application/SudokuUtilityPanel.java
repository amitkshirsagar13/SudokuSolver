package io.games.swings.application;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 5, 2014
 */

import java.util.Set;

import javax.swing.JTextField;

import org.apache.log4j.Logger;

import io.games.sudoku.solverPro.SudokuPuzzlePro;
import io.games.swings.base.UtilityBasePanel;

public class SudokuUtilityPanel extends UtilityBasePanel {
	static Logger log = Logger.getLogger(SudokuUtilityPanel.class.getName());

	private String RESOLVE = "RESOLVE";
	private List<String> DEFINED_ACTIONS = List.of("RESOLVE");


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.swings.base.UtilityBasePanel#populateCenterPanel()
	 */
	@Override
	protected void populateCenterPanel() {
		super.populateCenterPanel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.swings.base.UtilityBasePanelActionHandler#executeOK()
	 */
	@Override
	protected void executeOK() {
		log.info("Executing OK");
		super.executeOK();
		// Set<String> keySet = _formComponents.keySet();
		// SudokuSolver sudokuSolver = new SudokuSolver();
		// for (String key : keySet) {
		// JTextField cell = (JTextField) _formComponents.get(key);
		// int i = Integer.parseInt(cell.getName().substring(1, 2));
		// int j = Integer.parseInt(cell.getName().substring(3, 4));
		// int cellValue = 0;
		// if (cell.getText() != null && !cell.getText().equals("")) {
		// cellValue = Integer.parseInt(cell.getText());
		// cell.setBackground(Color.BLUE);
		// }
		// sudokuSolver.initilizePuzzleParameter(i, j, cellValue, _formComponents);

		// }
		// sudokuSolver.printPuzzle();
		// sudokuSolver.populatePuzzleMap();
		// try {
		// sudokuSolver.processPuzzle();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println("Processed....");

		SudokuPuzzlePro sudokuPuzzlePro = new SudokuPuzzlePro(_formComponents);
		sudokuPuzzlePro.printPuzzle();
		sudokuPuzzlePro.solve();
	}

	@Override
	protected void executeReset() {
		_formComponents.values().stream().forEach(p -> {
			JTextField pt = ((JTextField) p);
			pt.setText("");
			pt.setBackground(Color.WHITE);
		});
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		final String command = event.getActionCommand();
		if (DEFINED_ACTIONS.contains(command)) {
			Runnable actionRunner = new Runnable() {
				@Override
				public void run() {
					if (command.equalsIgnoreCase(RESOLVE)) {
						executeResolve();
					}
				}
			};

			Thread actionThread = new Thread(actionRunner, event.getSource()
					.toString());
			actionThread.start();
		} else {
			super.actionPerformed(event);
		}
	}

	protected void executeResolve() {
		log.info("Executing Resolve");
		System.out.println("Executing Resolve");
		executeOK();
	}
}
