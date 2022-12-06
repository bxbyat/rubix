import components.Cube;
import core.Solve;
import org.junit.jupiter.api.Test;

public class SolveTests {

    @Test
    void randomInstructions(){
        Cube cube = new Cube();
        Solve solve = new Solve(cube);
        System.out.println(solve.getInstructions());
    }
}
