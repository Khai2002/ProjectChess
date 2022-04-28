package server;

import board.Board;
import gui.ChessTime;
import gui.ConnectionWindowClient;
import gui.Interface;
import gui.WindowTheEnd;
import move.Move;
import game.Loop;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Stream;

public class Client {

    private Scanner scanner = new Scanner(System.in);
    int moveNumber;
    Move chosenMove;
    Board board;
    Interface interFace;
    int color;
    ConnectionWindowClient connectionWindowClient;
    int readyOrNot = 0;


    public static void main(String[] args) throws Exception {
        new Client("localhost");
    }

    public Client(String ip) throws Exception {
        // Init Socket
        Socket socket = new Socket(ip, Server.PORT);

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        // Connection Window
        connectionWindowClient = new ConnectionWindowClient();

        while(inputStream.available() != 0);
        readyOrNot = inputStream.read();
        System.out.println(readyOrNot);
        connectionWindowClient.dispose();



        // Init Board
        this.color = -1;
        board = new Board(Board.startFEN);
        interFace = new Interface(board);
        interFace.setTitle("Chess Client");

        interFace.Page1.setVisible(false);
        interFace.Page2.setVisible(true);
        interFace.Page3.setVisible(false);
        interFace.ChoixPage1.setBackground(new Color(34, 32, 32));
        interFace.ChoixPage1.setForeground(new Color(233, 233, 233));
        interFace.ChoixPage2.setBackground(new Color(233, 233, 233));
        interFace.ChoixPage2.setForeground(new Color(34, 32, 32));
        interFace.ChoixPage3.setBackground(new Color(34, 32, 32));
        interFace.ChoixPage3.setForeground(new Color(233, 233, 233));

        interFace.chessBoard.isInGame = true;
        interFace.menu1.isInGame = true;
        interFace.menu2.isInGame = true;
        interFace.menu1.checkForAvatar.stop();
        interFace.menu2.checkForAvatar.stop();
        interFace.timerUpdate.start();

        if(interFace.timer1==null){
            interFace.timer1 = new ChessTime(readyOrNot, 0, -1);
        }
        if(interFace.timer2==null){
            interFace.timer2 = new ChessTime(readyOrNot,0,1);
        }

        interFace.timer2.start();
        interFace.timer1.start();
        interFace.timer1.freezetime();

        interFace.TempPartie.setEditable(false);


        // Init Game
        while(Loop.gameEnd(board) == 99){
            if(board.colorActive == this.color){
                interFace.chessBoard.definedMove = null;
                while(interFace.chessBoard.definedMove == null){
                    System.out.print("");
                }
                board = new Board(board, interFace.chessBoard.definedMove,true,0);
                interFace.chessBoard.definedMove = null;

                interFace.history.moves.add(board.previousMove.toString());

                while(interFace.history.moves.size()>30){
                    interFace.history.moves.removeFirst();
                    interFace.history.moves.removeFirst();
                    interFace.history.index+=1;
                }

                interFace.updateInterface(board);

                outputStream.writeObject(board);

                if(Loop.gameEnd(board)!=99){
                    break;
                }
            }
            interFace.chessBoard.definedMove = null;
            while(inputStream.available() != 0);
            board = (Board)inputStream.readObject();

            interFace.history.moves.add(board.previousMove.toString());

            while(interFace.history.moves.size()>30){
                interFace.history.moves.removeFirst();
                interFace.history.moves.removeFirst();
                interFace.history.index+=1;
            }
            interFace.chessBoard.definedMove = null;
            interFace.updateInterface(board);

        }

        interFace.dispose();

        if(Loop.gameEnd(board)==1){
            new WindowTheEnd("Black wins");
        }else{
            new WindowTheEnd("White wins");
        }


        socket.close();
        outputStream.close();

    }
}
