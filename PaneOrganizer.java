package tetris;


import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class PaneOrganizer {
    private BorderPane root;
    private Pane gamePane;
    private HBox buttomPane;
    private tetrisGame game;

    /**
     * This is the constructor for the PaneOrganizer class. It instantiates the border pane, creates an instance of the
     * game, and calls methods seUpGamePane and setUpQuit.
     */
    public PaneOrganizer(){
        this.root = new BorderPane();
        this.setUpGamePane();
        this.setUpQuit();
        this.game = new tetrisGame(this.gamePane,this.buttomPane);
    }
    /**
     * This method is a getter method that returns the borderPane
     */
    public BorderPane getRoot(){
        return this.root;
    }
    /**
     * This method sets up the quit button and buttomPane. The buttomPane is a new HBox and the method adds the quit
     * button to the buttomPane. When the quit button is clicked, the system exits.
     */
    private void setUpQuit() {
        this.buttomPane = new HBox();
        this.root.setBottom(this.buttomPane);
        this.buttomPane.setAlignment(Pos.CENTER);
        this.buttomPane.setStyle("-fx-background-color: lightblue");
        this.buttomPane.setPrefSize(Constants.BUTTON_PANE, Constants.SQUARE_WIDTH - 3);
        this.buttomPane.setSpacing(Constants.BUTTON_PANE_SPACING);
        this.buttomPane.setFocusTraversable(false);

        Button b1 = new Button("Quit");
        this.buttomPane.getChildren().addAll(b1);
        b1.setOnAction((ActionEvent e) -> System.exit(0));
    }
    /**
     * This method creates the gamePane, a new Pane, and the borderPane sets the gamePane to the center of the screen.
     */
    private void setUpGamePane() {
        this.gamePane = new Pane();
        this.root.setCenter(this.gamePane);
        this.gamePane.setFocusTraversable(true);
    }
}
