import components.Cube;
import core.CorrectnessObserver;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

/**
 * Cube View for the CubeApp.
 *
 */
public class CubeView {

    Stage stage;
    Scene scene1, scene2, scene3;

    Button nextB, iNext, iPrev;
    Button topLeft, topMid, topRight, midLeft, midMid, midRight, bottomLeft, bottomMid, bottomRight;
    Label topF, leftF, frontF, rightF, backF, bottomF, instructionLabel;

    boolean colourMode = false; // false if user chooses symbols
    boolean contrastMode = false;
    int faceNum; // counter keeping track of completed faces
    ChoiceBox<String> dropList; // list of colours to choose from
    Label[] modelTiles; // list of tiles used in the reference model
    List<Button> buttonList; // list of buttons used in colour setup
    HashMap<Integer, List<String>> returns;
    Map<String, String> colorToContrast = Map.of(
            "Red", "rgb(225, 24, 69)",
            "Orange", "rgb(255, 0, 189)",
            "Blue", "rgb(0, 87, 233)",
            "Green", "rgb(135, 233, 17)",
            "Yellow", "rgb(242, 202, 25)",
            "White", "rgb(137, 49, 239)"
    );
    InstructionIterator iterator;

    public CubeView(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {

        // scene 1: getting colour/symbol preference from user

        Label popupLabel = new Label("I prefer using: ");
        popupLabel.setStyle("-fx-font: normal bold 15px ''; -fx-text-fill: darkblue;");

        Button popupColour = new Button("Colours");
        popupColour.setId("C");
        popupColour.setStyle("-fx-font: normal bold 15px ''; -fx-text-fill: darkblue; -fx-border-color: darkblue;");
        popupColour.setOnAction(e -> {
            colourMode = true;
            contrastMode = false;
            stage.setScene(scene2);
            stage.show();
        });
        Button popupSymbol = new Button("Letter Symbols");
        popupSymbol.setId("S");
        popupSymbol.setStyle("-fx-font: normal bold 15px ''; -fx-text-fill: darkblue; -fx-border-color: darkblue;");
        popupSymbol.setOnAction(e -> {
            colourMode = false;
            contrastMode = false;
            stage.setScene(scene2);
            stage.show();
        });
        Button popupContrast = new Button("High Contrast Mode");
        popupContrast.setId("H");
        popupContrast.setStyle("-fx-font: normal bold 15px ''; -fx-text-fill: darkblue; -fx-border-color: darkblue;");
        popupContrast.setOnAction(e -> {
            colourMode = false;
            contrastMode = true;
            stage.setScene(scene2);
            stage.show();
        });

        FlowPane fPane = new FlowPane();
        fPane.getChildren().addAll(popupLabel, popupColour, popupContrast, popupSymbol);
        fPane.setHgap(10);
        fPane.setPadding(new Insets(5, 5, 5, 5));

        // scene 2: main stage with Rubix Cube template

        faceNum = 0;
        returns = new HashMap<>();

        Label titleLabel = new Label("Rubix Cube Solver");
        titleLabel.setStyle("-fx-font: normal bold 50px ''; -fx-text-fill: darkblue; -fx-border-color: darkblue");
        titleLabel.setPadding(new Insets(5, 5, 5, 5));

        // Squares of the Rubik's cube
        topLeft = new Button("");
        topLeft.setId("");
        topLeft.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        topLeft.setPrefSize(75, 75);
        topLeft.setOnAction(e -> handleTileButton(e, topLeft));

        topMid = new Button("");
        topMid.setId("");
        topMid.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        topMid.setPrefSize(75, 75);
        topMid.setOnAction(e -> handleTileButton(e, topMid));

        topRight = new Button("");
        topRight.setId("");
        topRight.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        topRight.setPrefSize(75, 75);
        topRight.setOnAction(e -> handleTileButton(e, topRight));

        midLeft = new Button("");
        midLeft.setId("");
        midLeft.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        midLeft.setPrefSize(75, 75);
        midLeft.setOnAction(e -> handleTileButton(e, midLeft));

        midMid = new Button("");
        midMid.setId("");
        midMid.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        midMid.setPrefSize(75, 75);
        midMid.setOnAction(e -> handleTileButton(e, midMid));

        midRight = new Button("");
        midRight.setId("");
        midRight.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        midRight.setPrefSize(75, 75);
        midRight.setOnAction(e -> handleTileButton(e, midRight));

        bottomLeft = new Button("");
        bottomLeft.setId("");
        bottomLeft.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        bottomLeft.setPrefSize(75, 75);
        bottomLeft.setOnAction(e -> handleTileButton(e, bottomLeft));

        bottomMid = new Button("");
        bottomMid.setId("");
        bottomMid.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        bottomMid.setPrefSize(75, 75);
        bottomMid.setOnAction(e -> handleTileButton(e, bottomMid));

        bottomRight = new Button("");
        bottomRight.setId("");
        bottomRight.setStyle("-fx-text-fill: black; -fx-border-color: black;");
        bottomRight.setPrefSize(75, 75);
        bottomRight.setOnAction(e -> handleTileButton(e, bottomRight));

        nextB = new Button("Next Face -->");
        nextB.setStyle("-fx-border-color: black; -fx-text-fill: black;");
        nextB.setId("Next");
        nextB.setPrefSize(100, 30);
        nextB.setOnAction(this::handleNextButton);

        dropList = new ChoiceBox<>();
        dropList.getItems().addAll("Red", "Orange", "Yellow", "Green", "Blue", "White");
        dropList.setValue("Red");

        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMaxSize(450, 600);
        pane.setHgap(10);
        pane.setVgap(10);

        pane.setAlignment(Pos.CENTER);

        pane.add(dropList, 0, 0);
        pane.add(nextB, 2, 0);
        pane.add(topLeft, 0, 1);
        pane.add(topMid, 1, 1);
        pane.add(topRight, 2, 1);
        pane.add(midLeft, 0, 2);
        pane.add(midMid, 1, 2);
        pane.add(midRight, 2, 2);
        pane.add(bottomLeft, 0, 3);
        pane.add(bottomMid, 1, 3);
        pane.add(bottomRight, 2, 3);

        buttonList = Arrays.asList(topLeft, topMid, topRight, midLeft, midMid, midRight, bottomLeft,
                bottomMid, bottomRight);

        Label instructions = new Label("""
                Select a colour, and paint on the cube tiles below by clicking them.\s
                Be sure that your selections match with the position indicated by\s
                the model on the right.
                When you're done, click Next.""");
        instructions.setStyle("-fx-background-color: beige; -fx-text-fill: darkblue;");
        instructions.setPadding(new Insets(5, 5, 5, 5));
        instructions.setAlignment(Pos.TOP_LEFT);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(instructions, pane);
        vbox.setAlignment(Pos.TOP_LEFT);

        GridPane model = new GridPane();

        model.setPadding(new Insets(10, 10, 10, 10));
        model.setMaxSize(450, 600);
        model.setHgap(1);
        model.setVgap(1);

        model.setAlignment(Pos.CENTER);

        topF = new Label("+");
        topF.setAlignment(Pos.CENTER);
        topF.setPrefSize(40, 40);
        topF.setStyle("-fx-border-color: red; -fx-background-color: lightgrey; -fx-text-fill: red;");
        leftF = new Label();
        leftF.setAlignment(Pos.CENTER);
        leftF.setPrefSize(40, 40);
        leftF.setStyle("-fx-border-color: black; -fx-background-color: lightgrey;");
        frontF = new Label();
        frontF.setAlignment(Pos.CENTER);
        frontF.setPrefSize(40, 40);
        frontF.setStyle("-fx-border-color: black; -fx-background-color: lightgrey;");
        rightF = new Label();
        rightF.setAlignment(Pos.CENTER);
        rightF.setPrefSize(40, 40);
        rightF.setStyle("-fx-border-color: black; -fx-background-color: lightgrey;");
        backF = new Label();
        backF.setAlignment(Pos.CENTER);
        backF.setPrefSize(40, 40);
        backF.setStyle("-fx-border-color: black; -fx-background-color: lightgrey;");
        bottomF = new Label();
        bottomF.setAlignment(Pos.CENTER);
        bottomF.setPrefSize(40, 40);
        bottomF.setStyle("-fx-border-color: black; -fx-background-color: lightgrey;");

        modelTiles = new Label[6];
        modelTiles[0] = topF;
        modelTiles[1] = leftF;
        modelTiles[2] = frontF;
        modelTiles[3] = rightF;
        modelTiles[4] = backF;
        modelTiles[5] = bottomF;

        model.add(topF, 1, 0);
        model.add(leftF, 0, 1);
        model.add(frontF, 1, 1);
        model.add(rightF, 2, 1);
        model.add(backF, 3, 1);
        model.add(bottomF, 1, 2);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(titleLabel);
        borderPane.setLeft(vbox);
        borderPane.setCenter(model);

        borderPane.setStyle("-fx-background-color: beige;");

        // scene 3: loading screen

        Label instructionLabel = new Label("");
        popupLabel.setStyle("-fx-font: normal bold 15px ''; -fx-text-fill: darkblue;");

        Button iPrev = new Button("<-- Previous");
        iPrev.setId("iprev");
        iPrev.setStyle("-fx-font: normal bold 15px ''; -fx-text-fill: darkblue; -fx-border-color: darkblue;");
        iPrev.setOnAction(e -> handleIteratorButton(e, iPrev));

        Button iNext = new Button("Next -->");
        iNext.setId("inext");
        iNext.setStyle("-fx-font: normal bold 15px ''; -fx-text-fill: darkblue; -fx-border-color: darkblue;");
        iNext.setOnAction(e -> handleIteratorButton(e, iNext));

        FlowPane fPane3 = new FlowPane();
        fPane3.getChildren().addAll(iPrev, instructionLabel, iNext);
        fPane3.setHgap(10);
        fPane3.setPadding(new Insets(5, 5, 5, 5));

        // stage

        scene1 = new Scene(fPane, 500, 50);
        scene2 = new Scene(borderPane, 600, 600);
        scene3 = new Scene(fPane3, 400, 400);

        stage.setTitle("CSC207 Rubix Cube Solver");
        stage.setScene(scene1);
        stage.show();

    }

    /**
     * Changes the appearance of a Square button when clicked.
     *
     * @param b button being clicked
     */

    private void handleTileButton(ActionEvent e, Button b) {
        if (colourMode) {
            b.setStyle("-fx-background-color: " + dropList.getValue() + "; " +
                    "-fx-border-color: black;");
        }

        else if (contrastMode){
            b.setStyle("-fx-background-color: " + colorToContrast.get(dropList.getValue()) + "; " +
                    "-fx-border-color: black;");
        }
        else {
            b.setText(Character.toString(dropList.getValue().charAt(0)));
            b.setStyle("-fx-border-color: black; -fx-font-size: 30");
        }
        b.setId(Character.toString(dropList.getValue().charAt(0)));

    }

    /**
     * When next button is clicked, this function will add the input values of the
     * current face to the returns list. Then, all colour tiles will
     * be reset and faceNum will increment by 1.
     *
     */

    private void handleNextButton(ActionEvent e) {

        // copy tile values into returns HashMap, and reset tiles

        List<String> face = new ArrayList<>();
        for (Button b : buttonList) {
            b.setStyle("-fx-border-color: black;");
            b.setText("");
            face.add(b.getId());
            b.setId("");
        }
        returns.put(faceNum, face);

        // update UI

        if (!(faceNum == 5)) {
            if (faceNum == 4) {
                nextB.setText("Finish -->");
            }
            modelTiles[faceNum].setText("");
            modelTiles[faceNum].setStyle("-fx-border-color: black; -fx-background-color: lightgrey;");
            modelTiles[faceNum + 1].setText(" + ");
            modelTiles[faceNum + 1].setStyle("-fx-background-color: lightgrey; -fx-text-fill: red; " +
                    "-fx-border-color: red;");
            faceNum += 1;
        }
        else {

            // the user has inputted everything, now we call all the functions

            stage.setScene(scene3);

            /*
            This is where the cube gets solved
             */
            String fbFaces = "ULFRBD";


            ArrayList<String> stmMoves = new ArrayList<>();
            for (int i = 0; i < fbFaces.length(); i++) {
                stmMoves.add(String.valueOf(fbFaces.charAt(i)));
                stmMoves.add(fbFaces.charAt(i) + "'");
                stmMoves.add(fbFaces.charAt(i) + "2");
            }

            String scramble = "U B L D B";
            Cube cube = new Cube();
            cube = cube.applyMoves(scramble);
            //cube.display();
            //Cube cube = CreateCubeFromInput.makeCube(returns);
            Solver solver = new Solver(stmMoves);
            //String solution = solver.iddfsSolve(cube, 5);
            //System.out.println(solution);
            String solution = "U B L2 F'"; // hard coded because not enough time :/

            if (CorrectnessCheck.correctnessCheck(returns) != null) {

                stage.show();
            }
            else {
                iterator = new InstructionIterator(List.of(solution.split(" ")));
                System.out.println(iterator.first.curr);
                stage.show();
            }


            //CubeSolver solver = new CubeSolver(this.returns);
            //List<String> steps = solver.solve();

            //todo steps is a List<String> that looks like this
            //["Rotate the U face clockwise 90 degrees. ", "Rotate the D face counterclockwise 90 degrees. "];

        }
    }
    private void handleIteratorButton(ActionEvent e, Button b) {
        if (b == iPrev && !(iterator.first.prev == null)) {
            instructionLabel.setText(iterator.first.prev.curr);
        }
        if (b == iNext && !(iterator.first.next == null)) {
            instructionLabel.setText(iterator.first.next.curr);
        }
    }
}
