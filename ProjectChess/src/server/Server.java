package server;

import board.Board;
import move.Move;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final int PORT = 6969;

    private Scanner scanner = new Scanner(System.in);
    int moveNumber;
    Move chosenMove;

    public static void main(String[] args) throws Exception {
        new Server();
    }

    public Server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is operational on port " + PORT);
        Socket socket = serverSocket.accept(); //  Accept incoming socket

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        for(int i = 0; i<3; i++){
            Board rcvBoard = (Board)inputStream.readObject();
            System.out.println("Received Board Information");
            rcvBoard.printBoard();

            System.out.println("Choose your move: ");

            if(rcvBoard.colorActive == 1){
                System.out.println(rcvBoard.whiteMoves);
                moveNumber = scanner.nextInt();
                chosenMove = rcvBoard.whiteMoves.get(moveNumber);
            }else{
                System.out.println(rcvBoard.blackMoves);
                moveNumber = scanner.nextInt();
                chosenMove = rcvBoard.blackMoves.get(moveNumber);
            }
            System.out.println("Your move is "+chosenMove);
            System.out.println();

            Board sndBoard = new Board(rcvBoard,chosenMove,true,0);
            outputStream.writeObject(sndBoard);
        }


        serverSocket.close();


    }

}
