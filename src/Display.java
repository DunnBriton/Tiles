import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Display extends Application {
    final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    int currentCombo, longestCombo, i, j;
    Text currentText, longestText;
    static ArrayList[][] boardCopy;

    public static void main() {
        boardCopy = Board.Manager.board;
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        TilePane tilePane = new TilePane();

        Scene scene = new Scene(mainPane, SCREEN_BOUNDS.getWidth()/2, SCREEN_BOUNDS.getHeight()/2);

        for(i=0; i<4; i++) {
            for (j = 0; j < 5; j++) {
                StackPane stack = new StackPane();
                Rectangle x = new Rectangle(0,0,100,100);

                x.setOnMouseClicked(event -> stack.getChildren().remove(x));

                x.setStroke(Color.BLACK);

                if("R" == boardCopy[i][j].get(0)){
                    x.setFill(Color.DARKRED);
                }
                else if("G" == boardCopy[i][j].get(0)){
                    x.setFill(Color.DARKGREEN);
                }
                else if("B" == boardCopy[i][j].get(0)){
                    x.setFill(Color.DODGERBLUE);
                }
                else if("Y" == boardCopy[i][j].get(0)){
                    x.setFill(Color.YELLOW);
                }
                else if("M" == boardCopy[i][j].get(0)){
                    x.setFill(Color.MAGENTA);
                }
                else{
                    x.setFill(Color.AQUA);
                }

                Circle y = new Circle(0,0,40);
                y.setOnMouseClicked(event -> stack.getChildren().remove(y));
                y.setStroke(Color.BLACK);
                if("R" == boardCopy[i][j].get(1)){
                    y.setFill(Color.DARKRED);
                }
                else if("G" == boardCopy[i][j].get(1)){
                    y.setFill(Color.DARKGREEN);
                }
                else if("B" == boardCopy[i][j].get(1)){
                    y.setFill(Color.DODGERBLUE);
                }
                else if("Y" == boardCopy[i][j].get(1)){
                    y.setFill(Color.YELLOW);
                }
                else if("M" == boardCopy[i][j].get(1)){
                    y.setFill(Color.MAGENTA);
                }
                else{
                    y.setFill(Color.AQUA);
                }

                stack.getChildren().addAll(x,y);

                if("Square" == boardCopy[i][j].get(2)){
                    Rectangle z = new Rectangle(0,0,20,20);
                    z.setStroke(Color.BLACK);
                    z.setFill(Color.MISTYROSE);
                    z.setOnMouseClicked(event -> stack.getChildren().remove(z));
                    stack.getChildren().add(z);
                }
                else if("Circle" == boardCopy[i][j].get(2)){
                    Circle z = new Circle(10);
                    z.setStroke(Color.BLACK);
                    z.setFill(Color.MISTYROSE);

                    z.setOnMouseClicked(event -> stack.getChildren().remove(z));

                    stack.getChildren().add(z);
                }
                else{
                    Polygon z = new Polygon();
                    z.getPoints().addAll(
                            10.0, 0.0,
                            0.0, 20.0,
                            20.0, 20.0);
                    z.setStroke(Color.BLACK);
                    z.setFill(Color.MISTYROSE);
                    z.setOnMouseClicked(event -> stack.getChildren().remove(z));
                    stack.getChildren().add(z);
                }
                tilePane.getChildren().add(stack);
            }
        }
        mainPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setCenter(tilePane);
        tilePane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        tilePane.setMaxSize(mainPane.getWidth()/2, mainPane.getHeight()/2);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}