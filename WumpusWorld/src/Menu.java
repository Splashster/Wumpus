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


      WumplusTitle title = new WumplusTitle();
      title.print();

      System.out.println("Please select from one of the following options.");
      System.out.println("1) Start Game");
      System.out.println("2) Set Objects");
      System.out.println("3) Rules");
      System.out.println("4) Help");
      System.out.println("5) Quit");
      System.out.print("Selection Choice: ");
      if(reader.hasNextInt()){
          choice = reader.nextInt();
      }else{
          reader.next();
          System.out.println("Invalid selection!");
          mainMenu();
      }



      if(choice == 1){
         if(g == null){
           System.out.println("You must set gold on the map!");
           mainMenu();
         }else{
          //System.out.println("Wumpus " + w.getX() + " " + w.getY());
          //System.out.println("Sup " + sup.getX() + " " + sup.getY());
          //System.out.println("Gold location " + g.getX() + " " + g.getY());
          //System.out.println("Get Pit " + p[0].getX() + " " + p[0].getY());
          //System.out.println("Get Pass " + noPass[0].getX() + " " + noPass[0].getY());

           WumplusWorld ww = new WumplusWorld();
           ww.startGame(w, sup, noPass, p, g);
         }
      }else if(choice == 2){
          setPieces();
      }else if(choice == 3){
          System.out.println("The first rule of Wumplus World is: You do not talk about Wumplus World");
          System.out.println("The second rule of Wumplus World is: YOU DO NOT TALK ABOUT WUMPLUS WORLD!");
          mainMenu();
      }else if(choice == 4){
          System.out.println("There is no help.....");
          mainMenu();
      }else if(choice == 5){
          System.out.println("Hope you had fun! Goodbye!");
          System.exit(0);
      }else{
          System.out.println("Invalid selection!");
          mainMenu();
      }
  }

  public static void setPieces(){
    int choice = 0;
    int x = 0, y = 0, pitCount = 0, noPassCount = 0;

    System.out.println("\nPlease select from one of the following options.");
    System.out.println("1) Place Wumpus");
    System.out.println("2) Place Supmuw");
    System.out.println("3) Place Gold");
    System.out.println("4) Place Pits");
    System.out.println("5) Place No Trespassing Zones");
    System.out.println("6) Return to Main Menu");
    System.out.print("Selection Choice: ");
    try{choice = reader.nextInt();}catch(Exception e){
      System.out.println("Invalid Selection!");
      setPieces();
    }

    if(choice == 1){
        System.out.print("Please choose an x coordinate value between 0 and 9 for the Wumpus: ");
        x = reader.nextInt();
        while(x > 10){
          System.out.println("x coordinate must be betwen 0 and 9!");
          System.out.print("Please choose an x coordinate value between 0 and 9 for the Wumpus: ");
          x = reader.nextInt();
        }
        System.out.print("Please choose an y coordinate value between 0 and 9 for the Wumpus: ");
        y = reader.nextInt();
        while(y > 10){
          System.out.println("y coordinate must be betwen 0 and 9!");
          System.out.print("Please choose an y coordinate value between 0 and 9 for the Wumpus: ");
          y = reader.nextInt();
        }
        w = new coordinate(x,y);
    }else if (choice == 2){
        System.out.print("Please choose an x coordinate value between 0 and 9 for the Supmuw: ");
        x = reader.nextInt();
        while(x > 10){
          System.out.println("x coordinate must be betwen 0 and 9!");
          System.out.print("Please choose an x coordinate value between 0 and 9 for the Supmuw: ");
          x = reader.nextInt();
        }
        System.out.print("Please choose an y coordinate value between 0 and 9 for the Supmuw: ");
        y = reader.nextInt();
        while(y > 10){
          System.out.println("y coordinate must be betwen 0 and 9!");
          System.out.print("Please choose an y coordinate value between 0 and 9 for the Supmuw: ");
          y = reader.nextInt();
        }
        sup = new coordinate(x,y);
    }else if(choice == 3){
        System.out.print("Please choose an x coordinate value between 0 and 9 for the Gold: ");
        x = reader.nextInt();
        while(x > 10){
          System.out.println("x coordinate must be betwen 0 and 9!");
          System.out.print("Please choose an x coordinate value between 0 and 9 for the Gold: ");
          x = reader.nextInt();
        }
        System.out.print("Please choose an y coordinate value between 0 and 9 for the Gold: ");
        y = reader.nextInt();
        while(y > 10){
          System.out.println("y coordinate must be betwen 0 and 9!");
          System.out.print("Please choose an y coordinate value between 0 and 9 for the Gold: ");
          y = reader.nextInt();
        }
        g = new coordinate(x,y);
    }else if(choice == 4){
        System.out.print("Please enter number of Pits: ");
        pitCount = reader.nextInt();
        p = new coordinate[pitCount];
        for(int i = 0; i < pitCount; i++){
          System.out.print("Please choose an x coordinate value between 0 and 9 for Pit " + i + " : ");
          x = reader.nextInt();
          while(x > 10){
            System.out.println("x coordinate must be betwen 0 and 9!");
            System.out.print("Please choose an x coordinate value between 0 and 9 for Pit " + i + " : ");
            x = reader.nextInt();
          }
          System.out.print("Please choose an y coordinate value between 0 and 9 for Pit " + i + " : ");
          y = reader.nextInt();
          while(y > 10){
            System.out.println("y coordinate must be betwen 0 and 9!");
            System.out.print("Please choose an y coordinate value between 0 and 9 for Pit " + i + " : ");
            y = reader.nextInt();
          }
          p[i] = new coordinate(x,y);
        }
    }else if(choice == 5){
        System.out.print("Please enter number of NoTrespass Zones: ");
        noPassCount = reader.nextInt();
        noPass = new coordinate[noPassCount];
        for(int i = 0; i < noPassCount; i++){
          System.out.print("Please choose an x coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
          x = reader.nextInt();
          while(x > 10){
            System.out.println("x coordinate must be betwen 0 and 9!");
            System.out.print("Please choose an x coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
            x = reader.nextInt();
          }
          System.out.print("Please choose an y coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
          y = reader.nextInt();
          while(y > 10){
            System.out.println("y coordinate must be betwen 0 and 9!");
            System.out.print("Please choose an y coordinate value between 0 and 9 for NoTrespass Zone " + i + " : ");
            y = reader.nextInt();
          }
          noPass[i] = new coordinate(x,y);
        }
    }else if(choice == 6){
        mainMenu();
    }else{
        System.out.println("Invalid selection!");
        setPieces();
    }
    setPieces();
  }

}
