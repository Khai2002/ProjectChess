package gui;

import board.Board;
import board.Tile;
import move.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Chessboard extends JComponent implements MouseListener, ActionListener {

    public static Image[] pieceIcons = new Image[13];

    public Board board;
    public int[] startingCoordination;
    public int[] destinationCoordination;

    public boolean tileChose;
    public int[] highlightTile;

    public Timer timerAnimation;
    public Image pieceIconType;
    public int xPiece;
    public int yPiece;
    public int xGoal;
    public int yGoal;
    public int xVelocity;
    public int yVelocity;
    public boolean isAnimating;

    public Image affectedPieceIconType;
    public int affectedXPiece;
    public int affectedYPiece;
    public int affectedXGoal;
    public int affectedYGoal;
    public int affectedXVelocity;
    public int affectedYVelocity;



    public LinkedList<Integer[]> dottedTiles =  new LinkedList<>();

    public Move definedMove;


    int theme;
    Color[] themeChessCom = {new Color(238, 238, 210), new Color(118, 150, 86), new Color(246,246,105),
            new Color(186,202,43),new Color(214,214,189), new Color(106,135,77),};
    Color[] themeLichess = {new Color(240,217,181), new Color(181,136,99), new Color(205,210,106),
            new Color(170,162,58), new Color(130,151,105), new Color(100,111,54)};
    Color[] themeBlackPink = {new Color(219, 130, 207), new Color(52, 41, 52)};

    Color color1;
    Color color2;
    Color color3;
    Color color4;
    Color color5;
    Color color6;

    Chessboard(Board board) throws IOException {
        this.board = board;
        loadImage();
        this.theme = 0;
        this.timerAnimation = new Timer(5,this);
        this.timerAnimation.start();
        this.isAnimating = false;
        this.addMouseListener(this);
        this.tileChose = false;
    }

    Chessboard(Board board, int theme) throws IOException {
        this.board = board;
        loadImage();
        this.theme = theme;
        this.timerAnimation = new Timer(10,this);
        this.timerAnimation.start();
        this.isAnimating = false;
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
                color5 = themeChessCom[4];
                color6 = themeChessCom[5];
                break;

            case 1:
                color1 = themeLichess[0];
                color2 = themeLichess[1];
                color3 = themeLichess[2];
                color4 = themeLichess[3];
                color5 = themeLichess[4];
                color6 = themeLichess[5];
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




                //System.out.println(dottedTiles);

                for(Integer[] dot : dottedTiles){
                    if(Arrays.equals(dot, new Integer[]{i,j})){
                        if(white){
                            g.setColor(color6);
                        }else{
                            g.setColor(color5);
                        }

                        if(board.board[i][j] instanceof Tile.OccupiedTile){
                            this.drawHollowCircle((Graphics2D) g,j*64+32,i*64+32,50);
                        }else{
                            this.drawCenteredCircle((Graphics2D) g,j*64+32,i*64+32,20);
                        }


                    }
                }

                if(isAnimating){

                    // Animation Board
                    if(board.board[i][j] instanceof Tile.OccupiedTile){
                        if(board.previousMove == null){
                            int pieceIconsPosition = board.board[i][j].pieceOnTile.id + 6;
                            g.drawImage(pieceIcons[pieceIconsPosition], j*64,i*64,this);
                        }else if(board.previousMove.destinationTile.tileCoordinate[0]==i && board.previousMove.destinationTile.tileCoordinate[1]==j){

                        }else if(board.previousMove.type == 2 && board.previousMove.affectedDestinationTile.tileCoordinate[0] == i && board.previousMove.affectedDestinationTile.tileCoordinate[1] == j){

                        }else{
                            int pieceIconsPosition = board.board[i][j].pieceOnTile.id + 6;
                            g.drawImage(pieceIcons[pieceIconsPosition], j*64,i*64,this);
                        }

                    }

                }else{

                    // Non Animation Board
                    if(board.board[i][j] instanceof Tile.OccupiedTile){
                        int pieceIconsPosition = board.board[i][j].pieceOnTile.id + 6;
                        g.drawImage(pieceIcons[pieceIconsPosition], j*64,i*64,this);
                    }

                }




            }
            white=!white;
        }

        if(isAnimating){
            g.drawImage(pieceIconType,xPiece,yPiece,this);
            if(board.previousMove.type == 2){
                g.drawImage(affectedPieceIconType,affectedXPiece,affectedYPiece,this);
            }

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
        board.printBoard1();

        // Animation for the 1st piece
        this.xPiece = board.previousMove.startingTile.tileCoordinate[1] * 64;
        this.yPiece = board.previousMove.startingTile.tileCoordinate[0] * 64;

        this.xGoal = board.previousMove.destinationTile.tileCoordinate[1] * 64;
        this.yGoal = board.previousMove.destinationTile.tileCoordinate[0] * 64;

        if(xGoal == xPiece){
            xVelocity = 0;
            yVelocity = 1;
        }else if(yGoal == yPiece){
            yVelocity = 0;
            xVelocity = 1;
        }else{
            if(Math.abs(xGoal-xPiece)<=Math.abs(yGoal-yPiece)){
                xVelocity = 1;
                yVelocity = Math.abs(yGoal-yPiece)/Math.abs(xGoal-xPiece);
            }else{
                yVelocity = 1;
                xVelocity = Math.abs(xGoal-xPiece)/Math.abs(yGoal-yPiece);
            }
        }

        if(xGoal<xPiece){
            xVelocity *= -1;
        }

        if(yGoal<yPiece){
            yVelocity *= -1;
        }

        xVelocity *= 8;
        yVelocity *= 8;

        this.pieceIconType = pieceIcons[board.previousMove.piece.id + 6];
        //System.out.println("Updating Chess Board with " + xPiece + " "+ yPiece + " "+ xGoal+" "+yGoal);


        // Animation for 2nd piece if type is 2
        if(board.previousMove.type == 2){
            this.affectedXPiece = board.previousMove.affectedStartingTile.tileCoordinate[1] * 64;
            this.affectedYPiece = board.previousMove.affectedStartingTile.tileCoordinate[0] * 64;

            this.affectedXGoal = board.previousMove.affectedDestinationTile.tileCoordinate[1] * 64;
            this.affectedYGoal = board.previousMove.affectedDestinationTile.tileCoordinate[0] * 64;

            if(affectedXGoal == affectedXPiece){
                affectedXVelocity = 0;
                affectedYVelocity = 1;
            }else if(affectedYGoal == affectedYPiece){
                affectedYVelocity = 0;
                affectedXVelocity = 1;
            }else{
                if(Math.abs(affectedXGoal-affectedXPiece)<=Math.abs(affectedYGoal-affectedYPiece)){
                    affectedXVelocity = 1;
                    affectedYVelocity = Math.abs(affectedYGoal-affectedYPiece)/Math.abs(affectedXGoal-affectedXPiece);
                }else{
                    affectedYVelocity = 1;
                    affectedXVelocity = Math.abs(affectedXGoal-affectedXPiece)/Math.abs(affectedYGoal-affectedYPiece);
                }
            }

            if(affectedXGoal<affectedXPiece){
                affectedXVelocity *= -1;
            }

            if(affectedYGoal<affectedYPiece){
                affectedYVelocity *= -1;
            }

            affectedXVelocity *= 8;
            affectedYVelocity *= 8;

            this.affectedPieceIconType = pieceIcons[board.previousMove.affectedPiece.id + 6];
        }else{

        }

        this.isAnimating = true;
        this.repaint();
    }

    public void resetChessboard(){
        this.startingCoordination = null;
        this.destinationCoordination = null;
    }

    public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
    }

    public void drawHollowCircle(Graphics2D g, int x, int y, int r){
        //g.setColor(Color.LIGHT_GRAY);
        g.setStroke(new BasicStroke(4));
        g.drawOval(x-r/2, y-r/2, r, r);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
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
                        if(move.type == 2){
                            definedMove = new Move(board.board[highlightTile[0]][highlightTile[1]].pieceOnTile,
                                    move.affectedPiece,
                                    board.board[highlightTile[0]][highlightTile[1]],
                                    board.board[y][x],
                                    move.affectedStartingTile,
                                    move.affectedDestinationTile);
                        }else if(move.type == 1){
                            definedMove = new Move(board.board[highlightTile[0]][highlightTile[1]].pieceOnTile,
                                    board.board[board.previousMove.destinationTile.tileCoordinate[0]][board.previousMove.destinationTile.tileCoordinate[1]].pieceOnTile,
                                    board.board[highlightTile[0]][highlightTile[1]],
                                    board.board[board.enPassantTileCoordinate[0]][board.enPassantTileCoordinate[1]]);
                        }else{
                            definedMove = moveProposed;
                        }
                        break;
                    }
                }
            }else{
                for(Move move:board.blackMoves){
                    if(move.equals(moveProposed)){
                        if(move.type == 2){
                            definedMove = new Move(board.board[highlightTile[0]][highlightTile[1]].pieceOnTile,
                                    move.affectedPiece,
                                    board.board[highlightTile[0]][highlightTile[1]],
                                    board.board[y][x],
                                    move.affectedStartingTile,
                                    move.affectedDestinationTile);
                        }else if(move.type == 1){
                            definedMove = new Move(board.board[highlightTile[0]][highlightTile[1]].pieceOnTile,
                                    board.board[board.previousMove.destinationTile.tileCoordinate[0]][board.previousMove.destinationTile.tileCoordinate[1]].pieceOnTile,
                                    board.board[highlightTile[0]][highlightTile[1]],
                                    board.board[board.enPassantTileCoordinate[0]][board.enPassantTileCoordinate[1]]);
                        }else{
                            definedMove = moveProposed;
                        }
                        break;
                    }
                }
            }
        }
        if(definedMove!=null){
//            System.out.println(definedMove.toString());
        }else{
//            System.out.println("Nope");
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

        dottedTiles = new LinkedList<>();

        if(this.highlightTile !=null){
//            System.out.println("x= "+ highlightTile[1]+" y= "+ highlightTile[0]);
            if(board.colorActive == 1){
                for(Move move : board.whiteMoves){
                    if(move.startingTile.tileCoordinate[0] == highlightTile[0] &&
                            move.startingTile.tileCoordinate[1] == highlightTile[1]){
                        dottedTiles.add(new Integer[]{move.destinationTile.tileCoordinate[0], move.destinationTile.tileCoordinate[1]});
                    }
                }
            }else{
                for(Move move : board.blackMoves){
                    if(move.startingTile.tileCoordinate[0] == highlightTile[0] &&
                            move.startingTile.tileCoordinate[1] == highlightTile[1]){
                        dottedTiles.add(new Integer[]{move.destinationTile.tileCoordinate[0], move.destinationTile.tileCoordinate[1]});
                    }
                }
            }
        }else{
//            System.out.println("Non defined");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timerAnimation){

            boolean flicker = false;

            if(xPiece != xGoal || yPiece != yGoal){
                xPiece += xVelocity;
                yPiece += yVelocity;
//                System.out.println("Timer reached " +xPiece + " " +yPiece);
                flicker = true;

            }


            if(affectedXPiece != affectedXGoal || affectedYPiece != affectedYGoal){
                affectedXPiece += affectedXVelocity;
                affectedYPiece += affectedYVelocity;
                flicker = true;
            }

            if(flicker){
                repaint();
            }


        }
    }
}




