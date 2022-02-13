package piece;

import Move.Move;

import java.util.List;

public class Rook extends Piece{

    public Rook(int[] position, int color){
        super(position, color);
        this.value = 5;
        this.id = 2 * this.color;
    }

    @Override
    public List<Move> generateMove(){
        int[][] moveCoefficient = {{0,1},{1,0}};
        return null;
    }
}
