package piece;

import board.Board;
import board.Tile;
import move.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece{

    public boolean checkDoubleMove;

    public Pawn(int[] position, int color){
        super(position, color);
        this.value = 1;
        this.id = 1 * this.color;
        this.name = "Pawn";
        this.checkMoved();
        this.checkDoubleMove = false;
    }

    @Override
    public void updateStatus(Move move){
        super.updateStatus(move);
        this.moved = true;
    }

    public void checkMoved(){
        if(this.id>0){
            if(this.position[0] == 6){
                this.moved = false;
            }else{
                this.moved = true;
            }
        }else{
            if(this.position[0] == 1){
                this.moved = false;
            }else{
                this.moved = true;
            }
        }
    }

    @Override
    public void generateMove(Board board){
        int[][] moveCoefficientEat = {{-1,1},{-1,-1}};

        int xCoordinate = this.position[0];
        int yCoordinate = this.position[1];
        // Check if pawn can move forward 1 tile
        if(board.board[xCoordinate-this.color][yCoordinate] instanceof Tile.EmptyTile){
            listMove.add(new Move(this,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color][yCoordinate]));

            // Check if pawn can move forward 2 tiles
            if(board.board[xCoordinate-this.color*2][yCoordinate] instanceof Tile.EmptyTile && !this.moved){
                listMove.add(new Move(this,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color*2][yCoordinate]));
            }
        }

        // Check if pawn can eat other piece
        for(int[] move:moveCoefficientEat){
            move[0] = this.color*move[0];
            move[1] = this.color*move[1];

            if(checkValidMove(this.position, move,1, board.board) == 1){
                listMove.add(new Move(this, board.board[xCoordinate][yCoordinate],board.board[xCoordinate+move[0]][yCoordinate+move[1]]));
            }
        }



        // Check for en passant




    }


}
