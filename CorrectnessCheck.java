import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CorrectnessCheck {

  public static String correctnessCheck(HashMap<Integer, List<String>> returns) {
    System.out.println(returns);
    HashMap<String, Integer> count = new HashMap<>(); // to count the colours
    String faces = "WOGRBY";
    for (int i = 0; i < 6; i++) {
      String s = returns.get(i).get(5);
      if (!s.equals(String.valueOf(faces.charAt(i)))) {
        return "Centres are not inputted correctly. Ensure the cube centres are inputted in the order of 'White, Orange, Green, Red, Blue, Yellow";
      }
      count.put(String.valueOf(faces.charAt(i)), 0);
    }


    for (int i = 0; i < 6; i++) {
      for (String s: returns.get(i)) {
        count.put(s, count.get(s) + 1);
      }
    }
    System.out.println(count);

    for (int v: count.values()) {
      if (v != 9) return "Too many instances of the same colour were inputted";
    }

    return null;
  }

}
