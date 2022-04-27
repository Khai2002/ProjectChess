package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class WindowTheEnd extends JFrame {
    JLabel text;

    public WindowTheEnd(String insert) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 430);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // in center of the screen
        JPanel fond = new JPanel();
        fond.setBackground(Color.white);
        fond.setSize(1000, 1000);
        this.add(fond);

        text = new JLabel(insert);
        text.setForeground(Color.BLACK);
        text.setBackground(Color.WHITE);
        text.setBounds(500, 300, 200, 100);
        text.setFont(new Font("Comic Sans",Font.BOLD,48));
        fond.add(text);

        JLabel monEtiquette  = new JLabel(new ImageIcon("res/Egalite.png"));
        monEtiquette.setSize(600, 430);
        fond.add(monEtiquette);

        this.setVisible(true);
    }
    public static void main(String[] args){
        new WindowTheEnd("Time up ! DRAW o_o");
    }
}