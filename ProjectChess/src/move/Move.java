package move;

import board.Board;
import board.Tile;
import piece.*;

import java.io.Serializable;
import java.util.Arrays;

public class Move implements Serializable {

    public Board board;
    public String notation;
    public Piece piece;
    public Piece affectedPiece = null;
    public int transformedPieceId;
    public Tile startingTile;
    public Tile destinationTile;
    public Tile affectedStartingTile;
    public Tile affectedDestinationTile;
    public int type;    // 0 for normal, 1 for castle, 2 for en passant

    public Move(Piece piece, Tile startingTile , Tile destinationTile){
        this.piece = piece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.type = 0;

    }

    public Move(){
        this.type = 0;
    }

    public Move(Piece piece, Piece affectedPiece, Tile startingTile, Tile destinationTile){
        this.piece = piece;
        this.affectedPiece = affectedPiece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.type = 1;

    }

    public Move(Piece piece, Piece affectedPiece, Tile startingTile, Tile destinationTile, Tile affectedStaringTile, Tile affectedDestinationTile){
        this.piece = piece;
        this.affectedPiece = affectedPiece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.affectedStartingTile = affectedStaringTile;
        this.affectedDestinationTile = affectedDestinationTile;
        this.type = 2;
    }

    public Move(Piece piece, int transformedPieceId, Tile startingTile, Tile destinationTile){
        this.piece = piece;
        this.transformedPieceId = transformedPieceId;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.type = 3;
    }


    public boolean equals(Move move){
        return (this.startingTile.tileCoordinate[0] == move.startingTile.tileCoordinate[0]) &&
                (this.startingTile.tileCoordinate[1] == move.startingTile.tileCoordinate[1]) &&
                (this.destinationTile.tileCoordinate[0] == move.destinationTile.tileCoordinate[0]) &&
                (this.destinationTile.tileCoordinate[1] == move.destinationTile.tileCoordinate[1]);
    }

//    public String toString(){
//        if(this.type == 2){
//            if(affectedPiece.position[1]>piece.position[1]){
//                return "0-0";
//            }else{
//                return "0-0-0";
//            }
//        }else if(this.type == 3){
//            return "Promotion";
//        }else{
//            return this.piece.name + this.startingTile.tileCoordinate[0] + this.startingTile.tileCoordinate[1]+ "-"+ this.destinationTile.tileCoordinate[0] + this.destinationTile.tileCoordinate[1];
//        }
//
//    }

    public String toString(){

        return board.notation;

    }

}