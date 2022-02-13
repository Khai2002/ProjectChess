package piece;

import Move.Move;

import java.util.List;

public class Queen extends Piece{

    public Queen(int[] position, int color){
        super(position, color);
        this.value = 9;
        this.id = 5 * this.color;
    }

    @Override
    public List<Move> generateMove(){
        int[][] moveCoefficient = {{1,0},{0,1},{1,1},{1,-1}};
        return null;
    }
}
