package board;

import move.Move;
import piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Board {

    // Global Attributes ===================================================== //

    public static final Character[] COLUMN_NOTATION = {'A','B','C','D','E','F','G','H'};

    public static final String startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String testFEN = "8/8/8/6RK/6P1/8/8/8 w KQkq - 0 1";
    public static final String failSaveFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    // Local Attributes ====================================================== //

    public Character[] piecePrint = {'k','q','b','n','r','p','_','P','R','N','B','Q','K'};

    // Variables that connect board to a previous state
    public Board previousBoard;
    public Move previousMove;

    // Useful attributes to describe current state of a chess board
    public String fen;
    public Tile[][] board;
    public int colorActive; // Indicate which side is active
    public boolean[] castleAvailable = new boolean[4];  // An array of 4 indicating possibility of castling
    public int[] enPassantTileCoordinate = new int[2];  // Tile where en passant is available
    public int halfMoveCounter; // Counter for number of move without pawn advancement or taking of piece (used for 50 moves rule)
    public int fullMoveCounter; // Number for total move in the game

    // Constructors ========================================================== //

    // Initialise Board using FEN code
    public Board(String fen){

        try{
            this.fen = fen;
            this.fenToBoard(this.fen);
        }catch (Exception e){
            System.out.println("FEN code not compatible");
            this.fen = failSaveFEN;
            this.fenToBoard(this.fen);
        }


    }

    // Initialise Board using previous Board and a transition Move
    public Board(Board previousBoard, Move move){
        this.previousBoard = previousBoard;
        this.previousMove = move;

        // Anh Tung them cho em phuong trinh cho ra board moi dua vao trang thai Board cu va 1 Move.
        // Thankss

        // Bat dau tu previousBoard
        // Dung temp de store quan co
        // Xoa quan co khoi vi tri cu
        // Copy no vao vi tri moi
        // Doi attribute vi tri cua no
        // Return ra this.board


    }

    // Methods ============================================================== //

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
    public void fenToBoard(String fen){

        int[] array1D = new int[64];
        String[] fenPart = fen.split(" ");
        char[] array = fenPart[0].toCharArray();
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

        this.board = new Tile[8][8];
        for(int i = 0; i<array2D.length; i++){
            for(int j = 0; j<array2D[0].length; j++){
                int tempo = array2D[i][j];
                int[] coord = {i,j};
                Piece pieceOnTile = null;

                if(Math.abs(tempo)==1){
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
                    this.board[i][j] = new Tile.EmptyTile(coord);
                }else{
                    this.board[i][j] = new Tile.OccupiedTile(coord,pieceOnTile);
                }

            }
        }

        // Treat string "w" or "b" for current active color
        switch (fenPart[1]) {
            case "w" -> this.colorActive = 1;
            case "b" -> this.colorActive = -1;
        }

        // KQkq castling availability
        if(!fenPart[2].equals("-")){
            char[] castlingArray = fenPart[2].toCharArray();
            for(char character:castlingArray){
                switch (character){
                    case 'K' -> this.castleAvailable[0] = true;
                    case 'Q' -> this.castleAvailable[1] = true;
                    case 'k' -> this.castleAvailable[2] = true;
                    case 'q' -> this.castleAvailable[3] = true;
                }
            }
        }

        // En passant Square
        if(!fenPart[3].equals("-")){
            char[] enPassantArray = fenPart[3].toCharArray();
            char firstElement = Character.toUpperCase(enPassantArray[0]);
            for(char chara:COLUMN_NOTATION){
                if(chara == firstElement){
                    this.enPassantTileCoordinate[1] = Arrays.asList(COLUMN_NOTATION).indexOf(chara);
                    break;
                }
            }
            this.enPassantTileCoordinate[0] = 8 - Character.getNumericValue(enPassantArray[1]);
        }

        // Half Move & Full Move Counter
        this.halfMoveCounter = Integer.parseInt(fenPart[4]);
        this.fullMoveCounter = Integer.parseInt(fenPart[5]);

    }

    // Transform chess board to Fen
    public String BoardToFen(){
        return null;
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
