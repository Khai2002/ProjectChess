package piece;

import board.Board;
import board.Tile;
import move.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece{

    public boolean checkDoubleMove;

    public Pawn(int[] position, int color, boolean moved){
        super(position, color, moved);
        this.value = 1;
        this.id = 1 * this.color;
        this.name = "Pawn";
        this.checkMoved();
        this.checkDoubleMove = false;
    }

    @Override
    public void updateStatus(Move move){
        super.updateStatus(move);
        this.moved = true;
    }

    public void checkMoved(){
        if(this.id>0){
            if(this.position[0] == 6){
                this.moved = false;
            }else{
                this.moved = true;
            }
        }else{
            if(this.position[0] == 1){
                this.moved = false;
            }else{
                this.moved = true;
            }
        }
    }

    @Override
    public void generateMove(Board board){
        int[][] moveCoefficientEat = {{-1,1},{-1,-1}};

        int xCoordinate = this.position[0];
        int yCoordinate = this.position[1];
        // Check if pawn can move forward 1 tile

        boolean isPromotion = false;
        if(this.color == 1){
            if(xCoordinate == 1){
                isPromotion = true;
            }
        }else{
            if(xCoordinate == 6){
                isPromotion = true;
            }
        }



        if(xCoordinate-this.color>=0 && xCoordinate-this.color<8 && board.board[xCoordinate-this.color][yCoordinate] instanceof Tile.EmptyTile){


            //System.out.println("Hello Im here " + this.position[0]+ " " + this.position[1] + " "+ this.color);

            if(isPromotion){
                //System.out.println("About to promote");
                listMove.add(new Move(this,2 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color][yCoordinate]));
                listMove.add(new Move(this,3 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color][yCoordinate]));
                listMove.add(new Move(this,4 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color][yCoordinate]));
                listMove.add(new Move(this,5 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color][yCoordinate]));
            }else{
                listMove.add(new Move(this,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color][yCoordinate]));
            }



            // Check if pawn can move forward 2 tiles
            if(!this.moved && board.board[xCoordinate-this.color*2][yCoordinate] instanceof Tile.EmptyTile){
                listMove.add(new Move(this,board.board[xCoordinate][yCoordinate],board.board[xCoordinate-this.color*2][yCoordinate]));
            }
        }

        // Check if pawn can eat other piece
        for(int[] move:moveCoefficientEat){
            move[0] = this.color*move[0];
            move[1] = this.color*move[1];

            if(checkValidMove(this.position, move,1, board.board) == 1) {
                if(isPromotion){
                    listMove.add(new Move(this,2 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate + move[0]][yCoordinate + move[1]]));
                    listMove.add(new Move(this,3 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate + move[0]][yCoordinate + move[1]]));
                    listMove.add(new Move(this,4 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate + move[0]][yCoordinate + move[1]]));
                    listMove.add(new Move(this,5 ,board.board[xCoordinate][yCoordinate],board.board[xCoordinate + move[0]][yCoordinate + move[1]]));
                }else{
                    listMove.add(new Move(this, board.board[xCoordinate][yCoordinate], board.board[xCoordinate + move[0]][yCoordinate + move[1]]));
                }


            }else if(!(board.enPassantTileCoordinate == null) &&
                board.enPassantTileCoordinate[0] == this.position[0] + move[0] &&
                board.enPassantTileCoordinate[1] == this.position[1] + move[1]){

            listMove.add(new Move(this, board.board[this.position[0]][this.position[1] + move[1]].pieceOnTile, board.board[xCoordinate][yCoordinate],
                    board.board[this.position[0]+move[0]][this.position[1]+move[1]]));

            if(board.eliminateMove){
                //System.out.println("solved the problem" + board.board[this.position[0]][this.position[1] + move[1]].pieceOnTile);
            }

            }
        }
    }


}
