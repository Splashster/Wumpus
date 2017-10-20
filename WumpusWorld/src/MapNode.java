/***********************************************************************
MapNode stores all information about a particular location on the board
such as objects in the node and neighbors of the node
************************************************************************/
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
  private boolean supmuw;
  private boolean moo;
  private boolean northWall;
  private boolean southWall;
  private boolean eastWall;
  private boolean westWall;
  private boolean hasGold;
  private boolean hasFood;
  private boolean actAsWumpus;
  private boolean noTrespassing;

  //Used to print ASCII text on game board
  private String attributes[];

  public MapNode(coordinate c)
  {
    location = c;

    //Set all attributes to false by default
    agent = false;
    wumpus = false;
    stench = false;
    pit = false;
    breeze = false;
    gold = false;
    glitter = false;
    supmuw = false;
    moo = false;
    noTrespassing = false;
    northWall = false;
    southWall = false;
    eastWall = false;
    westWall = false;
    hasGold = false;
    hasFood = false;
    actAsWumpus = false;

    //Initialize attributes to a fixed height, fixed width square of blank space
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
    agent = true;

    if(hasGold){
      attributes[0] = "+====>  ";
      attributes[1] = "\\ O _$  ";
      attributes[2] = "  |     ";
      attributes[3] = " / \\    ";
    }else{
      attributes[0] = " +====> ";
      attributes[1] = " \\ O __ ";
      attributes[2] = "   |    ";
      attributes[3] = "  / \\   ";
    }
  }
  public void setWumpus()
  {
    wumpus=true;
    if(northNeighbor != null) {northNeighbor.setStench();}
    if(southNeighbor != null) {southNeighbor.setStench();}
    if(eastNeighbor != null) {eastNeighbor.setStench();}
    if(westNeighbor != null) {westNeighbor.setStench();}


    if(agent)
    {
      attributes[0] = " Tarzan ";
      attributes[1] = " Eaten  ";
      attributes[2] = "        ";
      attributes[3] = " -1000  ";
    }
    else
    {
      attributes[0] = "/\\_/\\   ";
      attributes[1] = "(-,-)   ";
      attributes[2] = "|   \\   ";
      attributes[3] = "(\")_(\") ";
    }
  }
  public void setDeadWumpus()
  {
    wumpus=false;
    if(northNeighbor != null) {northNeighbor.removeStench();}
    if(southNeighbor != null) {southNeighbor.removeStench();}
    if(eastNeighbor != null) {eastNeighbor.removeStench();}
    if(westNeighbor != null) {westNeighbor.removeStench();}

    attributes[0] = "/\\_/\\   ";
    attributes[1] = "(x,x)   ";
    attributes[2] = "|   \\   ";
    attributes[3] = "(\")_(\") ";
  }
  public void setStench()
  {
    stench=true;

    if(!wumpus && !supmuw && !pit && !gold)
    {
      if(agent && hasGold)
      {
        attributes[0] = "+====>  ";
        attributes[1] = "\\ O _$ (";
        attributes[2] = ") |   ) ";
        attributes[3] = " / \\ (  ";
      }
      else if(agent)
      {
        attributes[0] = "+====>  ";
        attributes[1] = "\\ O _  (";
        attributes[2] = ") |   ) ";
        attributes[3] = " / \\ (  ";
      }
      else if(breeze && moo)
      {
        attributes[0] = "(Moo ~~(";
        attributes[1] = ")~~ Moo)";
        attributes[2] = "( Moo ~(";
        attributes[3] = ")~  Moo)";
      }
      else if(moo)
      {
        attributes[0] = "  ) ) ) ";
        attributes[1] = " Moo (  ";
        attributes[2] = " ) ) )  ";
        attributes[3] = "( ( Moo ";
      }
      else if(breeze)
      {
        attributes[0] = "~~~) ) )";
        attributes[1] = " ~( ( ( ";
        attributes[2] = " ) ) )~~";
        attributes[3] = "( ~~(  ~";
      }
      else
      {
        attributes[0] = "        ";
        attributes[1] = " ( ( (  ";
        attributes[2] = " ) ) )  ";
        attributes[3] = "( ( (   ";
      }
    }
  }

  public void setPit()
  {
    pit=true;
    if(northNeighbor != null) {northNeighbor.setBreeze();}
    if(southNeighbor != null) {southNeighbor.setBreeze();}
    if(eastNeighbor != null) {eastNeighbor.setBreeze();}
    if(westNeighbor != null) {westNeighbor.setBreeze();}

    if(agent)
    {
      attributes[0] = " Tarzan ";
      attributes[1] = "Pit Fall";
      attributes[2] = "        ";
      attributes[3] = " -1000  ";
    }
    else
    {
      attributes[0] = "__    __";
      attributes[1] = " [    ] ";
      attributes[2] = " [    ] ";
      attributes[3] = "  ^--^  ";
    }
  }
  public void setBreeze()
  {
    breeze=true;

    if(!wumpus && !supmuw && !pit && !gold)
    {
      if(agent && hasGold)
      {
        attributes[0] = "+====>~ ";
        attributes[1] = "\\ O _$  ";
        attributes[2] = "~~|   ~ ";
        attributes[3] = " / \\ ~~ ";
      }
      else if(agent)
      {
        attributes[0] = "+====>~ ";
        attributes[1] = "\\ O __  ";
        attributes[2] = "~~|   ~ ";
        attributes[3] = " / \\ ~~ ";
      }
      else if(stench && moo)
      {
        attributes[0] = "(Moo ~~(";
        attributes[1] = ")~~ Moo)";
        attributes[2] = "( Moo ~(";
        attributes[3] = ")~  Moo)";
      }
      else if(stench)
      {
        attributes[0] = "~~~) ) )";
        attributes[1] = " ~( ( ( ";
        attributes[2] = " ) ) )~~";
        attributes[3] = "( ~~(  ~";
      }
      else if(moo)
      {
        attributes[0] = "Moo ~ ~~";
        attributes[1] = "~~~  Moo";
        attributes[2] = "Moo ~~~ ";
        attributes[3] = "~~  ~Moo";
      }
      else
      {
        attributes[0] = "        ";
        attributes[1] = "~~~   ~~";
        attributes[2] = "  ~~~~  ";
        attributes[3] = "~~  ~~~~";
      }
    }
  }

  public void setEscaped(){
    if(hasGold){
      attributes[0] = " Tarzan ";
      attributes[1] = "Escaped!";
      attributes[2] = "        ";
      attributes[3] = " +1000  ";
    }else{
      attributes[0] = " Tarzan ";
      attributes[1] = "Escaped!";
      attributes[2] = "        ";
      attributes[3] = "        ";
    }

  }

  public void setGold()
  {
    gold=true;

    if(agent)
    {
      attributes[0] = "+====>  ";
      attributes[1] = "\\ O _$  ";
      attributes[2] = "  |     ";
      attributes[3] = " / \\    ";
    }
    else
    {
      attributes[0] = " *    * ";
      attributes[1] = "  $$$$ *";
      attributes[2] = " $$$$$$ ";
      attributes[3] = "|______|";
    }
  }

  public void setSupmuw()
  {
    supmuw=true;
    if(!actAsWumpus && !pit){
      if(northNeighbor != null) {northNeighbor.setMoo();}
      if(southNeighbor != null) {southNeighbor.setMoo();}
      if(eastNeighbor != null) {eastNeighbor.setMoo();}
      if(westNeighbor != null) {westNeighbor.setMoo();}

      if(agent && hasFood){
        hasFood = false;
        attributes[0] = " Tarzan ";
        attributes[1] = "Got Food";
        attributes[2] = "        ";
        attributes[3] = "  +100  ";
      }else if(agent && !hasFood){
          attributes[0] = "  Out   ";
          attributes[1] = "of Food!";
          attributes[2] = "        ";
          attributes[3] = "        ";
      }else if(!agent){
        attributes[0] = "        ";
        attributes[1] = "(\\_/)   ";
        attributes[2] = "( ^,^)  ";
        attributes[3] = "(\")_(\") ";
      }
    }else if(!pit){
      if(northNeighbor != null) {northNeighbor.setStench();}
      if(southNeighbor != null) {southNeighbor.setStench();}
      if(eastNeighbor != null) {eastNeighbor.setStench();}
      if(westNeighbor != null) {westNeighbor.setStench();}

      if(agent){
        attributes[0] = " Tarzan ";
        attributes[1] = " Eaten  ";
        attributes[2] = "        ";
        attributes[3] = " -1000  ";
      }else{
        attributes[0] = "        ";
        attributes[1] = "(\\_/)   ";
        attributes[2] = "( -,-)  ";
        attributes[3] = "(\")_(\") ";
      }
    }else{
      if(northNeighbor != null) {northNeighbor.setMoo();}
      if(southNeighbor != null) {southNeighbor.setMoo();}
      if(eastNeighbor != null) {eastNeighbor.setMoo();}
      if(westNeighbor != null) {westNeighbor.setMoo();}
      attributes[0] = "        ";
      attributes[1] = "_      _";
      attributes[2] = "[\\_/)  ]";
      attributes[3] = "[ ^,^) ]";
    }

  }
  public void setMoo()
  {
    moo=true;

    if(!wumpus && !supmuw && !pit && !gold)
    {
      if(agent && hasGold)
      {
        attributes[0] = "+====>  ";
        attributes[1] = "\\ O _$  ";
        attributes[2] = "  | Moo ";
        attributes[3] = " / \\    ";
      }
      else if(agent)
      {
        attributes[0] = "+====>  ";
        attributes[1] = "\\ O __  ";
        attributes[2] = "  | Moo ";
        attributes[3] = " / \\    ";
      }
      else if(stench && breeze)
      {
        attributes[0] = "(Moo ~~(";
        attributes[1] = ")~~ Moo)";
        attributes[2] = "( Moo ~(";
        attributes[3] = ")~  Moo)";
      }
      else if(stench)
      {
        attributes[0] = "  ) ) ) ";
        attributes[1] = " Moo (  ";
        attributes[2] = " ) ) )  ";
        attributes[3] = "( ( Moo ";
      }
      else if(breeze)
      {
        attributes[0] = "Moo ~ ~~";
        attributes[1] = "~~~  Moo";
        attributes[2] = "Moo ~~~ ";
        attributes[3] = "~~  ~Moo";
      }
      else
      {
        attributes[0] = "        ";
        attributes[1] = " ((  )) ";
        attributes[2] = "((MOO!))";
        attributes[3] = " ((  )) ";
      }
    }
  }

  public void setNoTrespassing()
  {
    noTrespassing=true;
    northWall= true;
    southWall = true;
    eastWall = true;
    westWall = true;

    if(northNeighbor != null) {northNeighbor.setSouthWall();}
    if(southNeighbor != null) {southNeighbor.setNorthWall();}
    if(eastNeighbor != null) {eastNeighbor.setWestWall();}
    if(westNeighbor != null) {westNeighbor.setEastWall();}

    attributes[0] = "  _____ ";
    attributes[1] = " |Stop!|";
    attributes[2] = " |__ __|";
    attributes[3] = "    |   ";
  }
  public void setNorthWall(){northWall = true;}
  public void setSouthWall(){southWall = true;}
  public void setEastWall(){eastWall = true;}
  public void setWestWall(){westWall = true;}
  public void setHasGold(){hasGold = true;}
  public void setHasFood(){hasFood = true;}
  public void setActAsWumpus(){actAsWumpus = true;}

  public coordinate getCoordinates() {return location;}

  //Functions to get boolean values
  public boolean getWumpus() {return wumpus;}
  public boolean getStench() {return stench;}
  public boolean getPit() {return pit;}
  public boolean getBreeze() {return breeze;}
  public boolean getGold() {return gold;}
  public boolean getGlitter() {return glitter;}
  public boolean getSupmuw() {return supmuw;}
  public boolean getMoo() {return moo;}
  public boolean getNorthWall(){return northWall;}
  public boolean getSouthWall(){return southWall;}
  public boolean getEastWall(){return eastWall;}
  public boolean getWestWall(){return westWall;}
  public boolean getActAsWumpus(){return actAsWumpus;}
  public boolean getHasFood(){return hasFood;}
  public boolean getNoTrespassing() {return noTrespassing;}

  //Functions to obtain parts of attribute array for printing on the game board
  public String getAttr1() {return attributes[0];}
  public String getAttr2() {return attributes[1];}
  public String getAttr3() {return attributes[2];}
  public String getAttr4() {return attributes[3];}

  //Remove identification attributes upon death of the Wumpus
  public void removeStench()
  {
    stench = false;
  }
}
