import java.util.ArrayList;

public class Character{
  private String name;
  private double hp, atk, maxHP;
  private ArrayList<String> dialog;
  private ArrayList<Item> inventory;
  private ArrayList<Move> attackPattern;
  private boolean isBoss;


  public String getName() {
    return name;
  }
  public double getMaxHP() {
    return maxHP;
  }
  public double getHP() {
    return hp;
  }
  public double getAtk() {
    return atk;
  }
  public boolean getIsBoss(){
    return isBoss;
  }

  public Character(String name, double hp, double atk){
    this.name = name;
    this.hp = hp;
    this.maxHP = hp;
    this.atk = atk;
    this.isBoss = false;
  }
  public void addHP(double h){
    this.hp+=h;
  }
  public void addAtk(double h){
    this.atk+=h;
  }
  
  public void performMove(){
    
  }
  /*public Move getMove(int turn){
    return
  }*/
  public void getItem(Item i){
    
  }
  public void useItem(Item i){

  }
}