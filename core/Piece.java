package core;
import components.Sticker;
import components.Vector;

import java.util.List;

/**
 * A piece on a Rubik's Cube.
 */

public class Piece {
    /**
     * list of squares in the piece
     */
    public List<Sticker> squares;
    /**
     * type of piece:
     * 1 : center
     * 2 : edge
     * 3 : corner
     */
    public Integer id;
    public Vector pos;

    /** Piece Constructor
     * ------------------
     * @param id  The type of piece
     * @param squares  The squares in the piece
     */
    public Piece(int id, List<Sticker> squares, Vector pos) {
        this.id = id;
        this.squares = squares;
        this.pos = pos;
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
    public List<Sticker> getSquares() {
        return this.squares;
    }

    @Override
    public String toString() {
        return squares.toString();
    }

}
