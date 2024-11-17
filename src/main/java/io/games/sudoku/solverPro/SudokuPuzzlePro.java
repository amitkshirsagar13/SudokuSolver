package io.games.sudoku.solverPro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JComponent;

public class SudokuPuzzlePro {
    static Logger log = Logger.getLogger(SudokuPuzzlePro.class.getName());
    private List<PuzzleCellPro> cellList;

    public SudokuPuzzlePro(HashMap<String, JComponent> formComponents) {
        cellList = new ArrayList<>();
        int i = 0;
        for (int row=0; row<9; row++) {
            for (int col=0; col<9; col++) {
                cellList.add(new PuzzleCellPro(i, formComponents.get("i"+row+"j"+col), row, col));
                i++;
            }
        }
    }

    public void solve() {
        boolean canContinue = true;
        int iteration = 0;
        while (canContinue) {
            canContinue = false;
            System.out.println("Reducing by values");
            for (PuzzleCellPro cell : cellList) {
                boolean isReduced = cell.reducePossiblesByValues(cellList);
                if (!canContinue && isReduced) {
                    canContinue = isReduced;
                }
            }
            if (!canContinue) {
                System.out.println("Reducing by possibles");
                for (PuzzleCellPro cell : cellList) {
                    boolean isReduced = cell.reducePossiblesByPossibles(cellList);
                    if (!canContinue && isReduced) {
                        canContinue = isReduced;
                    }
                }
            }
            iteration++;
            log.info("Iteration: " + iteration);
            System.out.println("Iteration: " + iteration + " canContinue: " + canContinue);
            printPuzzle();
        }
    }

    public void printPuzzle() {
        System.out.println("=========================================");
        for (int rs = 0; rs < 9; rs++) {
            final int r =  rs;
            final List<PuzzleCellPro> row = cellList.stream().filter(p -> p.getRow() == r).toList();
            System.out.print("||");
            for (int c = 0; c < 9; c++) {
                String endString = c == 2 || c == 5 || c == 8 ? " ||" : " |";
                System.out.print(" " + row.get(c).getValue() + endString);
            }
            System.out.println("");
            
            if (rs == 2 || rs == 5) {
                System.out.println("-----------------------------------------");

            }
        }
        System.out.println("=========================================");
    }
}
