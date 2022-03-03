package gui;

import javax.swing.*;
import java.awt.*;

public class Chessboard extends JPanel {

    public static void main(String[] args) {
        new Chessboard();
    }
    Chessboard(){

    }
    @Override
    public void paint(Graphics g){
        boolean white = true;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(white){
                    g.setColor(Color.white);
                }else{
                    g.setColor(Color.green);
                }
                g.fillRect(i*64+30, j*64+55, 64, 64);
                white = !white;
            }
            white=!white;
        }

    }

};




