package move;

import board.Tile;
import piece.*;

public class Move {

    Piece piece;
    Tile destinationTile;

    public Move(Piece piece, Tile destinationTile){
        this.piece = piece;
        this.destinationTile = destinationTile;
    }

}
