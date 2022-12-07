package core;
import components.*;
import java.util.Random;

/**
 * Class containing all the methods to solve the cube
 */
public class Solve {

    //Instructions to be eventually returned
    private String instructions;

    //Solve constructor
    public Solve(Cube cube){
        this.instructions = randomInstructions();
    }

    //Returns randomly generated instructions for development purposes
    private String randomInstructions(){
        String s = "";

        Random random = new Random();
        int length = random.nextInt(21)+10;
        String moves[] = {"U", "R", "L", "F", "B", "D"};

        for(int i = 0; i < length; i++){
            int move = random.nextInt(moves.length);
            int rotations = random.nextInt(5)+1;
            boolean inverted = random.nextBoolean();
            s += moves[move];

            if (rotations != 1) s += Integer.toString(rotations);
            if (inverted) s += "'";
            if (i != length-1) s += " ";
        }
        return s;
    }

    //Returns instructions
    public String getInstructions(){
        return this.instructions;
    }
}
