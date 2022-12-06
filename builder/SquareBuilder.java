package builder;
import core.*;

import java.awt.*;

public class SquareBuilder implements Builder{

    private Square result;

    public SquareBuilder(){
        this.result = new Square();
    }

    public void reset(){
        this.result.text = "";
        this.result.symbol = "";
        this.result.colour = null;
    }

    public void addText(String text){
        this.result.text = text;
    }

    public void addSymbol(String symbol){
        this.result.symbol = symbol;
    }

    public void addColour(Color colour){
        this.result.colour = colour;
    }

    public Square getResult(){
        return this.result;
    }
}
