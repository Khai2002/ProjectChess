package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChooseName extends JFrame implements KeyListener, ActionListener {

    JFrame frame;
    JButton question;
    JTextField text;
    JPanel panel;
    String name;

    public static void main(String[] args) {
        new ChooseName();
    }

    ChooseName(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(360,235);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // in center of the screen
        this.addKeyListener(this);
        this.setVisible(true);

        question = new JButton("What is your name ?");
        question.setBounds(0,0,360,60);
        question.setHorizontalAlignment(JTextField.CENTER);
        question.setFont(new Font("Comic Sans",Font.BOLD,25));
        question.setBackground(Color.orange);
        question.setForeground(Color.black);
        //question.setEditable(false);
        question.addActionListener(this);

        text = new JTextField();
        text.setBounds(0,60,360,140);
        text.setFont(new Font("Comic Sans",Font.BOLD,25));
        text.setHorizontalAlignment(JTextField.CENTER); // text in the center of
        text.addKeyListener(this);

        panel = new JPanel();
        panel.setBounds(0,0,360,200);
        panel.setLayout(null);

        panel.add(question);
        panel.add(text);

        this.add(panel);
        this.setVisible(true);

        this.name = name;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==10){
            dispose();
            new ChooseAvatar();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
         System.out.println("char "+e.getKeyChar());
         System.out.println("code "+e.getKeyCode());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new ChooseAvatar();
    }
}
