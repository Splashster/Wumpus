public class WumplusWorld
{
  private Map m;
  private Agent agent;
  private ScoringEngine se;
  private int score = 0;
  private MapNode[][] theWorld;
  private coordinate s;
  private coordinate w;
  private coordinate sup;
  private coordinate ag;
  private coordinate[] p;
  private coordinate g;
  private boolean inPit, ateByWumpus;

  public WumplusWorld()
  {
    agent = new Agent(false, false, false, false);
    se = new ScoringEngine();
  }

  public void generateMap()
  {
    //Set coordinates for User-specified attributes
    w = new coordinate(4,4);
    sup = new coordinate(6,4);
    ag = new coordinate (0,0);
    p = new coordinate[1];
    p[0] = new coordinate(5,5);
    g = new coordinate(9,9);
    //Create map with given attributes
    m = new Map(w, sup, g, p, ag);
    theWorld = m.getMap();
    //Print map layout
    m.print(0);
  }

  public MapNode move(int x, int y){
    //returns mapnode of current location
    return null;
  }

  public void moveAgent(int x, int y){
    inPit = false;
    ateByWumpus = false;
    agent.setAgentPosition(x, y);
    ag.setX(x);
    ag.setY(y);
    m = new Map(w, sup, g, p, ag);
    theWorld = m.getMap();
    isInPit(ag.getX(),ag.getY());
    wumpusAteHim(ag.getX(),ag.getY());
    score = se.scoreEvent(1);
    m.print(score);
    if(inPit){System.out.println("TARZAN FELL INTO THE PIT!!!!!!! GOOD THING HE CAN CLIMB OUT!");}
    if(ateByWumpus){System.out.println("THE WUMPUS ATE TARZAN!!!!!!! GOOD THING HE HAS EXTRA LIVES!");}
  }

  public coordinate getAgentPosition(){
    return agent.getAgentPosition();
  }

  //Gets the attributes for the node the Agent is currently in
  /*public String getPerception(int x, int y){
    return theWorld[x][y].getAttr();
  }*/

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

  public void killWumpus()
  {
    se.removeWumpus();
  }

  public void startGame()
  {

  }
}
