package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instantiation
        Scanner scan = new Scanner(System.in);
        char[][] grid = new char[3][3];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '_';
            }
        }

        printGrid(grid);

        // Mainloop
        int xFromLeft;
        int yFromBottom;
        String gameState = "";
        char turn = 'X';

        while (true) {
            // Player Input
            while (true) {
                try {
                    System.out.print("Enter the coordinates: ");
                    String posInput = scan.nextLine();
                    String[] pieces = posInput.split(" ");
                    xFromLeft = Integer.parseInt(pieces[0]);
                    yFromBottom = Integer.parseInt(pieces[1]);

                    if (grid[3 - yFromBottom][xFromLeft - 1] == '_') {
                        grid[3 - yFromBottom][xFromLeft - 1] = turn;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
            }

            printGrid(grid);

            // Game state test
            gameState = getGameState(grid);
            if (gameState.equals("Impossible") || gameState.equals("X wins") || gameState.equals("O wins") || gameState.equals("Draw")) {
                break;
            }

            // Switch turn
            switch (turn) {
                case 'X':
                    turn = 'O';
                    break;
                case 'O':
                    turn = 'X';
                    break;
                default:
                    break;
            }
        }

        // Output
        System.out.println(gameState);
    }

    static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < grid.length; i++) {
            System.out.println("| " + grid[i][0] + " " + grid[i][1] + " " + grid[i][2] + " |");
        }
        System.out.println("---------");
    }

    static String getGameState(char[][] grid) {
        // Declaration
        String gameState;
        int xCount = 0;
        int oCount = 0;
        int emptyCount = 0;
        int xInARowCount = 0;
        int oInARowCount = 0;

        // X O and _ counts
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 'X') {
                    xCount++;
                } else if (grid[i][j] == 'O') {
                    oCount++;
                } else {
                    emptyCount++;
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            int rowSum = grid[i][0] + grid[i][1] + grid[i][2];
            int colSum = grid[0][i] + grid[1][i] + grid[2][i];

            // Horizontal 3 in row counts
            if (rowSum == 264) {
                xInARowCount++;
            } else if (rowSum == 237) {
                oInARowCount++;
            }

            // Vertical 3 in row counts
            if (colSum == 264) {
                xInARowCount++;
            } else if (colSum == 237) {
                oInARowCount++;
            }
        }

        // Diagonal 3 in row counts
        int topLeftBottomRight = grid[0][0] + grid[1][1] + grid[2][2];
        int bottomLeftTopRight = grid[2][0] + grid[1][1] + grid[0][2];

        if (topLeftBottomRight == 264) {
            xInARowCount++;
        } else if (topLeftBottomRight == 237) {
            oInARowCount++;
        }

        if (bottomLeftTopRight == 264) {
            xInARowCount++;
        } else if (bottomLeftTopRight == 237) {
            oInARowCount++;
        }

        // Conditions
        boolean tooManyXorOs = Math.abs(xCount - oCount) > 1;
        boolean bothHave3InRow = xInARowCount > 0 && oInARowCount > 0;

        if (tooManyXorOs || bothHave3InRow) {
            gameState = "Impossible";
        } else if (xInARowCount > 0) {
            gameState = "X wins";
        } else if (oInARowCount > 0) {
            gameState = "O wins";
        } else if (emptyCount == 0) {
            gameState = "Draw";
        } else {
            gameState = "Game not finished";
        }

        // Output
        return gameState;
    }
}
