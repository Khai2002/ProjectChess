import board.*;

import game.Player;
import move.Move;
import piece.*;


import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        long avantTraitement = System.currentTimeMillis();

        Board board = new Board(Board.startFEN);
        board.printBoard();


        Player white = new Player(board, 1);


        List<Piece> list_piece = white.pieceInventory;
        System.out.println(list_piece);

        List<Move> list_move = white.possibleMove;
        System.out.println(list_move);



        //List<Move> listMove = board.board[3][6].pieceOnTile.generateMove(board.board);
        //System.out.println(listMove.size());
        //List<Piece> list_piece = board.getPiece(-1);


        long apresTraitement = System.currentTimeMillis();
        System.out.println("Duree:" + (apresTraitement-avantTraitement));



    }


}
