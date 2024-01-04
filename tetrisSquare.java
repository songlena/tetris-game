package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class tetrisSquare {
    private Rectangle square;

    /**
     * This is constructor of the tetrisSquare class which creates a new square with a set width and height. It also
     * sets the stroke color/width of the square. The square is then added to the gamePane.
     */
    public tetrisSquare(Pane gamePane){
        this.square = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        this.square.setStroke(Color.LIGHTGREY);
        this.square.setStrokeWidth(1);
        gamePane.getChildren().add(this.square);
    }

    /**
     * This method is a getter method that returns the square.
     */
    public Rectangle getSquare(){
        return this.square;
    }
    /**
     * This method is a getter method that returns the square's X location.
     */
    public double getX(){
        return this.square.getX();
    }
    /**
     * This method is a getter method that returns the square's X location.
     */
    public double getY(){
        return this.square.getY();
    }
    /**
     * This method is responsible for moving an individual square right by one unit (the square width)
     */
    public void moveRight(){
        this.square.setX(this.square.getX() + Constants.SQUARE_WIDTH);
    }
    /**
     * This method is responsible for moving an individual square left by one unit (the square width)
     */
    public void moveLeft(){
        this.square.setX(this.square.getX() - Constants.SQUARE_WIDTH);
    }
    /**
     * This method is responsible for moving an individual square down by one unit (the square width)
     */
    public void moveDown(){
        this.square.setY(this.square.getY() + Constants.SQUARE_WIDTH);
    }
    /**
     * Thi method sets the square's Y location to the passed in parameter double.
     */
    public void setY(double newY){
        this.square.setY(newY);
    }
}
