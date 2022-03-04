package piece;

import board.Board;
import board.Tile;
import move.Move;

import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece{

    public Queen(int[] position, int color){
        super(position, color);
        this.value = 9;
        this.id = 5 * this.color;
        this.name = "Queen";
    }


    @Override
    public void generateMove(Board board){
        int[][] moveCoefficient = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
        int coeff;
        int temp;
        int xCoordinate;
        int yCoordinate;

        for(int[] move: moveCoefficient){
            coeff = 1;
            while(checkValidMove(this.position, move,coeff, board.board) != 0){
                temp = checkValidMove(this.position, move,coeff, board.board);
                xCoordinate = this.position[0] + coeff*move[0];
                yCoordinate = this.position[1] + coeff*move[1];

                if(temp == 2){
                    listMove.add(new Move(this,board.board[this.position[0]][this.position[1]],board.board[xCoordinate][yCoordinate]));
                    coeff ++;
                }else if(temp == 1){
                    listMove.add(new Move(this,board.board[this.position[0]][this.position[1]],board.board[xCoordinate][yCoordinate]));
                    coeff ++;
                    break;
                }else{
                    break;
                }
            }

        }
    }


}
