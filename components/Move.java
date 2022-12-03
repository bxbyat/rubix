package components;


public class Move {

  public String name;
  public Vector axis;
  public int angle;
  public CheckPosition check;

  public Move(String name, Vector axis, int angle, CheckPosition check) {
    this.name = name;
    this.axis = axis;
    this.angle = angle;
    this.check = check;
  }

}
