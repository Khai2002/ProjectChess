package game;

import board.Board;
import move.Move;

import java.util.TreeSet;

public class Engine extends Player{

    public int depth;

    public Engine(int depth){
        this.depth = depth;
    }

    public void engineChoose(Board board, int color){}

    public int buildNextSet(Board board){

        int counter = 0;

        // If Base case then skip
        if(board.searchDepth < this.depth){
            if(board.colorActive ==1){
                for(Move move: board.whiteMoves){
                    Board newBoard = new Board(board, move, true, board.searchDepth + 1);
                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }else{
                for(Move move: board.blackMoves){
                    Board newBoard = new Board(board, move, true, board.searchDepth + 1);
                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }
        }

        //System.out.println(board.nextBoardSet.size());

        if(!board.nextBoardSet.isEmpty()){
            for(Board cursor: board.nextBoardSet){
                counter += buildNextSet(cursor);
            }
        }

        return counter;
    }

}
