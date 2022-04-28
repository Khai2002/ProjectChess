package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ConnectionWindowClient extends JFrame implements ActionListener {

    Image img = ImageIO.read(new File("res/logo.jpg")).getScaledInstance(200,200, Image.SCALE_AREA_AVERAGING);
    ImageIcon imgIcon = new ImageIcon(img);


    JLabel connection;
    Timer timer;

    JLabel waitForHost;



    public ConnectionWindowClient() throws IOException {

        timer = new Timer(500, this);
        timer.start();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(530, 650);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        //this.setOpacity(1);

        JLabel logo = new JLabel();
        logo.setIcon(imgIcon);
        logo.setBounds(165,0,200,200);

        connection = new JLabel("CONNECTED");
        connection.setBounds(0,230,525,50);
        connection.setFont(new Font("Comic Sans",Font.BOLD,25));
        connection.setHorizontalAlignment(JLabel.CENTER);
        this.add(connection);

        waitForHost = new JLabel("Waiting for host to choose Game Mode");
        waitForHost.setBounds(0,300,525,50);
        waitForHost.setFont(new Font("Comic Sans",Font.BOLD,22));
        waitForHost.setHorizontalAlignment(JLabel.CENTER);
        this.add(waitForHost);



        this.add(logo);

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new ConnectionWindowClient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer){

        }
    }
}
