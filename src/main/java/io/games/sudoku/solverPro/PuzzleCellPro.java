package io.games.sudoku.solverPro;

import lombok.Getter;

import javax.swing.JComponent;
import javax.swing.JTextField;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PuzzleCellPro {
    private JTextField cell = null;
    private int index = -1;
    private int row, col, section = -1;
    private List<Integer> possibles;
    private boolean isSolved = false;

    public PuzzleCellPro(int i, JComponent cell, int row, int col) {
        index = i;
        this.cell = (JTextField) cell;
        this.row = row;
        this.col = col;
        setSection();
        String iValue = this.cell.getText();
        this.cell.setHorizontalAlignment(JTextField.CENTER);
        isSolved = !iValue.isEmpty();
        if (isSolved) {
            possibles = List.of(Integer.valueOf(iValue));
            this.cell.setBackground(java.awt.Color.GREEN);
            this.cell.setFont(new Font("Tahoma", Font.BOLD, 14));
        } else {
            this.cell.setBackground(java.awt.Color.RED);
            this.cell.setText("0");
            possibles = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
    }

    public void setValue(int value) {
        cell.setText(value+"");
        isSolved = true;
    }

    public int getValue() {
        return Integer.valueOf(cell.getText());
    }

    public boolean reducePossibles(List<PuzzleCellPro> puzzleCells) {
        if (isSolved) {
            return false;
        }
        int possiblesCount = possibles.size();
        // resolve row possibles
        puzzleCells.stream().filter(p -> p.getRow() == row && p.getIndex() != index).forEach((p) -> {
            if (p.isSolved()) {
                possibles.remove(Integer.valueOf(p.getValue()));
            }
        });
        
        // resolve col possibles
        puzzleCells.stream().filter(p -> p.getCol() == col && p.getIndex() != index).forEach((p) -> {
            if (p.isSolved()) {
                possibles.remove(Integer.valueOf(p.getValue()));
            }
        });

        // resolve section possibles
        puzzleCells.stream().filter(p -> p.getSection() == section && p.getIndex() != index).forEach((p) -> {
            if (p.isSolved()) {
                possibles.remove(Integer.valueOf(p.getValue()));
            }
        });
        if (possibles.size() == 1) {
            setValue(possibles.get(0));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            this.cell.setBackground(java.awt.Color.BLUE);
        }

        return possibles.size() < possiblesCount;
    }

    private void setSection() {
        if (row < 3 && col < 3) {
            section = 1;
        } else if (row < 3 && col < 6) {
            section = 2;
        } else if (row < 3 && col < 9) {
            section = 3;
        } else if (row < 6 && col < 3) {
            section = 4;
        } else if (row < 6 && col < 6) {
            section = 5;
        } else if (row < 6 && col < 9) {
            section = 6;
        } else if (row < 9 && col < 3) {
            section = 7;
        } else if (row < 9 && col < 6) {
            section = 8;
        } else {
            section = 9;
        }
    }
}
