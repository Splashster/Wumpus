public class MapNode
{
  //Location of MapNode
  private coordinate location;

  //Define neighboring nodes
  private MapNode northNeighbor;
  private MapNode southNeighbor;
  private MapNode eastNeighbor;
  private MapNode westNeighbor;

  //Define all posssible attributes allowed within the node
  private boolean agent;
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

  private String attributes[];

  public MapNode(coordinate c)
  {
    location = c;

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
    agent = true;

    attributes = new String[4];

    for(int i=0; i<4; i++)
    {
      attributes[i] = "        ";
    }
  }

  //Allow neighboring nodes to be set
  public void setNorthNeighbor(MapNode north) {northNeighbor = north;}
  public void setSouthNeighbor(MapNode south) {southNeighbor = south;}
  public void setEastNeighbor(MapNode east) {eastNeighbor = east;}
  public void setWestNeighbor(MapNode west) {westNeighbor = west;}

  //Set specified attributes, sense attributes are automatically set in all neighboring nodes
  public void setAgent(){
    attributes[0] = " Tarzan ";
    attributes[1] = " Tarzan ";
    attributes[2] = " Tarzan ";
    attributes[3] = " Tarzan ";
  }
  public void setWumpus()
  {
    wumpus=true;
    if(northNeighbor != null) {northNeighbor.setStench();}
    if(southNeighbor != null) {southNeighbor.setStench();}
    if(eastNeighbor != null) {eastNeighbor.setStench();}
    if(westNeighbor != null) {westNeighbor.setStench();}

    attributes[0] = "/\\_/\\   ";
    attributes[1] = "(*,*)   ";
    attributes[2] = "|   \\   ";
    attributes[3] = "(\")_(\") ";
  }
  public void setStench()
  {
    stench=true;

    attributes[0] = "        ";
    attributes[1] = " ( ( (  ";
    attributes[2] = " ) ) )  ";
    attributes[3] = "( ( (   ";
  }

  public void setPit()
  {
    pit=true;
    if(northNeighbor != null) {northNeighbor.setBreeze();}
    if(southNeighbor != null) {southNeighbor.setBreeze();}
    if(eastNeighbor != null) {eastNeighbor.setBreeze();}
    if(westNeighbor != null) {westNeighbor.setBreeze();}

    attributes[0] = "        ";
    attributes[1] = "XXXXXXXX";
    attributes[2] = "XXXXXXXX";
    attributes[3] = "XXXXXXXX";
  }
  public void setBreeze()
  {
    breeze=true;

    attributes[0] = "        ";
    attributes[1] = "~~~   ~~";
    attributes[2] = "  ~~~~  ";
    attributes[3] = "~~  ~~~~";
  }

  public void setGold()
  {
    gold=true;
    if(northNeighbor != null) {northNeighbor.setGlitter();}
    if(southNeighbor != null) {southNeighbor.setGlitter();}
    if(eastNeighbor != null) {eastNeighbor.setGlitter();}
    if(westNeighbor != null) {westNeighbor.setGlitter();}

    attributes[0] = "        ";
    attributes[1] = "  $$$$  ";
    attributes[2] = " $$$$$$ ";
    attributes[3] = "|______|";
  }
  public void setGlitter()
  {
    glitter=true;

    attributes[0] = "        ";
    attributes[1] = "*       ";
    attributes[2] = "   *    ";
    attributes[3] = "*     * ";
  }

  public void setStart()
  {
    start=true;

    //attributes += "Start";
  }

  public void setSupmuw()
  {
    supmuw=true;
    if(northNeighbor != null) {northNeighbor.setMoo();}
    if(southNeighbor != null) {southNeighbor.setMoo();}
    if(eastNeighbor != null) {eastNeighbor.setMoo();}
    if(westNeighbor != null) {westNeighbor.setMoo();}

    attributes[0] = "        ";
    attributes[1] = "(\\_/)   ";
    attributes[2] = "( *,*)  ";
    attributes[3] = "(\")_(\") ";
  }
  public void setMoo()
  {
    moo=true;

    attributes[0] = "        ";
    attributes[1] = " ((  )) ";
    attributes[2] = "((MOO!))";
    attributes[3] = " ((  )) ";
  }

  public void setNoTrespassing()
  {
    noTrespassing=true;

    //attributes += "No Trespassing";
  }

  public coordinate getCoordinates() {return location;}

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

  public String getAttr1()
  {
    return attributes[0];
  }

  public String getAttr2()
  {
    return attributes[1];
  }

  public String getAttr3()
  {
    return attributes[2];
  }

  public String getAttr4()
  {
    return attributes[3];
  }

  public void removeAttr(){
    attributes[0].replace("Tarzan", "        ");
    attributes[1].replace("Tarzan", "        ");
    attributes[2].replace("Tarzan", "        ");
    attributes[3].replace("Tarzan", "        ");
  }
}
