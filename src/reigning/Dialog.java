package reigning;

import java.util.ArrayList;
import java.util.Arrays;

public class Dialog {
    private String content, function, reply; // reply is reply of the npc, function = " " if no function
    private double[] effects;
    private ArrayList<Dialog> branch;
    private Character speaker;
    private Dialog attackedDialog = null;

    //Constructors
    public Dialog(String e, double[] d, String c, String r){
        content = c;
        function = e;
        reply = r;
        effects = d;
        branch = new ArrayList<>();
    }
    public Dialog(String e, double[] d, String c){
        content = c;
        function = e;
        reply = "";
        effects = d;
        branch = new ArrayList<>();
    }
    public Dialog(String c, String e){
        content = c;
        function = e;
        reply = "";
        double[] d = {0,0,0,0,0};
        effects = d;
        branch = new ArrayList<>();
    }
    public Dialog(String c){
        reply = "";
        content = c;
        reply = "";
        function = " ";
        double[] d = {0,0,0,0,0};
        effects = d;
        branch = new ArrayList<>();
    }

    //setters
    public void setSpeaker(Character c){
        this.speaker = c;
    }
    public void addBranch(Dialog d){
        branch.add(d);
    }
    public void addBranch(Dialog... list){
        this.branch.addAll(Arrays.asList(list));
    }
    public void setAttackedDialog(Dialog d){
        attackedDialog = d;
    }
    
    //getters
    public String getContent(){
        return content;
    }
    public String getFunction(){
        return function;
    }
    public String getReply(){
        return reply;
    }
    public ArrayList<Dialog> getBranch() {
        return branch;
    }
    public double[] getEffects(){
        return effects;
    }
    public Dialog getAttackedDialog(){
        return attackedDialog;
    }
}
