import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Agent
{

  boolean stench, breeze, glitter, moo, wumpus, supmuw, gold, pit;
  boolean nWall, sWall, eWall, wWall;
  boolean hasArrow, wumpus_alive, gotGold, escaped;
  ArrayList<coordinate> directions;
  coordinate current_position;
  KnowledgeBase[][] kb;
  WumplusWorld theWorld;

  public Agent(WumplusWorld ww){
    kb = new KnowledgeBase[10][10];
    current_position = new coordinate (0,0);
    hasArrow = true;
    wumpus_alive = true;
    gotGold = false;
    theWorld = ww;
    escaped = false;

    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        kb[i][j] = new KnowledgeBase();
      }
    }

    kb[0][0].visit();
  }

  public void goGoAgent(){
    while(!gotGold && !escaped){
      setPerceptions(current_position.getX(),current_position.getY());
      directions = getChoices(current_position.getX(),current_position.getY());
      current_position = getAgentNextMove();
      theWorld.moveAgent(current_position.getX(),current_position.getY());
      if((current_position.getX() == 0 && current_position.getY() == 0) && (gotGold)){
        escaped = true;
      }
      kb[current_position.getX()][current_position.getY()].visit();
      //System.out.println("Tarzan's Current Position: " + current_position.getX() + "," + current_position.getY());
      try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
    }
  }


  public coordinate getAgentPosition(){
     return current_position;
  }

  public void setHasGold(){gotGold=true;}

  public boolean hasGold(){return gotGold;}

  //TODO Need set finished when agent gets to escape
  public boolean hasEscaped(){return escaped;}

  public ArrayList<coordinate> getChoices(int x, int y){
      coordinate left, right, up, down, move, previousMove;
      ArrayList<coordinate> choices = new ArrayList<coordinate>();

      if(x == 0 && y == 0){
        up = new coordinate(1,0);
        right = new coordinate (0,1);
        choices.add(up);
        choices.add(right);
      }else if(x == 0 && y != 9){
        up = new coordinate(x+1,y);
        right = new coordinate(0,y+1);
        left = new coordinate(x,y-1);
        choices.add(up);
        choices.add(right);
        choices.add(left);
      }else if(y == 0 && x != 9){
        up = new coordinate(x+1,y);
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        choices.add(up);
        choices.add(right);
        choices.add(down);
      }else if(x == 9 && y == 9){
        left = new coordinate(x,y-1);
        down = new coordinate(x-1,y);
        choices.add(left);
        choices.add(down);
      }else if(x == 9 && y == 0){
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        choices.add(right);
        choices.add(down);
      }else if(y == 9 && x == 0){
        up = new coordinate(x+1,y);
        left = new coordinate(x,y-1);
        choices.add(up);
        choices.add(left);
      }else if(x == 9 && y != 0){
        left = new coordinate(x,y-1);
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        choices.add(right);
        choices.add(down);
        choices.add(left);
      }else if(y == 9 && x!= 0){
        up = new coordinate(x+1,y);
        left = new coordinate(x,y-1);
        down = new coordinate(x-1,y);
        choices.add(down);
        choices.add(up);
        choices.add(left);
        //up, left, down
      }else{
        up = new coordinate(x+1,y);
        down = new coordinate(x-1,y);
        left = new coordinate(x,y-1);
        right = new coordinate(x,y+1);
        choices.add(up);
        choices.add(down);
        choices.add(left);
        choices.add(right);
      }
      for(int i = 0; i < choices.size(); i++){
        move = choices.get(i);
        System.out.println("Tarzan's choice: " + i + " " + move.getX() + "," + move.getY());
      }
      return choices;
  }

    public void setPerceptions(int x, int y){
        MapNode percept = theWorld.getPerception(x,y);
        stench = percept.getStench();
        kb[current_position.getX()][current_position.getY()].setStench(stench);
        breeze = percept.getBreeze();
        kb[current_position.getX()][current_position.getY()].setStench(breeze);
        glitter = percept.getGlitter();
        kb[current_position.getX()][current_position.getY()].setStench(glitter);
        moo = percept.getMoo();
        kb[current_position.getX()][current_position.getY()].setStench(moo);
        wumpus = percept.getWumpus();
        kb[current_position.getX()][current_position.getY()].setStench(wumpus);
        supmuw = percept.getSupmuw();
        kb[current_position.getX()][current_position.getY()].setStench(supmuw);
        gold = percept.getGold();
        kb[current_position.getX()][current_position.getY()].setStench(gold);
        pit = percept.getPit();
        kb[current_position.getX()][current_position.getY()].setStench(pit);
        nWall = percept.getNorthWall();
        sWall = percept.getSouthWall();
        eWall = percept.getEastWall();
        wWall = percept.getNorthWall();
    }

    public coordinate getAgentNextMove()
    {
      kb[current_position.getX()][current_position.getY()].resetHazards();

      //Add incentive for exploring new regions
      for(coordinate c: directions)
      {
        int x = c.getX();
        int y = c.getY();

        if(kb[x][y].visited())
        {
          kb[x][y].incHazards();
        }
      }

      //Calculate Wumpus location
      for(coordinate c: directions)
      {
        int x = c.getX();
        int y = c.getY();

        if(stench)
        {
          kb[x][y].incHazards();
        }
        else
        {
          kb[x][y].decHazards();
        }
      }


      //Calculate Supmuw location
      if(moo)
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          kb[x][y].decHazards();
        }
      }
      else
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          kb[x][y].incHazards();
        }
      }


      //Calculate Pit location
      for(coordinate c: directions)
      {
        int x = c.getX();
        int y = c.getY();

        if(breeze)
        {
          kb[x][y].incHazards();
        }
        else
        {
          kb[x][y].decHazards();
        }
      }

      //see if its the Wumpus's time to go.
      huntWumpus();

      return bestMove();
    }

    /*BACKUP
    public coordinate getAgentNextMove()
    {
      kb[current_position.getX()][current_position.getY()].resetHazards();

      //Add incentive for exploring new regions
      for(coordinate c: directions)
      {
        int x = c.getX();
        int y = c.getY();

        if(!kb[x][y].visited())
        {
          kb[x][y].decHazards();
        }
      }

      //Calculate Wumpus location
      for(coordinate c: directions)
      {
        int x = c.getX();
        int y = c.getY();

        if(stench)
        {
          kb[x][y].decHazards();
        }
        else
        {
          kb[x][y].incHazards();
        }
      }


      //Calculate Supmuw location
      if(moo)
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          kb[x][y].decHazards();
        }
      }


      //Calculate Pit location
      for(coordinate c: directions)
      {
        int x = c.getX();
        int y = c.getY();

        if(breeze)
        {
          kb[x][y].incHazards();
        }
        else
        {
          kb[x][y].decHazards();
        }
      }

      return bestMove();
    }
    */

    public coordinate bestMove()
    {
      int elements = directions.size();
      int min = 0;
      int minIndex = 0;

      switch(elements)
      {
        case 1: min = convertHazards(0);
                break;
        case 2: /*if(convertHazards(directions.get(0)) < convertHazards(directions.get(1))) {min=0;}
                else {min=1;}
                break;*/
                min = Math.min(convertHazards(0), convertHazards(1));
                break;

        case 3: /*if(convertHazards(directions.get(0)) < convertHazards(directions.get(1)))
                {
                  if(convertHazards(directions.get(0)) < convertHazards(directions.get(2))) {min=0;}
                  else {min=2;}
                }
                else
                {
                  if(convertHazards(directions.get(1)) < convertHazards(directions.get(2))) {min=1;}
                  else {min=2;}
                }
                break;*/
                int tempMin = Math.min(convertHazards(0), convertHazards(1));
                min = Math.min(tempMin, convertHazards(2));
                break;

        case 4: /*if(convertHazards(directions.get(0)) < convertHazards(directions.get(1)))
                {
                  if(convertHazards(directions.get(0)) < convertHazards(directions.get(2)))
                  {
                    if(convertHazards(directions.get(0)) < convertHazards(directions.get(3))) {min=0;}
                    else {min=3;}
                  }
                  else
                  {
                    if(convertHazards(directions.get(2)) < convertHazards(directions.get(3))) {min=2;}
                    else {min=3;}
                  }
                }
                else
                {
                  if(convertHazards(directions.get(1)) < convertHazards(directions.get(2)))
                  {
                    if(convertHazards(directions.get(1)) < convertHazards(directions.get(3))) {min=1;}
                    else {min=3;}
                  }
                  else
                  {
                    if(convertHazards(directions.get(2)) < convertHazards(directions.get(3))) {min=2;}
                    else {min=3;}
                  }
                }*/
                int tempMin1 = Math.min(convertHazards(0), convertHazards(1));
                int tempMin2 = Math.min(convertHazards(2), convertHazards(3));
                min = Math.min(tempMin1, tempMin2);
      }

      System.out.println("min - " + min);

      for(int x=0; x<elements; x++)
      {
        System.out.println(x + "     " + (min == convertHazards(x)) + convertHazards(x));
        if(min == convertHazards(x)) {minIndex=x;}
      }
      System.out.println("X: " + directions.get(minIndex).getX());
      System.out.println("Y: " + directions.get(minIndex).getY());

      return directions.get(minIndex);
    }

    public int convertHazards(int pos)
    {
      coordinate c = directions.get(pos);
      int x = c.getX();
      int y = c.getY();

      int hazards = kb[x][y].getHazards();// + kb[x][y].getWumpusHazards() + kb[x][y].getPitHazards();

      System.out.println("Position " + x + ": " + hazards + " - " + x + "," + y);

      return hazards;
    }

    public void huntWumpus()
    {
      if(hasArrow)
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          if(kb[x][y].getHazards() >= 5 && stench)
          {
            theWorld.killWumpus(c);
            hasArrow = false;

            for(coordinate a: directions)
            {
              int i = a.getX();
              int j = a.getY();

              kb[i][j].wumpusDied();
            }
          }
        }
      }
    }

}
