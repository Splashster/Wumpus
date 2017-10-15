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


    Supmuw -
    "\n"
    "(\\_/)   \n"
    "( •,•)  \n"
    "(\")_(\") \n"

    moo -
    "\n"
    " ((  )) \n"
    "((MOO!))\n"
    " ((  )) \n"

    Wumpus -
    "/\\_/\\   \n"
    "(•ㅅ•)   \n"
    "|   \\   \n"
    "(\")_(\") \n"　

    Dead wumpus -
    "/\\_/\\   \n"
    "(xㅅx)   \n"
    "|   \\   \n"
    "(\")_(\") \n"

    Stench -
    "\n"
    " ( ( (  \n"
    " ) ) )  \n"
    "( ( (   \n"


    Pit -
    "\n"
    "XXXXXXXX\n"
    "XXXXXXXX\n"
    "XXXXXXXX\n"


    Breeze -
    "\n"
    "~~~   ~~\n"
    "  ~~~~  \n"
    "~~  ~~~~\n"


    Glitter -
    "\n"
    "*       \n"
    "   *    \n"
    "*     * \n"

    Gold -
    "\n"
    "  $$$$  \n"
    " $$$$$$ \n"
    "|______|\n"


    Player -
    "+====> \n"
    "\\ O __ \n"
    "  |    \n"
    " / \\   \n"

    ***********NEW CHARACTERS***************

    Player with gold
    "+====> "
    "\\ O _$ "
    "  |    "
    " / \\   "

    Pit
    "__    __"
    " [    ] "
    " [    ] "
    "  ^--^  "

    Supmuw in pit
    "        "
    "_      _"
    "[\\_/) ]"
    "[ •,•) ]"


    No Trespassing
    " |Stop!|"
    " |__ __|"
    "    |   "
    "-~--~~~-"

    Player in Breeze
    "+====>~"
    "\\ O __ "
    "~~|   ~"
    " / \\ ~~"

    Player in stench
    "+====> "
    "\\ O _ ("
    ") |  ) "
    " / \\(  "

    Player in MOO
    "+====> "
    "\\ O __ "
    "  | Moo"
    " / \\   "

    Player in glitter
    "+====>*"
    "\\ O __ "
    "* |   *"
    " / \\*  "

    Stench - moo
    " ( ( (  "
    " Moo    "
    " ) ) )  "
    "( ( Moo "

    Stench - Glitter
    " *) ) ) "
    " ( (*(  "
    " ) ) )  "
    "(*( (  *"

    Stench - Breeze
    "~~~) ) )"
    " ~( ( ( "
    " ) ) )~~"
    "( ~~(  ~"

    Moo - Glitter
    "      * "
    "* Moo   "
    "   *    "
    "*    Moo"

    Moo - Breeze
    "Moo ~ ~~"
    "~~~  Moo"
    "Moo ~~~ "
    "~~  ~Moo"

    Breeze - Glitter
    "~~~~  * "
    "~~ *  ~~"
    "* ~~~~  "
    "~~  ~~*~"

    ************************

    Moo - Breeze - Stench
    "(Moo ~~("
    ")~~ Moo)"
    "( Moo ~("
    ")~  Moo)"

    Moo - Breeze - Glitter
    "Moo * ~~"
    "*~~  Moo"
    "Moo ~~* "
    "**  ~Moo"

    Breeze - Stench - Glitter
    "~~~) )*)"
    " ~( ( (*"
    "*) * )~~"
    "( *~(  ~"

    Moo - Breeze - Stench - Glitter
    "(Moo ~~("
    ")~~ * ~)"
    "( Moo ~("
    ")~  ** )"

  }
}
