package board;

import piece.*;

import java.util.HashMap;

public class Board {

    //public static final String startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    public static final String startFEN = "rnb1kbnr/pp5p/2pppqp1/5p2/4P3/5P2/PPPP2PP/RNBQKBNR";

    public HashMap<Integer, Character> piecePrint = new HashMap<>();
    public String fen;
    public Tile[][] board;

    public Board(String fen){
        piecePrint.put(1,'P');
        piecePrint.put(2,'R');
        piecePrint.put(3,'N');
        piecePrint.put(4,'B');
        piecePrint.put(5,'Q');
        piecePrint.put(6,'K');
        piecePrint.put(-1,'p');
        piecePrint.put(-2,'r');
        piecePrint.put(-3,'n');
        piecePrint.put(-4,'b');
        piecePrint.put(-5,'q');
        piecePrint.put(-6,'k');

        this.fen = fen;
        this.board = fenToBoard(this.fen);


    }

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
                System.out.println(character);
            }else if(Character.isDigit(character)){
                int temp = Character.getNumericValue(character);
                System.out.println(temp);
                for(int i = 0; i<temp; i++){
                    array1D[counter] = 0;
                    counter++;
                }
            }
        }

        int[][] array2D = new int[8][8];
        for(int i = 0; i<array1D.length; i++){
            array2D[i/8][i%8] = array1D[i];
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

    public void printBoard(){
        String string_board = "";
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(board[i][j] instanceof Tile.EmptyTile){
                    System.out.print("_");
                }else{
                    System.out.print(piecePrint.get(board[i][j].pieceOnTile.id));
                }
            }
            System.out.println();
        }
    }
}
