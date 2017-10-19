/*
A lower hazard count represents a safe square to travel
to whereas a high hazard count represents a dangerous
square.
*/
public class KnowledgeBase
{
  private int loot;
  private int hazards;
  private boolean visitied;

  public KnowledgeBase()
  {
    loot = 0;
    hazards = 0;
    visited = false;
  }

  public void incHazards() {hazards++;}
  public void decHazards() {hazards--;}
 
  public void incLoot() {loot++;}
  public void decLoot() {loot--;}

  public void visit() {visited=true;}
  public boolean visited() {return visited;}

  public int getHazards() {return hazards;}
  public int getLoot() {return loot;}

  //***************************************************************************
  //CODE FOR AGENT CLASS
  public coordinate getAgentNextMove()
  {
    //Calculate Wumpus location
    if(!stench || !wumpus)
    {
      for(coordinate c: directions)
      {
        int x = c.getX();
        int y = x.getY();

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
        int y = x.getY();

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
          int y = x.getY();

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
          int y = x.getY();

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
          int y = x.getY();

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
          int y = x.getY();

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
        int y = x.getY();

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
        int y = x.getY();

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

    switch(elements)
    {
      case 2: if(directions.get(0).getHazards() < directions.get(1).getHazards()) {min=0;}
              else {min=1;}
              break;

      case 3: if(directions.get(0).getHazards() < directions.get(1).getHazards())
              {
                if(directions.get(0).getHazards() < directions.get(2).getHazards()) {min=0;}
                else {min=2;}
              }
              else
              {
                if(directions.get(1).getHazards() < directions.get(2).getHazards()) {min=1;}
                else {min=2;}
              }
              break;

      case 4: if(directions.get(0).getHazards() < directions.get(1).getHazards())
              {
                if(directions.get(0).getHazards() < directions.get(2).getHazards())
                {
                  if(directions.get(0).getHazards() < directions.get(3).getHazards()) {min=0;}
                  else {min=3;}
                }
                else
                {
                  if(directions.get(2).getHazards() < directions.get(3).getHazards()) {min=2;}
                  else {min=3;}
                }
              }
              else
              {
                if(directions.get(1).getHazards() < directions.get(2).getHazards())
                {
                  if(directions.get(1).getHazards() < directions.get(3).getHazards()) {min=1;}
                  else {min=3;}
                }
                else
                {
                  if(directions.get(2).getHazards() < directions.get(3).getHazards()) {min=2;}
                  else {min=3;}
                }
              }
    }

    return directions.get(min);
  }
}
