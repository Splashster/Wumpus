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
  private boolean wumpus;
  private boolean stench;
  private boolean supmuw;
  private boolean moo;
  private boolean pit;
  private boolean breeze;
  private boolean gold;
  private boolean glitter;
  private boolean locked;

  public KnowledgeBase()
  {
    loot = 0;
    hazards = 0;
    visited = false;
    wumpus = false;
    stench = false;
    supmuw = false;
    moo = false;
    pit = false;
    breeze = false;
    gold = false;
    glitter = false;
    locked = false;
  }

  public void incHazards() {if(!locked) {hazards++;}}
  public void decHazards() {if(!locked) {hazards--;}}

  public void incLoot() {loot++;}
  public void decLoot() {loot--;}

  public void visit() {visited=true;}
  public boolean visited() {return visited;}

  public int getHazards() {return hazards;}
  public int getLoot() {return loot;}

  public void setWumpus(boolean b) {wumpus=b;}
  public void setStench(boolean b) {stench=b;}
  public void setSupmuw(boolean b) {supmuw=b;}
  public void setMoo(boolean b) {moo=b;}
  public void setPit(boolean b) {pit=b;}
  public void setBreeze(boolean b) {breeze=b;}
  public void setGold(boolean b) {gold=b;}
  public void setGlitter(boolean b) {glitter=b;}

  public boolean getWumpus() {return wumpus;}
  public boolean getStench() {return stench;}
  public boolean getSupmuw() {return supmuw;}
  public boolean getMoo() {return moo;}
  public boolean getPit() {return pit;}
  public boolean getBreeze() {return breeze;}
  public boolean getGold() {return gold;}
  public boolean getGlitter() {return glitter;}

  public void resetHazards()
  {
    hazards = 0;
    locked = true;
  }


}
