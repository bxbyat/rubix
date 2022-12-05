import components.*;

public class Main {

  public static void main(String[] args) {
    Cube cube = new Cube();
    Sticker sticker = new Sticker(new Vector(0, -2, 3));
    sticker.moveSticker(cube.moveSet.get("D").get(0));
    System.out.println(sticker.target);
  }
}