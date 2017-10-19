public class Map
{
  private MapNode[][] map;

  public Map(coordinate wumpus, coordinate supmuw, coordinate gold, coordinate[] noPassZones, coordinate[] pits, coordinate agent, boolean hasGold)
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
        if(x != 0) {map[x][y].setSouthNeighbor(map[x-1][y]);}else{map[x][y].setSouthWall();}
        if(x != 9) {map[x][y].setNorthNeighbor(map[x+1][y]);}else{map[x][y].setNorthWall();}
        if(y != 0) {map[x][y].setWestNeighbor(map[x][y-1]);}else{map[x][y].setWestWall();}
        if(y != 9) {map[x][y].setEastNeighbor(map[x][y+1]);}else{map[x][y].setEastWall();}

        //Check to see if current node should hold a specified parameter
        if((x == agent.getX() && y == agent.getY()) && hasGold){map[x][y].setHasGold(); map[x][y].setAgent();}
        if(x == agent.getX() && y == agent.getY()){map[x][y].setAgent();}
        if(wumpus != null && (x == wumpus.getX() && y == wumpus.getY())) {map[x][y].setWumpus();}
        if(supmuw != null && (x == supmuw.getX() && y == supmuw.getY())) {map[x][y].setSupmuw();}
        if(x == gold.getX() && y == gold.getY()) {map[x][y].setGold();}
        if(pits != null){for(coordinate p : pits) {if(x == p.getX() && y == p.getY()) {map[x][y].setPit();}}}
        if(noPassZones != null){for(coordinate noPass : noPassZones) {if(x == noPass.getX() && y == noPass.getY()) {map[x][y].setNoTrespassing();}}}
      }
    }
  }

 public MapNode[][] getMap(){return map;}
 //public String getNodeInformation(int x, int y){return map[x][y].getAttr();}

  //Function to display map will all elements visible
  public void print(int score)
  {
    System.out.println("                                       __    __                       _             __    __           _     _ ");
    System.out.println("                                      / / /\\ \\ \\_   _ _ __ ___  _ __ | |_   _ ___  / / /\\ \\ \\___  _ __| | __| |");
    System.out.println("                                      \\ \\/  \\/ / | | | '_ ` _ \\| '_ \\| | | | / __| \\ \\/  \\/ / _ \\| '__| |/ _` |");
    System.out.println("                                       \\  /\\  /| |_| | | | | | | |_) | | |_| \\__ \\  \\  /\\  / (_) | |  | | (_| |");
    System.out.println("                                        \\/  \\/  \\__,_|_| |_| |_| .__/|_|\\__,_|___/   \\/  \\/ \\___/|_|  |_|\\__,_|");
    System.out.println("                                                               |_|                                             ");

    for(int x=9; x>=0; x--)
    {
      System.out.println("       +-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+");
      System.out.println("       |  " +   map[x][0].getAttr1() + "   |  " +   map[x][1].getAttr1() + "   |  " +   map[x][2].getAttr1() + "   |  " +   map[x][3].getAttr1() + "   |  " +   map[x][4].getAttr1() + "   |  " +   map[x][5].getAttr1() + "   |  " +   map[x][6].getAttr1() + "   |  " +   map[x][7].getAttr1() + "   |  " +   map[x][8].getAttr1() + "   |  " +   map[x][9].getAttr1() + "   |");
      System.out.println("     " + x + " |  " +   map[x][0].getAttr2() + "   |  " +   map[x][1].getAttr2() + "   |  " +   map[x][2].getAttr2() + "   |  " +   map[x][3].getAttr2() + "   |  " +   map[x][4].getAttr2() + "   |  " +   map[x][5].getAttr2() + "   |  " +   map[x][6].getAttr2() + "   |  " +   map[x][7].getAttr2() + "   |  " +   map[x][8].getAttr2() + "   |  " +   map[x][9].getAttr2() + "   |");
      System.out.println("       |  " +   map[x][0].getAttr3() + "   |  " +   map[x][1].getAttr3() + "   |  " +   map[x][2].getAttr3() + "   |  " +   map[x][3].getAttr3() + "   |  " +   map[x][4].getAttr3() + "   |  " +   map[x][5].getAttr3() + "   |  " +   map[x][6].getAttr3() + "   |  " +   map[x][7].getAttr3() + "   |  " +   map[x][8].getAttr3() + "   |  " +   map[x][9].getAttr3() + "   |");
      System.out.println("       |  " +   map[x][0].getAttr4() + "   |  " +   map[x][1].getAttr4() + "   |  " +   map[x][2].getAttr4() + "   |  " +   map[x][3].getAttr4() + "   |  " +   map[x][4].getAttr4() + "   |  " +   map[x][5].getAttr4() + "   |  " +   map[x][6].getAttr4() + "   |  " +   map[x][7].getAttr4() + "   |  " +   map[x][8].getAttr4() + "   |  " +   map[x][9].getAttr4() + "   |");
    }
    System.out.println("       +-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+");
    System.out.println("              0             1             2             3             4             5             6             7             8             9");

    System.out.println("                                                                                                                                  +----------------+");
    System.out.println("                                                                                                                                  |   Score: " + score + "    |");
    System.out.println("                                                                                                                                  +----------------+");
  }
}
