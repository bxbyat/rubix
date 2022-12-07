import components.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {

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
    cube.display();

    Solver solver = new Solver(stmMoves);
    String solution = solver.iddfsSolve(cube, 5);
    System.out.println(solution);
  }
}