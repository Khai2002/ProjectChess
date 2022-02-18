package board;

import piece.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Board {

    public static final Character[] COLUMN_NOTATION = {'A','B','C','D','E','F','G','H'};

    //public static final String startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    public static final String startFEN = "8/8/8/6RK/6P1/8/8/8";

    public Character[] piecePrint = {'k','q','b','n','r','p','_','P','R','N','B','Q','K'};

    public String fen;
    public Tile[][] board;

    public Board(String fen){

        this.fen = fen;
        this.board = fenToBoard(this.fen);

    }

    // Collect a list of all piece belonging to a color
    public List<Piece> getPiece(int color){
        List<Piece> list_piece = new LinkedList<>();
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8;j++){
                if(this.board[i][j] instanceof Tile.OccupiedTile && this.board[i][j].pieceOnTile.color == color){
                    list_piece.add(this.board[i][j].pieceOnTile);
                }
            }
        }
        return list_piece;
    }

    // Transform FEN code to chess board
    public Tile[][] fenToBoard(String fen){

        int[] array1D = new int[64];
        char[] array = fen.toCharArray();
        int counter = 0;
        HashMap<Character, Integer> fenToBoard = new HashMap<>();
        fenToBoard.put('P',1);
        fenToBoard.put('R',2);
        fenToBoard.put('N',3);
        fenToBoard.put('B',4);
        fenToBoard.put('Q',5);
        fenToBoard.put('K',6);
        fenToBoard.put('p', -1);
        fenToBoard.put('r',-2);
        fenToBoard.put('n',-3);
        fenToBoard.put('b',-4);
        fenToBoard.put('q',-5);
        fenToBoard.put('k',-6);

        for(char character:array){
            if(fenToBoard.containsKey(character)){
                array1D[counter] = fenToBoard.get(character);
                counter ++;
            }else if(Character.isDigit(character)){
                int temp = Character.getNumericValue(character);
                for(int i = 0; i<temp; i++){
                    array1D[counter] = 0;
                    counter++;
                }
            }
        }

        int[][] array2D = new int[8][8];
        for(int i = 0; i<array1D.length; i++){
            array2D[i>>3][i%8] = array1D[i];
        }

        Tile[][] board = new Tile[8][8];
        for(int i = 0; i<array2D.length; i++){
            for(int j = 0; j<array2D[0].length; j++){
                int tempo = array2D[i][j];
                int[] coord = {i,j};
                Piece pieceOnTile = null;
                if(Math.abs(tempo)==0){
                }else if(Math.abs(tempo)==1){
                    if(tempo>0){
                        pieceOnTile = new Pawn(coord, 1);
                    }else {
                        pieceOnTile = new Pawn(coord, -1);
                    }
                }else if(Math.abs(tempo)==2){
                    if(tempo>0){
                        pieceOnTile = new Rook(coord, 1);
                    }else {
                        pieceOnTile = new Rook(coord, -1);
                    }
                }else if(Math.abs(tempo)==3){
                    if(tempo>0){
                        pieceOnTile = new Knight(coord, 1);
                    }else {
                        pieceOnTile = new Knight(coord, -1);
                    }
                }else if(Math.abs(tempo)==4){
                    if(tempo>0){
                        pieceOnTile = new Bishop(coord, 1);
                    }else {
                        pieceOnTile = new Bishop(coord, -1);
                    }
                }else if(Math.abs(tempo)==5){
                    if(tempo>0){
                        pieceOnTile = new Queen(coord, 1);
                    }else {
                        pieceOnTile = new Queen(coord, -1);
                    }
                }else if(Math.abs(tempo)==6){
                    if(tempo>0){
                        pieceOnTile = new King(coord, 1);
                    }else {
                        pieceOnTile = new King(coord, -1);
                    }
                }

                if(pieceOnTile == null){
                    board[i][j] = new Tile.EmptyTile(coord);
                }else{
                    board[i][j] = new Tile.OccupiedTile(coord,pieceOnTile);
                }

            }
        }

        return board;
    }

    // Print out chess board
    public void printBoard(){
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++) {
                if(board[i][j] instanceof Tile.EmptyTile){
                    System.out.print(piecePrint[6]+" ");
                }else{
                    System.out.print(piecePrint[board[i][j].pieceOnTile.id + 6]+ " ");
                }
            }
            System.out.println();
        }
    }
}
