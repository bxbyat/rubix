import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A Rubix Cube Application, in JavaFX.
 *
 */
public class CubeApp extends Application {


    CubeView view;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        // run CubeView, which then calls solver and gets instructions

        this.view = new CubeView(primaryStage);


        // change steps into sentences
        //return Arrays.asList("U", "B'", "F2", "L'", "R", "2D", "U'");

    }



}