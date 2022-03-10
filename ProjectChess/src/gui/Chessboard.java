package gui;

import board.Board;
import board.Tile;
import move.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Chessboard extends JComponent implements MouseListener {

    public static Image[] pieceIcons = new Image[13];

    public Board board;
    public int[] startingCoordination;
    public int[] destinationCoordination;

    public boolean tileChose;
    public int[] highlightTile;

    public Move definedMove;


    int theme;
    Color[] themeChessCom = {new Color(238, 238, 210), new Color(118, 150, 86), new Color(246,246,105), new Color(186,202,43)};
    Color[] themeLichess = {new Color(240,217,181), new Color(181,136,99), new Color(205,210,106), new Color(170,162,58)};
    Color[] themeBlackPink = {new Color(219, 130, 207), new Color(52, 41, 52)};

    Color color1;
    Color color2;
    Color color3;
    Color color4;

    Chessboard(Board board) throws IOException {
        this.board = board;
        loadImage();
        this.theme = 0;
        this.addMouseListener(this);
        this.tileChose = false;
    }

    Chessboard(Board board, int theme) throws IOException {
        this.board = board;
        loadImage();
        this.theme = theme;
        this.addMouseListener(this);
        this.tileChose = false;
    }

    @Override
    public void paintComponent(Graphics g){
        boolean white = true;

        switch (theme){
            case 0:
                color1 = themeChessCom[0];
                color2 = themeChessCom[1];
                color3 = themeChessCom[2];
                color4 = themeChessCom[3];
                break;

            case 1:
                color1 = themeLichess[0];
                color2 = themeLichess[1];
                color3 = themeLichess[2];
                color4 = themeLichess[3];
                break;

            case 2:
                color1 = themeBlackPink[0];
                color2 = themeBlackPink[1];
                color3 = themeBlackPink[2];
                color4 = themeBlackPink[3];
                break;
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){

                if(white){
                    if(highlightTile!=null && highlightTile[0]==i && highlightTile[1]==j){
                        g.setColor(color3);
                    }else if(board.previousMove != null &&
                            board.previousMove.startingTile.tileCoordinate[0] == i &&
                            board.previousMove.startingTile.tileCoordinate[1] == j){
                        g.setColor(color3);
                    }else if(board.previousMove != null &&
                            board.previousMove.destinationTile.tileCoordinate[0] == i &&
                            board.previousMove.destinationTile.tileCoordinate[1] == j){
                        g.setColor(color3);
                    }else{
                        g.setColor(color1);
                    }
                }else{
                    if(highlightTile!=null && highlightTile[0]==i && highlightTile[1]==j){
                        g.setColor(color4);
                    }else if(board.previousMove != null &&
                            board.previousMove.startingTile.tileCoordinate[0] == i &&
                            board.previousMove.startingTile.tileCoordinate[1] == j){
                        g.setColor(color4);
                    }else if(board.previousMove != null &&
                            board.previousMove.destinationTile.tileCoordinate[0] == i &&
                            board.previousMove.destinationTile.tileCoordinate[1] == j) {
                        g.setColor(color4);
                    }else{
                        g.setColor(color2);
                    }
                }

                g.fillRect(j*64,i*64, 64, 64);

                white = !white;

                if(j == 0){
                    g.setFont(new Font("Comic Sans", Font.BOLD, 13));
                    if(white){
                        g.setColor(color1);
                    }else{
                        g.setColor(color2);
                    }
                    g.drawString(Integer.toString(8-i),3,i*64+14);
                }

                Character[] characters = new Character[]{'a','b','c','d','e','f','g','h'};
                if(i==7){
                    g.setFont(new Font("Comic Sans", Font.BOLD, 13));
                    if(white){
                        g.setColor(color1);
                    }else{
                        g.setColor(color2);
                    }
                    g.drawString(Character.toString(characters[j]),j*64+52,506);
                }

                if(board.board[i][j] instanceof Tile.OccupiedTile){
                    int pieceIconsPosition = board.board[i][j].pieceOnTile.id + 6;
                    g.drawImage(pieceIcons[pieceIconsPosition], j*64,i*64,this);
                }
            }
            white=!white;
        }

    }
    public void loadImage() throws IOException {
        pieceIcons[0] = ImageIO.read(new File("res/King_b.png")).getScaledInstance(64,64,Image.SCALE_AREA_AVERAGING);
        pieceIcons[1] = ImageIO.read(new File("res/Queen_b.png")).getScaledInstance(64,64,Image.SCALE_AREA_AVERAGING);
        pieceIcons[2] = ImageIO.read(new File("res/Bishop_b.png")).getScaledInstance(64,64,Image.SCALE_AREA_AVERAGING);
        pieceIcons[3] = ImageIO.read(new File("res/Knight_b.png")).getScaledInstance(64,64,Image.SCALE_AREA_AVERAGING);
        pieceIcons[4] = ImageIO.read(new File("res/Rook_b.png")).getScaledInstance(64,64,Image.SCALE_AREA_AVERAGING);
        pieceIcons[5] = ImageIO.read(new File("res/Pawn_b.png")).getScaledInstance(64,64,Image.SCALE_AREA_AVERAGING);

        pieceIcons[7] = ImageIO.read(new File("res/Pawn_w.png")).getScaledInstance(64,64,Image.SCALE_SMOOTH);
        pieceIcons[8] = ImageIO.read(new File("res/Rook_w.png")).getScaledInstance(64,64,Image.SCALE_SMOOTH);
        pieceIcons[9] = ImageIO.read(new File("res/Knight_w.png")).getScaledInstance(64,64,Image.SCALE_SMOOTH);
        pieceIcons[10] = ImageIO.read(new File("res/Bishop_w.png")).getScaledInstance(64,64,Image.SCALE_SMOOTH);
        pieceIcons[11] = ImageIO.read(new File("res/Queen_w.png")).getScaledInstance(64,64,Image.SCALE_SMOOTH);
        pieceIcons[12] = ImageIO.read(new File("res/King_w.png")).getScaledInstance(64,64,Image.SCALE_SMOOTH);


    }

    public void updateChessBoard(Board board){
        this.board = board;
        this.repaint();
    }

    public void resetChessboard(){
        this.startingCoordination = null;
        this.destinationCoordination = null;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
/*
        int x = e.getX() >> 6;
        int y = e.getY() >> 6;

        if(board.colorActive == 1){
            if(board.board[y][x] instanceof Tile.OccupiedTile && board.whitePieces.contains(board.board[y][x].pieceOnTile)){
                this.highlightTile = new int[]{y,x};
            }else{
                this.highlightTile = null;
            }
        }else{
            if(board.board[y][x] instanceof Tile.OccupiedTile && board.blackPieces.contains(board.board[y][x].pieceOnTile)){
                this.highlightTile = new int[]{y,x};
            }else{
                this.highlightTile = null;
            }
        }

        if(this.highlightTile !=null){
            System.out.println("x= "+ highlightTile[1]+" y= "+ highlightTile[0]);
        }else{
            System.out.println("Non defined");
        }

        repaint();

 */





        /*
        if(this.startingCoordination == null){
            this.startingCoordination = new int[]{y,x};
            //System.out.println(startingCoordination[0] +" "+ startingCoordination[1]);
        }else{
            this.destinationCoordination = new int[]{y,x};
            //tileChose = false;
        }

        if(startingCoordination != null){
            //System.out.println(startingCoordination[0] +" "+ startingCoordination[1]);
        }

        if(destinationCoordination != null){
            //System.out.println(destinationCoordination[0]+" "+destinationCoordination[1]);

        }


         */



    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() >> 6;
        int y = e.getY() >> 6;

        this.definedMove = null;

        if(highlightTile!=null){
            Move moveProposed = new Move(board.board[highlightTile[0]][highlightTile[1]].pieceOnTile,
                    board.board[highlightTile[0]][highlightTile[1]],
                    board.board[y][x]);


            if(board.colorActive==1){
                for(Move move:board.whiteMoves){
                    if(move.equals(moveProposed)){
                        definedMove = moveProposed;
                        break;
                    }
                }
            }else{
                for(Move move:board.blackMoves){
                    if(move.equals(moveProposed)){
                        definedMove = moveProposed;
                        break;
                    }
                }
            }
        }
        if(definedMove!=null){
            System.out.println(definedMove.toString());
        }else{
            System.out.println("Nope");
        }

        if(board.colorActive == 1){
            if(board.board[y][x] instanceof Tile.OccupiedTile && board.whitePieces.contains(board.board[y][x].pieceOnTile)){
                this.highlightTile = new int[]{y,x};
            }else{
                this.highlightTile = null;
            }
        }else{
            if(board.board[y][x] instanceof Tile.OccupiedTile && board.blackPieces.contains(board.board[y][x].pieceOnTile)){
                this.highlightTile = new int[]{y,x};
            }else{
                this.highlightTile = null;
            }
        }

        if(this.highlightTile !=null){
            System.out.println("x= "+ highlightTile[1]+" y= "+ highlightTile[0]);
        }else{
            System.out.println("Non defined");
        }

        repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}




