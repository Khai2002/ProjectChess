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

        this.buildNextSetAB(engineBoard);
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
                        newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;
                        //System.out.println(newBoard.treeStateEvaluation);

                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);
                        newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;

                    }

                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }else{
                for(Move move: board.blackMoves){
                    Board newBoard;
                    if(board.searchDepth+1 == this.depth){
                        newBoard = new Board(board, move, false, board.searchDepth + 1);
                        newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;
                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);
                    }
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
    public int buildNextSetAB(Board board){

        int counter = 0;

        // If Base case then skip
        if(board.searchDepth < this.depth){
            if(board.colorActive ==1){
                for(Move move: board.whiteMoves){
                    Board newBoard;
                    if(board.searchDepth+1 == this.depth){
                        newBoard = new Board(board, move, false, board.searchDepth + 1);
                        newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;
                        //System.out.println(newBoard.treeStateEvaluation);

                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);
                        newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;

                    }

                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }else{
                for(Move move: board.blackMoves){
                    Board newBoard;
                    if(board.searchDepth+1 == this.depth){
                        newBoard = new Board(board, move, false, board.searchDepth + 1);
                        newBoard.treeStateEvaluation = newBoard.boardStateEvaluation;
                    }else{
                        newBoard = new Board(board, move, true, board.searchDepth + 1);
                    }
                    board.nextBoardSet.add(newBoard);
                    counter ++;
                }
            }
        }

        //System.out.println(board.nextBoardSet.size());

        if(!board.nextBoardSet.isEmpty()){
            int temp = 0;
            for(Board cursor: board.nextBoardSet){
                cursor.alpha = board.alpha;
                cursor.beta = board.beta;
                counter += buildNextSet(cursor);

                if(temp == 0){
                    System.out.println("hi1");
                    board.treeStateEvaluation = cursor.treeStateEvaluation;
                    board.optiMalMove = cursor.previousMove;
                    if(board.colorActive == 1){
                        board.alpha = cursor.treeStateEvaluation;
                    }else{
                        board.beta = cursor.treeStateEvaluation;
                    }
                    if(board.alpha > board.beta){
                        System.out.println("hihihihihihihihi");
                        break;
                    }

                    //System.out.println("Heyyyyyyyyyyyy");
                }else{
                    if(board.colorActive == 1){

                        if(board.treeStateEvaluation < cursor.treeStateEvaluation){
                            System.out.println("hi2");
                            board.treeStateEvaluation = cursor.treeStateEvaluation;
                            board.optiMalMove = cursor.previousMove;
                            board.alpha = cursor.treeStateEvaluation;
                        }
                        if(board.alpha > board.beta){
                            System.out.println("hihihihihihihihi");
                            break;
                        }
                    }else{
                        if(board.treeStateEvaluation > cursor.treeStateEvaluation){
                            System.out.println("hi3");
                            board.treeStateEvaluation = cursor.treeStateEvaluation;
                            board.optiMalMove = cursor.previousMove;
                            board.beta = cursor.treeStateEvaluation;
                        }
                        if(board.alpha > board.beta){
                            System.out.println("hihihihihihihihi");
                            break;
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

}
