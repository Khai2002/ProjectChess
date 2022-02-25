package board;

import move.Move;
import piece.*;
import game.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Board {

    // Global Attributes ===================================================== //

    public static final Character[] COLUMN_NOTATION = {'a','b','c','d','e','f','g','h'};
    public static final Character[] PIECE_PRINT = {'k','q','b','n','r','p','_','P','R','N','B','Q','K'};

    public static final String startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String testFEN = "8/8/8/6rk/6P1/8/8/8 b KQkq - 0 1";
    public static final String test2FEN = "1r2kr2/pp1p1p2/2p4p/6pP/P1PP4/1P6/5PP1/R3K2R w KQ g6 0 21";
    public static final String failSaveFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    // Local Attributes ====================================================== //


    // Variables that connect board to a previous state
    public Board previousBoard;
    public Move previousMove;

    // Useful attributes to describe current state of a chess board
    public String fen;
    public Tile[][] board;
    public int colorActive; // Indicate which side is active
    public boolean[] castleAvailable = new boolean[4];  // An array of 4 indicating possibility of castling
    public int[] enPassantTileCoordinate;  // Tile where en passant is available
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

        this.colorActive = this.previousBoard.colorActive * (-1);

        if(this.previousBoard.colorActive == -1){
            this.fullMoveCounter ++;
        }

        this.board = this.previousBoard.board;

        int[] startCoordinate = move.startingTile.tileCoordinate;
        int[] destinationCoordinate = move.destinationTile.tileCoordinate;

        Piece temp = move.piece;
        temp.updateStatus(move);

        this.board[startCoordinate[0]][startCoordinate[1]] = new Tile.EmptyTile(startCoordinate);
        this.board[destinationCoordinate[0]][destinationCoordinate[1]] = new Tile.OccupiedTile(destinationCoordinate,temp);

        //this.verifyInCheck(this.colorActive);


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
            this.enPassantTileCoordinate = new int[2];
            char[] enPassantArray = fenPart[3].toCharArray();
            char firstElement = enPassantArray[0];

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
        String fenCode = new String();
        int counter = 0;
        boolean checkLast = false;

        // Piece position
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(this.board[i][j] instanceof Tile.EmptyTile){
                    counter ++;
                }else{
                    if(counter!=0){
                        fenCode += counter;
                        counter = 0;
                    }
                    fenCode += PIECE_PRINT[this.board[i][j].pieceOnTile.id + 6];
                }
            }

            if(counter!=0){
                fenCode += counter;
                counter = 0;
            }

            if(i!=7){
                fenCode += "/";
            }

        }

        // Color Active
        if(this.colorActive == 1){
            fenCode += " w ";
        }else{
            fenCode += " b ";
        }

        // Castling Availability
        if(!(this.castleAvailable[0] || this.castleAvailable[1] || this.castleAvailable[2] || this.castleAvailable[3])){
            fenCode += "-";
        }
        if(this.castleAvailable[0]){ fenCode += "K"; }
        if(this.castleAvailable[1]){ fenCode += "Q"; }
        if(this.castleAvailable[2]){ fenCode += "k"; }
        if(this.castleAvailable[3]){ fenCode += "q"; }


        // En passant Square
        if(this.enPassantTileCoordinate == null){
            fenCode += " -";
        }else{
            fenCode += " ";
            fenCode += COLUMN_NOTATION[this.enPassantTileCoordinate[1]];
            fenCode += 8 - this.enPassantTileCoordinate[0];
        }

        // Half Move and Full Move
        fenCode += (" " + this.halfMoveCounter + " "+ this.fullMoveCounter);

        return fenCode;
    }

    // Print out chess board
    public void printBoard(){
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++) {
                if(board[i][j] instanceof Tile.EmptyTile){
                    System.out.print(PIECE_PRINT[6]+" ");
                }else{
                    System.out.print(PIECE_PRINT[board[i][j].pieceOnTile.id + 6]+ " ");
                }
            }
            System.out.println();
        }
    }
}
