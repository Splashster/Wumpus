import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
/*********************************************
Main Driver for the Wumpus World Program
**********************************************/

public class WumpusDriver{
  public static void main(String[] args){
      System.out.println("The Wumpus Says COME TO MEEEEEE");
      WumplusWorld ww = new WumplusWorld();
      ww.generateMap();
      ArrayList<coordinate> previousMoves = new ArrayList<coordinate>();
      int x, y, previousX = 0, previousY = 0, move_count = 40;
      coordinate previousMove;
      System.out.println("Tarzan's Current Position: " + ww.getAgentPosition().getY() + "," + ww.getAgentPosition().getX());

      for(int i = 0; i < move_count; i++){
        previousMove = new coordinate(previousX,previousY);
        previousMoves.add(previousMove);
        moveAgent(previousX, previousY, previousMoves, ww);
        System.out.println("Move Number: " + i);
        System.out.println("Tarzan's Current Position: " + ww.getAgentPosition().getX() + "," +  ww.getAgentPosition().getY());
        System.out.println("Tarzan's Previous Position: " + previousX + "," + previousY);
        try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
        previousX = ww.getAgentPosition().getX();
        previousY = ww.getAgentPosition().getY();
      }
  }
  public static void moveAgent(int x, int y, ArrayList<coordinate> previousMoves, WumplusWorld ww){
    coordinate left, right, up, down, move, previousMove;
    boolean go = true;
    ArrayList<coordinate> directions = new ArrayList<coordinate>();
    Random random = new Random();

    if(x == 0 && y == 0){
      up = new coordinate(1,0);
      right = new coordinate (0,1);
      directions.add(up);
      directions.add(right);
    }else if(x == 0){
      up = new coordinate(1,y);
      right = new coordinate(0,y+1);
      directions.add(up);
      directions.add(right);
    }else if(y == 0){
      up = new coordinate(x+1,0);
      right = new coordinate(x,1);
      directions.add(up);
      directions.add(right);
    }else if(x == 9 && y == 9){
      left = new coordinate(x,y-1);
      down = new coordinate(x-1,y);
      directions.add(left);
      directions.add(down);
    }else if(x == 9){
      left = new coordinate(x,y-1);
      right = new coordinate(x,y+1);
      down = new coordinate(x-1,y);
      directions.add(left);
      directions.add(right);
      directions.add(down);
    }else if(y == 9){
      up = new coordinate(x+1,y);
      down = new coordinate(x-1,y);
      left = new coordinate(x,y-1);
      directions.add(up);
      directions.add(down);
      directions.add(left);
    }
    else{
      up = new coordinate(x+1,y);
      down = new coordinate(x-1,y);
      left = new coordinate(x,y-1);
      right = new coordinate(x,y+1);
      directions.add(up);
      directions.add(down);
      directions.add(left);
      directions.add(right);
    }

    move = directions.get(random.nextInt(directions.size()));

    for(int i = 0; i < previousMoves.size(); i++){
      previousMove = previousMoves.get(i);
      if(move.getX() == previousMove.getX() && move.getY() == previousMove.getY()){
        go = false;
        moveAgent(x,y,previousMoves,ww);
      }
    }
    if(go){ww.moveAgent(move.getX(),move.getY());}

  }
}
