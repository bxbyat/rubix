package components;


public class Vector {

  public int x;
  public int y;
  public int z;

  public Vector(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  @Override
  public String toString() {
    return "(" + x + ", " + y + ", " + z + ")";
  }
  @Override
  public boolean equals(Object o) {
    if (o instanceof Vector) {
      return this.toString().equals(o.toString());
    }
    return false;
  }

}
