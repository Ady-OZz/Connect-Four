import java.util.Scanner;

public class ConnectFour {
    private char[][] board;
    private char currentPlayer;

    public ConnectFour() {
        board = new char[6][7];
        currentPlayer = 'R'; // R for Red, Y for Yellow
        initializeBoard();
    }

    // Initialize the game board with empty spaces
    public void initializeBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the game board
    public void printBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Drop a disc into the specified column
    public boolean dropDisc(int column) {
        if (column < 0 || column >= 7) {
            System.out.println("Invalid column. Try again.");
            return false;
        }

        for (int i = 5; i >= 0; i--) {
            if (board[i][column] == '-') {
                board[i][column] = currentPlayer;
                return true;
            }
        }

        System.out.println("Column is full. Try again.");
        return false;
    }

    // Check if the current player has won
    public boolean checkWin() {
        // Check horizontal, vertical, and diagonal lines
        return (checkHorizontal() || checkVertical() || checkDiagonal());
    }

    // Check for a horizontal win
    private boolean checkHorizontal() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i][j + 1] == currentPlayer &&
                    board[i][j + 2] == currentPlayer && board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check for a vertical win
    private boolean checkVertical() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j] == currentPlayer &&
                    board[i + 2][j] == currentPlayer && board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check for a diagonal win
    private boolean checkDiagonal() {
        // Check for positive slope diagonals
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i - 1][j + 1] == currentPlayer &&
                    board[i - 2][j + 2] == currentPlayer && board[i - 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        // Check for negative slope diagonals
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j + 1] == currentPlayer &&
                    board[i + 2][j + 2] == currentPlayer && board[i + 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    // Switch the player
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
    }

    // Main game loop
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;

        while (!gameWon) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter a column (0-6) to drop your disc:");
            int column = scanner.nextInt();

            if (dropDisc(column)) {
                gameWon = checkWin();
                if (!gameWon) {
                    switchPlayer();
                }
            }
        }

        printBoard();
        System.out.println("Player " + currentPlayer + " wins!");
        scanner.close();
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.playGame();
    }
}
