public class Map
{
  private MapNode[][] map;

  Map(coordinate wumpus, coordinate[] pit, coordinate gold, coordinate supmuw)
  {
    map = new MapNode[10][10];

    for(int x=0; x<10; x++)
    {
      for(int y=0; y<10; y++)
      {
        map[x][y] = new MapNode();
      }
    }

    
  }

  void placeObject(String obj) {}

  void printMap()
  {

  }
}
