package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board {
    private tetrisSquare[][] board;
    private Pane gamePane;
    /**
     * This is the constructor of the Board class which creates a new tetrisSquare 2D array. addBoard() is also called
     * in the constructor.
     */
    public Board(Pane gamePane) {
        this.gamePane = gamePane;
        this.board = new tetrisSquare[22][12];
        this.addBoard();
    }
    /**
     * This method creates the board and loops through the number of rows and columns of tetrisSquare[][]. If the
     * location based on row and col are at the border, the color of the square is set to steelblue and the tetrisSquare
     * is added to the board. Otherwise, the square is set to the color white.
     */
    private void addBoard() {
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                // instantiate square, setting Y/X loc using row/col indices
                int x = col * Constants.SQUARE_WIDTH;
                int y = row * Constants.SQUARE_WIDTH;
                tetrisSquare borderS = new tetrisSquare(this.gamePane);
                if (row == 0 || col == 0 || col == this.board[0].length - 1 || row == this.board.length - 1) {
                    this.board[row][col] = borderS;
                    borderS.getSquare().setFill(Color.STEELBLUE);
                    borderS.getSquare().setX(x);
                    borderS.getSquare().setY(y);
                }
                else {
                    borderS.getSquare().setFill(Color.WHITE);
                    borderS.getSquare().setX(x);
                    borderS.getSquare().setY(y);
                }
            }
        }
    }

    /**
     * This method is responsible for reseting the board. If the board square is not a part of the border, then the
     * gamePane removes the square from the board given a certain row and col location. resetBoard is called when
     * the reset button is clicked, the function is set up in tetrisGame.
     */
    public void resetBoard(tetrisSquare[] pcs) {
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                if ((row != 0 && col != 0 && col != this.board[0].length - 1 && row != this.board.length - 1) &&
                        this.board[row][col] != null) {
                    this.gamePane.getChildren().remove(this.board[row][col].getSquare());
                    this.board[row][col] = null;
                }
            }
        }
        for (int i = 0; i < Constants.PIECE_LENGTH; i++) {
            this.gamePane.getChildren().remove(pcs[i].getSquare());
        }
    }
    /**
     * This method checks if the piece moving down is valid. It does so by seeing if the row below is empty. Then,
     * it sets the boolean canMoveDown to either true or false based on whether the next row (given the same
     * column) is empty.
     */
    public boolean validDown(tetrisSquare[] pcs) {
        boolean canMoveDown = false;
        for (int i = 0; i < Constants.PIECE_LENGTH; i++) {
            int validRow = (int) (pcs[i].getY() / Constants.SQUARE_WIDTH);
            int validColumn = (int) (pcs[i].getX() / Constants.SQUARE_WIDTH);
            if (this.board[validRow + 1][validColumn] == null) {
                canMoveDown = true;
                //System.out.println("down valid");
            } else {
                canMoveDown = false;
                //System.out.println("down invalid");
                break;
            }
        }
        return canMoveDown;
    }
    /**
     * This method checks if the piece moving right is valid. It does so by seeing if the right column is empty. Then,
     * it sets the boolean canMoveRight to either true or false based on whether the next column right (given the same
     * row) is empty.
     */
    public boolean validRight(tetrisSquare[] pcs) {
        boolean canMoveRight = false;
        for (int i = 0; i < Constants.PIECE_LENGTH; i++) {
            int validRow = (int) (pcs[i].getY() / Constants.SQUARE_WIDTH);
            int validColumn = (int) (pcs[i].getX() / Constants.SQUARE_WIDTH);
            if (this.board[validRow][validColumn + 1] == null) {
                canMoveRight = true;
                //System.out.println("right valid");
            } else {
                canMoveRight = false;
                //System.out.println("right invalid");
                break;
            }
        }
        return canMoveRight;
    }
    /**
     * This method checks if the piece moving left is valid. It does so by seeing if the left column is empty. Then,
     * it sets the boolean canMoveLeft to either true or false based on whether the next column left (given the same
     * row) is empty.
     */
    public boolean validLeft(tetrisSquare[] pcs) {
        boolean canMoveLeft = false;
        for (int i = 0; i < Constants.PIECE_LENGTH; i++) {
            int validRow = (int) (pcs[i].getY() / Constants.SQUARE_WIDTH);
            int validColumn = (int) (pcs[i].getX() / Constants.SQUARE_WIDTH);
            if (this.board[validRow][validColumn - 1] == null) {
                canMoveLeft = true;
                //System.out.println("left valid");
            } else {
                canMoveLeft = false;
                //System.out.println("left invalid");
                break;
            }
        }
        return canMoveLeft;
    }
    /**
     * This method checks if the rotation of a piece is valid. It does so by setting xRot and yRot equal to the first
     * square of the piece which is then used by validRow and validCol to determine if the board at a certain index
     * is empty. Then, it sets the boolean canRotate equal to true or false based on the null if statement.
     */
    public boolean validRotation(tetrisSquare[] pcs) {
        boolean canRotate = false;
        for (int i = 0; i < Constants.PIECE_LENGTH; i++) {
            double xRot = pcs[1].getX() / Constants.SQUARE_WIDTH;
            double yRot = pcs[1].getY() / Constants.SQUARE_WIDTH;
            int validRow = (int) (yRot + xRot - pcs[i].getX() / Constants.SQUARE_WIDTH);
            int validCol = (int) (xRot - yRot + pcs[i].getY() / Constants.SQUARE_WIDTH);
            if (this.board[validRow][validCol] == null) {
                canRotate = true;
            } else {
                canRotate = false;
                break;
            }
        }
        return canRotate;
    }
    /**
     * This method adds the piece to the board by looping through each square in the piece and adding it to the 2D
     * array board. addPieceToBoard is called in the tetrisGame class under the checkRowFillMethod.
     */
    public void addPieceToBoard(tetrisSquare[] pcs) {
        for (int i = 0; i < Constants.PIECE_LENGTH; i++) {
            int row = (int) pcs[i].getY() / Constants.SQUARE_WIDTH;
            int col = (int) pcs[i].getX() / Constants.SQUARE_WIDTH;
            this.board[row][col] = pcs[i];
        }
    }

    /**
     * This method is responsible for clearing the lines once a row is full. It does this by looping through the
     * rows and columns of the board and determining if the board is null or not at a certain board location. When
     * the loop determines that some location on the board is null, it breaks out of the loop. Otherwise, it sets full
     * boolean to true. If full is true, the next if statement is triggered and the gamePane removes each square
     * graphically and logically off the board.
     */
    public int clearLines() {
        boolean full = false;
        int  countLines = 0;
        for (int row = 1; row < this.board.length - 1; row++) {
            for (int col = 0; col < this.board[0].length - 1; col++) {
                if (this.board[row][col] == null) {
                    full = false;
                    break;
                }
                else {
                    full = true;
                }
            }
            if (full) {
                countLines += 1;
                for (int col = 1; col < this.board[0].length - 1; col++) {
                    this.gamePane.getChildren().remove(this.board[row][col].getSquare());
                    this.board[row][col] = null;
                }
                for (int r = row; r > 1; r--) {
                    for (int col = 1; col < this.board[0].length - 1; col++) {
                        if (this.board[r - 1][col] != null) {
                            this.board[r - 1][col].setY(this.board[r - 1][col].getY() + Constants.SQUARE_WIDTH);
                        }
                        this.board[r][col] = this.board[r - 1][col];
                    }
                }
            }
        }
        return countLines;
    }
}









