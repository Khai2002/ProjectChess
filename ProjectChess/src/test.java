import board.*;

import game.Player;
import move.Move;
import piece.*;
import game.*;
import java.util.*;


import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
    
        Scanner sc= new Scanner(System.in);
        long avantTraitement = System.currentTimeMillis();


        Board board = new Board(Board.castle2FEN);
        board.printBoard();

        System.out.println(board.whiteMoves);
        System.out.println(board.blackMoves);

        Loop loop = new Loop();
        loop.gameLoop(board, sc);

        //System.out.println(board.board[0][0].pieceOnTile.getClass().getName());
        //System.out.println(board.board[0][0].pieceOnTile instanceof Rook);

        for(int i = 0; i<4; i++){System.out.println(board.castleAvailable[i]);}

        for(int i = 0; i<4; i++){System.out.println(board.castleCurrentAvailable[i]);}






        /*

        System.out.println(board.isWhiteInCheck);
        System.out.println(board.isBlackInCheck);

        System.out.println(board.isWhiteInCheckMate);
        System.out.println(board.isBlackInCheckMate);

        System.out.println(board.isWhiteInStaleMate);
        System.out.println(board.isBlackInStaleMate);



        //Player white = new Player(board,1);
        //System.out.println(white.possibleMove);






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
