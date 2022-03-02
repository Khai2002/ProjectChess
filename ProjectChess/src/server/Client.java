package server;

import board.Board;
import move.Move;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Scanner scanner = new Scanner(System.in);
    int moveNumber;
    Move chosenMove;

    public static void main(String[] args) throws Exception {
        new Client();
    }

    public Client() throws Exception {

        Socket socket = new Socket("localhost", Server.PORT);

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        Board board = new Board(Board.startFEN);
        outputStream.writeObject(board);

        for(int i = 0; i<2;i++){
            Board newBoard = (Board) inputStream.readObject();
            newBoard.printBoard();

            System.out.println("Choose your move: ");

            if(newBoard.colorActive == 1){
                System.out.println(newBoard.whiteMoves);
                moveNumber = scanner.nextInt();
                chosenMove = newBoard.whiteMoves.get(moveNumber);
            }else{
                System.out.println(newBoard.blackMoves);
                moveNumber = scanner.nextInt();
                chosenMove = newBoard.blackMoves.get(moveNumber);
            }
            System.out.println("Your move is "+chosenMove);

            Board sndBoard = new Board(newBoard,chosenMove,0);
            outputStream.writeObject(sndBoard);
        }


        socket.close();
        outputStream.close();

    }
}
