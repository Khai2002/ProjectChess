package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ConnectionWindow extends JFrame implements ActionListener {

    Image img = ImageIO.read(new File("res/logo.jpg")).getScaledInstance(200,200, Image.SCALE_AREA_AVERAGING);
    ImageIcon imgIcon = new ImageIcon(img);
    public boolean checkConnection;
    public boolean readyToPlay;

    public JComboBox TempPartie;
    Object[] ElementsTempPartie;
    public JButton blackOrWhite;
    JButton gameMode;
    JButton BJouer;
    JLabel connection;
    Timer timer;

    JLabel chooseTimeControl;
    public JLabel ipShow;



    public ConnectionWindow() throws IOException {

        timer = new Timer(500, this);
        timer.start();
        checkConnection = false;
        readyToPlay = false;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(530, 650);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        //this.setOpacity(1);

        JLabel logo = new JLabel();
        logo.setIcon(imgIcon);
        logo.setBounds(165,0,200,200);

        connection = new JLabel("Waiting for another player");
        connection.setBounds(0,230,525,50);
        connection.setFont(new Font("Comic Sans",Font.BOLD,25));
        connection.setHorizontalAlignment(JLabel.CENTER);
        this.add(connection);

        // Ip Connection
        ipShow = new JLabel("Host IP Address: ");
        ipShow.setBounds(0,260,525,50);
        ipShow.setFont(new Font("Comic Sans",Font.PLAIN,18));
        ipShow.setHorizontalAlignment(JLabel.CENTER);
        this.add(ipShow);

        // Choose Time Control
        chooseTimeControl = new JLabel("Choose Time Control");
        chooseTimeControl.setBounds(0,350,510,50);
        chooseTimeControl.setFont(new Font("Comic Sans",Font.BOLD,22));
        chooseTimeControl.setHorizontalAlignment(JLabel.CENTER);
        this.add(chooseTimeControl);

        //Création JComboBox
        ElementsTempPartie = new Object[] {"1 min", "3 min", "5 min", "10 min"};
        TempPartie = new JComboBox<>(ElementsTempPartie);
        TempPartie.setBounds(70, 402, 260, 50);
        TempPartie.setFont(new Font("Comics Sans",Font.BOLD,18));
        TempPartie.setFocusable(false);
        this.add(TempPartie);
        TempPartie.addActionListener(this);

        // Game Mode
        blackOrWhite = new JButton("White");
        blackOrWhite.setBackground(new Color(224, 224, 224));
        blackOrWhite.setForeground(new Color(71, 71, 71));
        blackOrWhite.setBounds(340,402,100,50);
        blackOrWhite.setFont(new Font("Comics Sans",Font.BOLD,20));
        blackOrWhite.setFocusable(false);
        this.add(blackOrWhite);
        blackOrWhite.addActionListener(this);


        //Bouton "Jouer"
        BJouer = new JButton("PLAY");
        BJouer.setBounds(70, 466, 370, 50);
        BJouer.setFont(new Font("Comic Sans", Font.BOLD, 20));
        BJouer.setBackground(new Color(71, 71, 71));
        BJouer.setForeground(new Color(224, 224, 224));
        BJouer.setFocusable(false);
        BJouer.addActionListener(this); //à implémenter dans actionPerformed()
        this.add(BJouer);

        this.add(logo);

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new ConnectionWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer){
            if(checkConnection == true){
                connection.setText("Client CONNECTED");
            }else{

                if(connection.getText().equals("Waiting for another player...")){
                    connection.setText("Waiting for another player");
                }else{
                    connection.setText(connection.getText() + ".");
                }
            }
        }else if(e.getSource()==BJouer){
            readyToPlay = true;
        }else if(e.getSource()==blackOrWhite){
            if(blackOrWhite.getText().equals("Black")){
                blackOrWhite.setText("White");
                blackOrWhite.setForeground(new Color(71, 71, 71));
                blackOrWhite.setBackground(new Color(224,224,224));
            }else{
                blackOrWhite.setText("Black");
                blackOrWhite.setBackground(new Color(71, 71, 71));
                blackOrWhite.setForeground(new Color(224,224,224));
            }
        }else if(e.getSource()==gameMode){
            if(gameMode.getText().equals("Player vs Computer")){
                gameMode.setText("Player vs Player");
                blackOrWhite.setVisible(false);
            }else{
                gameMode.setText("Player vs Computer");
                blackOrWhite.setVisible(true);
            }
        }
    }
}
