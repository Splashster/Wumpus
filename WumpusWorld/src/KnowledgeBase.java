/*
A lower hazard count represents a safe square to travel
to whereas a high hazard count represents a dangerous
square.
*/
public class KnowledgeBase
{
  private int loot;
  private int hazards;
  private boolean visited;

  public KnowledgeBase()
  {
    loot = 0;
    hazards = 0;
    visited = false;
  }

  public void incHazards() {hazards++;}
  public void decHazards() {hazards--;}

  public void incLoot() {loot++;}
  public void decLoot() {loot--;}

  public void visit() {visited=true;}
  public boolean visited() {return visited;}

  public int getHazards() {return hazards;}
  public int getLoot() {return loot;}

}
