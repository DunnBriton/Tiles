/**
 * Board
 * CS 375-002
 * @author Briton Dunn
 * Board is called to create a gameboard for
 *     the current iteration of the game.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board{
    public static class Manager{
        // Variables used for rows/columns and iteration.
        int rows, columns, i ,j;
        static ArrayList[][] board;
        // ArrayLists of Strings used to store element options.
        ArrayList<String> colOptions = new ArrayList<>();
        ArrayList<String> shapeOptions = new ArrayList<>();
        ArrayList<String> elementOneColor = new ArrayList<>(20);
        ArrayList<String> elementTwoColor = new ArrayList<>(20);
        ArrayList<String> elementThreeShape = new ArrayList<>(20);

        /**
         * Manager assigns values to rows/columns/board.
         * @param passedRows    - How many rows of tiles to make.
         * @param passedColumns - How many columns of tiles to make.
         */
        public Manager(int passedRows, int passedColumns){
            rows = passedRows;
            columns = passedColumns;
            board = new ArrayList[rows][columns];
        }

        /**
         * Main method. Creates board/gui/loop.
         * @param seed - Value used to generate board values.
         * Return void.
         */
        public void fill(int seed){
            // Creates random variable from seed.
            Random random = new Random(seed);
            //Adds the options for elements to ArrayLists.
            colOptions.add("R");
            colOptions.add("G");
            colOptions.add("B");
            colOptions.add("Y");
            colOptions.add("M");
            shapeOptions.add("Square");
            shapeOptions.add("Circle");
            shapeOptions.add("Ellipse");

            //Adds color options for back square.
            i = 0;
            while (i < 10){
                String x = colOptions.get(random.nextInt(colOptions.size()));
                elementOneColor.add(x);
                elementOneColor.add(x);
                i++;
            }
            Collections.shuffle(elementOneColor);

            //Adds color options for middle circle.
            i = 0;
            while (i < 10){
                String x = colOptions.get(random.nextInt(colOptions.size()));
                elementTwoColor.add(x);
                elementTwoColor.add(x);
                i++;
            }
            Collections.shuffle(elementTwoColor);

            // Adds shape options for front shape.
            i = 0;
            while (i < 10){
                String x = shapeOptions.get(random.nextInt(shapeOptions.size()));
                elementThreeShape.add(x);
                elementThreeShape.add(x);
                i++;
            }
            Collections.shuffle(elementThreeShape);

            /* Adds the selected elements to one ArrayList
                   holding three elements per spot.*/
            int counter = 0;
            for (i = 0; i < rows; i++) {
                for (j = 0; j < columns; j++) {
                    ArrayList<String> hold = new ArrayList<>(3);
                    hold.add(0, elementOneColor.get(counter));
                    hold.add(1, elementTwoColor.get(counter));
                    hold.add(2, elementThreeShape.get(counter));
                    counter++;
                    board[i][j] = hold;
                }
            }
        }

        /*** Override for toString so that we can display the 2d
         * Array as a String in the proper layout.
         * @return - Returns String containing the board.
         */
        @Override
        public String toString() {
            // Creates StringBuilder
            StringBuilder holder = new StringBuilder();

            // Counters for columns and offset.
            int columnCounter = 0;

            // Adds strings to StringBuilder
            for (ArrayList[] arrayLists : board) {
                for (ArrayList character : arrayLists) {
                    if (character != null) {
                        holder.append(character).append(" ");
                    } else {
                        holder.append(" ").append(" ");
                    }
                    columnCounter++;
                    if (columnCounter == columns) {
                        columnCounter = 0;
                        holder.append("\n");
                    }
                }
            }
            return holder.toString();
        }
    }

}