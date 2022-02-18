package piece;

import board.Tile;
import move.Move;

import java.util.LinkedList;
import java.util.List;

public class King extends Piece{

    public King(int[] position, int color){
        super(position, color);
        this.value = 999;
        this.id = 6 * this.color;
        this.name = "King";
        this.moved = false;
    }

    @Override
    public void generateMove(Tile[][] board){
        int[][] moveCoefficient = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
        int temp;
        int xCoordinate;
        int yCoordinate;

        for(int[] move: moveCoefficient){
            while(checkValidMove(this.position, move,1, board) != 0){
                temp = checkValidMove(this.position, move,1, board);
                xCoordinate = this.position[0] + move[0];
                yCoordinate = this.position[1] + move[1];

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
