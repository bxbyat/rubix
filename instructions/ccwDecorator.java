package instructions;

public class ccwDecorator extends InstructionsDecorator {

    /**
     * Class ccwDecorator, for handling steps containing "'"
     */

    public ccwDecorator(Instructions instr) {
        super(instr);
    }

    /**
     * Returns the sentence form of a given step
     */

    @Override
    public String getInstructions() {
        return "Rotate the " + super.instructions.face + " face counterclockwise 90 degrees. ";
    }

}
