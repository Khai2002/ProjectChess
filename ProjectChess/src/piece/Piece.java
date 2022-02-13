package piece;

public class Piece {

    public int[] position; // init position of piece
    public int value; // relative value of piece (based on type)
    public int color; // -1 for black, 1 for white
    public int id;

    Piece(int[] position, int color){
        this.position = position;
        this.color = color;
    }
}
