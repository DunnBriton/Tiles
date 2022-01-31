import javafx.application.Application;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class TilesManager{

    public static class Manager {
        // Rows and Columns in the 2D array.
        public static int rows;
        public static int columns;
        // The 2D Array.
        public static ArrayList[][] board;
        int i = 0;

        //Constructor
        public Manager(int passedRows, int passedColumns){
            rows = passedRows;
            columns = passedColumns;
            board = new ArrayList[rows][columns];
        }


        public void fill(int seed) {
            // ArrayList to hold large square color choices.
            ArrayList<String> colOptions = new ArrayList<>();
            // Choices wanted. "colors".
            colOptions.add("R");
            colOptions.add("G");
            colOptions.add("B");
            colOptions.add("Y");
            colOptions.add("M");

            //ArrayList to hold shape choices;
            ArrayList<String> shapeOptions = new ArrayList<>();
            // Choices wanted. "shapes".
            shapeOptions.add("Square");
            shapeOptions.add("Circle");
            shapeOptions.add("Ellipse");


            // Random nums and the number of rows to generate.
            Random random = new Random(seed);

            ArrayList<String> colorChoicesOne = new ArrayList<>(20);
            i = 0;
            while (i < 10){
                String x = colOptions.get(random.nextInt(colOptions.size()));
                colorChoicesOne.add(x);
                colorChoicesOne.add(x);
                i++;
            }
            Collections.shuffle(colorChoicesOne);

            ArrayList<String> colorChoicesTwo = new ArrayList<>(20);
            i = 0;
            while (i < 10){
                String x = colOptions.get(random.nextInt(colOptions.size()));
                colorChoicesTwo.add(x);
                colorChoicesTwo.add(x);
                i++;
            }
            Collections.shuffle(colorChoicesTwo);

            ArrayList<String> shapeChoices = new ArrayList<>(20);
            i = 0;
            while (i < 10){
                String x = shapeOptions.get(random.nextInt(shapeOptions.size()));
                shapeChoices.add(x);
                shapeChoices.add(x);
                i++;
            }
            Collections.shuffle(shapeChoices);

            // Fills in the 2D Array with an array of three characteristics.
            int counter = 0;
            for (i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    ArrayList<String> hold = new ArrayList<>(3);
                    hold.add(0, colorChoicesOne.get(counter));
                    hold.add(1, colorChoicesTwo.get(counter));
                    hold.add(2, shapeChoices.get(counter));
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

        public static void run(){
            Manager boardOne = new Manager(4,5);
            boardOne.fill(100);
            GUI.main(board);

            System.out.println(boardOne);
        }
    }
    public static void main(String[] args){
        Manager.run();
    }
}
