import components.*;

import java.util.*;

public class CreateCubeFromInput {
  /**
   *
   * @param input Colours of the cube, assumed to be legal
   * @return Cube.java object with those colours
   */
  public static Cube makeCube(HashMap<Integer, List<String>> input) {
    Cube cube = new Cube();


    // manually change pieces
    ArrayList<Sticker> pieces = new ArrayList<>();




    cube.setPieces(pieces);
    return cube;
  }

}
