package piece;

import Move.Move;

import java.util.List;

public class King extends Piece{

    public King(int[] position, int color){
        super(position, color);
        this.value = 999;
        this.id = 6 * this.color;
    }

    @Override
    public List<Move> generateMove(){
        int[][] moveCoefficient = {{1,0},{0,1},{1,1},{1,-1}};
        return null;
    }
}
