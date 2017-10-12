/*********************************************
Main Driver for the Wumpus World Program
**********************************************/

public class WumpusDriver{
  public static void main(String[] args){
      System.out.println("The Wumpus Says COME TO MEEEEEE");
      WumplusWorld ww = new WumplusWorld();
      ww.generateMap();
      //System.out.println(ww.getPerception(3, 3));
  }
}
