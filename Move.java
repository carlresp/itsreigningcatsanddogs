public class Move{
  private String name;
  private double damageMultiplier;
  private Move strongAgainst;

  public String getName() {
    return name;
  }
  public double getDamageMultiplier() {
    return damageMultiplier;
  }
  public Move getStrongAgainst() {
    return strongAgainst;
  }
  public Move(String name, double damageMultiplier){
    this.name = name;
    this.damageMultiplier = damageMultiplier;
  }
  public Move(String name, double damageMultiplier, Move strongAgainst){
    this.name = name;
    this.damageMultiplier = damageMultiplier;
    this.strongAgainst = strongAgainst;
  }
  public void setStrongAgainst(Move s) {
    this.strongAgainst = s;
  }
  public void executeEffect(Character c){
    
  }
}