package piece;

public class Knight extends Piece{

    public Knight(int[] position, int color){
        super(position, color);
        this.value = 3;
        this.id = 3 * this.color;
    }
}
