/**
 * Display
 * CS 375-002
 * @author Briton Dunn
 * Display is used to create and interact
 *     with the gameboard/gui.
 * The actual game loop is here.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Display extends Application {
    final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    // Variables used for combos and iteration.
    int currentCombo, longestCombo, i, j;
    //Stores copy of the gameboard. This is not really needed.
    static ArrayList[][] boardCopy;
    //Creates panes.
    BorderPane mainPane = new BorderPane();
    TilePane tilePane = new TilePane();
    // Variables used for clicking elements.
    private Display selected = null;
    private int clickCount = 2;
    //Labels for combos.
    Label labelOne = new Label("Current Streak: 0");
    Label labelTwo = new Label("Longest Streak: 0");

    /**
     * Main used to retrieve board and start game loop.
     * Return void.
     */
    public static void main() {
        boardCopy = Board.Manager.board;
        launch();
    }

    /** Override for start.
     *  start used for creating GUI and interactions.
     *  Return void.
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(mainPane, SCREEN_BOUNDS.getWidth() / 2, SCREEN_BOUNDS.getHeight() / 2);

        //Loop used to create tiles and assign three elements to it.
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 5; j++) {
                StackPane stack = new StackPane();
                Rectangle empty = new Rectangle(0, 0, 100, 100);

                empty.setFill(Color.MOCCASIN);
                empty.setStroke(Color.BLACK);
                stack.getChildren().add(empty);

                // Assigns mouse event to handle a null "empty" click.
                empty.setOnMouseClicked(this::handleNullClick);

                //Back rectangle creation.
                Rectangle x = new Rectangle(0, 0, 100, 100);
                x.setStroke(Color.BLACK);
                colorSet((String) Board.Manager.board[i][j].get(0), x);

                //What happens if rectangle x is clicked.
                x.setOnMouseClicked(event -> {
                    if(clickCount == 0){
                        return;
                    }
                    clickCount--;
                    if(selected == null){
                        selected = this;
                    }
                    else{
                        if(selected == this){
                            stack.getChildren().remove(selected);
                            stack.getChildren().remove(x);
                            currentCombo++;
                        }
                        selected = null;
                        clickCount = 2;
                        labelOne.setText("Current Streak: " + currentCombo);
                    }
                });

                //Creation of middle circle.
                Circle y = new Circle(0, 0, 40);
                Shape z;
                y.setStroke(Color.BLACK);
                colorSet((String) Board.Manager.board[i][j].get(1), y);

                //What happens if circle y is clicked.
                y.setOnMouseClicked(event -> {
                    if(clickCount == 0){
                        return;
                    }
                    clickCount--;
                    if(selected == null){
                        selected = this;
                    }
                    else{
                        if(selected == this){
                            stack.getChildren().remove(selected);
                            stack.getChildren().remove(y);
                            currentCombo++;
                        }
                        selected = null;
                        clickCount = 2;
                        labelOne.setText("Current Streak: " + currentCombo);
                    }
                });

                //Creation of front shape.
                if ("Square" == boardCopy[i][j].get(2)) {
                    z = new Rectangle(0, 0, 20, 20);
                    z.setStroke(Color.BLACK);
                    z.setFill(Color.MISTYROSE);
                }
                else if ("Circle" == boardCopy[i][j].get(2)) {
                    z = new Circle(10);
                    z.setStroke(Color.BLACK);
                    z.setFill(Color.MISTYROSE);
                }
                else{
                    z = new Polygon();
                    ((Polygon) z).getPoints().addAll(
                            10.0, 0.0,
                            0.0, 20.0,
                            20.0, 20.0);
                    z.setStroke(Color.BLACK);
                    z.setFill(Color.MISTYROSE);
                }

                //What happens if shape z is clicked.
                z.setOnMouseClicked(event -> {
                    if(clickCount == 0){
                        return;
                    }
                    clickCount--;
                    if(selected == null){
                        selected = this;
                    }
                    else{
                        if(selected == this){
                            stack.getChildren().remove(selected);
                            stack.getChildren().remove(z);
                            currentCombo++;

                        }
                        selected = null;
                        clickCount = 2;
                        labelOne.setText("Current Streak: " + currentCombo);
                    }
                });

                //Adds elements onto stack and adds stack to a tile.
                stack.getChildren().addAll(x, y, z);
                tilePane.getChildren().add(stack);
            }
        }
        //Sets characteristics of GUI.
        mainPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setCenter(tilePane);
        mainPane.setLeft(labelOne);
        mainPane.setRight(labelTwo);
        tilePane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        tilePane.setMaxSize(mainPane.getWidth() , mainPane.getHeight() / 2);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * handleNull Click used when part of a tile used
     *     for empty/void space is clicked.
     * @param event - Used to run multiple things on one event.
     * Return void.
     */
    public void handleNullClick(MouseEvent event){
        //If current combo is failed and is > previous longest combo. New value is assigned.
        if (currentCombo > longestCombo) {
            longestCombo = currentCombo;
            labelTwo.setText("Longest Streak: " + currentCombo);
        }
        currentCombo = 0;
        labelOne.setText("Current Streak: " + currentCombo);
    }

    /**
     * ColorSet is used to fill shapes based on element.
     * @param color - Used to know color value of tile.
     * @param a     - Used to know what shape to create.
     * Return void.
     */
    public void colorSet(String color, Shape a){
        if ("R".equals(color)) {
            a.setFill(Color.DODGERBLUE);
        } else if ("G".equals(color)) {
            a.setFill(Color.DARKGREEN);
        } else if ("B".equals(color)) {
            a.setFill(Color.DODGERBLUE);
        } else if ("Y".equals(color)) {
            a.setFill(Color.YELLOW);
        } else if ("M".equals(color)) {
            a.setFill(Color.MAGENTA);
        } else {
            a.setFill(Color.AQUA);
        }
    }
}