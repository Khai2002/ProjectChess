package gui;

import board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class History extends JComponent implements ActionListener {
    public LinkedList<String> moves;
    JPanel panel;
    JPanel historyBoard;
    JButton draw;
    JButton resign;
    ImageIcon flag;
    ImageIcon drawIcon;

    public static void main(String[] args) {
        new History();
    }


    History(){

        moves = new LinkedList<>();

        drawIcon = new ImageIcon("res/drawIcon.webp");
        Image image = drawIcon.getImage();
        Image imageScaled = image.getScaledInstance(10,10,0);
        drawIcon = new ImageIcon(imageScaled);

        draw = new JButton();
        draw.setIcon(drawIcon);
        draw.setBounds(0,512,55,20);
        draw.setBackground(Color.BLACK);
        draw.addActionListener(this);
        draw.setFocusable(false);


        flag = new ImageIcon("res/resign flag.png");

        resign = new JButton();
        resign.setIcon(flag);
        resign.setBounds(100,512,55,20);
        resign.setBackground(Color.black);
        resign.addActionListener(this);
        resign.setFocusable(false);

        this.add(draw);
        this.add(resign);

    }
    public void paintComponent(Graphics g){
        System.out.println("Im painting");
        g.setFont(new Font("Comic Sans",Font.BOLD,13));
        g.setColor(Color.WHITE);
        System.out.println(moves);
        for(int i = 0; i<this.moves.size(); i++){
            if(i%2==0){
                g.drawString(Integer.toString(i/2+1),5,i*10+20);
                g.drawString(moves.get(i),20,i*10+20);
            } else{
                g.drawString(moves.get(i),150,(i-1)*10+20);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resign){

        }
        if(e.getSource()==draw){

        }

    }
}
