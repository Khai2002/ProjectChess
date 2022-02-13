package piece;

public class King extends Piece{

    public King(int[] position, int color){
        super(position, color);
        this.value = 999;
        this.id = 6 * this.color;
    }
}
