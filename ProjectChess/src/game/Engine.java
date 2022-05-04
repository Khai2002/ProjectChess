package game;

import board.Board;
import move.Move;

import java.util.TreeSet;

public class Engine extends Player{

    public int depth;

    public Engine(int depth){
        this.depth = depth;
    }

    public Move engineChoose(Board board){

        String stringBoard = board.BoardToFen();

        Board engineBoard = new Board(stringBoard);

        this.buildNextSetTest(engineBoard);
        return engineBoard.optiMalMove;
    }

    public int buildNextSet(Board board){

        int counter = 0;

        // If Base case then skip
        if(board.searchDepth < this.depth){
            if(board.colorActive ==1){
                for(Move move: board.whiteMoves){
                    Board newBoard;
                    if(board.searchDepth+1 == this.depth){
                        newBoard = new Board(board, move, false, board.searchDepth + 1);
                        //System.out.println(newBoard.treeStateEvaluation);

                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);

                    }
                    newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;

                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }else{
                for(Move move: board.blackMoves){
                    Board newBoard;
                    if(board.searchDepth+1 == this.depth){
                        newBoard = new Board(board, move, false, board.searchDepth + 1);
                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);
                    }
                    newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;
                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }
        }

        //System.out.println(board.nextBoardSet.size());

        if(!board.nextBoardSet.isEmpty()){
            int temp = 0;
            for(Board cursor: board.nextBoardSet){
                counter += buildNextSet(cursor);

                if(temp == 0){
                    board.treeStateEvaluation = cursor.treeStateEvaluation;
                    board.optiMalMove = cursor.previousMove;
                    //System.out.println("Heyyyyyyyyyyyy");
                }else{
                    if(board.colorActive == 1){
                        if(board.treeStateEvaluation < cursor.treeStateEvaluation){
                            board.treeStateEvaluation = cursor.treeStateEvaluation;
                            board.optiMalMove = cursor.previousMove;
                        }
                    }else{
                        if(board.treeStateEvaluation > cursor.treeStateEvaluation){
                            board.treeStateEvaluation = cursor.treeStateEvaluation;
                            board.optiMalMove = cursor.previousMove;
                        }
                    }
                }

                temp ++;
            }

            //board.nextBoardSet = null;
        }else{
            board.treeStateEvaluation = board.boardStateEvaluation;
        }

        return counter;
    }

    public int buildNextSetTest(Board board){

        int counter = 0;

        // If Base case then skip
        if(board.searchDepth < this.depth){
            if(board.colorActive ==1){
                for(Move move: board.whiteMoves){
                    Board newBoard;
                    if(board.searchDepth+1 == this.depth){
                        newBoard = new Board(board, move, false, board.searchDepth + 1);
                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);
                    }
                    newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;

                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }else{
                for(Move move: board.blackMoves){
                    Board newBoard;
                    if(board.searchDepth+1 == this.depth){
                        newBoard = new Board(board, move, false, board.searchDepth + 1);
                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);
                    }
                    newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;

                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }
        }

        if(!board.nextBoardSet.isEmpty()){
            int temp = 0;
            for(Board cursor: board.nextBoardSet){

                counter += buildNextSetTest(cursor);

                if(temp == 0){
                    board.treeStateEvaluation = cursor.treeStateEvaluation;
                    board.optiMalMove = cursor.previousMove;
                }else{
                    if(board.colorActive == 1){
                        if(board.treeStateEvaluation < cursor.treeStateEvaluation){
                            board.treeStateEvaluation = cursor.treeStateEvaluation;
                            board.optiMalMove = cursor.previousMove;
                        }
                    }else{
                        if(board.treeStateEvaluation > cursor.treeStateEvaluation){
                            board.treeStateEvaluation = cursor.treeStateEvaluation;
                            board.optiMalMove = cursor.previousMove;
                        }
                    }
                }

                if(board.colorActive==1){
                    if(board.alpha < cursor.treeStateEvaluation){
                        board.alpha = cursor.treeStateEvaluation;
                    }
                }else{
                    if(board.beta > cursor.treeStateEvaluation){
                        board.beta = cursor.treeStateEvaluation;
                    }
                }

                if(board.beta < board.alpha){
                    break;
                }

                temp ++;
            }


        }else{
            board.treeStateEvaluation = board.boardStateEvaluation;
        }

        return counter;
    }

}
