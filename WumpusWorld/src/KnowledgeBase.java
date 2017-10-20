/***********************************************************************
KnowledgeBase stores information about each square that the agent knows.
As the agent learns more about the board, its knowledgeBase expands
************************************************************************/
public class KnowledgeBase
{
  //Hazard counter
  private int hazards;

  //Shows whether or not object is in particular node
  private boolean visited;
  private boolean stench;
  private boolean moo;
  private boolean breeze;
  private boolean locked;

  public KnowledgeBase()
  {
    hazards = 0;
    visited = false;
    stench = false;
    moo = false;
    breeze = false;
    locked = false;
  }

  //Increase and decrease hazard counter
  public void incHazards() {if(!locked) {hazards++;}}
  public void decHazards() {if(!locked) {hazards--;}}

  //mark knowledgeBase node as visited
  public void visit() {visited=true;}
  //Check if node has been visited
  public boolean visited() {return visited;}

  //return hazard counter
  public int getHazards() {return hazards;}

  //Setter functions
  public void setStench(boolean b) {stench=b;}
  public void setMoo(boolean b) {moo=b;}
  public void setBreeze(boolean b) {breeze=b;}

  //Getter functions
  public boolean getStench() {return stench;}
  public boolean getMoo() {return moo;}
  public boolean getBreeze() {return breeze;}

  //Set hazard counter to zero (visited) and lock counter
  public void resetHazards()
  {
    hazards = 0;
    locked = true;
  }

  //Refactor hazard counter upon death of the Wumpus
  public void wumpusDied() {hazards = 0;}


}
