package piece;

import Move.Move;

import java.util.List;

public class Bishop extends Piece{

    public Bishop(int[] position, int color){
        super(position, color);
        this.value = 3;
        this.id = 4 * this.color;
    }

    @Override
    public List<Move> generateMove(){
        int[][] moveCoefficient = {{1,1},{1,-1}};
        return null;
    }

}
