package game;

import board.*;
import move.Move;
import piece.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    // Local Attributes ====================================================== //

    public int color;
    public List<Piece> pieceInventory;
    public List<Move> possibleMove = new ArrayList<>();

    // Constructors ========================================================== //

    public Player(Board board, int color){
        this.color = color;
        this.statusUpdate(board);
    }

    // Methods ============================================================== //

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
                this.possibleMove.addAll(moveGenerated);
            }
        }
    }

    // Update Possible Move for 1 Piece only, used for display on interface
    @SuppressWarnings("unused")
    public void updateSpecificMove(Board board){

    }
}