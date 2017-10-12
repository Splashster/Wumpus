public class Map
{
  private MapNode[][] map;

  public Map(coordinate start, coordinate wumpus, coordinate supmuw, coordinate gold, coordinate[] pits, coordinate agent)
  {
    map = new MapNode[10][10];
    coordinate c;

    //initialize map to graph of MapNodes
    for(int x=0; x<10; x++)
    {
      for(int y=0; y<10; y++)
      {
        c = new coordinate(x, y);
        map[x][y] = new MapNode(c);
      }
    }

    //initialize attributes of each MapNode
    for(int x=0; x<10; x++)
    {
      for(int y=0; y<10; y++)
      {
        //If position is not on the edge of the graph, set all the nodes neighors
        if(x != 0) {map[x][y].setWestNeighbor(map[x-1][y]);}
        if(x != 9) {map[x][y].setEastNeighbor(map[x+1][y]);}
        if(y != 0) {map[x][y].setSouthNeighbor(map[x][y-1]);}
        if(y != 9) {map[x][y].setNorthNeighbor(map[x][y+1]);}

        //Check to see if current node should hold a specified parameter
        if(x == start.getX() && y == start.getY()) {map[x][y].setStart();}
        if(x == agent.getX() && y == agent.getY()) {map[x][y].setAgent();}
        if(x == wumpus.getX() && y == wumpus.getY()) {map[x][y].setWumpus();}
        if(x == supmuw.getX() && y == supmuw.getY()) {map[x][y].setSupmuw();}
        if(x == gold.getX() && y == gold.getY()) {map[x][y].setGold();}
        for(coordinate p : pits) {if(x == p.getX() && y == p.getY()) {map[x][y].setPit();}}
      }
    }
  }

 public MapNode[][] getMap(){return map;}
 public String getNodeInformation(int x, int y){return map[x][y].getAttr();}

  //Function to display map will all elements visible
  public void print()
  {
    for(int x=9; x>=0; x--)
    {
      System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");
      System.out.println("|  " +   map[x][0].getAttr() + "   |  " +   map[x][1].getAttr() + "   |  " +   map[x][2].getAttr() + "   |  " +   map[x][3].getAttr() + "   |  " +   map[x][4].getAttr() + "   |  " +   map[x][5].getAttr() + "   |  " +   map[x][6].getAttr() + "   |  " +   map[x][7].getAttr() + "   |  " +   map[x][8].getAttr() + "   |  " +   map[x][9].getAttr() + "   |");
      System.out.println("|          |          |          |          |          |          |          |          |          |          |");
      System.out.println("|          |          |          |          |          |          |          |          |          |          |");
      System.out.println("|          |          |          |          |          |          |          |          |          |          |");
    }
    System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");

  }
}
