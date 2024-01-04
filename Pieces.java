
package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Pieces {
    private final Pane gamePane;
    boolean rotate;
    private tetrisSquare[] pcs;
    private Board board;

    /**
     * This is the constructor of the Pieces class that takes in a pane, coordinates, a color, and a board. This makes
     * random generation in the game class a lot simpler because you can just pass values in for each parameter
     * to create the seven different shapes.
     */
    public Pieces(Pane gamePane, int[][] coord, Color color, Board board, boolean rotate){
        this.gamePane = gamePane;
        this.board = board;
        this.rotate = rotate;
        this.pcs = new tetrisSquare[4];
        this.makeNew(coord, color);
    }

    /**
     * This method makes a new piece of four squares. It is then called in the Pieces constructor which shares the
     * same parameters of color and coordinates.
     */
    public void makeNew(int[][] coord, Color color){
        for(int i = 0; i < Constants.PIECE_LENGTH; i++) {
            this.pcs[i] = new tetrisSquare(this.gamePane);
            this.pcs[i].getSquare().setFill(color);
            this.pcs[i].getSquare().setX(coord[i][0]);
            this.pcs[i].getSquare().setY(coord[i][1]);
        }
    }
    /**
     * when moveRight() is called by pcs in the game class, it is responsible for moving the piece right on key press.
     * This is done by shifting each square in pcs by one square width. moveRight() is also a method in tetrisSquare,
     * making it easier to move each piece based on the square itself.
     */
    public void moveRight(){
        if (this.board.validRight(this.pcs)) {
            System.out.println("moving right");
            for (tetrisSquare pcs : this.pcs) {
                pcs.moveRight();
            }
        }
    }
    /**
     * when moveLeft() is called by pcs in the game class, it is responsible for moving the piece left on key press.
     * This is done by shifting each square in pcs by one square width. moveLeft() is also a method in tetrisSquare,
     * making it easier to move each piece based on the square itself.
     */
    public void moveLeft() {
        if (this.board.validLeft(this.pcs)) {
            System.out.println("moving left");
            for (tetrisSquare pcs : this.pcs) {
                pcs.moveLeft();
            }
        }
    }
    /**
     * when moveDown() is called by pcs in the game class, it is responsible for moving the piece down on key press.
     * This is done by shifting each square in pcs by one square width down. moveDown() is also a method in
     * tetrisSquare, making it easier to move each piece based on the square itself.
     */
    public void moveDown() {
        if (this.board.validDown(this.pcs)) {
            System.out.println("moving down");
            for (tetrisSquare pcs : this.pcs) {
                pcs.moveDown();
            }
        }
    }
    /**
     * when rotatePiece() is called by pcs in the game class, it is responsible for rotating the piece 90 degrees
     * on key press. This is done by getting the location of the first square in each piece and using the rotation
     * formula given to set the X and Y of each square in the piece to a new location.
     */
    public void rotatePiece() {
        if (this.board.validRotation(this.pcs)) {
            int centerX = (int) this.pcs[1].getSquare().getX();
            int centerY = (int) this.pcs[1].getSquare().getY();

            for (int i = 0; i < Constants.PIECE_LENGTH; i++) {
                int rotatedX = (int) (centerX - centerY + this.pcs[i].getSquare().getY());
                int rotatedY = (int) (centerX + centerY - this.pcs[i].getSquare().getX());
                this.pcs[i].getSquare().setX(rotatedX);
                this.pcs[i].getSquare().setY(rotatedY);
            }
        }
    }

    /**
     * This method is responsible for dropping a piece down and is called by the game class when the space bar is
     * pressed.
     */
    public void dropPiece() {
        while (this.board.validDown(this.pcs)) {
            for (tetrisSquare pcs : this.pcs) {
                pcs.moveDown();
            }
        }
    }

    /**
     * this method adds this.pcs as a parameter for board's addPieceToBoard method in which a piece is added logically
     * to the board itself.
     */
    public void addPieceToBoard() {
        this.board.addPieceToBoard(this.pcs);
    }

    /**
     * This method returns this.pcs of type tetrisSquare[].
     */
    public tetrisSquare[] getArray(){
        return this.pcs;
    }
}

