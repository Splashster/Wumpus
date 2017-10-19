public class ScoringEngine
{
  /**
  Table stores all values of events.
  Keys are listed below.
  1 - Player Move
  2 - Use Arrow
  3 - Fall in Pit
  4 - Eaten by Wumpus
  5 - Eaten by Supmuw
  6 - Received Food from Supmuw
  7 - Found Gold
  */
  private int[][] scoreTable;
  private int gameScore;

  public ScoringEngine()
  {
    scoreTable = new int[7][2];
    gameScore = 1;

    scoreTable[0][0] = 1; //Agent move
    scoreTable[0][1] = -1;
    scoreTable[1][0] = 2; //Shot arrow
    scoreTable[1][1] = -10;
    scoreTable[2][0] = 3; //Ate by Wumpus or Supmuw or Fell in pit
    scoreTable[2][1] = -1000;
    scoreTable[3][0] = 4; //Got food from Supmuw
    scoreTable[3][1] = 100;
    scoreTable[4][0] = 5; //Escaped with Gold
    scoreTable[4][1] = 1000;
  }

  public int scoreEvent(int e)
  {
    gameScore += scoreTable[e-1][1];
    return gameScore;
  }

  public int getGameScore() {return gameScore;}

  //TODO We may need to just have a isWumpusDead function or set the wumpus attributes
  //to false
  public void removeWumpus() {scoreTable[4][1] = 0;}
}
