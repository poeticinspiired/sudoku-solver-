import java.util.*;

/**
 * Tests for the {@link Dijkstra} class.
 */
public class DijkstraTest {

    public static void main(String[] args) {
        int[][] board = {
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

        if (solve(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean solve(int[][] board) {
        int[] find = findEmpty(board);
        if (find == null) {
            return true; // Solved
        }
        int row = find[0], col = find[1];

        for (int num = 1; num <= 9; num++) {
            if (valid(board, row, col, num)) {
                board[row][col] = num;
                if (solve(board)) {
                    return true; // Solved
                }
                board[row][col] = 0; // Backtrack
            }
        }
        return false; // Trigger backtracking
    }

    private static boolean valid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num && col != i) {
                return false; // Row check
            }
            if (board[i][col] == num && row != i) {
                return false; // Column check
            }
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num && (row != 3 * (row / 3) + i / 3 || col != 3 * (col / 3) + i % 3)) {
                return false; // Box check
            }
        }
        return true;
    }

    private static int[] findEmpty(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j}; // Empty cell found
                }
            }
        }
        return null; // No empty cells
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-----------");
            }
            for (int j = 0; j < board[0].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
