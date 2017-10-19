import java.util.ArrayList;

public class Agent
{

  boolean stench, breeze, glitter, moo, wumpus, supmuw, gold, pit;
  boolean nWall, sWall, eWall, wWall;
  boolean hasArrow, wumpus_alive, gotGold;
  ArrayList<coordinate> directions;
  coordinate current_position;
  KnowledgeBase[][] kb;
  WumplusWorld theWorld;

  public Agent(){
    kb = new KnowledgeBase[10][10];
    current_position = new coordinate (0,0);
    hasArrow = true;
    wumpus_alive = true;
    gotGold = false;
    setPerceptions(0,0);

    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        kb[i][j] = new KnowledgeBase();
      }
    }
  }

  public void goGoAgent(){
    directions = getChoices(current_position.getX(),current_position.getY());
    setPerceptions(current_position.getX(),current_position.getY());
  }


/*  public void setAgentPosition(int 0, int 0){
     previous_position = current_position;
     current_position = new coordinate (0,0);
  }*/

  public void setHasGold(){gotGold=true;}

  public boolean hasGold(){return gotGold;}


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
        choices.add(up);
        choices.add(left);
        choices.add(down);
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
        breeze = percept.getBreeze();
        glitter = percept.getGlitter();
        moo = percept.getMoo();
        wumpus = percept.getWumpus();
        supmuw = percept.getSupmuw();
        gold = percept.getGold();
        pit = percept.getPit();
        nWall = percept.getNorthWall();
        sWall = percept.getSouthWall();
        eWall = percept.getEastWall();
        wWall = percept.getNorthWall();
    }

    public coordinate getAgentNextMove()
    {
      //Calculate Wumpus location
      if(!stench || !wumpus)
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          if(kb[x][y].visited())
          {
            if(!stench || !wumpus)
            {
              kb[x][y].decHazards();
            }
            else
            {
              kb[x][y].incHazards();
            }
          }
          else
          {
              kb[x][y].decHazards();
          }
        }
      }
      else
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          if(kb[x][y].visited())
          {
            if(!stench || !wumpus)
            {
              kb[x][y].incHazards();
            }
            else
            {
              kb[x][y].decHazards();
            }
          }
          else
          {
              kb[x][y].incHazards();
          }
        }
      }

      //Calculate Supmuw location
      if(wumpus_alive)
      {
        if(!moo || !supmuw)
        {
          for(coordinate c: directions)
          {
            int x = c.getX();
            int y = c.getY();

            if(kb[x][y].visited())
            {
              if(!moo || !supmuw)
              {
                kb[x][y].decHazards();
              }
              else
              {
                kb[x][y].incHazards();
              }
            }
            else
            {
                kb[x][y].decHazards();
            }
          }
        }
        else
        {
          for(coordinate c: directions)
          {
            int x = c.getX();
            int y = c.getY();

            if(kb[x][y].visited())
            {
              if(!moo || !supmuw)
              {
                kb[x][y].incHazards();
              }
              else
              {
                kb[x][y].decHazards();
              }
            }
            else
            {
                kb[x][y].incHazards();
            }
          }
        }
      }
      else
      {
        if(!moo || !supmuw)
        {
          for(coordinate c: directions)
          {
            int x = c.getX();
            int y = c.getY();

            if(kb[x][y].visited())
            {
              if(moo || supmuw)
              {
                kb[x][y].decHazards();
              }
            }
          }
        }
        else
        {
          for(coordinate c: directions)
          {
            int x = c.getX();
            int y = c.getY();

            if(kb[x][y].visited())
            {
              if(!moo || !supmuw)
              {
                kb[x][y].decHazards();
              }
            }
            else
            {
                kb[x][y].decHazards();
            }
          }
        }
      }

      //Calculate Pit location
      if(!breeze || !pit)
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          if(kb[x][y].visited())
          {
            if(!breeze || !pit)
            {
              kb[x][y].decHazards();
            }
            else
            {
              kb[x][y].incHazards();
            }
          }
          else
          {
              kb[x][y].decHazards();
          }
        }
      }
      else
      {
        for(coordinate c: directions)
        {
          int x = c.getX();
          int y = c.getY();

          if(kb[x][y].visited())
          {
            if(!breeze || !pit)
            {
              kb[x][y].incHazards();
            }
            else
            {
              kb[x][y].decHazards();
            }
          }
          else
          {
              kb[x][y].incHazards();
          }
        }
      }

      return bestMove();
    }

    public coordinate bestMove()
    {
      int elements = directions.size();
      int min = -1;

      //convert directions.get(0) to kb[x][y].getHazards();

      switch(elements)
      {
        case 2: if(convertHazards(directions.get(0)) < convertHazards(directions.get(1))) {min=0;}
                else {min=1;}
                break;

        case 3: if(convertHazards(directions.get(0)) < convertHazards(directions.get(1)))
                {
                  if(convertHazards(directions.get(0)) < convertHazards(directions.get(2))) {min=0;}
                  else {min=2;}
                }
                else
                {
                  if(convertHazards(directions.get(1)) < convertHazards(directions.get(2))) {min=1;}
                  else {min=2;}
                }
                break;

        case 4: if(convertHazards(directions.get(0)) < convertHazards(directions.get(1)))
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
                }
      }

      return directions.get(min);
    }

    public int convertHazards(coordinate c)
    {
      int x = c.getX();
      int y = c.getY();

      int hazards = kb[x][y].getHazards();

      return hazards;
    }

}
