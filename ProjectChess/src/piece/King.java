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
    }

    @Override
    public List<Move> generateMove(Tile[][] board){
        List<Move> listMove = new LinkedList<>();
        int[][] moveCoefficient = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
        int coeff;
        int temp;
        int xCoordinate;
        int yCoordinate;

        for(int[] move: moveCoefficient){
            coeff = 1;
            while(checkValidMove(this.position, move,coeff, board) != 0){
                temp = checkValidMove(this.position, move,coeff, board);
                xCoordinate = this.position[0] + coeff*move[0];
                yCoordinate = this.position[1] + coeff*move[1];

                if(temp != 0){
                    listMove.add(new Move(this,board[xCoordinate][yCoordinate]));
                    break;
                }else{
                    break;
                }
            }

        }
        return listMove;
    }
}
