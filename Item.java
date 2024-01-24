import java.util.ArrayList;

public class Item{
  private String name;
  private int duration;
  private ArrayList<Integer> effects;

  public String getName() {
    return name;
  }
  public int getDuration() {
    return duration;
  }
  public ArrayList<Integer> effects() {
    return effects;
  }
  public Item(String name, int duration, int hp, int atk){
    this.name = name;
    this.duration = duration;
    this.effects = new ArrayList<Integer>();
    this.effects.add(hp);
    this.effects.add(atk);
  }
}