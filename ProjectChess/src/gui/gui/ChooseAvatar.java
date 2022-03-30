package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChooseAvatar extends JFrame implements ActionListener {

    // Attribute
    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JTextField text;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    String name;

    ImageIcon avatar1;
    ImageIcon avatar2;
    ImageIcon avatar3;
    ImageIcon avatar4;
    ImageIcon avatar5;
    ImageIcon avatar6;

    ImageIcon avatarChosen;

    CustomizeMenu customizeMenu;



    ChooseAvatar(CustomizeMenu customizeMenu){

        this.customizeMenu = customizeMenu;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // in center of the screen
        frame.setResizable(false);

        panel1 = new JPanel();
        panel1.setOpaque(true);
        panel1.setBounds(0,0,500,60);
        panel1.setLayout(null);

        this.text = new JTextField("Please choose your avatar >-<");
        this.text.setBounds(0,0,500,60);
        this.text.setFont(new Font("Comic Sans",Font.BOLD,25));
        this.text.setHorizontalAlignment(JTextField.CENTER);
        this.text.setBackground(Color.white);
        this.text.setForeground(Color.black);
        this.text.setEditable(false);

        panel2 = new JPanel();
        panel2.setOpaque(true);
        panel2.setBounds(0,60,500,450);
        panel2.setLayout(new GridLayout(2,3,0,0));

        avatar1 = new ImageIcon("res/avatar1.png");

        button1 = new JButton();
        button1.setIcon(avatar1);
        button1.setBackground(Color.orange);
        button1.addActionListener(this);
        button1.setFocusable(false);

        avatar2 = new ImageIcon("res/avatar2.png");

        button2 = new JButton();
        button2.setIcon(avatar2);
        button2.setBackground(Color.orange);
        button2.addActionListener(this);
        button2.setFocusable(false);

        avatar3= new ImageIcon("res/avatar3.png");

        button3 = new JButton();
        button3.setIcon(avatar3);
        button3.setBackground(Color.orange);
        button3.addActionListener(this);
        button3.setFocusable(false);

        avatar4 = new ImageIcon("res/avatar4.png");

        button4 = new JButton();
        button4.setIcon(avatar4);
        button4.setBackground(Color.pink);
        button4.addActionListener(this);
        button4.setFocusable(false);

        avatar5 = new ImageIcon("res/avatar5.png");

        button5 = new JButton();
        button5.setIcon(avatar5);
        button5.setBackground(Color.pink);
        button5.addActionListener(this);
        button5.setFocusable(false);

        avatar6 = new ImageIcon("res/avatar6.png");

        button6 = new JButton();
        button6.setIcon(avatar6);
        button6.setBackground(Color.pink);
        button6.addActionListener(this);
        button6.setFocusable(false);

        panel1.add(this.text);

        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        panel2.add(button6);

        frame.add(panel1);
        frame.add(panel2);

        avatarChosen = avatar1;

        frame.setVisible(true);

    }

    ChooseAvatar(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // in center of the screen
        frame.setResizable(false);

        panel1 = new JPanel();
        panel1.setOpaque(true);
        panel1.setBounds(0,0,500,60);
        panel1.setLayout(null);

        this.text = new JTextField("Please choose your avatar >-<");
        this.text.setBounds(0,0,500,60);
        this.text.setFont(new Font("Comic Sans",Font.BOLD,25));
        this.text.setHorizontalAlignment(JTextField.CENTER);
        this.text.setBackground(Color.white);
        this.text.setForeground(Color.black);
        this.text.setEditable(false);

        panel2 = new JPanel();
        panel2.setOpaque(true);
        panel2.setBounds(0,60,500,450);
        panel2.setLayout(new GridLayout(2,3,0,0));

        avatar1 = new ImageIcon("res/avatar1.png");

        button1 = new JButton();
        button1.setIcon(avatar1);
        button1.setBackground(Color.orange);
        button1.addActionListener(this);
        button1.setFocusable(false);

        avatar2 = new ImageIcon("res/avatar2.png");

        button2 = new JButton();
        button2.setIcon(avatar2);
        button2.setBackground(Color.orange);
        button2.addActionListener(this);
        button2.setFocusable(false);

        avatar3= new ImageIcon("res/avatar3.png");

        button3 = new JButton();
        button3.setIcon(avatar3);
        button3.setBackground(Color.orange);
        button3.addActionListener(this);
        button3.setFocusable(false);

        avatar4 = new ImageIcon("res/avatar4.png");

        button4 = new JButton();
        button4.setIcon(avatar4);
        button4.setBackground(Color.pink);
        button4.addActionListener(this);
        button4.setFocusable(false);

        avatar5 = new ImageIcon("res/avatar5.png");

        button5 = new JButton();
        button5.setIcon(avatar5);
        button5.setBackground(Color.pink);
        button5.addActionListener(this);
        button5.setFocusable(false);

        avatar6 = new ImageIcon("res/avatar6.png");

        button6 = new JButton();
        button6.setIcon(avatar6);
        button6.setBackground(Color.pink);
        button6.addActionListener(this);
        button6.setFocusable(false);

        panel1.add(this.text);

        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        panel2.add(button6);

        frame.add(panel1);
        frame.add(panel2);

        avatarChosen = avatar1;

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.dispose();
        if(e.getSource()==button1){
            avatarChosen = avatar1;

        }if(e.getSource()==button2){
            avatarChosen = avatar2;

        }if(e.getSource()==button3){
            avatarChosen = avatar3;

        }if(e.getSource()==button4){
            avatarChosen = avatar4;

        }if(e.getSource()==button5){
            avatarChosen = avatar5;

        }if(e.getSource()==button6){
            avatarChosen = avatar6;

        }

        if(this.customizeMenu!=null){
            this.customizeMenu.avatarIcon = avatarChosen;
        }

        frame.dispose();




    }
}

