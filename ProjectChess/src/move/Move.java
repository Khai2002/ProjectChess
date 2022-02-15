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
        String column;
        if (tile[0]==0){
            column = "a";
        } else if (tile[0]==1){
            column = "b";
        } else if (tile[0]==2){
            column = "c";
        } else if (tile[0]==3){
            column = "d";
        } else if (tile[0]==4){
            column = "e";
        } else if (tile[0]==5){
            column = "f";
        } else if (tile[0]==6){
            column = "g";
        } else if (tile[0]==7){
            column = "h";
        }
        return column;
    }
            
    public String createNotation(){
        
        String[] column = new String[8];
        
        
        // anh viet code trong nay nhe, dung 3 variable ma em cho them vao ay
        // Co vi tri bat dau, vi tri ket thuc va quan co
        // Ki hieu quan co
        if (Math.abs(this.id)==1){ 
        } else if (Math.abs(this.id)==2){
            notation += "R";  
        } else if (Math.abs(this.id)==3){
            notation += "N";
        } else if (Math.abs(this.id)==4){
            notation +=  "B";
        } else if (Math.abs(this.id)==5){
            notation +=  "Q";
        } else if (Math.abs(this.id)==6){
            notation += "K";
        }
        // neu bi an thi them dau x
        if (this.temp==1){
            // in ra cot diem bat dau
            notation += startingTile.columnNotation();
            notation += "x";
        }
        // in ra cot diem ket thuc
        notation += destinationTile.columnNotation();
        // in ra hang diem ket thuc
        notation += destinationTile[1];

        
            
        return notation;
    }

}
