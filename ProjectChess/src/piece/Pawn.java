package piece;

import Move.Move;

import java.util.List;

public class Pawn extends Piece{

    public Pawn(int[] position, int color){
        super(position, color);
        this.value = 1;
        this.id = 1 * this.color;
    }

    @Override
    public List<Move> generateMove(){
        int[][] moveCoefficient = {};
        return null;
    }
}
