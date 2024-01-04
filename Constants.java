package tetris;

public class Constants {
    public static final int SQUARE_WIDTH = 30; // width of each square
    public static final int PANE_WIDTH = 360;
    public static final int BUTTON_PANE = 300;
    public static final int LABEL_CENTERX = 130;
    public static final int LABEL_CENTERY = 300;
    public static final int SCORE_INCREMENT = 10;
    public static final int BUTTON_PANE_SPACING = 50;
    public static final int PANE_HEIGHT = 690;
    public static final int PIECE_OFFSET = 180; //adds the piece to the center during random generation
    public static final int PIECE_LENGTH = 4;


    //coordinates for squares in each tetris piece
    public static final int[][] ONE_PIECE_COORDS = {{PIECE_OFFSET, 30}, {PIECE_OFFSET, 2 * SQUARE_WIDTH},
            {PIECE_OFFSET, 3 * SQUARE_WIDTH}, {PIECE_OFFSET, 4 * SQUARE_WIDTH}};
    public static final int[][] TWO_PIECE_COORDS = {{-1 * SQUARE_WIDTH + PIECE_OFFSET, SQUARE_WIDTH},
            {-1 * SQUARE_WIDTH + PIECE_OFFSET, 2 * SQUARE_WIDTH}, {-1 * SQUARE_WIDTH + PIECE_OFFSET, 3 * SQUARE_WIDTH},
            {PIECE_OFFSET, 2 * SQUARE_WIDTH}};
    public static final int[][] THREE_PIECE_COORDS = {{-1 * SQUARE_WIDTH + PIECE_OFFSET, SQUARE_WIDTH},
            {-1 * SQUARE_WIDTH + PIECE_OFFSET, 2 * SQUARE_WIDTH}, {PIECE_OFFSET, SQUARE_WIDTH}, {PIECE_OFFSET,
            2 * SQUARE_WIDTH}};
    public static final int[][] FOUR_PIECE_COORDS = {{-1 * SQUARE_WIDTH + PIECE_OFFSET, SQUARE_WIDTH},
            {-1 * SQUARE_WIDTH + PIECE_OFFSET, 2 * SQUARE_WIDTH}, {-1 * SQUARE_WIDTH + PIECE_OFFSET, 3 * SQUARE_WIDTH},
            {PIECE_OFFSET, SQUARE_WIDTH}};
    public static final int[][] FIVE_PIECE_COORDS = {{-1 * SQUARE_WIDTH + PIECE_OFFSET, SQUARE_WIDTH}, {PIECE_OFFSET,
            SQUARE_WIDTH}, {PIECE_OFFSET, 2 * SQUARE_WIDTH}, {PIECE_OFFSET, 3 * SQUARE_WIDTH}};
    public static final int[][] SIX_PIECE_COORDS = {{-1 * SQUARE_WIDTH + PIECE_OFFSET, SQUARE_WIDTH},
            {-1 * SQUARE_WIDTH + PIECE_OFFSET, 2 * SQUARE_WIDTH}, {PIECE_OFFSET, 2 * SQUARE_WIDTH}, {PIECE_OFFSET,
            3 * SQUARE_WIDTH}};
    public static final int[][] SEVEN_PIECE_COORDS = {{-1 * SQUARE_WIDTH + PIECE_OFFSET, 2 * SQUARE_WIDTH},
            {-1 * SQUARE_WIDTH + PIECE_OFFSET, 3 * SQUARE_WIDTH}, {PIECE_OFFSET, SQUARE_WIDTH}, {PIECE_OFFSET, 2 *
            SQUARE_WIDTH}};
}
