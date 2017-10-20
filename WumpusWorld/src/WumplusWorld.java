/***************************************************************************
The WumplusWorld class is the heart of the game. It generates the map,
the pieces, it triggers the scoring engine, and keeps track of the things going on
in the world that the agent may not already know.
***************************************************************************/
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
  private boolean hasGold, actAsWumpus, hasFood, nothingSafe, goingToEscape;
  private boolean inPit, ateByWumpus, ateBySupmuw, fedBySupmuw, savedBySupmuw, gotTheGold, wumpus_alive, displayWumpusDeath;

  public WumplusWorld()
  {
    se = new ScoringEngine();
    hasGold = false;
    actAsWumpus = false;
    hasFood = true;
    goingToEscape = false;
  }

  public void generateMap(coordinate wumpus, coordinate supmuw, coordinate[] noPassing, coordinate[] pit, coordinate gold)
  {
    //Set coordinates for User-specified attributes
    w = wumpus;
    sup = supmuw;
    ag = new coordinate(0,0);
    p = pit;
    noPass = noPassing;
    g = gold;
    if(w != null) {wumpus_alive=true;}
    else {wumpus_alive=false;}
    displayWumpusDeath = true;

    //Create map with given attributes
    m = new Map(w, sup, g, noPass, p, ag, hasGold, actAsWumpus, wumpus_alive, hasFood, nothingSafe, goingToEscape);
    theWorld = m.getMap();

    //Print map layout
    m.print(0);
  }


  //Called by the agent class. Uses the agents next move to
  //check and see if  the agent should gain points or lose points.
  //It also regenerates the map to update certain things on the map
  //based on what the agent did.
  public void moveAgent(int x, int y){
    inPit = false;
    ateByWumpus = false;
    ateBySupmuw = false;
    savedBySupmuw = false;
    fedBySupmuw = false;
    gotTheGold = false;
    nothingSafe = agent.noMoreMoves();
    goingToEscape = agent.shallEscape();
    ag = agent.getAgentPosition();
    m = new Map(w, sup, g, noPass, p, ag, hasGold, actAsWumpus, hasFood, wumpus_alive, nothingSafe, goingToEscape);
    theWorld = m.getMap();
    isInPit(x,y);
    wumpusAteHim(x,y);
    inWithSupmuw(x,y);
    panForGold(x,y);
    score = se.scoreEvent(1);
    m.print(score);
    System.out.print("\n\t\t\t   Tarzan's Current Position: " + ag.getX() + "," + ag.getY() + "\t\t\t");
    if(w!= null &&!wumpus_alive && displayWumpusDeath)
    {
      System.out.print("DING DONG THE WUMPUS IS DEAD!!!!");
      displayWumpusDeath = false;
    }
    if(inPit){System.out.print("TARZAN FELL INTO THE PIT!!!!!!! GOOD THING HE CAN CLIMB OUT!");}
    if(ateByWumpus){System.out.print("THE WUMPUS ATE TARZAN!!!!!!! GOOD THING HE HAS EXTRA LIVES!");}
    if(ateBySupmuw){System.out.print("THE SUPMUW BETRAYED TARZAN AND ATE HIM!!!!!!! GET BACK OUT THERE TARZAN!");}
    if(savedBySupmuw){System.out.print("THE SUPMUW SAVED TARZAN FROM A FATAL FALL INTO THE PIT OF DEATH!!!!!");}
    if(fedBySupmuw){System.out.print("THE SUPMUW FED TARZAN CANDIES!!!!!!");}
    if(gotTheGold){System.out.print("TARZAN GOT THE GOLD!!! TIME TO GET OUT OF HERE!!!!!!");}
  }

  //Gets the attributes for the node the Agent is currently in
  public MapNode getPerception(int x, int y){
      return theWorld[x][y];
  }

  //Check to see if supmuw is inside of a pit
  public void isInPit(int x, int y){
    if(theWorld[x][y].getPit() && !theWorld[x][y].getSupmuw()){
      se.scoreEvent(3);
      inPit = true;
    }
  }

  //Check to see if the wumpus ate the agent
  public void wumpusAteHim(int x, int y){
    if(theWorld[x][y].getWumpus()){
      se.scoreEvent(3);
      ateByWumpus = true;
    }
  }

  //Check to see if the agent in in the same node as the wumpus.
  //Also check to see how the supmuw should be acting and whether points
  //should be deducted or not.
  public void inWithSupmuw(int x, int y){
    if(theWorld[x][y].getSupmuw()){
       if(theWorld[x][y].getActAsWumpus() && !theWorld[x][y].getWumpus() && !theWorld[x][y].getPit()){
          se.scoreEvent(3);
          ateBySupmuw = true;
       }else if(!theWorld[x][y].getActAsWumpus() && !theWorld[x][y].getPit()){
         if(hasSomeFood(x,y)){
           fedBySupmuw = true;
           hasFood = false;
           se.scoreEvent(4);
         }
       }else if(theWorld[x][y].getPit()){
         savedBySupmuw = true;
       }
    }
  }

  //Check to see if the supmuw has any food.
  public boolean hasSomeFood(int x,int y){
    boolean answer = false;
    if(hasFood){
      answer = true;
    }
    return answer;
  }

  //Check to see if the supmuw should act like a wumpus.
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
      if(!theWorld[up.getX()][up.getY()].getPit() && (theWorld[up.getX()][up.getY()].getStench() || theWorld[up.getX()][up.getY()].getWumpus())){
        answer = true;
      }
    }
    if(down != null){
      if(!theWorld[down.getX()][down.getY()].getPit() && theWorld[down.getX()][down.getY()].getStench() || theWorld[down.getX()][down.getY()].getWumpus()){
        answer = true;
      }
    }
    if(left != null){
      if(!theWorld[left.getX()][left.getY()].getPit() && theWorld[left.getX()][left.getY()].getStench() || theWorld[left.getX()][left.getY()].getWumpus()){
        answer = true;
      }
    }
    if(right != null){
      if(!theWorld[right.getX()][right.getY()].getPit() && theWorld[right.getX()][right.getY()].getStench() || theWorld[right.getX()][right.getY()].getWumpus()){
        answer = true;
      }
    }
    return answer;

  }

  //Check to see if the the agent found the gold
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

  //Check to see if the agent killed the wumpus
  public void killWumpus(coordinate shoot)
  {
    se.scoreEvent(2);
    if(shoot.getX() == w.getX() && shoot.getY() == w.getY())
    {
      wumpus_alive = false;
      se.removeWumpus();
    }
  }

  //Set the agent
  public void setAgent(Agent ag){
    agent = ag;
  }

  public void startGame(coordinate w, coordinate sup, coordinate[] noPass, coordinate[] pit, coordinate gold){
    try{

      //Generate the WumplusWorld map
      generateMap(w, sup, noPass, pit, gold);

      //Regenerate the WumplusWorld map if the supmuw is acting like a wumpus
      if(actsAsWumpus(sup.getX(), sup.getY())){generateMap(w, sup, noPass, pit, gold);}
    }catch(Exception e){

    }
    System.out.println("The Wumpus Says COME TO MEEEEEE");
    agent.goGoAgent();
    while(!agent.hasEscaped()){}

    //Check to see if the agent left with the gold.
    //If so add 1000 points to agent score.
    if(agent.hasGold()){
      se.scoreEvent(5);
    }

    //Print closing title screen and quit game
    WumplusTitle wumpTitle = new WumplusTitle();
    wumpTitle.printClose();
    System.exit(0);
  }
}
