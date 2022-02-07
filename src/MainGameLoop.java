import java.util.Random;

/**
 * MainGameLoop
 * CS 375-002
 * @author Briton Dunn
 * MainGameLoop is used to create the board/GUI.
 * Actual gameloop is in Display.
 */
public class MainGameLoop {

    /**
     * Main method. Creates board/gui/loop.
     * @param args - args.
     */
    public static void main(String[] args){
        Board.Manager boardOne = new Board.Manager(4,5);
        Random rand = new Random();
        boardOne.fill(rand.nextInt());
        Display.main();
        //Command to print gameboard to console if needed.
        //System.out.println(boardOne);
    }
}