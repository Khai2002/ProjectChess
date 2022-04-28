import board.Board;
import game.Engine;
import game.Human;
import game.Loop;
import gui.Interface;

import java.io.IOException;

public class GameStartOffline {
    public static void main(String[] args) throws Exception {
        Board board = new Board(Board.startFEN);

        Interface interFace = new Interface(board);

        Loop loop = new Loop();

        loop.gameLoopHumanMachineBetter(board,interFace);
    }
}
