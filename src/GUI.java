import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {

    //Public Variable SCREEN_BOUNDS stores boundary size of screen
    public final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private int currentCombo;
    private int longestCombo;
    private Text currentComboText;
    private Text longestComboText;

    /*
     What method does
     Method parameters
     return value
      */
    public static void main() {
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


        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

        TilePane tilePane = new TilePane();
        tilePane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        tilePane.setMaxSize(SCREEN_BOUNDS.getWidth()/3, SCREEN_BOUNDS.getHeight()/3);

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