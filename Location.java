import java.util.ArrayList;
public class Location{
  private String name;
  private ArrayList<Character> population;
  private boolean isUnlocked;
  private static ArrayList<Location> map = new ArrayList<Location>();
  
  public Location(String name){
    this.name = name;
    this.population = new ArrayList<Character>();
    
  }
  public String getName() {
    return name;
  }
  public boolean getIsUnlocked(){
    return isUnlocked;
  }
  public ArrayList<Location> map() {
    return map;
  }
  public void add(Character c){
    this.population.add(c);
  }
  public void unlock(){
    
  }
}