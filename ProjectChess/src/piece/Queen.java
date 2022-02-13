package piece;

public class Queen extends Piece{

    public Queen(int[] position, int color){
        super(position, color);
        this.value = 9;
        this.id = 5 * this.color;
    }
}
