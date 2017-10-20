/*********************************************
Coordinate class to store X,Y value pairs
**********************************************/
public class coordinate
{
  private int xValue;
  private int yValue;

  public coordinate(int x, int y)
  {
    xValue = x;
    yValue = y;
  }

  //Getter functions
  public int getX() {return xValue;}
  public int getY() {return yValue;}

  //Setter functions
  public void setX(int x) {xValue = x;}
  public void setY(int y) {yValue = y;}

  //Checks to see if user can place object on the board, or if
  //that space has alredy been occupied
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
