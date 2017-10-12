/**
Possible implementation of a Probabilistic Agent
*/
public class ProbabilisticAgent
{
  private Map known;
  private coordinate currentLocation;
  /**
  3-Dimensional array to store calulated probablilities
  of all game hazards. Codes for each hazard in the third
  dimension of the graph are listed below:
  1 - Wumpus
  2 - Supmuw
  3 - Pit
  4 - Gold
  */
  private double[][][] probabilities;

  public ProbabilisticAgent(coordinate start)
  {
    currentLocation = start;
    known = new Map(currentLocation, null, null, null);

    probablilities = new double[10][10][4];
  }

  public void runClassifier(MapNode location)
  {
    if(loation == null) {return;}
    if(location.getStench())
    {
      coordinate c = location.getCoordinates();
      //runClassifier(northNeighbor)
      //runClassifier(southNeighbor)
      //runClassifier(eastNeighbor)
      //runClassifier(westNeighbor)
    }
    /*if(location.getMoo())
    {

    }
    if(location.getBreeze())
    {

    }
    if(location.getGlitter())
    {

    }*/
  }
}
