package server;

import board.Board;
import game.Loop;
import gui.ChessTime;
import gui.ConnectionWindow;
import gui.Interface;
import gui.WindowTheEnd;
import move.Move;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static final int PORT = 6969;

    Board board;
    Interface interFace;
    int color;
    String ip;
    ConnectionWindow connectionWindow = new ConnectionWindow();

    public static void main(String[] args) throws Exception {
        new Server();
    }

    public Server() throws Exception {

        // Init Socket
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is operational on port " + PORT);
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        }
        connectionWindow.ipShow.setText(connectionWindow.ipShow.getText() + ip);

        Socket socket = serverSocket.accept(); //  Accept incoming socket

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());



        if(socket.isConnected()){
            // Change connection Window
            connectionWindow.checkConnection = true;
            while(!connectionWindow.readyToPlay){
                System.out.print("");
            }

            String chosenTime = (String) connectionWindow.TempPartie.getSelectedItem();
            int timeControl = Character.getNumericValue(chosenTime.charAt(0));
            System.out.println(timeControl);

            connectionWindow.dispose();
            outputStream.write(timeControl);
            outputStream.flush();

            // Init Board
            this.color = 1;
            board = new Board(Board.startFEN);
            interFace = new Interface(board);
            interFace.setTitle("Chess Server");

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
                interFace.timer1 = new ChessTime(timeControl, 0, -1);
            }
            if(interFace.timer2==null){
                interFace.timer2 = new ChessTime(timeControl,0,1);
            }

            interFace.timer2.start();
            interFace.timer1.start();
            interFace.timer1.freezetime();

            interFace.TempPartie.setEditable(false);

            // Game Loop
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
        }

        serverSocket.close();

    }

}
