package builder;
import java.awt.*;

/**
 * Builder Interface
 */
public interface Builder {
    public void reset();
    public void addText(String text);
    public void addSymbol(String symbol);
    public void addColour(Color colour);
}
