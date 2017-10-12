public class Agent
{
  //Agent's current position
  private coordinate current_position;

  //Agent's previous position
  private coordinate previous_position;

  //Does the agent have an arrow
  private boolean hasArrow;

  //Is the wumpus alive
  private boolean wumpusAlive;

  //Possible location of wumpus
  private coordinate wumpusPosition;

  //The agent's knowledgeBase
  //private KnowledgeBase KB;

  //Does the agent smell the Wumpus
  private boolean canSmell;

  //Does the agent feel the breeze
  private boolean feelsBreeze;

  //Does the agent hear the supmuw
  private boolean canHear;

  //Does the agent see the glitter from the gold
  private boolean seeGlitter;

  private WumplusWorld theWorld;

  Agent(boolean smell, boolean breeze, boolean hear, boolean glitter){
    this.current_position = new coordinate (0,0);
    this.previous_position = new coordinate (0,0);
    this.hasArrow = true;
    this.wumpusAlive = true;
    this.canSmell = smell;
    this.feelsBreeze = breeze;
    this.canHear = hear;
    this.seeGlitter = glitter;
  }

  public void setAgentPosition(int x, int y){
     previous_position = current_position;
     current_position = new coordinate (x,y);
  }

  public coordinate getAgentPosition(){
    //String position = "";
    //position += current_position.getX() + ", " + current_position.getY();
    return current_position;
  }

}
