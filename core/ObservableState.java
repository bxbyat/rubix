package core;

import java.util.ArrayList;

public class ObservableState {

  private ArrayList<CorrectnessObserver> observers;

  public ObservableState(ArrayList<CorrectnessObserver> observers) {
    this.observers = observers;
  }

  public void subscribe(CorrectnessObserver o) {
    observers.add(o);
  }

  public void unsubsribe(CorrectnessObserver o) {
    observers.remove(o);
  }

  public void update() {
    for (CorrectnessObserver o: observers) {
      o.update();
    }
  }


}
