package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CustomizeMenu extends JPanel implements ActionListener{

    int player;
    JButton avatar;
    JTextField name;
    JLabel label;
    ChooseAvatar chooseAvatar;
    Timer checkForAvatar;
    boolean isInGame;


    ImageIcon avatarIcon;


    public CustomizeMenu(int player){
        this.setPreferredSize(new Dimension(240,200));
        this.player =player;
        this.setLayout(null);
        this.setBackground(new Color(85, 152, 196));


        this.checkForAvatar = new Timer(10, this);
        checkForAvatar.start();

        //chooseAvatar = new ChooseAvatar();
        //chooseAvatar.setVisible(false);

        label = new JLabel("Player " + player);
        label.setBounds(10,10,220,50);
        //label.setVerticalTextPosition(JLabel.CENTER);
        //label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sans", Font.BOLD, 32));

        this.avatarIcon = new ImageIcon("res/avatar1.png");

        avatar = new JButton();
        avatar.setFocusable(false);
        avatar.setBounds(10,70,220,220);
        avatar.setBackground(new Color(155, 205, 231));
        avatar.setIcon(avatarIcon);
        avatar.addActionListener(this);

        name = new JTextField("Default Name " + player);
        name.setBounds(10,300,220,40);
        name.setFont(new Font("Comic Sans", Font.BOLD, 18));
        name.setHorizontalAlignment(JTextField.CENTER);

        this.add(avatar);
        this.add(name);
        this.add(label);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == avatar){
            if(!this.isInGame){
                chooseAvatar = new ChooseAvatar(this);
            }

        }else if(e.getSource() == checkForAvatar){
            avatar.setIcon(avatarIcon);
        }
    }


}
