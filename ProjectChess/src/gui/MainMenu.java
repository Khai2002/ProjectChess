package gui;

import board.Board;
import com.sun.tools.javac.Main;
import game.Loop;
import server.Server;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame implements ActionListener {

    Image img = ImageIO.read(new File("res/logo.jpg")).getScaledInstance(200,200, Image.SCALE_AREA_AVERAGING);
    ImageIcon imgIcon = new ImageIcon(img);

    JLabel welcome;
    JLabel credit;

    JButton playOffline;
    JButton playServer;
    JButton playClient;
    JButton exit;

    public JTextField ip;

    JLabel warning;
    public JLabel fault;



    int[] colors;

    public int choice = 0;

    Timer timer;


    public MainMenu() throws Exception {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(530, 850);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.setTitle("Project Chess");

        colors = new int[] {255,255,255,255,255,255,255,255};


        timer = new Timer(3,this);
        timer.start();


        JLabel logo = new JLabel();
        logo.setIcon(imgIcon);
        logo.setBounds(165,20,200,200);
        this.add(logo);

        welcome = new JLabel("PROJECT CHESS");
        welcome.setBounds(0,245,530,50);
        welcome.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC,35));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setForeground(new Color(colors[0],colors[0],colors[0]));
        this.add(welcome);

        credit = new JLabel("by Khải, Tùng, Hằng and Hélène");
        credit.setBounds(0,290,530,20);
        credit.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC,13));
        credit.setHorizontalAlignment(JLabel.CENTER);
        credit.setForeground(new Color(colors[1],colors[1],colors[1]));
        this.add(credit);

        playOffline = new JButton("Play Offline");
        playOffline.setBounds(115,350,300,50);
        playOffline.setFont(new Font("Comic Sans", Font.BOLD,22));
        playOffline.setFocusable(false);
        playOffline.setBackground(new Color(70,70,70));
        playOffline.setForeground(new Color(224,224,224));
        playOffline.addActionListener(this);
        this.add(playOffline);

        playServer = new JButton("Play on LAN as Host");
        playServer.setBounds(115,410,300,50);
        playServer.setFont(new Font("Comic Sans", Font.BOLD,22));
        playServer.setFocusable(false);
        playServer.setBackground(new Color(70,70,70));
        playServer.setForeground(new Color(224,224,224));
        playServer.addActionListener(this);
        this.add(playServer);

        warning = new JLabel("To play as Client, you need to provide Server IP Address");
        warning.setFont(new Font("Comic Sans", Font.BOLD,15));
        warning.setBounds(0,475,530,45);
        warning.setHorizontalAlignment(JLabel.CENTER);
        warning.setForeground(new Color(70,70,70));
        this.add(warning);

        ip = new JTextField("IP Address (Ex: 10.3.48.95)");
        ip.setBounds(115,590,300,50);
        ip.setFont(new Font("Comic Sans", Font.BOLD,15));
        ip.setForeground(new Color(70,70,70));
        ip.setHorizontalAlignment(JTextField.CENTER);
        this.add(ip);

        ip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ip.setText("");
            }
        });

        playClient = new JButton("Play on LAN as Client");
        playClient.setBounds(115,530,300,50);
        playClient.setFont(new Font("Comic Sans", Font.BOLD,22));
        playClient.setFocusable(false);
        playClient.setBackground(new Color(70,70,70));
        playClient.setForeground(new Color(224,224,224));
        playClient.addActionListener(this);
        this.add(playClient);

        fault = new JLabel("Trying to connect...");
        fault.setBounds(0,635,530,45);
        fault.setFont(new Font("Comic Sans", Font.BOLD,15));
        fault.setHorizontalAlignment(JLabel.CENTER);
        fault.setForeground(new Color(70,70,70));
        fault.setVisible(false);
        this.add(fault);

        exit = new JButton("EXIT");
        exit.setBounds(115,700,300,50);
        exit.setFont(new Font("Comic Sans", Font.BOLD,22));
        exit.setFocusable(false);
        exit.setBackground(new Color(70,70,70));
        exit.setForeground(new Color(224,224,224));
        exit.addActionListener(this);
        this.add(exit);



        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==playOffline){
            this.dispose();
            choice = 1;
        }else if(e.getSource()==playServer){
            this.dispose();
            choice = 2;
        }else if(e.getSource()==playClient){
            fault.setVisible(true);
            choice = 3;
        }else if(e.getSource()==exit){
            choice = 4;
        }else if(e.getSource()==timer){
            if(colors[0]>70){
                colors[0]--;
                welcome.setForeground(new Color(colors[0],colors[0],colors[0]));
            }
            if(colors[0]<150){
                if(colors[1]>70){
                    colors[1]--;
                    credit.setForeground(new Color(colors[1],colors[1],colors[1]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new MainMenu();
    }
}
