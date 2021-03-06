package piece;

import board.Board;
import board.Tile;
import move.Move;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Piece implements Serializable {

    // Local Attributes ====================================================== //

    public int[] position; // init position of piece
    public int value; // relative value of piece (based on type)
    public int color; // -1 for black, 1 for white
    public int id;
    public boolean moved; // indicate whether a piece has moved (apply to Pawn, King and Rook)
    public String name;
    public List<Move> listMove = new LinkedList<>();


    // Constructors ========================================================== //

    public Piece(int[] position, int color){
        this.position = position;
        this.color = color;
        this.moved = false;
    }

    public Piece(int[] position, int color, boolean moved){
        this.position = position;
        this.color = color;
        this.moved = moved;
    }

    // Methods ============================================================== //

    public void generateMove(Board board){}

    public int checkValidMove(int[] pieceCoordinate,int[] move,int coeff, Tile[][] board){

        // Return 0 for not valid, 1 for valid and stop, 2 for valid and continue

        int xCoordinate = pieceCoordinate[0] + coeff*move[0];
        int yCoordinate = pieceCoordinate[1] + coeff*move[1];

        if (xCoordinate>=8 || yCoordinate>=8 || xCoordinate<0 || yCoordinate<0){
            // Out of bound, invalid move
            return 0;
        }else if(board[xCoordinate][yCoordinate] instanceof Tile.OccupiedTile){
            if(board[xCoordinate][yCoordinate].pieceOnTile.color == this.color){
                // Ally piece on tile, invalid move
                return 0;
            }else{
                // Enemy piece on tile, valid move, but eat then stop
                return 1;
            }
        }
        // Empty valid tile, valid move and free to proceed (if ones are sliding pieces)
        return 2;
    }

    // Update status for Piece
    public void updateStatus(Move move){
        this.position = move.destinationTile.tileCoordinate;
    }

    // Spawn new Piece
    public void spawnNewPiece(Move move){

    }

    @Override
    public String toString(){
        return this.name + ": "+ Board.COLUMN_NOTATION[position[1]] + (8 - this.position[0]);
    }
}
