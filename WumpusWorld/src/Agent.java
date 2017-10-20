/********************************************************
The Agent class performs all of the logic and movement for
the agent. The agent only knows about the node it is in
and the previous nodes it has been to.
********************************************************/

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.Collections;

public class Agent
{

  boolean stench, breeze, glitter, moo, wumpus, supmuw, gold, pit;
  boolean nWall, sWall, eWall, wWall;
  boolean hasArrow, wumpus_alive, gotGold, escaped, nothingSafe, goingToEscape;
  ArrayList<coordinate> directions;
  ArrayList<coordinate> previousMoves;
  ArrayList<coordinate> newMoves;
  int stuckCounter, moveCount;
  coordinate current_position, previousMove, newSpot;
  KnowledgeBase[][] kb;
  WumplusWorld theWorld;

  public Agent(WumplusWorld ww){
    kb = new KnowledgeBase[10][10];
    current_position = new coordinate (0,0);
    previousMove = null;
    newSpot = null;
    hasArrow = true;
    wumpus_alive = true;
    gotGold = false;
    theWorld = ww;
    escaped = false;
    goingToEscape = false;
    nothingSafe = false;
    stuckCounter = 0;
    moveCount = 0;
    previousMoves = new ArrayList<coordinate>();
    newMoves = new ArrayList<coordinate>();

    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        kb[i][j] = new KnowledgeBase();
      }
    }

    kb[0][0].visit();
  }

  //Drives the agent around the map
  //Once the agent gets the gold, the agent heads back to the safe zone
  //If the agent realizes it can't get the gold, it heads back to the safe zone
  public void goGoAgent(){
    previousMoves.add(current_position);

    while(!gotGold && !escaped){
      setPerceptions(current_position.getX(),current_position.getY());
      directions = getChoices(current_position.getX(),current_position.getY());
      if(directions.isEmpty() && newMoves.isEmpty()){
        nothingSafe = true;
        escape();
      }else{
        current_position = getAgentNextMove();
        stuckCounter = getStuckCount();
        if((current_position.getX() == 0 && current_position.getY() == 0 && !gotGold && moveCount >= 150) || (stuckCounter >= 5 && moveCount >= 150)){
          if(newMoves.isEmpty()){
            noMoreSafeMoves();
            if(!nothingSafe){
              goToNewMove(newSpot);
            }
          }else{
            goToNewMove(newMoves.get(newMoves.size()-1));
            newMoves.remove(newMoves.size()-1);
          }

        }
        stuckCounter = 0;
        theWorld.moveAgent(current_position.getX(),current_position.getY());
        moveCount++;
        previousMoves.add(current_position);
        if(gotGold || nothingSafe){
          escape();
        }
        kb[current_position.getX()][current_position.getY()].visit();
        try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
      }

    }
  }


  //Returns the current position of the agent
  public coordinate getAgentPosition(){
     return current_position;
  }

  //Sets that Tarzan has the gold
  public void setHasGold(){gotGold=true;}

  //Returns if Tarzan has the gold
  public boolean hasGold(){return gotGold;}

  public boolean shallEscape(){return goingToEscape;}

  //Returns if Tarzan has escaped
  public boolean hasEscaped(){return escaped;}

  //Returns if there are any more safe moves
  public boolean noMoreMoves(){return nothingSafe;}

    public void escape(){
        for(int i = previousMoves.size()-1; i >= 0; i--){
          current_position = previousMoves.get(i);
          goingToEscape = true;
          theWorld.moveAgent(current_position.getX(),current_position.getY());
          moveCount++;
          try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
        }
        if(current_position.getX() == 0 && current_position.getY() == 0 && gotGold){
          System.out.println("PHEWWW TIME TO GO GET JANE SOMETHING NICE!");
          escaped = true;
        }else{
          System.out.println("TARZAN DECIDED TO GO HOME!");
          escaped = true;
        }
      }

  //Looks to see if the act knows of anymore safe moves to take.
  public void noMoreSafeMoves(){
    nothingSafe = true;
    for(int i = 0; i > 10; i++){
      for(int j = 0; j > 10; j++){
        if(kb[i][j].getHazards() < 0){
          newSpot = new coordinate(i,j);
          newMoves.add(newSpot);
          nothingSafe = false;
        }
      }
    }
  }

  //Looks for the directions for the agent to get to a new safe node
  public void goToNewMove(coordinate nextMove){
    coordinate direction = null;
    boolean noMatch;
    for(int i = 0; i < previousMoves.size(); i++){
       noMatch = true;
       previousMove = previousMoves.get(i);
       directions = getChoices(previousMove.getX(),previousMove.getY());
       for(int j = 0; j < directions.size(); j++){
          direction = directions.get(i);
          if(direction.getX() == nextMove.getX() && nextMove.getY() == nextMove.getY()){
            current_position = nextMove;
            noMatch = false;
            break;
          }
       }
       if(noMatch){
         theWorld.moveAgent(previousMove.getX(),previousMove.getY());
         moveCount++;
         try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){}
       }else{
         noMatch = false;
         break;
       }
      }
    }

//Counts the number of times a position have been previously stored
public int getStuckCount(){
    coordinate prev_move = null;
    int stuckCount = 0;
    for(int i = 0; i < previousMoves.size(); i++){
        prev_move = previousMoves.get(i);
        if(current_position.getX() == prev_move.getX() && current_position.getY() == prev_move.getY()){
            stuckCount += 1;
        }
    }
    return stuckCount;
}

  //Gives the user a list of all the nodes adjacent to their current position
  public ArrayList<coordinate> getChoices(int x, int y){
      coordinate left, right, up, down, move, previousMove;
      ArrayList<coordinate> choices = new ArrayList<coordinate>();

      if(x == 0 && y == 0){
        up = new coordinate(1,0);
        right = new coordinate (0,1);
        if(!nWall){choices.add(up);}
        if(!eWall){choices.add(right);}
      }else if(x == 0 && y != 9){
        up = new coordinate(x+1,y);
        right = new coordinate(0,y+1);
        left = new coordinate(x,y-1);
        if(!nWall){choices.add(up);}
        if(!eWall){choices.add(right);}
        if(!wWall){choices.add(left);}
      }else if(y == 0 && x != 9){
        up = new coordinate(x+1,y);
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        if(!sWall){choices.add(down);}
        if(!eWall){choices.add(right);}
        if(!nWall){choices.add(up);}
      }else if(x == 9 && y == 9){
        left = new coordinate(x,y-1);
        down = new coordinate(x-1,y);
        if(!wWall){choices.add(left);}
        if(!sWall){choices.add(down);}
      }else if(x == 9 && y == 0){
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        if(!eWall){choices.add(right);}
        if(!sWall){choices.add(down);}
      }else if(y == 9 && x == 0){
        up = new coordinate(x+1,y);
        left = new coordinate(x,y-1);
        if(!nWall){choices.add(up);}
        if(!wWall){choices.add(left);}
      }else if(x == 9 && y != 0){
        left = new coordinate(x,y-1);
        right = new coordinate(x,y+1);
        down = new coordinate(x-1,y);
        if(!eWall){choices.add(right);}
        if(!sWall){choices.add(down);}
        if(!wWall){choices.add(left);}
      }else if(y == 9 && x!= 0){
        up = new coordinate(x+1,y);
        left = new coordinate(x,y-1);
        down = new coordinate(x-1,y);
        if(!nWall){choices.add(down);}
        if(!wWall){choices.add(left);}
        if(!sWall){choices.add(up);}
      }else{
        up = new coordinate(x+1,y);
        down = new coordinate(x-1,y);
        left = new coordinate(x,y-1);
        right = new coordinate(x,y+1);
        if(!nWall){choices.add(up);}
        if(!sWall){choices.add(down);}
        if(!wWall){choices.add(left);}
        if(!eWall){choices.add(right);}
      }
      for(int i = 0; i < choices.size(); i++){
        move = choices.get(i);
      }
      return choices;
  }

    //Sets agent's perception for the node the agent is currently in
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
        wWall = percept.getWestWall();

    }

    //Logic for getting the agent's next move
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



    //Logic for choosing the best most for the agent to take
    public coordinate bestMove()
    {
      int elements = directions.size();
      int min = 0;
      int minIndex = 0;

      switch(elements)
      {
        case 1: min = convertHazards(0);
                break;
        case 2:
                min = Math.min(convertHazards(0), convertHazards(1));
                break;

        case 3:
                int tempMin = Math.min(convertHazards(0), convertHazards(1));
                min = Math.min(tempMin, convertHazards(2));
                break;

        case 4:
                int tempMin1 = Math.min(convertHazards(0), convertHazards(1));
                int tempMin2 = Math.min(convertHazards(2), convertHazards(3));
                min = Math.min(tempMin1, tempMin2);

      }

      for(int x=0; x<elements; x++)
      {
        if(min == convertHazards(x)) {minIndex=x;}
      }
    if(directions.isEmpty()){
      return null;
    }else{
      return directions.get(minIndex);
    }

  }

    //Changes the coordinate to a hazard
    public int convertHazards(int pos)
    {
      coordinate c = directions.get(pos);
      int x = c.getX();
      int y = c.getY();

      int hazards = kb[x][y].getHazards();

      return hazards;
    }

    //Logic for hunting and killing the wumpus
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
