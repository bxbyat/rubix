import instructions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CubeSolver {

    HashMap<Integer, List<String>> input;

    public CubeSolver(HashMap<Integer, List<String>> values) {
        this.input = values;

    }

    public List<String> solve() {
        List<String> temp =  Arrays.asList("U", "B'", "F2", "L'", "R", "2D", "U'");
        // todo change this


        return getAllInstructions(temp);
    }

    public List<String> getAllInstructions(List<String> steps) {
        List<String> allInstructions = new ArrayList<>();
        for (String step : steps) {
            Instructions a = new Instructions(step);
            if (step.contains("'")) {
                ccwDecorator b = new ccwDecorator(a);
                allInstructions.add(b.getInstructions());
            }
            else if (step.contains("2")) {
                twoDecorator c = new twoDecorator(a);
                allInstructions.add(c.getInstructions());
            }
            else {
                allInstructions.add(a.getInstructions());
            }
        }
        return allInstructions;
    }
}
