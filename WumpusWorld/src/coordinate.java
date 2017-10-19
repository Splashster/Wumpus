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

  public boolean canPlaceObject(coordinate[] objs, coordinate obj){
      boolean answer = true;
      coordinate objsPosition, objPosition;

      if(objs != null){
          for(int i = 0; i < objs.length; i++){
              objsPosition = objs[i];
              if(xValue == objsPosition.getX() && yValue == objsPosition.getY()){
                 answer = false;
                 break;
              }
          }
      }else if(obj != null){
            if(xValue == obj.getX() && yValue == obj.getY()){
               answer = false;
            }
      }
      return answer;
  }
}
