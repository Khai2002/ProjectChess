package gui;

import javax.swing.*;
import java.awt.*;

public class WindowTheEnd extends JFrame {
    public WindowTheEnd() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(360, 235);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // in center of the screen
        this.setVisible(false);

        JLabel TheText = new JLabel("FIN DE LA PARTIE !\n MATCH NUL");
        TheText.setForeground(Color.BLACK);
        TheText.setBackground(Color.WHITE);
        TheText.setBounds(200, 105, 200, 100);
    }

}