import java.util.Scanner;

public class Menu{
  private static coordinate s;
  private static coordinate w;
  private static coordinate sup;
  private static coordinate[] noPass;
  private static coordinate[] p;
  private static coordinate g;
  private static int x;
  private static int y;
  private static Scanner reader;

  public Menu(){
      reader = new Scanner(System.in);
  }

  public static void mainMenu(){
      int choice = 0;

      //Clear the screen
      System.out.print("\033[H\033[2J");
      WumplusTitle title = new WumplusTitle();
      title.print();
      System.out.println("\n\n\t\t\t\t\t\t\t   Please select from one of the following options.");
      System.out.println("\t\t\t\t\t\t\t   1) Start Game");
      System.out.println("\t\t\t\t\t\t\t   2) Set Objects");
      System.out.println("\t\t\t\t\t\t\t   3) Rules");
      System.out.println("\t\t\t\t\t\t\t   4) Help");
      System.out.println("\t\t\t\t\t\t\t   5) Quit");
      System.out.print("\t\t\t\t\t\t\t   Selection Choice: ");
      if(reader.hasNextInt()){
          choice = reader.nextInt();
      }else{
          reader.next();
          System.out.println("\t\t\t\t\t\t\t   Invalid selection!");
          mainMenu();
      }



      if(choice == 1){
         if(g == null){
           System.out.println("\t\t\t\t\t\t\t   You must set gold on the map!");
           mainMenu();
         }else{
          //System.out.println("Wumpus " + w.getX() + " " + w.getY());
          //System.out.println("Sup " + sup.getX() + " " + sup.getY());
          //System.out.println("Gold location " + g.getX() + " " + g.getY());
          //System.out.println("Get Pit " + p[0].getX() + " " + p[0].getY());
          //System.out.println("Get Pass " + noPass[0].getX() + " " + noPass[0].getY());

           WumplusWorld ww = new WumplusWorld();
           Agent agent = new Agent(ww);
           ww.setAgent(agent);
           ww.startGame(w, sup, noPass, p, g);
         }
      }else if(choice == 2){
          setPieces();
      }else if(choice == 3){
          System.out.println("\n\n\t\t\t\t\t\t\t   -------------------------------- RULES ---------------------------------");
          System.out.println("\n\t\t\t\t\t\t\t     1. Stay away from the Wumpus.");
          System.out.println("\n\t\t\t\t\t\t\t     2. Don't fall in a pit.");
          System.out.println("\n\t\t\t\t\t\t\t     3. The Supmuw is your friend...sometimes.");
          System.out.println("\n\t\t\t\t\t\t\t     4. You only have one arrow, use it wisely.");
          System.out.println("\n\t\t\t\t\t\t\t     5. When you find the gold, get out of the cave immediately!");
          System.out.println("\n\t\t\t\t\t\t\t     6. You cannot enter 'No Trespassing' zones because they do not allow, ");
          System.out.println("\n\t\t\t\t\t\t\t        well...trespassing.");
          System.out.println("\n\t\t\t\t\t\t\t     7. One more thing...you won't be abot to see the map, only the ground");
          System.out.println("\n\t\t\t\t\t\t\t        on which you stand.");
          System.out.println("\n\n\t\t\t\t\t\t\t\t\t     ~ Press ANY KEY + ENTER to go back. ~");
          reader.next();
          mainMenu();
      }else if(choice == 4){
          System.out.println("\n\n\t\t\t\t\t\t\t   --------------------------------- HELP ---------------------------------");
          System.out.println("\n\t\t\t\t\t\t\t   - Select option '2' from the main menu to place obstacles on the board. ");
          System.out.println("\n\t\t\t\t\t\t\t   - The agent will play the game for you, so just sit back and enjoy the ");
          System.out.println("\n\t\t\t\t\t\t\t     show! ");
          System.out.println("\n\n\t\t\t\t\t\t\t\t\t     ~ Press ANY KEY + ENTER to go back. ~");
          reader.next();
          mainMenu();
      }else if(choice == 5){
          System.out.println("\n\n\t\t\t\t\t\t\t   ---------------------------------- FIN ---------------------------------");
          System.out.println("\n\t\t\t\t\t\t\t    Caves are not for the light-hearted...perhaps our next adventurer will ");
          System.out.println("\n\t\t\t\t\t\t\t                          conquer the WUMPLUS WORLD.                       ");
          System.out.println("\n\n\t\t\t\t\t\t\t\t\t       ~ Press ANY KEY + ENTER to quit. ~");
          reader.next();
          title.printClose();
          System.exit(0);
      }else{
          System.out.println("\t\t\t\t\t\t\t   Invalid selection!");
          mainMenu();
      }
  }

  public static void setPieces(){
    int choice = 0;
    int x = 0, y = 0, pitCount = 0, noPassCount = 0;

    System.out.print("\033[H\033[2J");
    WumplusTitle title = new WumplusTitle();
    title.print();

    System.out.println("\n\n\t\t\t\t\t\t\t   Please select from one of the following options.");
    System.out.println("\t\t\t\t\t\t\t   1) Place Wumpus");
    System.out.println("\t\t\t\t\t\t\t   2) Place Supmuw");
    System.out.println("\t\t\t\t\t\t\t   3) Place Gold");
    System.out.println("\t\t\t\t\t\t\t   4) Place Pits");
    System.out.println("\t\t\t\t\t\t\t   5) Place No Trespassing Zones");
    System.out.println("\t\t\t\t\t\t\t   6) Return to Main Menu");
    System.out.print("\n\t\t\t\t\t\t\t   Selection Choice: ");
    try{choice = reader.nextInt();}catch(Exception e){
      System.out.println("\t\t\t\t\t\t\t   Invalid Selection!");
      setPieces();
    }

    if(choice == 1){
        System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Wumpus: ");
        x = reader.nextInt();
        while(x > 10){
          System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Wumpus: ");
          x = reader.nextInt();
        }
        System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Wumpus: ");
        y = reader.nextInt();
        while(y > 10){
          System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Wumpus: ");
          y = reader.nextInt();
        }
        w = new coordinate(x,y);
        while(!w.canPlaceObject(p, null)){
          System.out.println("\t\t\t\t\t\t\t   The wumpus cannot be placed at position " + x + " " + y + " because there is a pit already there!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Wumpus: ");
          x = reader.nextInt();
          while(x > 10){
            System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Wumpus: ");
            x = reader.nextInt();
          }
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Wumpus: ");
          y = reader.nextInt();
          while(y > 10){
            System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Wumpus: ");
            y = reader.nextInt();
          }
          w = new coordinate(x,y);
        }
    }else if (choice == 2){
        System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Supmuw: ");
        x = reader.nextInt();
        while(x > 10){
          System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Supmuw: ");
          x = reader.nextInt();
        }
        System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Supmuw: ");
        y = reader.nextInt();
        while(y > 10){
          System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Supmuw: ");
          y = reader.nextInt();
        }
        sup = new coordinate(x,y);
    }else if(choice == 3){
        System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Gold: ");
        x = reader.nextInt();
        while(x > 10){
          System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Gold: ");
          x = reader.nextInt();
        }
        System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Gold: ");
        y = reader.nextInt();
        while(y > 10){
          System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Gold: ");
          y = reader.nextInt();
        }
        g = new coordinate(x,y);
        while(!g.canPlaceObject(p,null) || !g.canPlaceObject(noPass,null) ){
          System.out.println("\t\t\t\t\t\t\t   The gold cannot be placed at position " + x + " " + y + " because there is a pit or no trespassing zone already there!");
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Gold: ");
          x = reader.nextInt();
          while(x > 10){
            System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for the Gold: ");
            x = reader.nextInt();
          }
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Gold: ");
          y = reader.nextInt();
          while(y > 10){
            System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for the Gold: ");
            y = reader.nextInt();
          }
          g = new coordinate(x,y);
        }
    }else if(choice == 4){
        System.out.print("\n\n\t\t\t\t\t\t\t   Please enter number of Pits: ");
        pitCount = reader.nextInt();
        p = new coordinate[pitCount];
        for(int i = 0; i < pitCount; i++){
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for Pit " + i + " : ");
          x = reader.nextInt();
          while(x > 10){
            System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for Pit " + i + " : ");
            x = reader.nextInt();
          }
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for Pit " + i + " : ");
          y = reader.nextInt();
          while(y > 10){
            System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for Pit " + i + " : ");
            y = reader.nextInt();
          }
          p[i] = new coordinate(x,y);
          while(!p[i].canPlaceObject(null,w) || !p[i].canPlaceObject(null,g)){
            System.out.println("\t\t\t\t\t\t\t   The pit cannot be placed at position " + x + " " + y + " because the wumpus or gold is already there!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for Pit " + i + " : ");
            x = reader.nextInt();
            while(x > 10){
              System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
              System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for Pit " + i + " : ");
              x = reader.nextInt();
            }
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for Pit " + i + " : ");
            y = reader.nextInt();
            while(y > 10){
              System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
              System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for Pit " + i + " : ");
              y = reader.nextInt();
            }
            p[i] = new coordinate(x,y);
          }
        }
    }else if(choice == 5){
        System.out.print("\n\n\t\t\t\t\t\t\t   Please enter number of NoTrespass Zones: ");
        noPassCount = reader.nextInt();
        noPass = new coordinate[noPassCount];
        for(int i = 0; i < noPassCount; i++){
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
          x = reader.nextInt();
          while(x > 10){
            System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
            x = reader.nextInt();
          }
          System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
          y = reader.nextInt();
          while(y > 10){
            System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
            y = reader.nextInt();
          }
          noPass[i] = new coordinate(x,y);
          while(!noPass[i].canPlaceObject(null,g)){
            System.out.println("\t\t\t\t\t\t\t   The no trepass zone cannot be placed at position " + x + " " + y + " because the gold is already there!");
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
            x = reader.nextInt();
            while(x > 10){
              System.out.println("\t\t\t\t\t\t\t   x coordinate must be betwen 0 and 9!");
              System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an x coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
              x = reader.nextInt();
            }
            System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
            y = reader.nextInt();
            while(y > 10){
              System.out.println("\t\t\t\t\t\t\t   y coordinate must be betwen 0 and 9!");
              System.out.print("\n\n\t\t\t\t\t\t\t   Please choose an y coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
              y = reader.nextInt();
            }
            noPass[i] = new coordinate(x,y);
          }
        }
    }else if(choice == 6){
        mainMenu();
    }else{
        System.out.println("\t\t\t\t\t\t\t   Invalid selection!");
        setPieces();
    }
    setPieces();
  }

}
