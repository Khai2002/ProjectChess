import board.Board;
import board.Tile;
import move.Move;
import piece.*;

import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        long avantTraitement = System.currentTimeMillis();

        Board board = new Board(Board.startFEN);
        board.printBoard();



        List<Move> listMove = board.board[3][6].pieceOnTile.generateMove(board.board);

        System.out.println(listMove.size());


        long apresTraitement = System.currentTimeMillis();
        System.out.println("Duree:" + (apresTraitement-avantTraitement));



    }


}
