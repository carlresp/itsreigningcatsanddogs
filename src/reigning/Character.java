package reigning;

import java.util.ArrayList;
import java.util.Arrays;

public class Character{
    
    private static Character user;
    private static ArrayList<Character> relevant = new ArrayList<>();
    
    private String name, imgFileName;
    private double hp, maxHP, atk, def;
    private int lvl;
    private boolean isBoss, isLocked, isMonument;
    private ArrayList<Item> inventory;
    private ArrayList<Integer> amountIndex;
    private ArrayList<Move> attackPattern;
    private Dialog initialDialog, attackedDialog = null;

    //constructors
    public Character(String name, double hp, double atk, double def){
        //stats
        this.name = name;
        this.hp = hp;
        this.maxHP = hp;
        this.atk = atk;
        this.def = def;
        this.lvl = 1;
        this.isBoss = false;
        this.isLocked = false;
        this.isMonument = false;
        //image
        this.imgFileName = name.toLowerCase();
        //inventory
        this.inventory = new ArrayList<>();
        this.amountIndex = new ArrayList<>();
        //moves
        this.attackPattern = new ArrayList<>();
        //dialog
        double[] d = {};
        this.initialDialog = new Dialog("default default");
    }
    public Character(String name, double hp, double atk, double def, String ifn){
        //stats
        this.name = name;
        this.hp = hp;
        this.maxHP = hp;
        this.atk = atk;
        this.def = def;
        this.lvl = 1;
        this.isBoss = false;
        this.isLocked = false;
        this.isMonument = false;
        //image
        this.imgFileName = ifn;
        //inventory
        this.inventory = new ArrayList<>();
        this.amountIndex = new ArrayList<>();
        //moves
        this.attackPattern = new ArrayList<>();
        //dialog
        double[] d = {};
        this.initialDialog = new Dialog("default default");
    }
    /*public Character(String name, double hp, double atk, double def){
        //stats
        this.name = name;
        this.hp = hp;
        this.maxHP = hp;
        this.atk = atk;
        this.def = def;
        this.lvl = 1;
        this.isBoss = false;
        this.isLocked = false;
        this.isMonument = false;
        //image
        this.imgFileName = "not.png";
        //inventory
        this.inventory = new ArrayList<>();
        this.amountIndex = new ArrayList<>();
        //moves
        this.attackPattern = new ArrayList<>();
        //dialog
        double[] d = {};
        this.initialDialog = new Dialog("default default");
    }*/
        //monument special case
    public Character(String name, String ifn){
        //monuments dont really have special abilities, we're relying on the creation and use of Dialogs for the 
        //user to balance it out; also since we dont have anything that tracks the user's previously opened character,
        //the monument's user effect must be automatically reusable, and so this only really works on HP :/
        //stats
        this.name = name;
        this.hp = 1;
        this.maxHP = 1;
        this.atk = 1;
        this.def = 1;
        this.lvl = 1;
        this.isBoss = false;
        this.isLocked = false;
        this.isMonument = true;
        //image
        this.imgFileName = ifn;
        //inventory
        this.inventory = new ArrayList<>();
        this.amountIndex = new ArrayList<>();
        //moves
        this.attackPattern = new ArrayList<>();
        //dialog
        double[] d = {};
        this.initialDialog = new Dialog("default default");
    }
    
    //getters
        //stats
    public String getName() {
        return name;
    }
    public double getHP() {
        return hp;
    }
    public double getMaxHP() {
        return maxHP;
    }
    public double getAtk() {
        return atk;
    }
    public double getDef() {
        return def;
    }
    public int getLvl(){
        return lvl;
    }
    public boolean getIsBoss(){
        return isBoss;
    }
    public boolean getIsLocked(){
        return isLocked;
    }
        //static
    public static Character getUser(){
        return user;
    }
    public static ArrayList<Character> getRelevantCharacters(){
        return relevant;
    }
        //image icon
    public String getImgFileName(){
        return imgFileName;
    }
        //items
    public ArrayList<Item> getInventory(){
        return inventory;
    }
    public Item getItemByIndex(int index){
        return inventory.get(index);
    }
    public Item getItemByName(String name){
        for(Item i : inventory){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }
    public int getAmount(Item j){
        int amt, index;
        
        index = inventory.indexOf(j);
        amt = amountIndex.get(index);
        
        return amt;
    }
        //moves
    public ArrayList<Move> getAttackPattern(){
        return attackPattern;
    }
    public Move getMove(int turn){
        int size = attackPattern.size();
        int t = turn/size;
        t = turn - (t*size);
        return attackPattern.get(t);
    }
        //dialog
    public Dialog getDialog(){
        return initialDialog;
    }
    public Dialog getAttackedDialog(){
        return attackedDialog;
    }
        //monument
    public boolean getIsMonument(){
        return isMonument;
    }

    //setters
        //stats
    public void addStat(double[] i){
        this.addMaxHP(i[1]);
        this.addHP(i[0]);
        this.atk += i[2];
        this.def += i[3];
        this.lvlUp((int)i[4]);
    }
    public void addHP(double h){
        double health = this.hp + h;
        if(health>this.maxHP) health = maxHP;
        if(health<0) health = 0;
        this.hp = health;
    }
    public void addMaxHP(double h){
        this.maxHP += h;
        addHP(0);
    }
    public void setIsLocked(boolean il){
        this.isLocked = il;
    }
    public void setIsBoss(boolean ib){
        this.isBoss = ib;
    }
        //static
    public static void setUser(Character u){
        user = u;
    }
    public static void addRelevantCharacter(Character c){
        relevant.add(c);
    }
        //moves
    public void addMove(Move m){
        this.attackPattern.add(m);
    }
    public void addMove(Move... list){
        this.attackPattern.addAll(Arrays.asList(list));
    }
    public void clearMoves(){
        this.attackPattern.clear();
    }
        //dialog
    public void setDialog(Dialog d){
        this.initialDialog = d;
        d.setSpeaker(this);
        /*initial dialog essentially
        has the first set of dialog choices (context: Dialog 
        instances has an array of Dialogs for choices)*/
    }
    public void setAttackedDialog(Dialog d){
        this.attackedDialog = d;
    }
        //items
    public void addItem(Item i){
        if(inventory.contains(i)){
            int index = inventory.indexOf(i);
            int currVal = amountIndex.get(index);
            amountIndex.set(index, currVal + 1);
        }
        else{
            this.inventory.add(i);
            amountIndex.add(1);
        }
    }
    public void addItem(Item... list){
        for(int i = 0; i<list.length;i++){
            addItem(list[i]);
        }
    }
    public void addItem(Item i, int qty){
        for(int j = 0; j<qty; j++){
            addItem(i);
        }
    }

    //methods
        //xp and leveling up
    public void lvlUp(int l){
        //method for leveling up
        this.lvl += l;
    }
        //items
    public void useItem(Item i){

    }
    public void giveItem(int index, Character receiver){
        receiver.addItem(this.getItemByIndex(index));
    }
    public void giveItem(int index, Character receiver, int qty){
        for(int i = 0; i<qty; i++){
            receiver.addItem(this.getItemByIndex(index));
        }
    }
    public void removeItem(int index){
        try{
            inventory.remove(index);
            amountIndex.remove(index);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("index out of bounds");
        }
    }
    public void removeItem(Item i){
        try{
            int index = inventory.indexOf(i);
            if(amountIndex.get(index)>1){
                int currVal = amountIndex.get(index);
                amountIndex.set(index, currVal-1);
            }
            else{
                inventory.remove(index);
                amountIndex.remove(index);
            }
        }
        catch(Exception e){
            System.out.println("item not found");
        }
    }
        //attack
    //0 still fighting, 1 user won, 2 user lost
    public void damage(double dmg){
        addHP(dmg);
        //dialog changes to being damaged
    }
    public static int getResult(Character user, Character opp){
        if(isDead(opp)){//user won
            user.addHP(1);//in case of tie, user wins
            return 1;
        }
        if(isDead(user)) return 2;//user lost
        return 0;//still fighting
    }
    public static void commenceTurn(Character user, Character opp, int turn){
        Move u = user.getMove(0);
        Move o = opp.getMove(turn);
        
        if(u.getStrongAgainst() == o){
            u.effect(opp, user);
        }
        else if(o.getStrongAgainst() == u){
            o.effect(user, opp);
        }
        else{
            u.tieEffect(opp, user);
            o.tieEffect(user, opp);
        }
    }
    public static boolean isDead(Character c){
        if(c.getHP()==0.0) return true;
        else return false;
    }
}