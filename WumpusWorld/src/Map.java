public class Map
{
  private MapNode[][] map;

  Map(coordinate start, coordinate wumpus, coordinate supmuw, coordinate gold, coordinate[] pits)
  {
    map = new MapNode[10][10];

    //initialize map to graph of MapNodes
    for(int x=0; x<10; x++)
    {
      for(int y=0; y<10; y++)
      {
        map[x][y] = new MapNode();
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
        if(x == wumpus.getX() && y == wumpus.getY()) {map[x][y].setWumpus();}
        if(x == supmuw.getX() && y == supmuw.getY()) {map[x][y].setSupmuw();}
        if(x == gold.getX() && y == gold.getY()) {map[x][y].setGold();}
        for(coordinate p : pits) {if(x == p.getX() && y == p.getY()) {map[x][y].setPit();}}
      }
    }
  }

 /*public Mapnode getNodeInformation(int x, int y){
   return map[x][y];
 }*/

  //Function to display map will all elements visible
  void print()
  {
    for(int y=0; y<10; y++)
    {
      System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");
      System.out.println("|  " +   map[0][y].getPit() + "   |  " +   map[1][y].getPit() + "   |  " +   map[2][y].getPit() + "   |  " +   map[3][y].getPit() + "   |  " +   map[4][y].getPit() + "   |  " +   map[5][y].getPit() + "   |  " +   map[6][y].getPit() + "   |  " +   map[7][y].getPit() + "   |  " +   map[8][y].getPit() + "   |  " +   map[9][y].getPit() + "   |");
      System.out.println("|          |          |          |          |          |          |          |          |          |          |");
      System.out.println("|          |          |          |          |          |          |          |          |          |          |");
      System.out.println("|          |          |          |          |          |          |          |          |          |          |");
    }
    System.out.println("+----------+----------+----------+----------+----------+----------+----------+----------+----------+----------+");

  }
}
