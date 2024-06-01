package reigning;

import java.util.ArrayList;

public class Move{
    private String name;
    private double damageMultiplier;
    private Move strongAgainst;
    private static ArrayList<Move> defaultMoves;

    //constructors
    public Move(String name, double damageMultiplier){
        this.name = name;
        this.damageMultiplier = damageMultiplier;
    }
    public Move(String name, double damageMultiplier, Move strongAgainst){
        this.name = name;
        this.damageMultiplier = damageMultiplier;
        this.strongAgainst = strongAgainst;
    }
    
    //setting and getting default moves
    public static ArrayList<Move> getDefaultMoves(){
        return defaultMoves;
    }
    public static void setDefaultMoves(ArrayList<Move> m){
        defaultMoves = m;
    }
    //getters
    public String getName() {
        return name;
    }
    public double getDamageMultiplier() {
        return damageMultiplier;
    }
    public Move getStrongAgainst() {
        return strongAgainst;
    }
    
    //setters
    public void setStrongAgainst(Move s) {
        this.strongAgainst = s;
    }
    
    //methods
    public void executeEffect(Character c){
        
    }
    public void effect(Character victim, Character attacker){
        double damage = -1 * this.getDamageMultiplier() * attacker.getAtk();
        //System.out.printf("%n%s used %s!%n, very effective! and did %f damage", attacker.getName(), this.name, -damage);//for testing
        victim.damage(damage);
    }
    public void tieEffect(Character victim, Character attacker){
        double damage = this.getDamageMultiplier() * attacker.getAtk() / -2;
        //System.out.printf("%n%s used %s!%n, half effective :/ and did %f damage", attacker.getName(), this.name, -damage);//for 
        victim.damage(damage);
    }
}