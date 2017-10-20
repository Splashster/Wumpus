/***********************************************************************
Class to print game title and game over screen
************************************************************************/
public class WumplusTitle{
  public void print(){
    //print game title
    System.out.println("\t\t                                              __    __                       _             __    __           _     _ ");
    System.out.println("\t\t                                             / / /\\ \\ \\_   _ _ __ ___  _ __ | |_   _ ___  / / /\\ \\ \\___  _ __| | __| |");
    System.out.println("\t\t                                             \\ \\/  \\/ / | | | '_ ` _ \\| '_ \\| | | | / __| \\ \\/  \\/ / _ \\| '__| |/ _` |");
    System.out.println("\t\t                                              \\  /\\  /| |_| | | | | | | |_) | | |_| \\__ \\  \\  /\\  / (_) | |  | | (_| |");
    System.out.println("\t\t                                               \\/  \\/  \\__,_|_| |_| |_| .__/|_|\\__,_|___/   \\/  \\/ \\___/|_|  |_|\\__,_|");
    System.out.println("\t\t                                                                      |_|                                             ");
    System.out.println("\n\t\t                                                                   Welcome to the Wumplus World Game!");
  }

  public void printClose(int score){
    //print game over screen
    System.out.print("\033[H\033[2J");
    System.out.println("\t\t                                              __    __                       _             __    __           _     _ ");
    System.out.println("\t\t                                             / / /\\ \\ \\_   _ _ __ ___  _ __ | |_   _ ___  / / /\\ \\ \\___  _ __| | __| |");
    System.out.println("\t\t                                             \\ \\/  \\/ / | | | '_ ` _ \\| '_ \\| | | | / __| \\ \\/  \\/ / _ \\| '__| |/ _` |");
    System.out.println("\t\t                                              \\  /\\  /| |_| | | | | | | |_) | | |_| \\__ \\  \\  /\\  / (_) | |  | | (_| |");
    System.out.println("\t\t                                               \\/  \\/  \\__,_|_| |_| |_| .__/|_|\\__,_|___/   \\/  \\/ \\___/|_|  |_|\\__,_|");
    System.out.println("\t\t                                                                      |_|                                             ");
    System.out.println("\n\t\t                                                                   Were you able to defeat the Wumpus?");

    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t                                                  (`-')  _ <-. (`-')   (`-')  _                     (`-') (`-')  _   (`-')     ");
    System.out.println("\t\t                                           .->    (OO ).-/    \\(OO )_  ( OO).-/         .->        _(OO ) ( OO).-/<-.(OO )     ");
    System.out.println("\t\t                                        ,---(`-') / ,---.  ,--./  ,-.)(,------.    (`-')----. ,--.(_/,-.\\(,------.,------,)    ");
    System.out.println("\t\t                                       '  .-(OO ) | \\ /`.\\ |   `.'   | |  .---'    ( OO).-.  '\\   \\ / (_/ |  .---'|   /`. '    ");
    System.out.println("\t\t                                       |  | .-, \\ '-'|_.' ||  |'.'|  |(|  '--.     ( _) | |  | \\   /   / (|  '--. |  |_.' |    ");
    System.out.println("\t\t                                       |  | '.(_/(|  .-.  ||  |   |  | |  .--'      \\|  |)|  |_ \\     /_) |  .--' |  .   .'    ");
    System.out.println("\t\t                                       |  '-'  |  |  | |  ||  |   |  | |  `---.      '  '-'  '\\-'\\   /    |  `---.|  |\\  \\     ");
    System.out.println("\t\t                                        `-----'   `--' `--'`--'   `--' `------'       `-----'     `-'     `------'`--' '--'    \n\n\n\n");
    System.out.println("\t\t                                                                         High Score: " + score + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }
}
