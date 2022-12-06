package rubix.instructions;

public class Instructions {

    /**
     * Class Instructions
     */

    String face;

    public Instructions(String letter) {
        if (letter.contains("U")) {this.face = "U";}
        else if (letter.contains("R")) {this.face = "R";}
        else if (letter.contains("L")) {this.face = "L";}
        else if (letter.contains("D")) {this.face = "D";}
        else if (letter.contains("F")) {this.face = "F";}
        else {this.face = "B";}

    }

    /**
     * Returns the sentence form of a given step
     */

    public String getInstructions() {
        return "Rotate the " + this.face + " face clockwise 90 degrees. ";
    }


}
