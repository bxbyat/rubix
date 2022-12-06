package core;

/**
 * One of six faces of a Rubik's cube.
 */

public class Face {

  //2D array of the squares that make up the face.
  private Square[][] squares = new Square[3][3];

  /**
   * Face constructor
   * ----------------
   * @param squareList A list of the Squares to initialize *squares* with.
   */
  public Face (Square[] squareList){
    for (int row = 0; row < 3; row++)
      for (int col = 0; col < 3; col++)
        this.squares[row][col] = squareList[row*3 + col];
  }

  /**
   * @return *squares*
   */
  public Square[][] getSquares(){
    return this.squares;
  }

  /**
   * @return the Square in the Face's given row and column.
   */
  public Square getSquare(int row, int col){
    return this.squares[row][col];
  }
}