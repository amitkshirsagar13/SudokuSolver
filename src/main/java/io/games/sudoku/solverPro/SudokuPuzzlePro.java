package io.games.sudoku.solverPro;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JComponent;
import javax.swing.JTextField;

import io.games.swings.base.UtilityStatusPanel;

public class SudokuPuzzlePro {
    static Logger log = Logger.getLogger(SudokuPuzzlePro.class.getName());
    private List<PuzzleCellPro> cellList;
    private UtilityStatusPanel statusPanel;
    private HashMap<String, JComponent> formComponents;
    
    public SudokuPuzzlePro(HashMap<String, JComponent> formComponents, UtilityStatusPanel statusPanel) {
        this.formComponents = formComponents;
        cellList = new ArrayList<>();
        int i = 0;
        for (int row=0; row<9; row++) {
            for (int col=0; col<9; col++) {
                cellList.add(new PuzzleCellPro(i, formComponents.get("i"+row+"j"+col), row, col));
                i++;
            }
        }
        this.statusPanel = statusPanel;
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
        setStats();
        System.out.println("=========================================");
    }
    public void setStats() {
        int solvedCount = (int)cellList.stream().filter(p->p.isSolved()).count();
        statusPanel.setProgressStatus((int)((double)solvedCount/81 * 100), "Solved: " + solvedCount+"/81");
        Map<Integer, Map<Integer, Long>> groupByMap = cellList.stream().filter(c -> c.isSolved())
                .collect(Collectors.groupingBy(PuzzleCellPro::getRowGroup, 
                         Collectors.groupingBy(PuzzleCellPro::getValue, Collectors.counting())));
        Map<Integer, Long> countMap = new HashMap<>();
        groupByMap.values().forEach((group)-> {
            group.keySet().forEach((key)-> {
                long existingCount = countMap.getOrDefault((Integer)key, 0l);
                existingCount += group.get(key);
                countMap.put((Integer)key, existingCount);
            });
        });
        countMap.forEach((key, value)-> {
            JTextField textField = (JTextField)this.formComponents.get(key+"s");
            textField.setText(value+"");
            if (value == 9) {
                textField.setBackground(java.awt.Color.GREEN);
                textField.setFont(new Font("Tahoma", Font.BOLD, 14));
                textField.setToolTipText("N "+key+" is solved");
                textField.setHorizontalAlignment(JTextField.CENTER);
            } else if(value < 3) {
                textField.setBackground(java.awt.Color.RED);
                textField.setFont(new Font("Tahoma", Font.BOLD, 14));
                textField.setToolTipText("N "+key+" is not solved");
                textField.setHorizontalAlignment(JTextField.CENTER);
            } else {
                textField.setBackground(java.awt.Color.YELLOW);
                textField.setFont(new Font("Tahoma", Font.BOLD, 14));
                textField.setToolTipText("N "+key+" is near solved");
                textField.setHorizontalAlignment(JTextField.CENTER);
            }
        });
    }
}
