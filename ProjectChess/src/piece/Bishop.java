package piece;

public class Bishop extends Piece{

    public Bishop(int[] position, int color){
        super(position, color);
        this.value = 3;
        this.id = 4 * this.color;
    }
}
