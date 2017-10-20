/******************************************************************
The Map class houses the logic for generating the map and
for setting the attributes for each node.
*******************************************************************/

import java.lang.Math;

public class Map
{
  private MapNode[][] map;


  public Map(coordinate wumpus, coordinate supmuw, coordinate gold, coordinate[] noPassZones, coordinate[] pits, coordinate agent, boolean hasGold, boolean actAsWumpus, boolean hasFood, boolean wumpus_alive, boolean nothingSafe, boolean goingToEscape)

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
        if((x != 0 || y != 0) && (x == agent.getX() && y == agent.getY()) && hasGold){
          map[x][y].setHasGold(); map[x][y].setAgent();
        }else if((x == 0 && y == 0) && (x == agent.getX() && y == agent.getY()) && hasGold && goingToEscape){
          map[x][y].setHasGold();
          map[x][y].setEscaping();
          map[x][y].setEscaped();
        }else if((x == 0 && y == 0) && (x == agent.getX() && y == agent.getY()) && !hasGold && nothingSafe && goingToEscape){
          map[x][y].setEscaping();
          map[x][y].setEscaped();
        }else if(x == agent.getX() && y == agent.getY()){
          map[x][y].setAgent();
        }
        if(wumpus != null && !wumpus_alive) {map[wumpus.getX()][wumpus.getY()].setDeadWumpus();}
        else if(wumpus != null && (x == wumpus.getX() && y == wumpus.getY())) {map[x][y].setWumpus();}
        if(x == gold.getX() && y == gold.getY()) {map[x][y].setGold();}
        if(pits != null){for(coordinate p : pits) {if(x == p.getX() && y == p.getY()) {map[x][y].setPit();}}}
        if(noPassZones != null){for(coordinate noPass : noPassZones) {if(x == noPass.getX() && y == noPass.getY()) {map[x][y].setNoTrespassing();}}}
        if(supmuw != null && (x == supmuw.getX() && y == supmuw.getY())) {
          if(actsAsWumpus(x,y)){
            map[x][y].setActAsWumpus();
          }
          if(hasFood){map[x][y].setHasFood();}
          map[x][y].setSupmuw();
        }
      }
    }
  }

  //Check to see if the supmuw should act like a wumpus.
  public boolean actsAsWumpus(int x, int y){
    coordinate left = null, right = null, up = null, down = null;
    boolean answer = false;

    if(x == 0 && y == 0){
      up = new coordinate(1,0);
      right = new coordinate (0,1);
    }else if(x == 0){
      up = new coordinate(1,y);
      right = new coordinate(0,y+1);
    }else if(y == 0){
      up = new coordinate(x+1,0);
      right = new coordinate(x,1);
    }else if(x == 9 && y == 9){
      left = new coordinate(x,y-1);
      down = new coordinate(x-1,y);
    }else if(x == 9){
      left = new coordinate(x,y-1);
      right = new coordinate(x,y+1);
      down = new coordinate(x-1,y);
    }else if(y == 9){
      up = new coordinate(x+1,y);
      down = new coordinate(x-1,y);
      left = new coordinate(x,y-1);
    }
    else{
      up = new coordinate(x+1,y);
      down = new coordinate(x-1,y);
      left = new coordinate(x,y-1);
      right = new coordinate(x,y+1);
    }

    if(up != null){
      if(!map[up.getX()][up.getY()].getPit() && (map[up.getX()][up.getY()].getStench() || map[up.getX()][up.getY()].getWumpus())){
        answer = true;
      }
    }
    if(down != null){
      if(!map[down.getX()][down.getY()].getPit() && map[down.getX()][down.getY()].getStench() || map[down.getX()][down.getY()].getWumpus()){
        answer = true;
      }
    }
    if(left != null){
      if(!map[left.getX()][left.getY()].getPit() && map[left.getX()][left.getY()].getStench() || map[left.getX()][left.getY()].getWumpus()){
        answer = true;
      }
    }
    if(right != null){
      if(!map[right.getX()][right.getY()].getPit() && map[right.getX()][right.getY()].getStench() || map[right.getX()][right.getY()].getWumpus()){
        answer = true;
      }
    }

    return answer;

  }

 public MapNode[][] getMap(){return map;}

  //Function to display map will all elements visible
  public void print(int score)
  {
    System.out.println("\n\t\t                                          __    __                       _             __    __           _     _ ");
    System.out.println("\t\t                                         / / /\\ \\ \\_   _ _ __ ___  _ __ | |_   _ ___  / / /\\ \\ \\___  _ __| | __| |");
    System.out.println("\t\t                                         \\ \\/  \\/ / | | | '_ ` _ \\| '_ \\| | | | / __| \\ \\/  \\/ / _ \\| '__| |/ _` |");
    System.out.println("\t\t                                          \\  /\\  /| |_| | | | | | | |_) | | |_| \\__ \\  \\  /\\  / (_) | |  | | (_| |");
    System.out.println("\t\t                                           \\/  \\/  \\__,_|_| |_| |_| .__/|_|\\__,_|___/   \\/  \\/ \\___/|_|  |_|\\__,_|");
    System.out.println("\t\t                                                                  |_|                                             ");

    for(int x=9; x>=0; x--)
    {
      System.out.println("\t\t          +-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+");
      System.out.println("\t\t          |  " +   map[x][0].getAttr1() + "   |  " +   map[x][1].getAttr1() + "   |  " +   map[x][2].getAttr1() + "   |  " +   map[x][3].getAttr1() + "   |  " +   map[x][4].getAttr1() + "   |  " +   map[x][5].getAttr1() + "   |  " +   map[x][6].getAttr1() + "   |  " +   map[x][7].getAttr1() + "   |  " +   map[x][8].getAttr1() + "   |  " +   map[x][9].getAttr1() + "   |");
      System.out.println("\t\t        " + x + " |  " +   map[x][0].getAttr2() + "   |  " +   map[x][1].getAttr2() + "   |  " +   map[x][2].getAttr2() + "   |  " +   map[x][3].getAttr2() + "   |  " +   map[x][4].getAttr2() + "   |  " +   map[x][5].getAttr2() + "   |  " +   map[x][6].getAttr2() + "   |  " +   map[x][7].getAttr2() + "   |  " +   map[x][8].getAttr2() + "   |  " +   map[x][9].getAttr2() + "   |");
      System.out.println("\t\t          |  " +   map[x][0].getAttr3() + "   |  " +   map[x][1].getAttr3() + "   |  " +   map[x][2].getAttr3() + "   |  " +   map[x][3].getAttr3() + "   |  " +   map[x][4].getAttr3() + "   |  " +   map[x][5].getAttr3() + "   |  " +   map[x][6].getAttr3() + "   |  " +   map[x][7].getAttr3() + "   |  " +   map[x][8].getAttr3() + "   |  " +   map[x][9].getAttr3() + "   |");
      System.out.println("\t\t          |  " +   map[x][0].getAttr4() + "   |  " +   map[x][1].getAttr4() + "   |  " +   map[x][2].getAttr4() + "   |  " +   map[x][3].getAttr4() + "   |  " +   map[x][4].getAttr4() + "   |  " +   map[x][5].getAttr4() + "   |  " +   map[x][6].getAttr4() + "   |  " +   map[x][7].getAttr4() + "   |  " +   map[x][8].getAttr4() + "   |  " +   map[x][9].getAttr4() + "   |");
    }
    System.out.println("\t\t          +-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+-------------+");
    System.out.println("\t\t                 0             1             2             3             4             5             6             7             8             9");

    int scoreDigits = String.valueOf(score).length();
    switch (scoreDigits) {
      case 1: System.out.println("\t\t                                                                                                                                  +-------------------+");
              System.out.println("\t\t                                                                                                                                  |   Score: " + score + "        |");
              System.out.println("\t\t                                                                                                                                  +-------------------+");
              break;

      case 2: System.out.println("\t\t                                                                                                                                  +-------------------+");
              System.out.println("\t\t                                                                                                                                  |   Score: " + score + "       |");
              System.out.println("\t\t                                                                                                                                  +-------------------+");
              break;

      case 3: System.out.println("\t\t                                                                                                                                  +-------------------+");
              System.out.println("\t\t                                                                                                                                  |   Score: " + score + "      |");
              System.out.println("\t\t                                                                                                                                  +-------------------+");
              break;

      case 4: System.out.println("\t\t                                                                                                                                  +-------------------+");
              System.out.println("\t\t                                                                                                                                  |   Score: " + score + "     |");
              System.out.println("\t\t                                                                                                                                  +-------------------+");
              break;

      case 5: System.out.println("\t\t                                                                                                                                  +-------------------+");
              System.out.println("\t\t                                                                                                                                  |   Score: " + score + "    |");
              System.out.println("\t\t                                                                                                                                  +-------------------+");
              break;

      default:System.out.println("\t\t                                                                                                                                  +-------------------+");
              System.out.println("\t\t                                                                                                                                  |   Score: MAX      |");
              System.out.println("\t\t                                                                                                                                  +-------------------+");
    }
  }
}
