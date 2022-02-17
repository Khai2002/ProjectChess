package move;
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
        int i = tile.tileCoordinate[0];
        return column[i];
    }
            
    public String createNotation(){
        String note;
        String[] pieceNotation = {"R", "N", "B", "Q", "K"};
       
        int i = Math.abs(this.piece.id);
        note = pieceNotation[i-2];
        
        // neu bi an thi them dau x
        //if (this.piece.checkValidMove()==1){
            // in ra cot diem bat dau
            note += columnNotation(startingTile);
            note += "x";
        //}
        // in ra cot diem ket thuc
        note += columnNotation(destinationTile);
        // in ra hang diem ket thuc
        note += destinationTile.tileCoordinate[1] + 1;

        
            
        return note;
    }

}
