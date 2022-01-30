import java.util.ArrayList;
import java.util.Random;

public class TilesManager {

    public static class Manager {
        // Rows and Columns in the 2D array.
        int rows;
        int columns;
        // The 2D Array.
        private static ArrayList[][] board;

        //Constructor
        public Manager(int passedRows, int passedColumns){
            rows = passedRows;
            columns = passedColumns;
            board = new ArrayList[rows][columns];
        }

        public void fill(int seed) {
            // ArrayList to hold large square color choices.
            ArrayList<String> larSquColChoices = new ArrayList<>();
            // Choices wanted. "colors".
            larSquColChoices.add("R");
            larSquColChoices.add("G");
            larSquColChoices.add("B");
            larSquColChoices.add("Y");
            larSquColChoices.add("M");

            //ArrayList to hold small square color choices;
            ArrayList<String> smaSquColChoices = new ArrayList<>();
            // Choices wanted. "colors".
            smaSquColChoices.add("R");
            smaSquColChoices.add("G");
            smaSquColChoices.add("B");
            smaSquColChoices.add("Y");
            smaSquColChoices.add("M");

            //ArrayList to hold shape choices;
            ArrayList<String> shapeChoices = new ArrayList<>();
            // Choices wanted. "shapes".
            shapeChoices.add("Square");
            shapeChoices.add("Circle");
            shapeChoices.add("Triangle");


            // Random nums and the number of rows to generate.
            Random random = new Random(seed);

            // Fills in the 2D Array with an array of three characteristics.
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    ArrayList<String> hold = new ArrayList<>(3);
                    hold.add(0, larSquColChoices.get(random.nextInt(larSquColChoices.size())));
                    hold.add(1, smaSquColChoices.get(random.nextInt(smaSquColChoices.size())));
                    hold.add(2, shapeChoices.get(random.nextInt(shapeChoices.size())));
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

        public static void main(String[] args){
            Manager boardOne = new Manager(4,5);
            boardOne.fill(100);
            System.out.println(boardOne);
        }
    }
}
