package board;

import piece.*;

import java.io.Serializable;

public class Tile implements Serializable {

    public int[] tileCoordinate;
    public Piece pieceOnTile;

    public Tile(int[] tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public static final class EmptyTile extends Tile {
        public EmptyTile (int[] tileCoordinate){
            super(tileCoordinate);
            this.pieceOnTile = null;
        }

    }

    public static final class OccupiedTile extends Tile {
        public OccupiedTile(int[] tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }
    }

    public boolean equals(Tile tile){
        return (this.tileCoordinate[0] == tile.tileCoordinate[0]) && (this.tileCoordinate[1] == tile.tileCoordinate[1]);
    }
}
