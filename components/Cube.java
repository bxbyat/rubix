package components;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * A Rubik's Cube representation
 */
public class Cube {

  public ArrayList<Sticker> solvedCube = new ArrayList<>();
  public final HashMap<String, Move> moveSet = new HashMap<>();
  public ArrayList<Character> facelet;
  final HashMap<Character, Integer> faceToInt = new HashMap<Character, Integer>();
  final HashMap<Character, String> colourMap = new HashMap<>();

  private void createMoveSet(String name, Vector axis, CheckPosition check) {
    Move move1 = new Move(name, axis, 90, check);
    Move move2 = new Move(name + "2", axis, 180, check);
    Move move3 = new Move(name + "'", axis, 270, check);
    moveSet.put(move1.name, move1);
    moveSet.put(move2.name, move2);
    moveSet.put(move3.name, move3);
  }
  public Cube() {
    /* this.facelet = new ArrayList<>();
    for (int i = 0; i < 54; i++) {
      facelet.add(colours[i / 9]);
    }
    char[] faceChars = {'U', 'L', 'F', 'R', 'B', 'D'};
    for (int i = 0; i < 6; i++) {
      faceToInt.put(faceChars[i], i * 9);
    }*/
    String[] colours = {"⬜", "🟧", "🟩", "🟥", "🟦", "🟨"};
    Character[] faces = {'U', 'L', 'F', 'R', 'B', 'D'};
    for (int i = 0; i < 6; i++) {
      colourMap.put(faces[i], colours[i]);
    }
    for (int i: new int[]{-3, 3}) {
      for (int j = -2; j <= 2; j += 2) {
        for (int k = -2; k <= 2; k += 2) {
          solvedCube.add(new Sticker(new Vector(i, j, k)));
          solvedCube.add(new Sticker(new Vector(j, i, k)));
          solvedCube.add(new Sticker(new Vector(j, k, i)));
        }
      }
    }
    this.sortCube();
    // cube centered at (0, 0, 0)
    // (1, 0, 0) is the axis
    // (2, 0, 0) is a piece
    // (3, 0, 0) is a square
    // modelling helped by https://observablehq.com/@onionhoney/how-to-model-a-rubiks-cube

    createMoveSet("R", new Vector(1, 0, 0), (p) -> p.x > 0);
    createMoveSet("L", new Vector(-1, 0, 0), (p) -> p.x < 0);
    createMoveSet("U", new Vector(0, 1, 0), (p) -> p.y > 0);
    createMoveSet("D", new Vector(0, -1, 0), (p) -> p.y < 0);
    createMoveSet("F", new Vector(0, 0, 1), (p) -> p.z > 0);
    createMoveSet("B", new Vector(0, 0, -1), (p) -> p.z < 0);

  }

  public void sortCube() {
    Comparator<Sticker> c = new Comparator<Sticker>() {
      public int compareHelper(int c1, int c2, int num) {

        if (c1 == num && c2 == num) return 0;
        if (c1 == num) return -1;
        if (c2 == num) return 1;
        return -2;
      }

      public int secondaryCheck(int pc1, int pc2, int qc1, int qc2) {
        return pc1 > qc1 ? 1 :
                pc1 < qc1 ? -1 :
                        pc2 > qc2 ? 1 : -1;
      }
      @Override
      public int compare(Sticker s1, Sticker s2) {
        Vector p1 = s1.pos;
        Vector p2 = s2.pos;

        List<Integer> checks = new ArrayList<>();
        checks.add(compareHelper(p1.y, p2.y, 3));
        checks.add(compareHelper(p1.x, p2.x, -3));
        checks.add(compareHelper(p1.z, p2.z, 3));
        checks.add(compareHelper(p1.x, p2.x, 3));
        checks.add(compareHelper(p1.z, p2.z, -3));
        checks.add(compareHelper(p1.y, p2.y, -3));
        for (int i = 0; i < 6; i++) {
          if (checks.get(i) == 0) {

            /*
            (-2 -2) (0 -2) (2 -2)
            (-2  0) (0  0) (2  0)
            (-2  2) (0  2) (2  2)
             */
            if (i == 0) return secondaryCheck(p1.z, p1.x, p2.z, p2.x);
            if (i == 1) return secondaryCheck(p2.y, p1.z, p1.y, p2.z);
            if (i == 2) return secondaryCheck(p2.y, p1.x, p1.y, p2.x);
            if (i == 3) return secondaryCheck(p2.y, p2.z, p1.y, p1.z);
            if (i == 4) return secondaryCheck(p2.y, p2.x, p1.y, p1.x);
            return secondaryCheck(p2.z, p1.x, p1.z, p2.x);
          }
          if (checks.get(i) != -2) return checks.get(i);
        }
        return 0;
      }
    };
    solvedCube.sort(c);
    this.generateFaceletModel();
  }

  public void generateFaceletModel() {
    facelet = new ArrayList<>();
    for (Sticker s: solvedCube) {
      facelet.add(s.getFace());
    }
  }

  public void applyMoves(String scramble) {

    for (String moveName: scramble.split(" ")) {
      if (moveName != null) {
        this.doMove(moveSet.get(moveName));
      }
    }
    this.sortCube();
  }
  public void doMove(Move move) {
    for (Sticker s: solvedCube) {
      s.moveSticker(move);
    }
  }
  public void display() {
    System.out.println(this);
  }
  public void showStickers() {
    for (Sticker s: solvedCube) {
      System.out.println(s);
    }
  }
  @Override
  public String toString() {

    StringBuilder str = new StringBuilder();
    str.append("⬛".repeat(17)).append('\n');
    for (int i = 0; i < 9; i += 3) {
      str.append("⬛".repeat(5));
      for (int j = i; j < i + 3; j++)
        str.append(colourMap.get(facelet.get(j)));
      str.append("⬛".repeat(9)).append('\n');
    }
    str.append("⬛".repeat(17)).append('\n');
    for (int i = 9; i <= 15; i+=3) {
      str.append("⬛");
      for (int j = i; j <= i + 27; j+=9) {
        for (int k = j; k <= j + 2; k++) {
          str.append(colourMap.get(facelet.get(k)));
        }
        str.append("⬛");
      }
      str.append('\n');
    }
    str.append("⬛".repeat(17)).append('\n');
    for (int i = 45; i < 54; i += 3) {
      str.append("⬛".repeat(5));
      for (int j = i; j < i + 3; j++)
        str.append(colourMap.get(facelet.get(j)));
      str.append("⬛".repeat(9)).append('\n');
    }
    str.append("⬛".repeat(17));
    return str.toString();
  }

















  /*

  below is useless, for setup of facelet model


  private Integer S(Character face, int index) {
    return faceToInt.get(face) + index - 1;
  }

  private void cycle(List<Integer> indices) {
    Character temp = facelet.get(indices.get(0));
    for (int i = 0; i < indices.size() - 1; i++) {
      facelet.set(indices.get(i), facelet.get(indices.get(i + 1)));
    }
    facelet.set(indices.get(indices.size() - 1), temp);
  }





  public Cube upCCW() {
    cycle(List.of(S('U', 1), S('U', 3), S('U', 5), S('U', 7)));
    cycle(List.of(S('U', 2), S('U', 4), S('U', 6), S('U', 8)));
    cycle(List.of(S('F', 1), S('L', 1), S('B', 1), S('R', 1)));
    cycle(List.of(S('F', 2), S('L', 2), S('B', 2), S('R', 2)));
    cycle(List.of(S('F', 3), S('L', 3), S('B', 3), S('R', 3)));
    return this;
  }
  */
}
