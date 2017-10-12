import java.util.Random;
import java.util.concurrent.TimeUnit;
/*********************************************
Main Driver for the Wumpus World Program
**********************************************/

public class WumpusDriver{
  public static void main(String[] args){
      System.out.println("The Wumpus Says COME TO MEEEEEE 😜");
      WumplusWorld ww = new WumplusWorld();
      ww.generateMap();
      System.out.println(ww.getPerception(9,6));
      int x, y;
      Random randomGenerator = new Random();
      System.out.println("Tarzan's Current Position: " + ww.getAgentPosition().getX() + "," + ww.getAgentPosition().getY());

      for(int i = 0; i < 9; i++){
         x = randomGenerator.nextInt(9)+1;
         y = randomGenerator.nextInt(9)+1;
         ww.moveAgent(x,y);
         System.out.println("Tarzan's Current Position: " + ww.getAgentPosition().getX() + "," +  ww.getAgentPosition().getY());
         try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}

      }
  }
}
