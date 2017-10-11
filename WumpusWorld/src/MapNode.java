public class MapNode
{
  //Define neighboring nodes
  private MapNode northNeighbor;
  private MapNode southNeighbor;
  private MapNode eastNeighbor;
  private MapNode westNeighbor;

  //Define all posssible attributes allowed within the node
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

  private String attributes;

  public MapNode()
  {
    //Set all attributes to false by default
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

    attributes = "";
  }

  //Allow neighboring nodes to be set
  public void setNorthNeighbor(MapNode north) {northNeighbor = north;}
  public void setSouthNeighbor(MapNode south) {southNeighbor = south;}
  public void setEastNeighbor(MapNode east) {eastNeighbor = east;}
  public void setWestNeighbor(MapNode west) {westNeighbor = west;}

  //Set specified attributes, sense attributes are automatically set in all neighboring nodes
  public void setWumpus()
  {
    wumpus=true;
    if(northNeighbor != null) {northNeighbor.setStench();}
    if(southNeighbor != null) {southNeighbor.setStench();}
    if(eastNeighbor != null) {eastNeighbor.setStench();}
    if(westNeighbor != null) {westNeighbor.setStench();}

    attributes += "Wumpus";
  }
  public void setStench()
  {
    stench=true;

    attributes += "Stench";
  }

  public void setPit()
  {
    pit=true;
    if(northNeighbor != null) {northNeighbor.setBreeze();}
    if(southNeighbor != null) {southNeighbor.setBreeze();}
    if(eastNeighbor != null) {eastNeighbor.setBreeze();}
    if(westNeighbor != null) {westNeighbor.setBreeze();}

    attributes += "Pit";
  }
  public void setBreeze()
  {
    breeze=true;

    attributes += "Breeze";
  }

  public void setGold()
  {
    gold=true;
    if(northNeighbor != null) {northNeighbor.setGlitter();}
    if(southNeighbor != null) {southNeighbor.setGlitter();}
    if(eastNeighbor != null) {eastNeighbor.setGlitter();}
    if(westNeighbor != null) {westNeighbor.setGlitter();}

    attributes += "Gold";
  }
  public void setGlitter()
  {
    glitter=true;

    attributes += "Glitter";
  }

  public void setStart()
  {
    start=true;

    attributes += "Start";
  }

  public void setSupmuw()
  {
    supmuw=true;
    if(northNeighbor != null) {northNeighbor.setMoo();}
    if(southNeighbor != null) {southNeighbor.setMoo();}
    if(eastNeighbor != null) {eastNeighbor.setMoo();}
    if(westNeighbor != null) {westNeighbor.setMoo();}

    attributes += "Supmuw";
  }
  public void setMoo()
  {
    moo=true;

    attributes += "Moo";
  }

  public void setNoTrespassing()
  {
    noTrespassing=true;

    attributes += "No Trespassing";
  }

  //Functions to get boolean values
  public boolean getWumpus() {return wumpus;}
  public boolean getStench() {return stench;}
  public boolean getPit() {return pit;}
  public boolean getBreeze() {return breeze;}
  public boolean getGold() {return gold;}
  public boolean getGlitter() {return glitter;}
  public boolean getStart() {return start;}
  public boolean getSupmuw() {return supmuw;}
  public boolean getMoo() {return moo;}
  public boolean getNoTrespassing() {return noTrespassing;}

  public String getAttr()
  {
    return attributes;
  }
}
