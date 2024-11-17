package io.games.sudoku.solver;

/**
 * ProjectName: SudokuSolver
 * @author amit_kshirsagar
 * @date Feb 28, 2014
 */

import org.apache.log4j.Logger;
import java.util.HashMap;
import javax.swing.JComponent;

public class SudokuPuzzle {
	static Logger log = Logger.getLogger(SudokuPuzzle.class.getName());

	PuzzleCell[][] puzzleCell = new PuzzleCell[9][9];
	HashMap<String, JComponent> formComponents = null;

	public void initilizePuzzleParameter(int i, int j, int cellValue, HashMap<String, JComponent> formComponents) {
		if (puzzleCell[i][j] == null) {
			puzzleCell[i][j] = new PuzzleCell();
		}
		if (cellValue > 0) {
			puzzleCell[i][j].setCellValue(cellValue);
		}
		puzzleCell[i][j].setId(i + "" + j);

		setPuzzleCellSection(puzzleCell[i][j]);
		this.formComponents = formComponents;
	}

	public void setPuzzleCellSection(PuzzleCell puzzleCell) {
		int row = puzzleCell.getRow();
		int column = puzzleCell.getColumn();

		if (row > -1 && row < 3 && column < 3) {
			puzzleCell.setSection(1 + "");
		} else if (row > 2 && row < 6 && column < 3) {
			puzzleCell.setSection(2 + "");
		} else if (row > 5 && row < 9 && column < 3) {
			puzzleCell.setSection(3 + "");
		} else if (row > -1 && row < 3 && column > 2 && column < 6) {
			puzzleCell.setSection(4 + "");
		} else if (row > 2 && row < 6 && column > 2 && column < 6) {
			puzzleCell.setSection(5 + "");
		} else if (row > 5 && row < 9 && column > 2 && column < 6) {
			puzzleCell.setSection(6 + "");
		} else if (row > -1 && row < 3 && column > 5 && column < 9) {
			puzzleCell.setSection(7 + "");
		} else if (row > 2 && row < 6 && column > 5 && column < 9) {
			puzzleCell.setSection(8 + "");
		} else if (row > 5 && row < 9 && column > 5 && column < 9) {
			puzzleCell.setSection(9 + "");
		}
	}

}
