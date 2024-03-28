package reigning;

public class Item{
    private String name, type, imgFileName;//3 types: equip, consume for yourself, throw for opponents
    private int duration;
    private double[] effects;

    //constructors
    public Item(String name){
        this.name = name;
        this.type = "equip";
        this.duration = -1;//-1 implies indefinite
        double[] e = {0,0,0,0,0};
        this.effects = e;
        this.imgFileName = "not.png";
    }
    public Item(String name, int duration){//duration implies consumable
        this.name = name;
        this.type = "consume";
        this.duration = duration;
        double[] e = {0,0,0,0,0};
        this.effects = e;
        this.imgFileName = "not.png";
    }
    public Item(String name, int duration, double hp, double maxHP, double atk, double def, int lvl){
        this.name = name;
        this.type = "consume";
        this.duration = duration;
        double[] e = {hp, maxHP, atk, def, (double) lvl};
        this.effects = e;
        this.imgFileName = "not.png";
    }
    public Item(String name, int duration, String ifn, double hp, double maxHP, double atk, double def, int lvl){
        this.name = name;
        this.type = "consume";
        this.duration = duration;
        double[] e = {hp, maxHP, atk, def, (double) lvl};
        this.effects = e;
        this.imgFileName = ifn;
    }

    //getters
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getImgFileName() {
        return imgFileName;
    }
    public int getDuration() {
        return duration;
    }
    public double[] getEffects() {
        return effects;
    }
    //setters
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setEffects(double[] effects){
        this.effects = effects;
    }
    public void setType(String t){
        type = t;
    }
    
    //methods
    public void use(Character user, Character victim){
        user.addStat(this.effects);
    }
    public int useAndGetDuration(Character c){
        c.addStat(this.effects);
        
        return this.duration;
    }
    public void executeBacklash(Character c){//backlash for items with duration
        double[] d = this.effects;
        double[] reversed = {(-d[0]), (-d[1]), (-d[2]), (-d[3]), (-d[4])};
        c.addStat(reversed);
    }
}