package board;

import piece.*;

public class Tile {

    int[] tileCoordinate;
    Piece pieceOnTile;

    Tile(int[] tileCoordinate){
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
}
