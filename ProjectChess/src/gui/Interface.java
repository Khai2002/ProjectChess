package gui;
import board.Board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Time;
import javax.swing .*;


public class Interface extends JFrame implements ActionListener {

    public Chessboard chessBoard;
    public History history;

    ImageIcon avatar1;
    ImageIcon avatar2;
    String name1;
    String name2;

    JButton button;
    JTextField startingTextField;
    JTextField destinationTextField;

    public boolean buttonActivated;

    public String startingInput;
    public String destinationInput;

    public JPanel Parameters;
    public JButton ChoixPage1;
    public JButton ChoixPage2;
    public JButton ChoixPage3;
    public JButton BJouer;
    public JButton StyleChessBoard;
    public JComboBox TempPartie;
    public Object[] ElementsTempPartie;
    public JTextField Recherche;
    public JTextField RechercheJ;
    public JPanel Page1;
    public JPanel Page2;
    public JPanel Page3;
    public JLabel AfftimerB;
    public JLabel AfftimerW;
    public ChessTime timer1;
    public ChessTime timer2;

    JButton draw;
    JButton resign;
    ImageIcon flag;
    ImageIcon drawIcon;

    JComponent thing;

    public static void main(String[] args) throws IOException {
        Board board = new Board(Board.startFEN);
        new Interface(board);
    }

    public Interface(Board board) throws IOException {

        chessBoard = new Chessboard(board,0);
        history = new History();

        //ChooseName chooseName1 = new ChooseName();
        //name1 = chooseName1.text.getText();

        //ChooseAvatar chooseAvatar1 = new ChooseAvatar(name1);
        //avatar1 = chooseAvatar1.avatarChosen;

        this.setTitle("Chess");
        this.setSize(1160,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setBackground(new Color(58,57,53));
        this.getContentPane().setBackground(new Color(58,57,53));
        this.setLayout(null);


        //Logo
        ImageIcon icon = new ImageIcon("logo.jpg");
        Image image = icon.getImage();
        Image imageScaled = image.getScaledInstance(5,5,5);



        chessBoard.setBounds(50,75,512,512);


        button = new JButton();
        button.setText("Submit");
        button.setBounds(800,0,100,100);
        button.addActionListener(this);





        startingTextField = new JTextField();
        startingTextField.setBounds(800,150,100,50);
        startingTextField.setFont(new Font("Comic Sans",Font.BOLD,27));


        destinationTextField = new JTextField();
        destinationTextField.setBounds(800,300,100,50);
        destinationTextField.setFont(new Font("Comic Sans",Font.BOLD,27));

        AfftimerB = new JLabel("");
        AfftimerB.setBounds(800, 10, 112, 40);
        AfftimerB.setBackground(new Color(30, 23, 23));
        AfftimerB.setForeground(Color.GRAY);

        AfftimerW = new JLabel("");
        AfftimerW.setBounds(800, 572, 112, 40);
        AfftimerW.setBackground(Color.GRAY);
        AfftimerW.setForeground(new Color(30, 23, 23));

        Parameters = new JPanel() ;
        Parameters.setLayout(null);
        Parameters.setBounds(600, 20, 510, 625);
        Parameters.setBackground(new Color(35,45,53)); // Main Color for this Panel
        this.add(Parameters);

        //PNouvellePartie
        //PParties
        //PJoueurs

        //En tête
        ChoixPage1 = new JButton("NEW"); //à implémenter dans actionPerformed()
        ChoixPage1.setBounds(0, 0, 170, 55);
        ChoixPage1.setBackground(new Color(34, 32, 32));
        ChoixPage1.setForeground(new Color(232, 235, 211));
        ChoixPage1.setFont(new Font("Comics Sans",Font.BOLD,18));
        ChoixPage1.setFocusable(false);
        ChoixPage1.addActionListener(this);


        ChoixPage2 = new JButton("PARTIES");
        ChoixPage2.setBounds(170, 0, 170, 55);
        ChoixPage2.setBackground(new Color(34, 32, 32));
        ChoixPage2.setForeground(new Color(232, 235, 211));
        ChoixPage2.setFont(new Font("Comics Sans",Font.BOLD,18));
        ChoixPage2.setFocusable(false);
        ChoixPage2.addActionListener(this);

        ChoixPage3 = new JButton("JOUEURS");
        ChoixPage3.setBounds(340, 0, 170, 55);
        ChoixPage3.setBackground(new Color(34, 32, 32));
        ChoixPage3.setForeground(new Color(232, 235, 211));
        ChoixPage3.setFont(new Font("Comics Sans",Font.BOLD,18));
        ChoixPage3.setFocusable(false);
        ChoixPage3.addActionListener(this);

        Parameters.add(ChoixPage1);
        Parameters.add(ChoixPage2);
        Parameters.add(ChoixPage3);

        //PNouvellePartie

        Page1 = new JPanel();
        Parameters.add(Page1);
        Page1.setLayout(null);
        Page1.setBounds(0, 55, 510, 570);
        //Page1.setBackground(new Color(34, 32, 32));
        Page1.setBackground(new Color(224, 224, 224));

        //Création JComboBox
        ElementsTempPartie = new Object[] {"1 min", "1|1", "2|1", "3 min", "3|2", "5 min", "5|5", "10 min"};
        JComboBox TempPartie = new JComboBox(ElementsTempPartie);
        TempPartie.setBounds(70, 192, 370, 50);
        TempPartie.setFont(new Font("Comics Sans",Font.BOLD,18));
        TempPartie.setFocusable(false);
        Page1.add(TempPartie);
        TempPartie.addActionListener(this);

        //Bouton "Jouer"
        BJouer = new JButton("JOUER");
        BJouer.setBounds(70, 256, 370, 50);
        BJouer.setFont(new Font("Comic Sans", Font.BOLD, 16));
        BJouer.setBackground(new Color(71, 71, 71));
        BJouer.setForeground(new Color(224, 224, 224));
        BJouer.setFocusable(false);
        BJouer.addActionListener(this); //à implémenter dans actionPerformed()
        Page1.add(BJouer);
        //Chessboard style
        //StyleChessBoard = new JButton("Personnaliser");
        //StyleChessBoard.setBounds(70, 140, 370, 50);
        //StyleChessBoard.addActionListener(this); //à implémenter dans actionPerformed()
        //Page1.add(StyleChessBoard);

        Page1.setVisible(true);


        //PParties, à modifier la colonne?




        drawIcon = new ImageIcon("res/drawIcon.webp");
        Image image1 = drawIcon.getImage();
        Image imageScaled1 = image1.getScaledInstance(10,10,0);
        drawIcon = new ImageIcon(imageScaled1);

        draw = new JButton();
        draw.setIcon(drawIcon);
        draw.setBounds(0,512,100,100);

        //draw.setBackground(Color.BLACK);
        draw.addActionListener(this);
        draw.setFocusable(false);


        flag = new ImageIcon("res/resign flag.png");

        resign = new JButton();
        resign.setIcon(flag);
        resign.setBounds(100,512,100,100);

        //resign.setBackground(Color.black);
        resign.addActionListener(this);
        resign.setFocusable(false);


        Color page2Color = new Color(224, 224, 224);

        history.setBounds(55,55,350,200);
        history.setBackground(page2Color);

        Page2 = new JPanel();
        Parameters.add(Page2);
        Page2.setLayout(null);
        Page2.setBounds(0, 55, 510, 570);
        Page2.setBackground(page2Color);
        //Page2.setBackground(new Color(224, 224, 224));
        //Recherche match en cours
        Recherche = new JTextField("Rechercher");
        //Page2.add(Recherche);
        Page2.add(draw);
        Page2.add(resign);
        Page2.add(history);
        Page2.repaint();
        Page2.setVisible(false);

        //PJoueurs, joueur enregistré? --- actionPerformed récupère nom puis recherche dans une liste qu'on pourrait créer, classement

        Page3 = new JPanel();
        Parameters.add(Page3);
        //Page3.setLayout(null);
        Page3.setBounds(0, 55, 510, 570);
        RechercheJ = new JTextField("Nom d'utilisateur...");
        RechercheJ.setBounds(0, 55, 510, 20 );
        //Page3.setBackground(new Color(34, 32, 32));
        Page3.setBackground(new Color(224, 224, 224));
        Page3.setVisible(false);


        this.add(chessBoard);
        //this.add(startingTextField);
        //this.add(destinationTextField);
        //this.add(button);
        this.add(AfftimerB);
        this.add(AfftimerW);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            startingInput = startingTextField.getText();
            destinationInput = destinationTextField.getText();
            buttonActivated = true;
        }else if(e.getSource() == BJouer){
            System.out.println("Fart");
            this.chessBoard.isInGame = true;
        }

        if(e.getSource() == ChoixPage1) {
            Page1.setVisible(true);
            Page2.setVisible(false);
            Page3.setVisible(false);
        } else if(e.getSource() == ChoixPage2) {
            Page1.setVisible(false);
            Page2.setVisible(true);
            Page3.setVisible(false);
        } else if(e.getSource() == ChoixPage3) {
            Page1.setVisible(false);
            Page2.setVisible(false);
            Page3.setVisible(true);
        }
        if(e.getSource() == TempPartie) {
            String temps = (String) TempPartie.getSelectedItem();
            switch (temps) {
                case "1 min":
                    timer1 = new ChessTime(1,0);
                    timer1.start();
                    AfftimerB.setText(timer1.minute + ":" + timer1.seconde);
                    AfftimerW.setText(timer1.minute + ":" + timer1.seconde);
                    break;
                case "1|1":
                    //create 2 timers
                    timer1 = new ChessTime(1, 0);
                    timer2 = new ChessTime(1, 0);
                    timer1.start();
                    timer2.start();
                    AfftimerB.setText(timer1.minute + ":" + timer1.seconde);
                    AfftimerW.setText(timer2.minute + ":" + timer2.seconde);
                    //chercher comment arrêter le temps
                    break;
                case "2|1":
                    break;
                case "3 min":
                    timer1 = new ChessTime(3, 0);
                    timer1.start();
                    AfftimerB.setText(timer1.minute + ":" + timer1.seconde);
                    AfftimerW.setText(timer1.minute + ":" + timer1.seconde);
                    break;
                case "3|2":
                    break;
                case "5 min":
                    timer1 = new ChessTime(5, 0);
                    timer1.start();
                    AfftimerB.setText(timer1.minute + ":" + timer1.seconde);
                    AfftimerW.setText(timer1.minute + ":" + timer1.seconde);
                    break;
                case "5|5":
                    break;
                case "10 min":
                    timer1 = new ChessTime(10, 0);
                    timer1.start();
                    AfftimerB.setText(timer1.minute + ":" + timer1.seconde);
                    AfftimerW.setText(timer1.minute + ":" + timer1.seconde);
                default:
                    timer1 = new ChessTime(10, 0);
                    timer1.start();
                    AfftimerB.setText(timer1.minute + ":" + timer1.seconde);
                    AfftimerW.setText(timer1.minute + ":" + timer1.seconde);
                    break;
            }
        }

    }

    public void updateInterface(Board board){
        this.chessBoard.updateChessBoard(board);
        this.startingInput = null;
        this.destinationInput = null;
        this.startingTextField.setText("");
        this.destinationTextField.setText("");
        this.buttonActivated = false;
        this.history.repaint();

    }
}





