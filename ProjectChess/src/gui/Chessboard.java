package gui;

import board.Board;
import board.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Chessboard extends JPanel {

    public static Image[] pieceIcons = new Image[13];

    public Board board;

    int theme;
    Color[] themeChessCom = {new Color(238, 238, 210), new Color(118, 150, 86)};
    Color[] themeLichess = {new Color(240,217,181), new Color(181,136,99)};
    Color[] themeBlackPink = {new Color(219, 130, 207), new Color(52, 41, 52)};

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
    public void paint(Graphics g){
        boolean white = true;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(white){
                    if(theme == 0){
                        g.setColor(themeChessCom[0]);
                    }else if(theme == 1){
                        g.setColor(themeLichess[0]);
                    }else if(theme == 2){
                        g.setColor(themeBlackPink[0]);
                    }
                }else{
                    if(theme == 0){
                        g.setColor(themeChessCom[1]);
                    }else if(theme == 1){
                        g.setColor(themeLichess[1]);
                    }else if(theme == 2){
                        g.setColor(themeBlackPink[1]);
                    }
                }
                g.fillRect(j*64+55,i*64+50, 64, 64);
                white = !white;

                if(board.board[i][j] instanceof Tile.OccupiedTile){
                    int pieceIconsPosition = board.board[i][j].pieceOnTile.id + 6;
                    g.drawImage(pieceIcons[pieceIconsPosition], j*64+55,i*64+50,this);
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



}




