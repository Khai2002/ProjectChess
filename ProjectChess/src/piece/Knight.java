package piece;

import board.Tile;
import move.Move;

import java.util.List;

public class Knight extends Piece{

    public Knight(int[] position, int color){
        super(position, color);
        this.value = 3;
        this.id = 3 * this.color;
    }

    @Override
    public List<Move> generateMove(Tile[][] board){
        int[][] moveCoefficient = {{2,1},{1,2},{-2,1},{-1,2}};

        for(int[] move:moveCoefficient){

        }

        return null;
    }
}
