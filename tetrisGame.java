package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class tetrisGame {
    private final Pane gamePane;
    private Timeline timeline;
    private Pieces pcs;
    private final HBox bottomPane;
    private final Board board;
    private boolean isPaused = false;
    private final Label scoreText;
    private final Label pauseText;
    private final Label gameOverText;
    private int score;

    /**
     * This is the constructor of the tetrisGame class, responsible for instantiating labels, the score, adding an
     */
    public tetrisGame(Pane gamePane, HBox hBox) {
        this.gamePane = gamePane;
        this.board = new Board(gamePane);
        this.score = 0;
        this.bottomPane = hBox;
        this.scoreText = new Label();
        this.pauseText = new Label();
        this.gameOverText = new Label();
        this.pcs = this.randomPieceGenerator();
        gamePane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPress(e));
        this.setUpTimeline();
        this.setUpScore();
        this.setUpRestart();
    }
    /**
     *this method controls what happens when different keys are pressed using a switch statement. Pressing the left
     * arrow key moves the piece left, pressing the right arrow key moves the piece right, up indicates rotation,
     * pressing space allows the piece to drop to the bottom, and pressing the P key pauses the timeline (pressing
     * P again starts the timeline again). This method checks to see if the timeline is still running, and if so,
     * it responds to key presses from the user. these move methods are called from the Pieces class.
     */
    private void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case LEFT:
                if (this.timeline.getStatus() == Animation.Status.RUNNING) this.pcs.moveLeft();
                break;
            case RIGHT:
                if (this.timeline.getStatus() == Animation.Status.RUNNING) this.pcs.moveRight();
                break;
            case DOWN:
                if (this.timeline.getStatus() == Animation.Status.RUNNING) this.pcs.moveDown();
                break;
            case UP:
                if (this.timeline.getStatus() == Animation.Status.RUNNING && this.pcs.rotate) this.pcs.rotatePiece();
                break;
            case SPACE:
                if (this.timeline.getStatus() == Animation.Status.RUNNING) this.pcs.dropPiece();
                break;
            case P:
                if (this.isPaused) {
                    this.timeline.play();
                    this.gamePane.getChildren().remove(this.pauseText);
                } else {
                    this.timeline.pause();
                    this.makePauseLabel();
                }
                this.isPaused = !this.isPaused;
                break;
            default:
                break;
        }
        e.consume();
    }

    /**
     * This method sets up the timeline for the game/animation. this.gameUpdate() is called every second, which contains
     * methods responsible for the functions of the game.
     */
    private void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(1),
                (ActionEvent e) -> this.gameUpdate());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }
    /**
     * This method is responsible for setting up the score at the bottom of the screen. In addition, if the
     * game isn't paused, the methods checkRowFill and moveDown from the Pieces class are called.
     */
    private void gameUpdate() {
        this.scoreText.setText("Score: " + String.valueOf(this.score));
        if (!this.isPaused) {
            this.pcs.moveDown();
        }
        this.checkRowFill();
    }
    /**
     * This method adds the scoreText to the bottomPane. It styles the score text and sets up the font color.
     */
    private void setUpScore() {
        this.bottomPane.getChildren().addAll(this.scoreText);
        this.scoreText.setFont(Font.font("Radio Stars", 12));
        this.scoreText.setTextFill(Color.BLACK);
    }
    /**
     * This method sets up the restart button and adds it to the bottomPane.
     */
    private void setUpRestart() {
        Button restart = new Button("Restart");
        this.bottomPane.getChildren().addAll(restart);
        this.bottomPane.setFocusTraversable(false);
        restart.setFocusTraversable(false);
        restart.setOnAction((EventHandler<ActionEvent>) new RestartHandler());
    }
    /**
     * This method generates random pieces basted on parameters passed in. For example, each piece has its own
     * color, coordinates, and each piece is added to the gamePane. The random generation is done through the factory
     * method of generating a random integer and using a switch statement for different cases.
     */

    private Pieces randomPieceGenerator() {
        int rand = (int) (Math.random() * 7);
        Pieces piece = null;
        switch (rand) {
            case 0:
                piece = new Pieces(this.gamePane, Constants.ONE_PIECE_COORDS, Color.CRIMSON, this.board, true);
                break;
            case 1:
                piece = new Pieces(this.gamePane, Constants.TWO_PIECE_COORDS, Color.LIGHTCORAL, this.board, true);
                break;
            case 2:
                piece = new Pieces(this.gamePane, Constants.THREE_PIECE_COORDS, Color.LIGHTPINK, this.board,
                        false);
                break;
            case 3:
                piece = new Pieces(this.gamePane, Constants.FOUR_PIECE_COORDS, Color.FIREBRICK, this.board,
                        true);
                break;
            case 4:
                piece = new Pieces(this.gamePane, Constants.FIVE_PIECE_COORDS, Color.FORESTGREEN, this.board,
                        true);
                break;
            case 5:
                piece = new Pieces(this.gamePane, Constants.SIX_PIECE_COORDS, Color.MEDIUMSEAGREEN, this.board,
                        true);
                break;
            case 6:
                piece = new Pieces(this.gamePane, Constants.SEVEN_PIECE_COORDS, Color.GREEN, this.board, true);
                break;
            default:
                break;
        }
        return piece;
    }

    /**
     * This method calls the validDown method in Board, which checks if the piece has room to move down/if it is
     * valid to move down. If it is not valid anymore, the piece is added to the board and another piece is
     * randomly generated. Within the first if statement is a second if statement, checking to see if the piece
     * can't move down for good, meaning it fills up the board at the top and the timeline stops. The end label
     * also appears in the center of the screen. Lastly, the score increments by 10 points every time a row is
     * filled.
     */
    private void checkRowFill() {
        //use board to check if horizontal lines are filled
        //filled row should be removed and pieces should move downward
        if (!this.board.validDown(this.pcs.getArray())) {
            this.pcs.addPieceToBoard();
            this.pcs = this.randomPieceGenerator();
            if (!this.board.validDown(this.pcs.getArray())) {
                this.timeline.stop();
                this.makeEndLabel();
            }
            this.score += this.board.clearLines() * Constants.SCORE_INCREMENT;
        }
    }

    /**
     * This method sets the end label of the game to "Game Over!" and calls the createTexts method that sets up
     * the layout of the game over text.
     */
    private void makeEndLabel() {
        this.gameOverText.setText("Game Over!");
        this.createTexts(this.gameOverText);
    }

    /**
     * This method sets the pauseText label to "The game is paused!" and calls the createTexts method to set up the
     * layout of the label.
     */
    private void makePauseLabel() {
        this.pauseText.setText("Game Paused!");
        this.createTexts(this.pauseText);
    }
    /**
     * When a label is passed into the parameter of createTexts, it sets the text color to black and positions
     * the label in a certain X and Y position.
     */
    private void createTexts(Label label) {
        label.setFont(Font.font("Radio Stars", 20));
        label.setTextFill(Color.BLACK);
        label.setLayoutX(Constants.LABEL_CENTERX);
        label.setLayoutY(Constants.LABEL_CENTERY);
        this.gamePane.getChildren().addAll(label);
    }

    /**
     * This is a private class that is responsible for restarting the program on the click of the restart button.
     * It stops the timeline, resets the board, removes the labels, sets the score back to 0, generates a random
     * piece to start off with, and plays the timeline again. Then, the game begins once more.
     */
    private class RestartHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            timeline.stop();
            board.resetBoard(pcs.getArray());
            gamePane.getChildren().remove(gameOverText);
            gamePane.getChildren().remove(pauseText);
            pcs = randomPieceGenerator();
            score = 0;
            timeline.play();
        }
    }
}












