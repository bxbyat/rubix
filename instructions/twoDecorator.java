package rubix.instructions;

public class twoDecorator extends InstructionsDecorator {

    /**
     * Class twoDecorator, for handling steps containing "2"
     */

    public twoDecorator(Instructions instr) {
        super(instr);
    }

    /**
     * Returns the sentence form of a given step
     */

    @Override
    public String getInstructions() {
        return "Rotate the " + super.instructions.face + " face 180 degrees. ";
    }

}