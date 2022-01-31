import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    //Public Variable SCREEN_BOUNDS stores boundary size of screen
    public final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private int currentCombo;
    private int longestCombo;
    private Text currentComboText;
    private Text longestComboText;
    public static ArrayList[][] boardCopy;
    /*
     What method does
     Method parameters
     return value
      */
    public static void main(ArrayList[][] x) {
        boardCopy = x;
        launch();
    }

    /*
    What method does
    Method parameters
    return value
    */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button("Current Combo Incrementor");
        currentCombo = 0;
        currentComboText = new Text("Current Combo: 0");
        btn.setMaxSize(SCREEN_BOUNDS.getWidth(), SCREEN_BOUNDS.getHeight());
        btn.setAlignment(Pos.BOTTOM_CENTER);
        btn.setOnAction(e -> {
            currentCombo += 1;
            currentComboText.setText("Current Combo: " + currentCombo);
        });

        Button btnTwo = new Button("Longest Combo Incrementor");
        longestCombo = 0;
        longestComboText = new Text("Longest Combo: 0");
        btnTwo.setMaxSize(SCREEN_BOUNDS.getWidth(), SCREEN_BOUNDS.getHeight());
        btnTwo.setAlignment(Pos.BOTTOM_CENTER);
        btnTwo.setOnAction(f -> {
            longestCombo += 1;
            longestComboText.setText("Longest Combo: " + longestCombo);
        });

        //MouseEvent click = new MouseEvent();


        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));


        TilePane tilePane = new TilePane();
        tilePane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        tilePane.setMaxSize(SCREEN_BOUNDS.getWidth()/3, SCREEN_BOUNDS.getHeight()/3);
        tilePane.setPrefRows(4);
        tilePane.setPrefColumns(5);

        for(int i=0; i<4; i++){
            for(int j=0; j<5; j++){
                StackPane stack = new StackPane();

                Rectangle x = new Rectangle(0,0,100,100);
                x.setOnMouseClicked(event -> {
                    stack.getChildren().remove(x);
                });
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
                y.setOnMouseClicked(event -> {
                    stack.getChildren().remove(y);
                });
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
                    z.setOnMouseClicked(event -> {
                        stack.getChildren().remove(z);
                    });
                    stack.getChildren().add(z);
                }
                else if("Circle" == boardCopy[i][j].get(2)){
                    Circle z = new Circle(10);
                    z.setStroke(Color.BLACK);
                    z.setFill(Color.MISTYROSE);
                    z.setOnMouseClicked(event -> {
                        stack.getChildren().remove(z);
                    });
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
                    z.setOnMouseClicked(event -> {
                        stack.getChildren().remove(z);
                    });
                    stack.getChildren().add(z);
                }
                tilePane.getChildren().add(stack);
            }
        }


        VBox combos = new VBox(currentComboText, longestComboText);
        combos.setAlignment(Pos.CENTER);

        mainPane.setCenter(tilePane);
        HBox test = new HBox(btn, btnTwo);
        test.setAlignment(Pos.CENTER);
        mainPane.setBottom(test);
        mainPane.setRight(combos);


        Scene scene = new Scene(mainPane, SCREEN_BOUNDS.getWidth()/2, SCREEN_BOUNDS.getHeight()/2);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}