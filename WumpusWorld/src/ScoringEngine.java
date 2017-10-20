/***********************************************************************
Scoring Engine controls game score by monitoring events and keeping
running total of the agent's total score.
************************************************************************/
public class ScoringEngine
{
  //Lookup table to find score values
  private int[][] scoreTable;
  //Running total of Agent's score
  private int gameScore;

  public ScoringEngine()
  {
    scoreTable = new int[7][2];
    gameScore = 1;

    //Agent move event
    scoreTable[0][0] = 1;
    scoreTable[0][1] = -1;
    //Shot arrow event
    scoreTable[1][0] = 2;
    scoreTable[1][1] = -10;
    //Eaten by Wumpus or Supmuw or Fell in Pit
    scoreTable[2][0] = 3;
    scoreTable[2][1] = -1000;
    //Received food from Supmuw
    scoreTable[3][0] = 4;
    scoreTable[3][1] = 100;
    //Escaped board with the Gold
    scoreTable[4][0] = 5;
    scoreTable[4][1] = 1000;
  }

  //Pass a key as listed above to score a particular event
  public int scoreEvent(int e)
  {
    //update score
    gameScore += scoreTable[e-1][1];
    //return current score
    return gameScore;
  }

  //Get current score
  public int getGameScore() {return gameScore;}
}
