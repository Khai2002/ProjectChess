package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TaskBar extends JPanel {

    JPanel taskBar;
    JLabel panel;
    ImageIcon avatar;
    String name;



    TaskBar(ImageIcon avatar, String name){
        this.avatar = avatar;
        this.name = name;
        this.add(panel);

        panel = new JLabel();



    }
}
