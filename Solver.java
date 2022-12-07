import components.*;
import components.Vector;

import java.util.*;

public class Solver {
  public List<Integer> fbPieces = List.of(
          Cube.S('L', 4),
          Cube.S('L', 5),
          Cube.S('L', 6),
          Cube.S('L', 7),
          Cube.S('L', 8),
          Cube.S('L', 9),
          Cube.S('F', 4),
          Cube.S('F', 5),
          Cube.S('B', 6),
          Cube.S('B', 9),
          Cube.S('D', 1),
          Cube.S('D', 4),
          Cube.S('D', 7));

  public Cube solvedCube;
  public ArrayList<String> moves;
  public HashMap<String, ArrayList<List<Integer>>> faceletMoveSet;

  public Solver(ArrayList<String> candidateMoves) {
    this.solvedCube = new Cube();
    this.faceletMoveSet = new HashMap<>();
    this.moves = candidateMoves;
    for (String entry : solvedCube.geoMoveSet.keySet()) {
      faceletMoveSet.put(entry, geometricMoveToFacelet(solvedCube.geoMoveSet.get(entry)));
    }
  }
  public boolean isSolved(Cube cube) {
    String fbFaces = "ULFRBD";

    for (int i = 0; i < fbPieces.size(); i++) {
      if (cube.getPieces().get(fbPieces.get(i)).getFace() != fbFaces.charAt((int) Math.floor(fbPieces.get(i) / 9.0))) return false;
    }
    return true;
  }
  public String dfsSolve(Cube cube, String solution, int depthCount) {
    if (this.isSolved(cube)) {
      cube.display();
      return solution;
    }
    if (depthCount == 0) return null;
    for (String move: moves) {
      String result = this.dfsSolve(applyFMoves(cube, move), solution + " " + move, depthCount - 1);
      if (result != null) return result;
    }
    return null;
  }

  public String iddfsSolve(Cube cube, int depthLimit) {
    for (int d = 0; d <= depthLimit; d++) {
      String solution = this.dfsSolve(cube, "", d);
      if (solution != null) return solution;
    }
    return null;
  }

  public Cube doFMove(Cube currCube, ArrayList<List<Integer>> cycleSet) {
    Cube newCube = new Cube();
    ArrayList<Sticker> currPieces = currCube.getPieces();
    ArrayList<Sticker> pieces = new ArrayList<>();
    for (Sticker s: currPieces) {
      pieces.add(new Sticker(s.pos, s.target));
    }
    for (List<Integer> cycle: cycleSet) {
      pieces.get(cycle.get(0)).pos = currPieces.get(cycle.get(1)).pos;
    }
    newCube.setPieces(pieces);
    return newCube;
  }
  public Cube applyFMoves(Cube oldCube, String scramble) {
    Cube newCube = oldCube;

    if (!scramble.equals("")) {
      for (String moveName : scramble.split(" ")) {
        if (moveName != null) {
          newCube = doFMove(newCube, faceletMoveSet.get(moveName));
          newCube.sortCube();
        }
      }
    }
    return newCube;
  }

  public void work(String rotation, int i, HashMap<String, Integer> map1) {
    for (int z = -2; z <= 2; z += 2) {
      for (int x = -2; x <= 2; x += 2) {
        Sticker s = new Sticker(new Vector(x, 3, z), null);
        for (String move: rotation.split(" ")) {
          if (!move.equals("")) s.moveSticker(solvedCube.geoMoveSet.get(move));
        }
        map1.put(s.pos.toString(), i++);
      }
    }
  }

  /**
   * Connects the facelet model index to its vector counterpart
   * @return HashMap containing the index facelet number related to each vector
   */
  public HashMap<String, Integer> geometricCubeToFacelet() {
    HashMap<String, Integer> map1 = new HashMap<>();
    List<String> face_rotations = List.of("", "x' y", "x'", "x' y'", "x' y2", "x2");
    //System.out.println(map1);
    for (int i = 0; i < 6; i++) {
      this.work(face_rotations.get(i), i * 9, map1);
      //System.out.println(map1);
    }
    return map1;
  }

  public ArrayList<List<Integer>> geometricMoveToFacelet(Move gmove) {
    ArrayList<List<Integer>> cycles = new ArrayList<>();
    solvedCube.doMove(gmove);
    solvedCube.sortCube();
    HashMap<String, Integer> pieceMap = this.geometricCubeToFacelet();
    for (Sticker s: solvedCube.getPieces()) {
      int pos = pieceMap.get(s.pos.toString());
      int target = pieceMap.get(s.target.toString());
      if (pos != target) cycles.add(List.of(target, pos));
    }
    solvedCube = new Cube();
    return cycles;
  }

}