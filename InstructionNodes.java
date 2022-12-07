//package instructioniterator;

/**
 * An iterator helper for instructions.
 */

public class InstructionNodes {
    /**
     * previous instruction
     */
    public InstructionNodes prev;
    /**
     * current instruction
     */
    public String curr;
    /**
     * current instruction symbol
     */
    public String currSymbol;
    /**
     * next instruction;
     */
    public InstructionNodes next;

    public InstructionNodes(String curr){
        this.curr = curr;
        this.currSymbol = curr.charAt(0) + "CW";
    }
}
