package gui;

import board.Board;
import board.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Chessboard extends JComponent {

    public static Image[] pieceIcons = new Image[13];

    public Board board;

    int theme;
    Color[] themeChessCom = {new Color(238, 238, 210), new Color(118, 150, 86)};
    Color[] themeLichess = {new Color(240,217,181), new Color(181,136,99)};
    Color[] themeBlackPink = {new Color(219, 130, 207), new Color(52, 41, 52)};

    Color color1;
    Color color2;

    Chessboard(Board board) throws IOException {
        this.board = board;
        loadImage();
        this.theme = 0;
    }

    Chessboard(Board board, int theme) throws IOException {
        this.board = board;
        loadImage();
        this.theme = theme;
    }

    @Override
    public void paintComponent(Graphics g){
        boolean white = true;

        switch (theme){
            case 0:
                color1 = themeChessCom[0];
                color2 = themeChessCom[1];
                break;

            case 1:
                color1 = themeLichess[0];
                color2 = themeLichess[1];
                break;

            case 2:
                color1 = themeBlackPink[0];
                color2 = themeBlackPink[1];
                break;
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(white){
                    g.setColor(color1);
                }else{
                    g.setColor(color2);
                }
                g.fillRect(j*64,i*64, 64, 64);
                white = !white;

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



}




