package instructioniterator;

import java.util.ArrayList;
import java.util.List;

/**
 * An iterator for instructions.
 */

public class InstructionIterator {
    /**
     * first node in iterator
     */
    public InstructionNodes first;

    public InstructionIterator(List<String> steps) {
        List<InstructionNodes> nodes = new ArrayList<>();
        for (int i = 0; i < steps.size(); i++) {
            nodes.add(new InstructionNodes(steps.get(i)));
        }
        for (int a = 0; a < nodes.size(); a++) {
            if (a == 0) {
                nodes.get(a).prev = null;
                nodes.get(a).next = nodes.get(a + 1);
            }
            else if (a == nodes.size() - 1) {
                nodes.get(a).prev = nodes.get(a - 1);
                nodes.get(a).next = null;
            }
            else {
                nodes.get(a).prev = nodes.get(a - 1);
                nodes.get(a).next = nodes.get(a + 1);
            }
        }
        this.first = nodes.get(0);
    }

    public InstructionNodes getFirst() {
        return this.first;
    }
}
