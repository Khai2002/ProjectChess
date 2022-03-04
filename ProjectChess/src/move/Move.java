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

    public Move(Piece piece, Tile startingTile , Tile destinationTile){
        this.piece = piece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.notation = this.createNotation();
    }

    public Move(Piece piece, Piece affectedPiece, Tile startingTile, Tile destinationTile){
        this.piece = piece;
        this.affectedPiece = affectedPiece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        this.notation = this.createNotation();

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
        return this.piece.name + this.startingTile.tileCoordinate[0] + this.startingTile.tileCoordinate[1]+ "-"+ this.destinationTile.tileCoordinate[0] + this.destinationTile.tileCoordinate[1];
    }

}
