package move;

import board.Tile;
import piece.*;

import java.io.Serializable;

public class Move implements Serializable {

    public String notation;
    public Piece piece;
    public Piece affectedPiece = null;
    public Tile startingTile;
    public Tile destinationTile;
    public Tile affectedStartingTile;
    public Tile affectedDestinationTile;
    public int type;    // 0 for normal, 1 for castle, 2 for en passant
    
    public Move(){
        
    }
    
    public Move(Piece piece, Tile startingTile , Tile destinationTile){
        this.piece = piece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.notation = this.createNotation();
        this.type = 0;
    }

    public Move(Piece piece, Piece affectedPiece, Tile startingTile, Tile destinationTile){
        this.piece = piece;
        this.affectedPiece = affectedPiece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.notation = this.createNotation();
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

    public String createNotation(){
        // anh viet code trong nay nhe, dung 3 variable ma em cho them vao ay
        // Co vi tri bat dau, vi tri ket thuc va quan co

        // Quan co

        // No co an hay ko

        // No co bi ambiguity ko?

        // Special Moves
        return null;
    }

    public String toString(){
        if(this.type == 2){
            if(affectedPiece.position[1]>piece.position[1]){
                return "0-0";
            }else{
                return "0-0-0";
            }
        }
        return this.piece.name + this.startingTile.tileCoordinate[0] + this.startingTile.tileCoordinate[1]+ "-"+ this.destinationTile.tileCoordinate[0] + this.destinationTile.tileCoordinate[1];
    }

}
