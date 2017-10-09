public class MapNode
{
  private MapNode northNeighbor;
  private MapNode southNeighbor;
  private MapNode eastNeighbor;
  private MapNode westNeighbor;

  private boolean wumpus;
  private boolean stench;
  private boolean pit;
  private boolean breeze;
  private boolean gold;
  private boolean glitter;
  private boolean start;
  private boolean supmuw;
  private boolean moo;
  private boolean noTrespassing;

  MapNode()
  {
    wumpus = false;
    stench = false;
    pit = false;
    breeze = false;
    gold = false;
    glitter = false;
    start = false;
    supmuw = false;
    moo = false;
    noTrespassing = false;
  }

  void setNorthNeighbor(MapNode north) {northNeighbor = north;}
  void setSouthNeighbor(MapNode south) {southNeighbor = south;}
  void setEastNeighbor(MapNode east) {eastNeighbor = east;}
  void setWestNeighbor(MapNode west) {westNeighbor = west;}

  void setWumpus()
  {
    wumpus=true;
    if(northNeighbor != null) {northNeighbor.setStench();}
    if(southNeighbor != null) {southNeighbor.setStench();}
    if(eastNeighbor != null) {eastNeighbor.setStench();}
    if(westNeighbor != null) {westNeighbor.setStench();} 
  }
  void setStench() {stench=true;}

  void setPit()
  {
    pit=true;
    if(northNeighbor != null) {northNeighbor.setBreeze();}
    if(southNeighbor != null) {southNeighbor.setBreeze();}
    if(eastNeighbor != null) {eastNeighbor.setBreeze();}
    if(westNeighbor != null) {westNeighbor.setBreeze();}
  }
  void setBreeze() {breeze=true;}

  void setGold()
  {
    gold=true;
    if(northNeighbor != null) {northNeighbor.setGlitter();}
    if(southNeighbor != null) {southNeighbor.setGlitter();}
    if(eastNeighbor != null) {eastNeighbor.setGlitter();}
    if(westNeighbor != null) {westNeighbor.setGlitter();}
  }
  void setGlitter() {glitter=true;}

  void setStart() {start=true;}

  void setSupmuw()
  {
    supmuw=true;
    if(northNeighbor != null) {northNeighbor.setMoo();}
    if(southNeighbor != null) {southNeighbor.setMoo();}
    if(eastNeighbor != null) {eastNeighbor.setMoo();}
    if(westNeighbor != null) {westNeighbor.setMoo();}
  }
  void setMoo() {moo=true;}

  void setNoTrespassing() {noTrespassing=true;}

  boolean getWumpus() {return wumpus;}
  boolean getStench() {return stench;}
  boolean getPit() {return pit;}
  boolean getBreeze() {return breeze;}
  boolean getGold() {return gold;}
  boolean getGlitter() {return glitter;}
  boolean getStart() {return start;}
  boolean getSupmuw() {return supmuw;}
  boolean getMoo() {return moo;}
  boolean getNoTrespassing() {return noTrespassing;}
}
