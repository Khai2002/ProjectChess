package piece;

import board.Tile;
import move.Move;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece{

    public Knight(int[] position, int color){
        super(position, color);
        this.value = 3;
        this.id = 3 * this.color;
        this.name = "Knight";
    }

    @Override
    public void generateMove(Tile[][] board){
        int[][] moveCoefficient = {{2,1},{1,2},{-2,1},{-1,2},{-2,-1},{-1,-2},{2,-1},{1,-2}};
        int coeff;
        int temp;
        int xCoordinate;
        int yCoordinate;

        for(int[] move: moveCoefficient){
            coeff = 1;
            while(checkValidMove(this.position, move, coeff, board) != 0){
                temp = checkValidMove(this.position, move, coeff, board);
                xCoordinate = this.position[0] + coeff*move[0];
                yCoordinate = this.position[1] + coeff*move[1];

                if(temp != 0){
                    listMove.add(new Move(this,board[this.position[0]][this.position[1]],board[xCoordinate][yCoordinate]));
                    break;
                }else{
                    break;
                }
            }

        }

    }


}
