// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

class Main {
  public static void main(String[] args) {
    //SCENARIO 1: Opening map , and character
      //start game 
      Location currentLoc = null;
      Character currentOpp = null;
    
      Location qc = new Location("QC");
      Character john = new Character("John", 1.0, 1.0); 
      qc.add(john);
    
      Location manila = new Location("Manila");
    
      qc.unlock();

      //display all loc options
      /*System.out.println(qc.getName());
      System.out.println(manila.getName());*/
      //user input: pick qc as location option
      currentLoc = qc;

      //display qc's options
      /*System.out.println(john.getName());
      System.out.println("healing station");
      System.out.println("back");*/
      //user input: pick john as Character option
      currentOpp = john;

    //SCENARIO 2: Fight, losing
      Move pMove, oMove; //pMove = player move, oMove = opponent move
      Move sA = new Move("strongAttack", 2.0);
      Move fA = new Move("fastAttack", 1.0, sA);
      Move b = new Move("block", 1.0, fA);
      sA.setStrongAgainst(fA);
      Character user = new Character("user", 1.0, 1.0);
      currentOpp = john; //fight with John begins

      //user input: use Move: strongAttack
      pMove = sA;
      //obtained from opponents atkPattern (currentOpp.getMove()): use Move: fastAttack
      oMove = fA;
      //fastAttack is stronger against strongAttack, so opponent's atk * fastAttack's multiplier will be done on user (currentOpp.performMove(user)):
      user.addHP(-3.0);

      //user health is zero, so:
      //John wins!
      //fight over 
    //SCENARIO 3: Fight, using item, win
        Item strPot = new Item("Strength potion", 1, 0, 3);
        Item hPot = new Item("Health potion", 1, 3, 0);

        currentOpp = john;

        //user input: open Inventory
        //display Inventory
        /*System.out.println(strPot.getName());
        System.out.println(strPot.getName());*/
        //user input: use Item: strength potion
        user.addAtk(3.0);
        //user.getInventory().remove(strPot);

        //user input: use Move: strongAttack
        pMove = sA;
        //obtained from opponent's atkPattern: uses Move: block
        oMove = fA;
        //strongAttack is stronger against block, so 4 * 2 = 8:
        currentOpp.addHP(-8.0);

        //opponent health is zero, so:
        //User wins!
        //fight over
      }
    }