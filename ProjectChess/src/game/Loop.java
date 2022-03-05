package game;

import java.util.Scanner;
import board.Board;
import move.*;
//import piece.*;
import board.*;

import java.util.LinkedList;
import java.util.List;


public class Loop {


    // Global Attributes ===================================================== //

    


    // Local Attributes ====================================================== //

    public Player white;
    public Player black;
    public List<Board> listBoard = new LinkedList<>();

    // Constructors ========================================================== //

    public Loop(Player white, Player black){
        this.white = white;
        this.black = black;
        this.listBoard = new LinkedList<>();
    }

    public Loop(){

    }

    // Methods =============================================================== //

    // Anh code game loop vao day
    // Luc choi ghi trong main
    // Loop.gameLoop(board)

    public void gameLoop(Board board, Scanner sc){
        this.listBoard.add(board);
        int count = 0;
            boolean whileCondition = true;
            while (this.gameEnd(this.listBoard.get(count)) ==99) {
                if (this.listBoard.get(count).colorActive == 1) {
                    Board newBoard = this.listBoard.get(count);
                    newBoard.printBoard1();
                    Move whitemove = new Move();
                    do {
                        System.out.println("Choose your white starting position: ");
                        String sP = sc.nextLine();
                        Tile startingPosition = getPosition(sP, newBoard);
                        System.out.println("Choose your white destination position: ");
                        String dP = sc.nextLine();
                        Tile destinationPosition = getPosition(dP, newBoard);;
                        if (startingPosition instanceof Tile.OccupiedTile) {
                            whitemove = new Move(startingPosition.pieceOnTile, startingPosition, destinationPosition);
                        }
                        System.out.println(newBoard.whiteMoves);
                        System.out.println(whitemove);
                        for (Move move: newBoard.whiteMoves){
                            if (move.equals(whitemove)){
                                whileCondition = false;
                            }
                        }
                    } while (whileCondition);
                    whileCondition = true;
                    this.listBoard.add(new Board(newBoard, whitemove, 0));
                    count++;
                }

                if (this.gameEnd(this.listBoard.get(count)) !=99){
                    System.out.println("End of the match");
                    break;
                }

                if (this.listBoard.get(count).colorActive == -1) {
                    Board newBoard = this.listBoard.get(count);
                    newBoard.printBoard1();
                    Move blackmove = new Move();
                    do {
                        System.out.println("Choose your black starting position: ");
                        String sP = sc.nextLine();
                        Tile startingPosition1 = getPosition(sP, newBoard);
                        System.out.println("Choose your black destination position: ");
                        String dP = sc.nextLine();
                        Tile destinationPosition1 = getPosition(dP, newBoard);
                        if (startingPosition1 instanceof Tile.OccupiedTile) {
                            blackmove = new Move(startingPosition1.pieceOnTile, startingPosition1, destinationPosition1);
                        }
                        System.out.println(newBoard.blackMoves);
                        System.out.println(blackmove);
                        for (Move move: newBoard.blackMoves){
                            if (move.equals(blackmove)){
                                whileCondition = false;
                            }
                        }
                    } while (whileCondition);
                    this.listBoard.add(new Board(newBoard, blackmove, 0));
                    count++;
                }

        }
    }

    // Anh dung 1 method check gameEnd
    // Co the cho no return int hoac boolean
    //Game-end condition
    public int gameEnd(Board board){
        if (board.isWhiteInCheckMate){
            return 1;
        }else if (board.isBlackInCheckMate){
            return -1;
        }else if (board.isWhiteInStaleMate || board.isBlackInStaleMate || board.halfMoveCounter >=50){
            return 0;
        }
        return 99;
    }

    public Tile getPosition(String s, Board board){
        int[] position = new int[2];
        for (int i = 0;i< 8;i++){
            if ( s.charAt(0) == Board.COLUMN_NOTATION[i]){
                position[0] = i ;
            }
            if ( s.charAt(1) == Board.ROW_NOTATION[i]){
                position[1] = 7- i ;
            }
        }
        return board.board[position[1]][position[0]];
    }
}
