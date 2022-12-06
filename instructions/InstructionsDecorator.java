package rubix.instructions;

public abstract class InstructionsDecorator {

    /**
     * Abstract Class InstructionsDecorator
     */

    Instructions instructions;

    public InstructionsDecorator(Instructions instr) {
        this.instructions = instr;
    }

    public abstract String getInstructions();



}
