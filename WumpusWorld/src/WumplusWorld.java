import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class WumplusWorld
{
  private Map m;
  private Agent agent;
  private ScoringEngine se;
  private int score;
  private MapNode[][] theWorld;
  private coordinate s;
  private coordinate w;
  private coordinate sup;
  private coordinate ag;
  private coordinate[] p;
  private coordinate[] noPass;
  private coordinate g;
  private boolean hasGold;
  private boolean inPit, ateByWumpus, ateBySupmuw, fedBySupmuw, savedBySupmuw, gotTheGold;
  Random random = new Random();

  public WumplusWorld()
  {
    se = new ScoringEngine();
    hasGold = false;
  }

  public void generateMap(coordinate wumpus, coordinate supmuw, coordinate[] noPassing, coordinate[] pit, coordinate gold)
  {
    //Set coordinates for User-specified attributes
    //System.out.println("Inside genreate");
    w = wumpus;
    //System.out.println("wum done");
    sup = supmuw;
    //System.out.println("sup done");
    ag = new coordinate(0,0);
  //  System.out.println("ag done");
    p = pit;
    //System.out.println("pit done");
    noPass = noPassing;
    //System.out.println("pass done");
    g = gold;
    //System.out.println("gold done");
    //System.out.println("Print Inside genreate");
    //System.out.println("Wumpus " + wum.getX() + " " + wum.getY());
    //System.out.println("Sup " + sup.getX() + " " + sup.getY());
    //System.out.println("Gold location " + gold.getX() + " " + gold.getY());
    //System.out.println("Get Pit " + pit[0].getX() + " " + pit[0].getY());
    //System.out.println("Get Pass " + noPass[0].getX() + " " + noPass[0].getY());
    //Create map with given attributes
    m = new Map(w, sup, g, noPass, p, ag, hasGold);
    theWorld = m.getMap();
    //Print map layout
    m.print(0);
  }



/*  public ArrayList<coordinate> getChoices(int x, int y){
      coordinate left, right, up, down, move, previousMove;
      ArrayList<coordinate> directions = new ArrayList<coordinate>();

      if(x == 0 && y == 0){
        up = new coordinate(1,0);
        right = new coordinate (0,1);
        directions.add(up);
        directions.add(right);
      }else if(x == 0 && y != 9){
        up = new coordinate(x+1,y);
        right = new coordinate(0,y+1);
        left = new coordinate(x,y-1);
        directions.add(up);
        directions.add(right);
        directions.add(left);
      }else if(y == 0 && x != 9){
        up = new coordinate(x+1,y);
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        directions.add(up);
        directions.add(right);
        directions.add(down);
      }else if(x == 9 && y == 9){
        left = new coordinate(x,y-1);
        down = new coordinate(x-1,y);
        directions.add(left);
        directions.add(down);
      }else if(x == 9 && y == 0){
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        directions.add(right);
        directions.add(down);
      }else if(y == 9 && x == 0){
        up = new coordinate(x+1,y);
        left = new coordinate(x,y-1);
        directions.add(up);
        directions.add(left);
      }else if(x == 9 && y != 0){
        left = new coordinate(x,y-1);
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        directions.add(right);
        directions.add(down);
        directions.add(left);
      }else if(y == 9 && x!= 0){
        up = new coordinate(x+1,y);
        left = new coordinate(x,y-1);
        down = new coordinate(x-1,y);
        directions.add(up);
        directions.add(left);
        directions.add(down);
      }else{
        up = new coordinate(x+1,y);
        down = new coordinate(x-1,y);
        left = new coordinate(x,y-1);
        right = new coordinate(x,y+1);
        directions.add(up);
        directions.add(down);
        directions.add(left);
        directions.add(right);
      }
      for(int i = 0; i < directions.size(); i++){
        move = directions.get(i);
        System.out.println("Tarzan's choice: " + i + " " + move.getX() + "," + move.getY());
      }
      return directions;
  }*/

/*  public boolean beenThere(ArrayList<coordinate> previousMoves, coordinate move){
      boolean answer = false;
      coordinate previousMove;

      for(int i = 0; i < previousMoves.size(); i++){
         previousMove = previousMoves.get(i);
         System.out.println("Comparing move with Previous Move: " + previousMove.getX() + " " + previousMove.getY());
         if(move.getX() == previousMove.getX() && move.getY() == previousMove.getY()){
           answer = true;
           break;
         }
       }
       return answer;
     }*/

/*  public void getAgentNextMove(int x, int y, ArrayList<coordinate> previousMoves){
      coordinate move, previousMove;
      ArrayList<coordinate> directions = new ArrayList<coordinate>();
      boolean go = true;

      directions = getChoices(x,y);
      move = directions.get(random.nextInt(directions.size()));
    //  if(beenThere(previousMoves,move)){go = false;}

      while(!go){
            System.out.println("Been there");
            move = directions.get(random.nextInt(directions.size()));
            if(!beenThere(previousMoves,move)){go = true;}
        }
      moveAgent(move.getX(),move.getY());
  }*/


  public void moveAgent(int x, int y){
    inPit = false;
    ateByWumpus = false;
    ateBySupmuw = false;
    savedBySupmuw = false;
    fedBySupmuw = false;
    gotTheGold = false;
    ag = agent.getAgentPosition();
    m = new Map(w, sup, g, noPass, p, ag, hasGold);
    theWorld = m.getMap();
    isInPit(x,y);
    wumpusAteHim(x,y);
    inWithSupmuw(x,y);
    panForGold(x,y);
    score = se.scoreEvent(1);
    m.print(score);
    if(inPit){System.out.println("TARZAN FELL INTO THE PIT!!!!!!! GOOD THING HE CAN CLIMB OUT!");}
    if(ateByWumpus){System.out.println("THE WUMPUS ATE TARZAN!!!!!!! GOOD THING HE HAS EXTRA LIVES!");}
    if(ateBySupmuw){System.out.println("THE SUPMUW BETRAYED TARZAN AND ATE HIM!!!!!!! GET BACK OUT THERE TARZAN!");}
    if(savedBySupmuw){System.out.println("THE SUPMUW SAVED TARZAN FROM A FATAL FALL INTO THE PIT OF DEATH!!!!!");}
    if(fedBySupmuw){System.out.println("THE SUPMUW FED TARZAN CANDIES!!!!!!");}
    if(gotTheGold){System.out.println("TARZAN GOT THE GOLD!!! TIME TO GET OUT OF HERE!!!!!!");}
  }

  /*public coordinate getAgentPosition(){
    return agent.getAgentPosition();
  }*/

  //Gets the attributes for the node the Agent is currently in
  public MapNode getPerception(int x, int y){
      return theWorld[x][y];
  }


  public void isInPit(int x, int y){
    if(theWorld[x][y].getPit() && !theWorld[x][y].getSupmuw()){
      se.scoreEvent(3);
      inPit = true;
    }
  }

  public void wumpusAteHim(int x, int y){
    if(theWorld[x][y].getWumpus()){
      se.scoreEvent(3);
      ateByWumpus = true;
    }
  }

  public void inWithSupmuw(int x, int y){
    if(theWorld[x][y].getSupmuw()){
       if(actsAsWumpus(x,y) && !theWorld[x][y].getWumpus()){
          se.scoreEvent(3);
          ateBySupmuw = true;
       }else if(!actsAsWumpus(x,y) && !theWorld[x][y].getWumpus()){
         fedBySupmuw = true;
         se.scoreEvent(4);
       }else if(theWorld[x][y].getPit()){
         savedBySupmuw = true;
       }
    }
  }

  public boolean actsAsWumpus(int x, int y){
    coordinate left = null, right = null, up = null, down = null;
    boolean answer = false;

    if(x == 0 && y == 0){
      up = new coordinate(1,0);
      right = new coordinate (0,1);
    }else if(x == 0){
      up = new coordinate(1,y);
      right = new coordinate(0,y+1);
    }else if(y == 0){
      up = new coordinate(x+1,0);
      right = new coordinate(x,1);
    }else if(x == 9 && y == 9){
      left = new coordinate(x,y-1);
      down = new coordinate(x-1,y);
    }else if(x == 9){
      left = new coordinate(x,y-1);
      right = new coordinate(x,y+1);
      down = new coordinate(x-1,y);
    }else if(y == 9){
      up = new coordinate(x+1,y);
      down = new coordinate(x-1,y);
      left = new coordinate(x,y-1);
    }
    else{
      up = new coordinate(x+1,y);
      down = new coordinate(x-1,y);
      left = new coordinate(x,y-1);
      right = new coordinate(x,y+1);
    }

    if(up != null){
      if(theWorld[up.getX()][up.getY()].getStench()){
        answer = true;
      }
    }else if(down != null){
      if(theWorld[down.getX()][down.getY()].getStench()){
        answer = true;
      }
    }else if(left != null){
      if(theWorld[left.getX()][left.getY()].getStench()){
        answer = true;
      }
    }else if(right != null){
      if(theWorld[right.getX()][right.getY()].getStench()){
        answer = true;
      }
    }
    return answer;
  }


  public boolean panForGold(int x, int y){
      boolean answer = false;
      if(theWorld[x][y].getGold()){
        answer = true;
        gotTheGold = true;
        agent.setHasGold();
        hasGold = true;
      }
      return answer;
  }

  public void killWumpus()
  {
    se.removeWumpus();
  }

  public void setAgent(Agent ag){
    agent = ag;
  }

  public void startGame(coordinate w, coordinate sup, coordinate[] noPass, coordinate[] pit, coordinate gold){
    try{
      //System.out.println("CAlling genreate");
    //  System.out.println("Wumpus " + wum.getX() + " " + wum.getY());
    //  System.out.println("Sup " + supm.getX() + " " + supm.getY());
    //  System.out.println("Gold location " + gold.getX() + " " + gold.getY());
    //  System.out.println("Get Pit " + pit[0].getX() + " " + pit[0].getY());
    //  System.out.println("Get Pass " + noPassing[0].getX() + " " + noPassing[0].getY());
      generateMap(w, sup, noPass, pit, gold);
    }catch(Exception e){
        //System.out.println("Please set objects on the map!");

    }

    System.out.println("The Wumpus Says COME TO MEEEEEE");
    agent.goGoAgent();
    while(!agent.hasEscaped()){}
    /*ArrayList<coordinate> previousMoves = new ArrayList<coordinate>();
    int x, y, previousX = 0, previousY = 0, move_count = 40;
    coordinate previousMove;
    System.out.println("Tarzan's Current Position: " + getAgentPosition().getY() + "," + getAgentPosition().getX());

    for(int i = 0; i < move_count; i++){
      previousMove = new coordinate(previousX,previousY);
      previousMoves.add(previousMove);
      getAgentNextMove(previousX, previousY, previousMoves);
      System.out.println("Move Number: " + i);
      System.out.println("Tarzan's Current Position: " + getAgentPosition().getX() + "," +  getAgentPosition().getY());
      System.out.println("Tarzan's Previous Position: " + previousX + "," + previousY);
      try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
      previousX = getAgentPosition().getX();
      previousY = getAgentPosition().getY();
    }*/
    Menu menu = new Menu();
    menu.mainMenu();
  }
}
