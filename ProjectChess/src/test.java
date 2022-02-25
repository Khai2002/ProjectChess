import board.*;

import game.Player;
import move.Move;
import piece.*;


import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        long avantTraitement = System.currentTimeMillis();


        Board board = new Board(Board.testFEN);
        board.printBoard();

        System.out.println(board.whiteMoves);
        System.out.println(board.blackMoves);

        System.out.println(board.isWhiteInCheck);
        System.out.println(board.isBlackInCheck);



        //Player white = new Player(board,1);
        //System.out.println(white.possibleMove);



        /*


        String str = board.BoardToFen();
        System.out.println(str);

        //Move move = new Move(board.board[6][4].pieceOnTile,board.board[6][4], board.board[5][4]);

        //Board newBoard = new Board(board,move);
        //newBoard.printBoard();




        for(boolean bool:board.castleAvailable){
            System.out.println(bool);
        }

        for(int in:board.enPassantTileCoordinate){
            System.out.println(in);
        }


        Player white = new Player(board, 1);


        List<Piece> list_piece = white.pieceInventory;
        System.out.println(list_piece);

        List<Move> list_move = white.possibleMove;
        System.out.println(list_move);

         */



        //List<Move> listMove = board.board[3][6].pieceOnTile.generateMove(board.board);
        //System.out.println(listMove.size());
        //List<Piece> list_piece = board.getPiece(-1);


        long apresTraitement = System.currentTimeMillis();
        System.out.println("Duree:" + (apresTraitement-avantTraitement));



    }


}
