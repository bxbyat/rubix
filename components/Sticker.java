package components;

public class Sticker {
  /**
   * Position to move Sticker
   */
  public Vector target;
  /**
   * Position Sticker currently holds
   */
  public Vector pos;

  public Sticker(Vector pos, Vector target) {
    this.pos = pos;
    this.target = target == null ? pos : target;
  }

  public Character getFace() {
    return target.x == 3 ? 'R' :
            target.x == -3 ? 'L' :
                    target.y == 3 ? 'U' :
                            target.y == -3 ? 'D' :
                                    target.z == 3 ? 'F' :
                                            target.z == -3 ? 'B' : '-';
  }
  public void moveSticker(Move move) {
    if (move.check.run(pos)) {

      /* axis coordinate tells which coordinate remains stationary
      Example:
        axis = (0, 1, 0) : U turn

        current = (2, 2, 3) : URF F
        target = (-3, 2, 2) : ULF L
        target = (-2, 2, -3) : ULB B

        overall 90 (x, y, z) -> (-z, y, x)
        overall 180 (x, y, z) -> (-x, y, -z)
        overall 270 (x, y, z) -> (z, y -x)

        current =
      */
      double x, y, z;
      double u, v, w;
      x = pos.x;
      y = pos.y;
      z = pos.z;
      u = move.axis.x;
      v = move.axis.y;
      w = move.axis.z;
      double theta = -move.angle * Math.PI / 180;
      double prodSum = u * x + v * y + w * z;
      double xPrime = u * prodSum *(1d - Math.cos(theta))
              + x * Math.cos(theta)
              + (-w * y + v * z) * Math.sin(theta);
      double yPrime = v * prodSum * (1d - Math.cos(theta))
              + y * Math.cos(theta)
              + (w*x - u*z)*Math.sin(theta);
      double zPrime = w * prodSum *(1d - Math.cos(theta))
              + z * Math.cos(theta)
              + (-v * x + u * y) * Math.sin(theta);
      pos = new Vector((int) Math.round(xPrime), (int) Math.round(yPrime), (int) Math.round(zPrime));

    }
  }

  @Override
  public String toString() {
    return this.getFace() + ": " + pos.toString();
  }

}
