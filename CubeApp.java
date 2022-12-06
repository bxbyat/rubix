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
    public void start(Stage primaryStage) throws Exception {
        this.view = new CubeView(primaryStage);

    }

}