package move;

import board.Tile;
import piece.*;

public class Move {

    String notation;
    Piece piece;
    Tile startingTile;
    Tile destinationTile;

    public Move(Piece piece, Tile startingTile , Tile destinationTile){
        this.piece = piece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        // Lam method de ap dung vao trong nay nhe
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

}
