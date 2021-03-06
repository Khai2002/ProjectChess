package gui;

import board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class History extends JPanel implements ActionListener {
    public LinkedList<String> moves;
    public int index = 0;

    public static void main(String[] args) {
        new History();
    }


    History(){

        moves = new LinkedList<>();
        this.setPreferredSize(new Dimension(200,200));


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("Comic Sans",Font.BOLD,20));
        g.setColor(new Color(71, 71, 71));
        for(int i = 0; i<this.moves.size(); i++){
            if(i%2==0){
                g.drawString(Integer.toString((i/2+1)+index),5,i*15+20);
                g.drawString(moves.get(i),100,i*15+20);
            } else{
                g.drawString(moves.get(i),250,(i-1)*15+20);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}