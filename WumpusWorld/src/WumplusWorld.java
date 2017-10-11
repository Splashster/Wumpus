public class WumplusWorld
{
  WumplusWorld()
  {
    //Set coordinates for User-specified attributes
    coordinate s = new coordinate(0,0);
    coordinate w = new coordinate(9,6);
    coordinate sup = new coordinate(1,5);
    coordinate[] p = new coordinate[3];
    p[0] = new coordinate(4,3);
    p[1] = new coordinate(6,0);
    p[2] = new coordinate(2,3);
    coordinate g = new coordinate(9,9);
    //Create map with given attributes
    Map m = new Map(s, w, sup, g, p);
    Agent a = new Agent(false, false, false, false);
    System.out.println(m.getNodeInformation(0,0));
    //Print map layout
    m.print();
  }
}
