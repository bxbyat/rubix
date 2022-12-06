package core;
import java.util.List;

/**
 * A piece on a Rubik's Cube.
 */

public class Piece {
    /**
     * list of squares in the piece
     */
    public List<Square> squares;
    /**
     * type of piece:
     * 1 : center
     * 2 : edge
     * 3 : corner
     */
    public Integer id;

    /** Piece Constructor
     * ------------------
     * @param id  The type of piece
     * @param squares  The squares in the piece
     */
    public Piece(int id, List<Square> squares) {
        this.id = id;
        this.squares = squares;
    }

    /**
     *
     * @return  the id of the piece
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return  the squares in the piece
     */
    public List<Square> getSquares() {
        return this.squares;
    }

}
