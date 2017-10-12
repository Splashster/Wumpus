public class WumplusWorld
{
  private Map m;
  private Agent agent;
  private ScoringEngine se;
  private MapNode[][] theWorld;
  private coordinate s;
  private coordinate w;
  private coordinate sup;
  private coordinate ag;
  private coordinate[] p;
  private coordinate g;

  public WumplusWorld()
  {
    agent = new Agent(false, false, false, false);
    se = new ScoringEngine();
  }

  public void generateMap()
  {
    //Set coordinates for User-specified attributes
    s = new coordinate(0,0);
    w = new coordinate(2,3);
    sup = new coordinate(1,5);
    ag = new coordinate (0,0);
    p = new coordinate[3];
    p[0] = new coordinate(4,3);
    p[1] = new coordinate(6,0);
    p[2] = new coordinate(6,6);
    g = new coordinate(9,9);
    //Create map with given attributes
    m = new Map(s, w, sup, g, p, ag);
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
    ag.setX(x);
    ag.setY(y);
    m = new Map(s, w, sup, g, p, ag);
    theWorld = m.getMap();
    m.print();
  }

  public coordinate getAgentPosition(){
    return agent.getAgentPosition();
  }

  //Gets the attributes for the node the Agent is currently in
  /*public String getPerception(int x, int y){
    return theWorld[x][y].getAttr();
  }*/

  public void killWumpus()
  {
    se.removeWumpus();
  }

  public void startGame()
  {

  }
}
