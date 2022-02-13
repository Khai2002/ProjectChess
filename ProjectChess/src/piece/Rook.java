package piece;

public class Rook extends Piece{

    public Rook(int[] position, int color){
        super(position, color);
        this.value = 5;
        this.id = 2 * this.color;
    }
}
