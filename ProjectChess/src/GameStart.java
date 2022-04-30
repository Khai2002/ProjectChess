import board.Board;
import game.Loop;
import gui.Interface;
import gui.MainMenu;
import server.Client;
import server.Server;


public class GameStart {
    public static void main(String[] args) throws Exception {
        MainMenu mainMenu = new MainMenu();

        while(true){

            System.out.println("Taking new inputs");
            while(mainMenu.choice == 0){
                System.out.print("");
            }
            System.out.println("The input is "+mainMenu.choice);

            if(mainMenu.choice == 1){

                Board board = new Board(Board.startFEN);
                Interface interFace = new Interface(board);
                Loop loop = new Loop();
                loop.gameLoopHumanMachineBetter(board,interFace);
                mainMenu.choice = 0;

            }else if(mainMenu.choice == 2){
                new Server();
                System.out.println("Im out");
                mainMenu.choice = 0;

            }else if(mainMenu.choice == 3){

                boolean checker = true;
                while(checker){
                    try{
                        new Client(mainMenu.ip.getText(), mainMenu);
                        checker = false;
                    }catch (Exception e){
                        mainMenu.fault.setText("IP Address incorrect, or Host not active");
                    }
                }

            }else if(mainMenu.choice == 4){
                System.exit(0);
            }
        }



    }
}
