import board.*;

import game.Engine;
import game.Human;
import game.Loop;
import game.Player;
import gui.Interface;
import move.Move;
import piece.*;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception {



        Scanner sc= new Scanner(System.in);
        long avantTraitement = System.currentTimeMillis();

        Board board = new Board(Board.startFEN);

        Interface interFace = new Interface(board);

        Human human1 = new Human();
        Human human2 = new Human();
        Engine engine1 = new Engine(3);
        Engine engine2 = new Engine(3);

        Loop loop = new Loop();
        loop.gameLoopHumanMachine(board,interFace,human1,human2);

//        board.printBoard();
//
//        System.out.println(board.board[1][1].pieceOnTile.id);
//
//        board.board[1][1].pieceOnTile.generateMove(board);
//
//        System.out.println(board.board[1][1].pieceOnTile.listMove);
//
//
//        System.out.println(board.whiteMoves);





//        board.printBoard();
//
//        Move move = new Move(board.board[1][6].pieceOnTile, board.board[1][6], board.board[3][6]);
//
//        Board newBoard = new Board(board, move,true,0);
//        newBoard.printBoard();
//
//        System.out.println(newBoard.enPassantTileCoordinate[0] + " "+ newBoard.enPassantTileCoordinate[1]);
//
//        Move move2 = new Move(newBoard.board[3][7].pieceOnTile, newBoard.board[newBoard.previousMove.destinationTile.tileCoordinate[0]][newBoard.previousMove.destinationTile.tileCoordinate[1]].pieceOnTile,newBoard.board[3][7], newBoard.board[2][6]);
//        System.out.println(move2.type);
//
//        Board anotherNewBoard = new Board(newBoard, move2,true,0);
//        anotherNewBoard.printBoard();
//
//        board.board[3][7].pieceOnTile.generateMove(board);
//
//        for(Move m : board.board[3][7].pieceOnTile.listMove){
//            System.out.println(m);
//        }





/*
        Engine mainEngine = new Engine(3);
        int counter = mainEngine.buildNextSet(board);
        System.out.println(counter);

        /*
        Interface interFace = new Interface(board);

        Human human1 = new Human();
        Human human2 = new Human();
        Engine engine1 = new Engine(3);
        Engine engine2 = new Engine(3);

        Loop loop = new Loop();
        loop.gameLoopHumanMachine(board,interFace,human1,human2);

        /*
        Engine mainEngine = new Engine(2);
        int counter = mainEngine.buildNextSet(board);
        System.out.println(counter);

        board.nextBoardSet.last().printBoard1();
        System.out.println(board.nextBoardSet.last().boardStateEvaluation);

/*
        Interface interFace = new Interface(board);

        Human human1 = new Human();
        Human human2 = new Human();
        Engine engine1 = new Engine(3);
        Engine engine2 = new Engine(3);

        Loop loop = new Loop();
        loop.gameLoopHumanMachine(board,interFace,human1,human2);

/*
        //loop.gameLoopInterfaceFinal(board, interFace);

        System.out.println(board.whiteMoves);
        System.out.println(board.blackMoves);

        Board newBoard = new Board(board,board.blackMoves.get(23),true);
        newBoard.printBoard1();

        Board newBoard2 = new Board(newBoard, newBoard.whiteMoves.get(15), true);
        newBoard2.printBoard1();


        System.out.println(board.whiteMoves);
        System.out.println(board.blackMoves);

        Board newBoard = new Board(board,board.blackMoves.get(1),true);
        newBoard.printBoard1();
        System.out.println(newBoard.whiteMoves);
        System.out.println(newBoard.blackMoves);
        System.out.println(newBoard.board[newBoard.whiteKingCoordinate[0]][newBoard.whiteKingCoordinate[1]].pieceOnTile instanceof King);
        System.out.println(newBoard.board[newBoard.whiteKingCoordinate[0]][newBoard.whiteKingCoordinate[1]].pieceOnTile.moved);
        for(boolean bool:newBoard.castleAvailable){
            System.out.println(bool);
        }


        for(boolean bool:newBoard.castleCurrentAvailable){
            System.out.println(bool);
        }

         */





        //loop.gameLoopInterfaceFinal(board,interFace);


        //loop.gameLoopInterface(board,interFace);

        //System.out.println(board.board[0][0].pieceOnTile.getClass().getName());
        //System.out.println(board.board[0][0].pieceOnTile instanceof Rook);

        //for(int i = 0; i<4; i++){System.out.println(board.castleAvailable[i]);}

        //for(int i = 0; i<4; i++){System.out.println(board.castleCurrentAvailable[i]);}






        /*

        System.out.println(board.isWhiteInCheck);
        System.out.println(board.isBlackInCheck);

        System.out.println(board.isWhiteInCheckMate);
        System.out.println(board.isBlackInCheckMate);

        System.out.println(board.isWhiteInStaleMate);
        System.out.println(board.isBlackInStaleMate);



        //Player white = new Player(board,1);
        //System.out.println(white.possibleMove);






        String str = board.BoardToFen();
        System.out.println(str);

        //Move move = new Move(board.board[6][4].pieceOnTile,board.board[6][4], board.board[5][4]);

        //Board newBoard = new Board(board,move);
        //newBoard.printBoard();




        for(boolean bool:board.castleAvailable){
            System.out.println(bool);
        }

        for(int in:board.enPassantTileCoordinate){
            System.out.println(in);
        }


        Player white = new Player(board, 1);


        List<Piece> list_piece = white.pieceInventory;
        System.out.println(list_piece);

        List<Move> list_move = white.possibleMove;
        System.out.println(list_move);

         */



        //List<Move> listMove = board.board[3][6].pieceOnTile.generateMove(board.board);
        //System.out.println(listMove.size());
        //List<Piece> list_piece = board.getPiece(-1);


        long apresTraitement = System.currentTimeMillis();
        System.out.println("Duree:" + (apresTraitement-avantTraitement));



    }


}
