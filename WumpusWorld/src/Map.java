public class Map
{
  private MapNode[][] map;

  public Map(coordinate start, coordinate wumpus, coordinate supmuw, coordinate gold, coordinate[] pits)
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
    map[1][1].setAgent();
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
        if(x == wumpus.getX() && y == wumpus.getY()) {map[x][y].setWumpus();}
        if(x == supmuw.getX() && y == supmuw.getY()) {map[x][y].setSupmuw();}
        if(x == gold.getX() && y == gold.getY()) {map[x][y].setGold();}
        for(coordinate p : pits) {if(x == p.getX() && y == p.getY()) {map[x][y].setPit();}}
      }
    }
  }

 public MapNode[][] getMap(){return map;}
 //public String getNodeInformation(int x, int y){return map[x][y].getAttr();}

  //Function to display map will all elements visible
  public void print()
  {
    for(int y=9; y>=0; y--)
    {
      System.out.println("+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+");
      System.out.println("|  " +   map[0][y].getAttr1() + "   |  " +   map[1][y].getAttr1() + "   |  " +   map[2][y].getAttr1() + "   |  " +   map[3][y].getAttr1() + "   |  " +   map[4][y].getAttr1() + "   |  " +   map[5][y].getAttr1() + "   |  " +   map[6][y].getAttr1() + "   |  " +   map[7][y].getAttr1() + "   |  " +   map[8][y].getAttr1() + "   |  " +   map[9][y].getAttr1() + "   |");
      System.out.println("|  " +   map[0][y].getAttr2() + "   |  " +   map[1][y].getAttr2() + "   |  " +   map[2][y].getAttr2() + "   |  " +   map[3][y].getAttr2() + "   |  " +   map[4][y].getAttr2() + "   |  " +   map[5][y].getAttr2() + "   |  " +   map[6][y].getAttr2() + "   |  " +   map[7][y].getAttr2() + "   |  " +   map[8][y].getAttr2() + "   |  " +   map[9][y].getAttr2() + "   |");
      System.out.println("|  " +   map[0][y].getAttr3() + "   |  " +   map[1][y].getAttr3() + "   |  " +   map[2][y].getAttr3() + "   |  " +   map[3][y].getAttr3() + "   |  " +   map[4][y].getAttr3() + "   |  " +   map[5][y].getAttr3() + "   |  " +   map[6][y].getAttr3() + "   |  " +   map[7][y].getAttr3() + "   |  " +   map[8][y].getAttr3() + "   |  " +   map[9][y].getAttr3() + "   |");
      System.out.println("|  " +   map[0][y].getAttr4() + "   |  " +   map[1][y].getAttr4() + "   |  " +   map[2][y].getAttr4() + "   |  " +   map[3][y].getAttr4() + "   |  " +   map[4][y].getAttr4() + "   |  " +   map[5][y].getAttr4() + "   |  " +   map[6][y].getAttr4() + "   |  " +   map[7][y].getAttr4() + "   |  " +   map[8][y].getAttr4() + "   |  " +   map[9][y].getAttr4() + "   |");
    }
    System.out.println("+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+");

  }
}
