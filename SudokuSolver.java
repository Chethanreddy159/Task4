public class SudokuSolver {

    private static final int SIZE = 9;
    private int[][] grid;

    public SudokuSolver(int[][] grid) {
        this.grid = grid;
    }

    public boolean solve() {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find first empty cell
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If no empty cell found, puzzle solved
        if (isEmpty) {
            return true;
        }

        // Try numbers from 1 to 9
        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(row, col, num)) {
                grid[row][col] = num;

                // Recursively solve the puzzle
                if (solve()) {
                    return true;
                }

                // If not successful, backtrack
                grid[row][col] = 0;
            }
        }

        // No number fits, backtrack
        return false;
    }

    private boolean isSafe(int row, int col, int num) {
        // Check if num is already used in current row, column or 3x3 grid
        return !usedInRow(row, num) && !usedInCol(col, num) && !usedInBox(row - row % 3, col - col % 3, num);
    }

    private boolean usedInRow(int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInCol(int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] sudokuGrid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuSolver solver = new SudokuSolver(sudokuGrid);
        if (solver.solve()) {
            System.out.println("Sudoku puzzle solved successfully:");
            solver.printGrid();
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }
}
