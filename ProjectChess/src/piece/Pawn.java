package piece;

import board.Tile;
import move.Move;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(int[] position, int color){
        super(position, color);
        this.value = 1;
        this.id = 1 * this.color;
        this.name = "Pawn";
        this.moved = false;
    }

    @Override
    public void generateMove(Tile[][] board){
        int[][] moveCoefficientEat = {{-1,1},{-1,-1}};

        int xCoordinate = this.position[0];
        int yCoordinate = this.position[1];
        // Check if pawn can move forward 1 tile
        if(board[xCoordinate-this.color][yCoordinate] instanceof Tile.EmptyTile){
            listMove.add(new Move(this,board[xCoordinate][yCoordinate],board[xCoordinate-this.color][yCoordinate]));

            // Check if pawn can move forward 2 tiles
            if(board[xCoordinate-this.color*2][yCoordinate] instanceof Tile.EmptyTile && !this.moved){
                listMove.add(new Move(this,board[xCoordinate][yCoordinate],board[xCoordinate-this.color*2][yCoordinate]));
            }
        }

        // Check if pawn can eat other piece
        for(int[] move:moveCoefficientEat){
            move[0] = this.color*move[0];
            move[1] = this.color*move[1];

            if(checkValidMove(this.position, move,1, board) == 1){
                listMove.add(new Move(this, board[xCoordinate][yCoordinate],board[xCoordinate+move[0]][yCoordinate+move[1]]));
            }
        }



        // Check for en passant


    }


}
