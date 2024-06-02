package reigning;

import java.util.ArrayList;
import java.util.Arrays;

public class Location{
    private String name, flavorText, imgFileName;
    private ArrayList<Character> population;
    private boolean isLocked;
    private static ArrayList<Location> locationList = new ArrayList<Location>();

    //constructors
    public Location(String name){
      this.name = name;
      this.population = new ArrayList<>();
      this.flavorText = "A great location";
      this.imgFileName = "not.png";
      this.isLocked = true;
      locationList.add(this);
    }
    public Location(String name, String ifn, String ft){
      this.name = name;
      this.population = new ArrayList<>();
      this.flavorText = ft;
      this.imgFileName = ifn;
      this.isLocked = true;
      locationList.add(this);
      locationList.get(0).setIsLocked(false);
    }
    public Location(String name, String ft){
      this.name = name;
      this.population = new ArrayList<>();
      this.flavorText = ft;
      this.imgFileName = name.toLowerCase();
      this.isLocked = true;
      locationList.add(this);
      locationList.get(0).setIsLocked(false);
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
    public boolean getIsLocked(){
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
    public void setImgFileName(String ifn){
        imgFileName = ifn;
    }
    public void setFlavorText(String f){
        flavorText = f;
    }
        //population
    public void add(Character c){
      this.population.add(c);
    }
    public void add(Character... list) {
        this.population.addAll(Arrays.asList(list));
    }
}