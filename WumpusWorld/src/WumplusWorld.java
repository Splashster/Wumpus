public class WumplusWorld
{
  private Map m;
  private Agent agent;
  private ScoringEngine se;
  private MapNode[][] theWorld;

  public WumplusWorld()
  {
    agent = new Agent(false, false, false, false);
    se = new ScoringEngine();
  }

  public void generateMap()
  {
    //Set coordinates for User-specified attributes
    coordinate s = new coordinate(0,0);
    coordinate w = new coordinate(9,6);
    coordinate sup = new coordinate(1,5);
    coordinate[] p = new coordinate[3];
    p[0] = new coordinate(4,3);
    p[1] = new coordinate(6,0);
    p[2] = new coordinate(2,3);
    coordinate g = new coordinate(9,9);
    //Create map with given attributes
    m = new Map(s, w, sup, g, p);
    theWorld = m.getMap();
    //Print map layout
    m.print();
  }

  public MapNode move(int x, int y){
    //returns mapnode of current location
    return null;
  }

  public void moveAgent(int x, int y){
    agent.setAgentPosition(x, y);
  }

  //Gets the attributes for the node the Agent is currently in
  public String getPerception(int x, int y){
    return theWorld[x][y].getAttr();
  }

  public void killWumpus()
  {
    se.removeWumpus();
  }

  public void startGame()
  {

  }
}
