import components.*;
import components.Vector;
import core.Piece;
import core.Square;

import java.sql.Array;
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
    ArrayList<Sticker> newPieces = new ArrayList<>();
    ArrayList<Sticker> oldPieces = cube.getPieces();

    for (int x = -2; x <= 2; x += 2) {
      for (int y = -2; y <= 2; y += 2) {
        for (int z = -2; z <= 2; z += 2) {
          if (x == 0 && y == 0 && z == 0) continue;
          int id = (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 3;
          Vector pos = new Vector(x, y, z);
          List<Sticker> squares = getNeighbors(oldPieces, pos);

          Piece piece = new Piece(id, squares, pos);
          System.out.println(piece);
        }
      }
    }
    cube.setPieces(newPieces);
    cube.sortCube();
    return cube;
  }

  private static ArrayList<Sticker> getNeighbors(ArrayList<Sticker> pieces, Vector pos) {
    ArrayList<Sticker> neighbors = new ArrayList<>();
    if (pos.x == 2) {
      for (Sticker s: pieces) {
        if (s.pos.equals(new Vector(3, pos.y, pos.z))) {
          neighbors.add(s);
          break;
        }
      }
    }
    else if (pos.x == -2) {
      for (Sticker s: pieces) {
        if (s.pos.equals(new Vector(-3, pos.y, pos.z))) {
          neighbors.add(s);
          break;
        }
      }
    }
    if (pos.y == 2) {
      for (Sticker s: pieces) {
        if (s.pos.equals(new Vector(pos.x, 3, pos.z))) {
          neighbors.add(s);
          break;
        }
      }

    }
    else if (pos.y == -2) {
      for (Sticker s: pieces) {
        if (s.pos.equals(new Vector(pos.x, -3, pos.z))) {
          neighbors.add(s);
          break;
        }
      }
    }
    else if (pos.z == 2) {
      for (Sticker s: pieces) {
        if (s.pos.equals(new Vector(pos.x, pos.y, 3))) {
          neighbors.add(s);
          break;
        }
      }
    }
    else if (pos.z == -2) {
      for (Sticker s: pieces) {
        if (s.pos.equals(new Vector(pos.x, pos.y, -3))) {
          neighbors.add(s);
          break;
        }
      }
    }
    return neighbors;
  }

}
