package move;
import board.Board;
import board.Tile;
import piece.*;

public class Move {

    String notation;
    Piece piece;
    Tile startingTile;
    Tile destinationTile;

    public Move(Piece piece, Tile startingTile , Tile destinationTile){
        this.piece = piece;
        this.startingTile = startingTile;
        this.destinationTile = destinationTile;
        // Lam method de ap dung vao trong nay nhe
        this.notation = this.createNotation();
    }
    
    public String columnNotation(Tile tile){
        String[] column = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int i = tile[0];
        return column[i];
    }
            
    public String createNotation(){
        String note;
        String[] pieceNotation = {"R", "N", "B", "Q", "K"};
            // anh viet code trong nay nhe, dung 3 variable ma em cho them vao ay
        // Co vi tri bat dau, vi tri ket thuc va quan co
        // Ki hieu quan co
        
        int i = Math.abs(this.id);
        note += pieceNotation[i-2];
        
        // neu bi an thi them dau x
        if (this.checkValidMove( )==1){
            // in ra cot diem bat dau
            note += startingTile.columnNotation();
            note += "x";
        }
        // in ra cot diem ket thuc
        note += destinationTile.columnNotation();
        // in ra hang diem ket thuc
        note += destinationTile[1] + 1;

        
            
        return note;
    }

}
