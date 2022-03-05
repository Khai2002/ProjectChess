package board;

import move.Move;
import piece.*;

import java.io.Serializable;
import java.util.*;

public class Board implements Serializable {

    // Global Attributes ===================================================== //

    public static final Character[] COLUMN_NOTATION = {'a','b','c','d','e','f','g','h'};
    public static final Character[] PIECE_PRINT = {'k','q','b','n','r','p','_','P','R','N','B','Q','K'};


    public static final String startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String testFEN = "8/8/8/6rk/6P1/6P1/8/8 b KQkq - 0 1";
    public static final String test2FEN = "1r2kr2/pp1p1p2/2p4p/6pP/P1PP4/1P6/5PP1/R3K2R w KQ g6 0 21";
    public static final String failSaveFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String checkmateFEN = "7Q/6R1/8/7k/8/8/8/8 b - - 0 1";
    public static final String stalemateFEN = "8/8/q7/4K3/7q/8/8/3q1q2 w - - 0 1";
    public static final String weirdPositionFEN = "8/8/1R6/7K/8/8/1r6/1k4r1 b - - 0 1";
    public static final String pawnDebugFEN = "8/6p1/8/8/8/8/1P6/8 w - - 0 1";
    public static final String castleFEN = "2qn1n2/8/8/8/8/8/8/R3K1NR w KQ - 0 1";
    public static final String castle2FEN = "r3k2r/8/8/8/8/8/8/3Q4 w kq - 0 1";

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

    // Attributes for White and Black
    public LinkedList<Move> whiteMoves;
    public LinkedList<Move> blackMoves;

    public LinkedList<Piece> whitePieces;
    public LinkedList<Piece> blackPieces;

    public int[] whiteKingCoordinate;
    public int[] blackKingCoordinate;

    public boolean isWhiteInCheck;
    public boolean isBlackInCheck;

    public boolean isWhiteInCheckMate;
    public boolean isBlackInCheckMate;

    public boolean isWhiteInStaleMate;
    public boolean isBlackInStaleMate;

    public boolean[] castleCurrentAvailable = new boolean[4];

    // Attribute used for search depth
    public int searchDepth;


    // Constructors ========================================================== //

    // Initialise Board using FEN code
    public Board(String fen){

        try{
            this.searchDepth = 0;
            this.fen = fen;
            this.fenToBoard(this.fen);
        }catch (Exception e){
            System.out.println("FEN code not compatible");
            this.searchDepth = 0;
            this.fen = failSaveFEN;
            this.fenToBoard(this.fen);
        }finally {
            this.whitePieces = this.getPiece(1);
            this.blackPieces = this.getPiece(-1);

            this.whiteMoves = this.getMove(this.whitePieces);
            this.blackMoves = this.getMove(this.blackPieces);

            this.whiteKingCoordinate = this.getKingPosition(1);
            this.blackKingCoordinate = this.getKingPosition(-1);

            this.isWhiteInCheck = this.isInCheck(1);
            this.isBlackInCheck = this.isInCheck(-1);

            if(this.searchDepth == 0){
                limitMoveResultInCheck(this.colorActive);
                //limitMoveResultInCheck(-1);
            }

            this.isWhiteInCheckMate = this.isInCheckMate(1);
            this.isBlackInCheckMate = this.isInCheckMate(-1);

            this.isWhiteInStaleMate = this.isInStalemate(1);
            this.isBlackInStaleMate = this.isInStalemate(-1);

            this.checkCastleAvailable(1);
            this.checkCastleAvailable(-1);
        }


    }

    // Initialise Board using previous Board and a transition Move
    public Board(Board previousBoard, Move move, int searchDepth){
        this.searchDepth = searchDepth;

        this.previousBoard = previousBoard;
        this.previousMove = move;

        this.colorActive = this.previousBoard.colorActive * (-1);

        if(this.previousBoard.colorActive == -1){
            this.fullMoveCounter ++;
        }


        this.board = new Tile[8][8];
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                this.board[i][j] = this.previousBoard.board[i][j];
            }
        }

        int[] startCoordinate = move.startingTile.tileCoordinate;
        int[] destinationCoordinate = move.destinationTile.tileCoordinate;


        Piece temp = null;
        int currentId = move.piece.id;
        
        if(Math.abs(currentId)==1){
            if(currentId>0){
                temp = new Pawn(destinationCoordinate, 1, true);
            }else {
                temp = new Pawn(destinationCoordinate, -1, true);
            }
        }else if(Math.abs(currentId)==2){
            if(currentId>0){
                temp = new Rook(destinationCoordinate, 1, true);
            }else {
                temp = new Rook(destinationCoordinate, -1, true);
            }
        }else if(Math.abs(currentId)==3){
            if(currentId>0){
                temp = new Knight(destinationCoordinate, 1);
            }else {
                temp = new Knight(destinationCoordinate, -1);
            }
        }else if(Math.abs(currentId)==4){
            if(currentId>0){
                temp = new Bishop(destinationCoordinate, 1);
            }else {
                temp = new Bishop(destinationCoordinate, -1);
            }
        }else if(Math.abs(currentId)==5){
            if(currentId>0){
                temp = new Queen(destinationCoordinate, 1);
            }else {
                temp = new Queen(destinationCoordinate, -1);
            }
        }else if(Math.abs(currentId)==6){
            if(currentId>0){
                temp = new King(destinationCoordinate, 1, true);
                this.castleAvailable[0] = false;
                this.castleAvailable[1] = false;
            }else {
                temp = new King(destinationCoordinate, -1, true);
                this.castleAvailable[2] = false;
                this.castleAvailable[3] = false;
            }
        }

        this.board[startCoordinate[0]][startCoordinate[1]] = new Tile.EmptyTile(startCoordinate);
        this.board[destinationCoordinate[0]][destinationCoordinate[1]] = new Tile.OccupiedTile(destinationCoordinate,temp);

        this.whitePieces = this.getPiece(1);
        this.blackPieces = this.getPiece(-1);

        this.whiteKingCoordinate = this.getKingPosition(1);
        this.blackKingCoordinate = this.getKingPosition(-1);

        // Disable castling right if Rook is moved
        if(currentId == 2 && (!move.piece.moved)){
            if (temp.position[1]>this.whiteKingCoordinate[1]){
                this.castleAvailable[0] = false;
            }else{
                this.castleAvailable[1] = false;
            }
        }else if(currentId == -2 && (!move.piece.moved)){
            if (temp.position[1]>this.blackKingCoordinate[1]){
                this.castleAvailable[2] = false;
            }else{
                this.castleAvailable[3] = false;
            }
        }

        this.whiteMoves = this.getMove(this.whitePieces);
        this.blackMoves = this.getMove(this.blackPieces);

        this.isWhiteInCheck = this.isInCheck(1);
        this.isBlackInCheck = this.isInCheck(-1);

        if(this.searchDepth == 0){
            limitMoveResultInCheck(this.colorActive);
            //limitMoveResultInCheck(-1);
        }

        this.isWhiteInCheckMate = this.isInCheckMate(1);
        this.isBlackInCheckMate = this.isInCheckMate(-1);


        this.isWhiteInStaleMate = this.isInStalemate(1);
        this.isBlackInStaleMate = this.isInStalemate(-1);

        this.checkCastleAvailable(1);
        this.checkCastleAvailable(-1);


    }

    // Methods ============================================================== //

    // Collect a list of all piece belonging to a color
    public LinkedList<Piece> getPiece(int color){
        LinkedList<Piece> list_piece = new LinkedList<>();
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8;j++){
                if(this.board[i][j] instanceof Tile.OccupiedTile && this.board[i][j].pieceOnTile.color == color){
                    list_piece.add(this.board[i][j].pieceOnTile);
                }
            }
        }
        return list_piece;
    }

    // Collect a list of all move by a color
    public LinkedList<Move> getMove(LinkedList<Piece> listPiece){
        LinkedList<Move> moveGenerated = new LinkedList<>();

        for(Piece piece:listPiece){
            piece.listMove = new ArrayList<>();
            piece.generateMove(this);
            if(piece.listMove!=null){
                moveGenerated.addAll(piece.listMove);
            }
        }

        return moveGenerated;
    }

    // Get King position
    public int[] getKingPosition(int color){

        int[] kingCoordinate = new int[2];

        if(color==1){
            for(Piece piece:whitePieces){
                if(piece instanceof King){
                    kingCoordinate = piece.position;
                }
            }
        }else{
            for(Piece piece:blackPieces){
                if(piece instanceof King){
                    kingCoordinate = piece.position;
                }
            }
        }

        return kingCoordinate;
    }

    // Verify if in check
    public boolean isInCheck(int color){
        if(color == 1){
            for(Move move:blackMoves){
                if(Arrays.equals(move.destinationTile.tileCoordinate,this.whiteKingCoordinate)){
                    return true;
                }
            }
        }else{
            for(Move move:whiteMoves){
                if(Arrays.equals(move.destinationTile.tileCoordinate,this.blackKingCoordinate)){
                    //System.out.println("Hello from the other side");
                    return true;
                }
            }
        }
        return false;
    }

    // Verify if a move can lead to king being checked
    public void limitMoveResultInCheck(int color){

        List<Move> moveToDelete = new LinkedList<>();

        if(color == 1){
            //System.out.println("Hello I'm white");
            for(Move move:whiteMoves){
                if(!Arrays.equals(move.destinationTile.tileCoordinate,blackKingCoordinate)){
                    Board newBoard = new Board(this,move,1);
                    if(newBoard.isWhiteInCheck){
                        //System.out.println("Hey");
                        moveToDelete.add(move);
                    }
                }
            }
            //System.out.println(moveToDelete);
            //Delete Moves
            if(moveToDelete != null){
                for(Move move:moveToDelete){
                    this.whiteMoves.remove(move);
                }
            }

        }else{
            //System.out.println("Hello I'm black");
            for(Move move:blackMoves){
                if(!Arrays.equals(move.destinationTile.tileCoordinate,whiteKingCoordinate)){
                    Board newBoard = new Board(this,move,1);
                    //newBoard.printBoard();
                    //System.out.println(newBoard.whiteMoves);
                    //System.out.println(newBoard.isBlackInCheck);
                    if(newBoard.isBlackInCheck){
                        //System.out.println("Hell yeah");
                        moveToDelete.add(move);
                    }
                }
            }
            //System.out.println(moveToDelete);
            //Delete Moves
            if(moveToDelete != null){
                for(Move move:moveToDelete){
                    this.blackMoves.remove(move);
                }
            }
        }
    }

    // Verify if in CheckMate
    public boolean isInCheckMate(int color){
        if(color == 1){
            return this.isWhiteInCheck && this.whiteMoves.isEmpty();
        }else{
            return this.isBlackInCheck && this.blackMoves.isEmpty();
        }
    }

    // Verify if in stalemate
    public boolean isInStalemate(int color){
        if(color == 1){
            return (!this.isWhiteInCheck) && this.whiteMoves.isEmpty();
        }else{
            return (!this.isBlackInCheck) && this.blackMoves.isEmpty();
        }
    }

    // add castle Move to list Move if possible
    public void checkCastleAvailable(int color){

        int row;
        boolean tileInDanger;

        if(color == 1){
            row = 7;
            if(this.castleAvailable[0]){
                this.castleCurrentAvailable[0] = true;
                for(int i = 5; i<7; i++){
                    tileInDanger = false;
                    for(Move move:this.blackMoves){
                        if(move.destinationTile.tileCoordinate[0] == row && move.destinationTile.tileCoordinate[1] == i){
                            //System.out.println(move);
                            tileInDanger = true;
                        }
                    }
                    if(!(this.board[row][i] instanceof Tile.EmptyTile) || tileInDanger){
                        this.castleCurrentAvailable[0] = false;
                    }
                }
            }else{
                this.castleCurrentAvailable[0] = false;
            }

            if(this.castleAvailable[1]){
                this.castleCurrentAvailable[1] = true;
                for(int i = 1; i<4; i++){
                    tileInDanger = false;
                    for(Move move:this.blackMoves){
                        if(move.destinationTile.tileCoordinate[0] == row && move.destinationTile.tileCoordinate[1] == i){
                            //System.out.println(move);
                            tileInDanger = true;
                        }
                    }
                    if(!(this.board[row][i] instanceof Tile.EmptyTile) || tileInDanger){
                        this.castleCurrentAvailable[1] = false;
                    }
                }
            }else{
                this.castleCurrentAvailable[1] = false;
            }

            if(this.castleCurrentAvailable[0]){
                Move newMove = new Move(board[7][4].pieceOnTile, board[7][7].pieceOnTile,board[7][4], board[7][6], board[7][7], board[7][5]);
                this.whiteMoves.add(newMove);
            }

            if(this.castleCurrentAvailable[1]){
                Move newMove = new Move(board[7][4].pieceOnTile, board[7][0].pieceOnTile,board[7][4], board[7][2], board[7][0], board[7][3]);
                this.whiteMoves.add(newMove);
            }

        }else{
            row = 0;
            if(this.castleAvailable[2]){
                this.castleCurrentAvailable[2] = true;
                for(int i = 5; i<7; i++){
                    tileInDanger = false;
                    for(Move move:this.whiteMoves){
                        if(move.destinationTile.tileCoordinate[0] == row && move.destinationTile.tileCoordinate[1] == i){
                            //System.out.println(move);
                            tileInDanger = true;
                        }
                    }
                    if(!(this.board[row][i] instanceof Tile.EmptyTile) || tileInDanger){
                        this.castleCurrentAvailable[2] = false;
                    }
                }
            }else{
                this.castleCurrentAvailable[2] = false;
            }

            if(this.castleAvailable[3]){
                this.castleCurrentAvailable[3] = true;
                for(int i = 1; i<4; i++){
                    tileInDanger = false;
                    for(Move move:this.whiteMoves){
                        if(move.destinationTile.tileCoordinate[0] == row && move.destinationTile.tileCoordinate[1] == i){
                            //System.out.println(move);
                            tileInDanger = true;
                        }
                    }
                    if(!(this.board[row][i] instanceof Tile.EmptyTile) || tileInDanger){
                        this.castleCurrentAvailable[3] = false;
                    }
                }
            }else{
                this.castleCurrentAvailable[3] = false;
            }

            if(this.castleCurrentAvailable[2]){
                Move newMove = new Move(board[0][4].pieceOnTile, board[0][7].pieceOnTile,board[0][4], board[0][6], board[0][7], board[0][5]);
                this.blackMoves.add(newMove);
            }

            if(this.castleCurrentAvailable[3]){
                Move newMove = new Move(board[0][4].pieceOnTile, board[0][0].pieceOnTile,board[0][4], board[0][2], board[0][0], board[0][3]);
                this.blackMoves.add(newMove);
            }
        }

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
                        pieceOnTile = new Pawn(coord, 1, false);
                    }else {
                        pieceOnTile = new Pawn(coord, -1, false);
                    }
                }else if(Math.abs(tempo)==2){
                    if(tempo>0){
                        pieceOnTile = new Rook(coord, 1, true);
                    }else {
                        pieceOnTile = new Rook(coord, -1, true);
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
                        pieceOnTile = new King(coord, 1, false);
                    }else {
                        pieceOnTile = new King(coord, -1, false);
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
            case "w":
                this.colorActive = 1;
                break;
            case "b":
                this.colorActive = -1;
                break;
        }

        // KQkq castling availability
        if(!fenPart[2].equals("-")){
            char[] castlingArray = fenPart[2].toCharArray();
            for(char character:castlingArray){
                switch (character){
                    case 'K':
                        this.castleAvailable[0] = true;
                        break;
                    case 'Q':
                        this.castleAvailable[1] = true;
                        break;
                    case 'k':
                        this.castleAvailable[2] = true;
                        break;
                    case 'q':
                        this.castleAvailable[3] = true;
                        break;
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
        StringBuilder fenCode = new StringBuilder("");
        int counter = 0;

        // Piece position
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(this.board[i][j] instanceof Tile.EmptyTile){
                    counter ++;
                }else{
                    if(counter!=0){
                        fenCode.append(counter);
                        counter = 0;
                    }
                    fenCode.append(PIECE_PRINT[this.board[i][j].pieceOnTile.id + 6]);
                }
            }

            if(counter!=0){
                fenCode.append(counter);
                counter = 0;
            }

            if(i!=7){
                fenCode.append("/");
            }

        }

        // Color Active
        if(this.colorActive == 1){
            fenCode.append(" w ");
        }else{
            fenCode.append(" b ");
        }

        // Castling Availability
        if(!(this.castleAvailable[0] || this.castleAvailable[1] || this.castleAvailable[2] || this.castleAvailable[3])){
            fenCode.append("-");
        }
        if(this.castleAvailable[0]){ fenCode.append("K"); }
        if(this.castleAvailable[1]){ fenCode.append("Q"); }
        if(this.castleAvailable[2]){ fenCode.append("k"); }
        if(this.castleAvailable[3]){ fenCode.append("q"); }


        // En passant Square
        if(this.enPassantTileCoordinate == null){
            fenCode.append(" -");
        }else{
            fenCode.append(" ");
            fenCode.append(COLUMN_NOTATION[this.enPassantTileCoordinate[1]]);
            fenCode.append(8 - this.enPassantTileCoordinate[0]);
        }

        // Half Move and Full Move
        fenCode.append(" ").append(this.halfMoveCounter).append(" ").append(this.fullMoveCounter);

        return fenCode.toString();
    }

    // Print out chess board
    public void printBoard(){
        for(int i = 0; i<8; i++){
            System.out.print(i + " ");
            for(int j = 0; j<8; j++) {
                if(board[i][j] instanceof Tile.EmptyTile){
                    System.out.print(PIECE_PRINT[6]+" ");
                }else{
                    System.out.print(PIECE_PRINT[board[i][j].pieceOnTile.id + 6]+ " ");
                }
            }

            System.out.println();
        }
        System.out.println("  0 1 2 3 4 5 6 7");
    }
}
