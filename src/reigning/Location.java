package reigning;

import java.util.ArrayList;
import java.util.Arrays;

public class Location{
    private String name, flavorText, imgFileName;
    public ArrayList<Character> population;
    private boolean isLocked;
    private static ArrayList<Location> locationList = new ArrayList<Location>();

    //constructors
    public Location(String name){
      this.name = name;
      this.population = new ArrayList<>();
      this.flavorText = "A great location";
      this.imgFileName = "not.png";
      this.isLocked = false;
      locationList.add(this);
    }
    public Location(String name, String ifn, String ft){
      this.name = name;
      this.population = new ArrayList<>();
      this.flavorText = ft;
      this.imgFileName = ifn;
      this.isLocked = false;
      locationList.add(this);
    }
    
    //getters (instance and static)
    public String getName() {
      return name;
    }
    public String getFlavorText(){
        return flavorText;
    }
    public String getImgFileName(){
        return imgFileName;
    }
    public boolean getIsUnlocked(){
      return isLocked;
    }
    public ArrayList<Character> getPopulation(){
        return this.population;
    }
    public static ArrayList<Location> getLocationList() {
      return locationList;
    }
    
    //setters
        //stats
    public void setIsLocked(boolean b){
        this.isLocked = b;
    }
        //population
    public void add(Character c){
      this.population.add(c);
    }
    public void add(Character... list) {
        this.population.addAll(Arrays.asList(list));
    }
}