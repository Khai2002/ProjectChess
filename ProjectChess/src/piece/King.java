package piece;

import board.Board;
import board.Tile;
import move.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class King extends Piece{

    boolean castleKingSide;
    boolean castleQueenSide;

    public King(int[] position, int color, boolean moved){
        super(position, color, moved);
        this.value = 999;
        this.id = 6 * this.color;
        this.name = "King";
        this.moved = false;
    }

    @Override
    public void updateStatus(Move move){
        super.updateStatus(move);
        this.moved = true;
    }

    @Override
    public void generateMove(Board board){
        int[][] moveCoefficient = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
        int temp;
        int xCoordinate;
        int yCoordinate;

        for(int[] move: moveCoefficient){
            while(checkValidMove(this.position, move,1, board.board) != 0){
                temp = checkValidMove(this.position, move,1, board.board);
                xCoordinate = this.position[0] + move[0];
                yCoordinate = this.position[1] + move[1];

                if(temp != 0){
                    listMove.add(new Move(this,board.board[this.position[0]][this.position[1]],board.board[xCoordinate][yCoordinate]));
                    break;
                }else{
                    break;
                }
            }

        }




    }




}
