import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private char[] board;
    private Random random;

    public GameLogic() {
        board = new char[9];
        random = new Random();
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    public boolean makeMove(int index, char symbol) {
        if (index < 0 || index >= 9) return false;
        if (board[index] != ' ') return false;
        board[index] = symbol;
        return true;
    }

    public boolean checkWinner(char symbol) {
        int[][] patterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int i = 0; i < patterns.length; i++) {
            int a = patterns[i][0];
            int b = patterns[i][1];
            int c = patterns[i][2];
            if (board[a] == symbol && board[b] == symbol && board[c] == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') return false;
        }
        return true;
    }

    public int computerMove() {
        // First try to win
        int move = findBestMove('O');
        if (move != -1) return move;

        // Then block player
        move = findBestMove('X');
        if (move != -1) return move;

        // Take center if free
        if (board[4] == ' ') return 4;

        // Otherwise pick a random empty cell
        ArrayList<Integer> empty = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') empty.add(i);
        }
        if (empty.isEmpty()) return -1;
        return empty.get(random.nextInt(empty.size()));
    }

    private int findBestMove(char symbol) {
        int[][] patterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] p : patterns) {
            int countSymbol = 0;
            int emptyIndex = -1;
            for (int idx : p) {
                if (board[idx] == symbol) countSymbol++;
                else if (board[idx] == ' ') emptyIndex = idx;
            }
            if (countSymbol == 2 && emptyIndex != -1) return emptyIndex;
        }
        return -1;
    }

    public char[] getBoard() {
        return board;
    }
}
