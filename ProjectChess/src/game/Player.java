package game;

import board.*;
import move.Move;
import piece.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {

    public int color;
    public List<Piece> pieceInventory;
    public List<Move> possibleMove = new ArrayList<>();

    public Player(Board board, int color){
        this.color = color;
        this.statusUpdate(board);
    }

    // Combination of 2 updates
    public void statusUpdate(Board board){
        this.updateInventory(board);
        this.updateAllMove(board);
    }

    // Updates Pieces in inventory
    public void updateInventory(Board board){
        this.pieceInventory = board.getPiece(this.color);
    }

    // Update Possible Moves List
    public void updateAllMove(Board board){
        List<Move> moveGenerated;
        for(Piece piece: pieceInventory){
            piece.generateMove(board.board);
            moveGenerated = piece.listMove;
            if(moveGenerated != null){
                for(Move move:moveGenerated){
                    this.possibleMove.add(move);
                }
            }
        }
    }
}
