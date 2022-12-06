import components.Cube;

public class Main {

  public static void main(String[] args) {
    Cube cube = new Cube();
    cube.applyMoves("U2 D2 R2 L2 F2 B2");
    cube.display();
    cube.applyMoves("U2 D2 R2 L2 F2 B2");
    cube.display();
  }
}