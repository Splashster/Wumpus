import java.util.Random;
/*********************************************
Main Driver for the Wumpus World Program
**********************************************/

public class WumpusDriver{
  public static void main(String[] args){
      System.out.println("The Wumpus Says COME TO MEEEEEE");
      WumplusWorld ww = new WumplusWorld();
      ww.generateMap();
      System.out.println(ww.getPerception(9,6));
      int x, y;
      Random randomGenerator = new Random();

      for(int i = 1; i <= 10; i++){
         x = randomGenerator.nextInt(10)+1;
         y = randomGenerator.nextInt(10)+1;
         ww.moveAgent(x,y);
      }
  }
}
