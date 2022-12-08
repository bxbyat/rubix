package core;

import java.awt.*;
import java.util.HexFormat;

/**
 * A square on a piece of a Rubik's Cube.
 */

public class Square implements CorrectnessObserver {
    /**
     * colour of square
     */
    public Color colour;
    /**
     * location of square on cube in text
     */
    public String text;
    /**
     * symbol of location of square on cube
     */
    public String symbol;

    /** Square Constructor
     * -------------------
     * @param colour  The colour of the square
     */
//    public Square(Color colour) {
//        this.colour = colour;
//        this.text = "";
//        this.symbol = "";
//    }

    public Square(){

    }


    /**
     *
     * @return  the colour of the square
     */
    public Color getColour() {
        return this.colour;
    }

    /**
     *
     * @return  the text of the square
     */
    public String getText() {
        return this.text;
    }

    /**
     *
     * @return  the symbol of the square
     */
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void update() {

    }
}
