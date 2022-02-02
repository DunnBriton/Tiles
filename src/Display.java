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
    int currentCombo, longestCombo, i, j;
    static ArrayList[][] boardCopy;
    BorderPane mainPane = new BorderPane();
    TilePane tilePane = new TilePane();
    private Display selected = null;
    private int clickCount = 2;
    Label labelOne = new Label("Current Streak: 0");
    Label labelTwo = new Label("Longest Streak: 0");

    public static void main() {
        boardCopy = Board.Manager.board;
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(mainPane, SCREEN_BOUNDS.getWidth() / 2, SCREEN_BOUNDS.getHeight() / 2);

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 5; j++) {
                StackPane stack = new StackPane();
                Rectangle empty = new Rectangle(0, 0, 100, 100);

                empty.setFill(Color.MOCCASIN);
                empty.setStroke(Color.BLACK);
                stack.getChildren().add(empty);

                empty.setOnMouseClicked(this::handleNullClick);

                Rectangle x = new Rectangle(0, 0, 100, 100);
                x.setStroke(Color.BLACK);
                ColorSet((String) Board.Manager.board[i][j].get(0), x);

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
                            stack.getChildren().remove(x);
                            //stack.getChildren().remove(selected);
                            currentCombo++;
                        }
                        selected = null;
                        clickCount = 2;
                        labelOne.setText("Current Streak: " + currentCombo);
                    }
                });

                Circle y = new Circle(0, 0, 40);
                Shape z;
                y.setStroke(Color.BLACK);
                ColorSet((String) Board.Manager.board[i][j].get(1), y);

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
                            stack.getChildren().remove(y);
                            //stack.getChildren().remove(selected);
                            currentCombo++;
                        }
                        selected = null;
                        clickCount = 2;
                        labelOne.setText("Current Streak: " + currentCombo);
                    }
                });

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

                //z.setOnMouseClicked(event -> stack.getChildren().remove(z));
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
                            stack.getChildren().remove(z);
                            //stack.getChildren().remove(selected);
                            currentCombo++;
                        }
                        selected = null;
                        clickCount = 2;
                        labelOne.setText("Current Streak: " + currentCombo);
                    }
                });

                stack.getChildren().addAll(x, y, z);
                tilePane.getChildren().add(stack);
            }
        }
        mainPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setCenter(tilePane);
        mainPane.setLeft(labelOne);
        mainPane.setRight(labelTwo);
        tilePane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        tilePane.setMaxSize(mainPane.getWidth() / 2, mainPane.getHeight() / 2);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleNullClick(MouseEvent event){
        if (currentCombo > longestCombo) {
            longestCombo = currentCombo;
            labelTwo.setText("Longest Streak: " + currentCombo);
        }
        currentCombo = 0;
        labelOne.setText("Current Streak: " + currentCombo);
    }

    public void ColorSet(String color, Shape a){
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