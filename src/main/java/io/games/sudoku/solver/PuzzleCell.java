package io.games.sudoku.solver;

/**
 * ProjectName: SudokuSolver
 * @author amit_kshirsagar
 * @date Feb 28, 2014
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PuzzleCell {
	static Logger log = Logger.getLogger(PuzzleCell.class.getName());

	public PuzzleCell() {
		for (int i = 1; i < 10; i++) {
			possibleValue.add(i);
		}
	}

	String id = null;
	String section = null;
	int row = -1;
	int column = -1;

	int cellValue = 0;

	/**
	 * @return the cellValue
	 */
	public int getCellValue() {
		return cellValue;
	}

	/**
	 * @param cellValue
	 *            the cellValue to set
	 */
	public void setCellValue(int cellValue) {
		this.cellValue = cellValue;
		setSolved();
	}

	List<Integer> possibleValue = new ArrayList<Integer>();
	boolean isSolved = false;

	/**
	 * @param isSolved
	 *            the isSolved to set
	 */
	public void setSolved() {
		this.isSolved = true;
	}

	/**
	 * @return the isSolved
	 */
	public boolean isSolved() {
		return isSolved;
	}

	public void removePossibleValue(Integer noPossibleValue) {
		for (int i = 0; i < possibleValue.size(); i++) {
			possibleValue.remove(noPossibleValue);
			break;
		}
		if (possibleValue.size() == 1) {
			setCellValue(possibleValue.get(0));
			SolveCounter.setInstanceContinue();
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
		this.row = Integer.parseInt(id.substring(0, 1));
		this.column = Integer.parseInt(id.substring(1, 2));
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section
	 *            the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return cellValue + "";
	}
}
