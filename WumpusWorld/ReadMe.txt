+------------------------------------------------------------------------------+
|   Project:   Wumplus World in Java                                           |
|   Class:     CSC 5240 - 001                                                  |
|   Date:      October 21, 2017                                                |
|   Team:      Darren Cunningham, Weston Smith                                 |
+------------------------------------------------------------------------------+

    __    __                       _             __    __           _     _
   / / /\ \ \_   _ _ __ ___  _ __ | |_   _ ___  / / /\ \ \___  _ __| | __| |
   \ \/  \/ / | | | '_ ` _ \| '_ \| | | | / __| \ \/  \/ / _ \| '__| |/ _` |
    \  /\  /| |_| | | | | | | |_) | | |_| \__ \  \  /\  / (_) | |  | | (_| |
     \/  \/  \__,_|_| |_| |_| .__/|_|\__,_|___/   \/  \/ \___/|_|  |_|\__,_|
  		                      |_|

Index...........................................................................

    1. How to Compile & Run the Program
    2. How to Use the Program
    3. How the Agent Works


How to Compile & Run the Program................................................

    PREREQUISITES:

        In order for this program to run properly, the host machine must have
        JDK version 9 installed. Please install the latest version of the Java
        Development Kit before proceeding.

    INCLUDED FILES:

        - WumpusWorld
           - src
             compile.sh
             WumpusDriver.jar
             *.java

   This program can be run in two different ways: either by running the included
   compile script, or by double-clicking on the included jar file. Details for
   both methods are listed below. Please note that due to the unreliability of
   displaying UNICODE I/O on Windows command prompt, it is recommended to run
   this program on a UNIX terminal for the best viewing experience.


   METHOD 1 - THE SCRIPT

      1. Open a bash terminal on your operating system of choice. On MacOS and
         Linux, this can be done natively in the terminal. On Windows, a program
         such as 'git bash' is needed to run the bash script. Alternatively,
         all commands can be run on shell.csc.tntech.edu.

      2. Maximize the terminal window. In order for the game to display
         properly, THE WINDOW MUST BE IN FULL SCREEN.

      3. Enter the following command to run the script without the '':
                './compile.sh'
         This script will clean all previous class files from the project,
         recompile all java files, and start the program. After running the
         script you should see the main menu of the game.

      *  Refer to 'How to Use the Program' for information on how to interact
         with the game.


   METHOD 2 - THE JAR FILE

      1. Open a new CMD or Terminal window, once again it is recommended to use
         a UNIX terminal in order for the Unicode characters to display properly
         although this will have no effect on gameplay.

      2. Navigate to the 'WumpusWorld/src' directory using your terminal of
         choice.

      3. Make sure to maximize the terminal window. In order for the game to
         display properly, THE WINDOW MUST BE IN FULL SCREEN.

      4. Enter the following command to execute the jar file:
                'java -jar WumpusWorld.jar'

      *  Refer to 'How to Use the Program' for information on how to interact
         with the game.


How to Use the Program..........................................................

    Upon running the program, you will be presented with a main menu like the
    one shown below.

              Please select from one of the following options.
              1) Start Game
              2) Set Objects
              3) Rules
              4) Help
              5) Quit

    A description of each menu option is as follows:

    1. START GAME - This begins the actual game with the Intelligent Agent
       playing as the main character, Tarzan. Before you choose this option,
       make sure to place all desired objects on the game board using option 2.

    2. SET OBJECTS - This contains a sub-menu of all the obstacles that can be
       added within the game. The obstacles and their description are listed
       below:

          WUMPUS: The monster of the cave is not happy to share his home with
                  you. Make sure to stay away from him or risk being eaten. You
                  will know when you run into him based on his foul smell.

                  * Only one Wumpus can be added to the board.

          SUPMUW: The Supmuw is a more docile creature that loves to share
                  snacks. He will also help you out if you run into a pit and
                  can be found by his gentle 'moo'ing. But don't be too
                  trusting because if the Supmuw can smell the Wumpus it will
                  start to behave like the Wumpus, adding just another hazard
                  to the deadly cave.

                  * Only one Supmuw can be added to the board.

             PIT: Be weary of the deep crevasses strewn throughout the cave.
                  A fall into one of these natural formations could be fatal,
                  just remember to look out for a breeze as their warning sign.

                  * Multiple pits can be added to the board.

            GOLD: This is what everyone is after. The gold is the most valuable
                  thing in the cave, and that is just what the Agent is after.
                  It is very elusive and has no indicators if it is nearby, but
                  finding it will be well worth the effort.

                  * Only one gold can be added to the board.

             NO
    TRESPASSING:  Not every part of the cave is accessible. These square have
                  no indicator and no penalties. The agent is not allowed in
                  these areas.

                  * Multiple No Trespassing zones can be added to the board.

        Each object can be specified in this sub-menu including the number of
        objects for pits and no trespassing, and the X,Y coordinates of the
        object on the 10 x 10 game board.

     3. RULES - This screen shows the rules of the game.

     4. HELP - The help screen gives users an idea of what to do next when
        playing the game.

     5. QUIT - If you decide that this game is just too much for you, you can
        always exit by choosing this option.


How the Agent Works.............................................................

    In a world of fight or flight, our agent prefers the latter. The agent
    can easily get discouraged if it doesn't find the gold within a reasonable
    amount of time, but what it is good at is avoiding obstacles. The agent's
    main focus is to determine the safest path to traverse the cave and make
    it out alive.

    At the heart of our agent, that was so honorably named Tarzan, lies a
    hazard counter. By taking information from his current and previous
    positions, Tarzan calculates a number to represent the risk of entering a
    particular square by combining the chance of a Wumpus, Supmuw, or Pit being
    in an adjacent square along with providing incentives for exploring new
    territory.

    The hazard counter has three main values: positive, negative, and zero. When
    the hazard counter is positive, it indicates to Tarzan that the square is
    dangerous; the higher the number, the more danger that lies beyond. When the
    hazard counter is negative, it tells Tarzan that the square is safe to enter.
    Finally, a hazard counter of zero indicates a previously visited square.

    When the each node's hazard counter is compounded over several moves, Tarzan
    gets a better picture of the environment just beyond his reach. In testing,
    Tarzan was able to consistently avoid objects after surveying just two
    indicator squares. Tarzan is then able to calculate other adjacent squares
    that may also contain the object and travel around all suspected objects to
    gather more information.

    If the hazard counter for a particular square becomes high enough, and it
    also contains the indicator for a nearby Wumpus, Tarzan is able to kill the
    Wumpus with no missed attempts.

    While the gold may remain elusive unless stumbled upon, Tarzan truly excels
    in finding the safest path around a cave lurking with danger. 
