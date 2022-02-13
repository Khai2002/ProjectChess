package piece;

import Move.Move;

import java.util.List;

public class Knight extends Piece{

    public Knight(int[] position, int color){
        super(position, color);
        this.value = 3;
        this.id = 3 * this.color;
    }

    @Override
    public List<Move> generateMove(){
        int[][] moveCoefficient = {{2,1},{1,2}};

        return null;
    }
}
