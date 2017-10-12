//Simple class to store X-Y coordinate pairs
public class coordinate
{
  private int xValue;
  private int yValue;

  public coordinate(int x, int y)
  {
    xValue = x;
    yValue = y;
  }

  public int getX() {return xValue;}

  public int getY() {return yValue;}

  public void setX(int x) {xValue = x;}

  public void setY(int y) {yValue = y;}
}
