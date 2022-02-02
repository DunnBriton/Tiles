import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board{
    public static class Manager{
        int rows, columns, i ,j;
        static ArrayList[][] board;
        ArrayList<String> colOptions = new ArrayList<>();
        ArrayList<String> shapeOptions = new ArrayList<>();
        ArrayList<String> elementOneColor = new ArrayList<>(20);
        ArrayList<String> elementTwoColor = new ArrayList<>(20);
        ArrayList<String> elementThreeShape = new ArrayList<>(20);

        public Manager(int passedRows, int passedColumns){
            rows = passedRows;
            columns = passedColumns;
            board = new ArrayList[rows][columns];
        }

        public void fill(int seed){
            Random random = new Random(seed);
            colOptions.add("R");
            colOptions.add("G");
            colOptions.add("B");
            colOptions.add("Y");
            colOptions.add("M");
            shapeOptions.add("Square");
            shapeOptions.add("Circle");
            shapeOptions.add("Ellipse");

            i = 0;
            while (i < 10){
                String x = colOptions.get(random.nextInt(colOptions.size()));
                elementOneColor.add(x);
                elementOneColor.add(x);
                i++;
            }
            Collections.shuffle(elementOneColor);

            i = 0;
            while (i < 10){
                String x = colOptions.get(random.nextInt(colOptions.size()));
                elementTwoColor.add(x);
                elementTwoColor.add(x);
                i++;
            }
            Collections.shuffle(elementTwoColor);

            i = 0;
            while (i < 10){
                String x = shapeOptions.get(random.nextInt(shapeOptions.size()));
                elementThreeShape.add(x);
                elementThreeShape.add(x);
                i++;
            }
            Collections.shuffle(elementThreeShape);

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