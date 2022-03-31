package gui;
import board.Board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JComboBox TempPartie;
    public Object[] ElementsTempPartie;
    public JTextField Recherche;
    public JTextField RechercheJ;
    public JPanel Page1;
    public JPanel Page2;
    public JPanel Page3;
    public JPanel AfftimerB;
    public JPanel AfftimerW;
    public ChessTime timer1;
    public ChessTime timer2;
    public JLabel TimerB;
    public JLabel TimerW;
    public Timer timerUpdate;

    public CustomizeMenu menu1;
    public CustomizeMenu menu2;
    public JButton changeTheme;

    JButton draw;
    JButton resign;
    ImageIcon flag;
    ImageIcon drawIcon;


    public static void main(String[] args) throws IOException {
        Board board = new Board(Board.startFEN);
        new Interface(board);
    }

    public Interface(Board board) throws IOException {

        chessBoard = new Chessboard(board,2);
        history = new History();

        timerUpdate = new Timer(10,this);

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

        AfftimerB = new JPanel();
        AfftimerB.setBounds(448, 20, 112, 40);
        AfftimerB.setBackground(Color.GRAY);

        TimerB = new JLabel();
        TimerB.setBounds(10, 0, 110, 40);
        TimerB.setFont(new Font("Comic Sans", Font.BOLD, 22));
        AfftimerB.add(TimerB);

        AfftimerW = new JPanel();
        AfftimerW.setBounds(448, 600, 112, 40);
        AfftimerW.setBackground(Color.GRAY);

        TimerW = new JLabel();
        TimerW.setBounds(10, 0, 110, 30);
        TimerW.setFont(new Font("Comic Sans", Font.BOLD, 22));
        AfftimerW.add(TimerW);

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

        ChoixPage3 = new JButton("CUSTOM");
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
        TempPartie = new JComboBox<>(ElementsTempPartie);
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

        menu1 = new CustomizeMenu(1);
        menu1.setBounds(10,10,240,350);

        menu2 = new CustomizeMenu(2);
        menu2.setBounds(260,10,240,350);

        changeTheme = new JButton("Change Theme");
        changeTheme.setBounds(55,380,400,50);
        changeTheme.setBackground(new Color(102, 182, 220));
        changeTheme.setFont(new Font("Comic Sans", Font.BOLD, 28));
        changeTheme.setFocusable(false);
        changeTheme.addActionListener(this);

        Page3 = new JPanel();
        Parameters.add(Page3);
        Page3.setLayout(null);
        Page3.setBounds(0, 55, 510, 570);
        //RechercheJ = new JTextField("Nom d'utilisateur...");
        //RechercheJ.setBounds(0, 55, 510, 20 );
        //Page3.setBackground(new Color(34, 32, 32));
        Page3.setBackground(new Color(224, 224, 224));
        Page3.add(menu1);
        Page3.add(menu2);
        Page3.add(changeTheme);
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

        // Page 3 Actions
        if(e.getSource() == changeTheme){
            if(chessBoard.theme == 2){
                chessBoard.theme = 0;
            }else{
                chessBoard.theme ++;
            }
            chessBoard.repaint();
        }

        if(e.getSource() == button){
            startingInput = startingTextField.getText();
            destinationInput = destinationTextField.getText();
            buttonActivated = true;
        }else if(e.getSource() == BJouer && !this.chessBoard.isInGame){
            System.out.println("Fart");
            this.chessBoard.isInGame = true;
            this.menu1.isInGame = true;
            this.menu2.isInGame = true;
            this.menu1.checkForAvatar.stop();
            this.menu2.checkForAvatar.stop();
            this.timerUpdate.start();

            if(timer1==null){
                timer1 = new ChessTime(1, 0);
            }
            if(timer2==null){
                timer2 = new ChessTime(1,0);
            }

            timer1.start();
            timer2.start();

            TempPartie.setEditable(false);
        }else if(e.getSource() == timerUpdate){
            if ((timer1.seconde > 10) || (timer2.seconde > 10)) {
                TimerB.setText(timer1.minute + ":" + timer1.seconde);
                TimerW.setText(timer2.minute + ":" + timer2.seconde);
            } else if ((timer1.seconde < 10) || (timer2.seconde < 10)) {
                TimerB.setText(timer1.minute + ":0" + timer1.seconde);
                TimerW.setText(timer2.minute + ":0" + timer2.seconde);
            }
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
        if(e.getSource() == getTempPartie() && !this.chessBoard.isInGame) {
            String temps = (String) getTempPartie().getSelectedItem();
            if (temps == "1 min") {
                timer1 = new ChessTime(1, 0);
                timer2 = new ChessTime(1, 0);
                //timer1.start();
                //timer2.start();
                //TimerB.setText(timer1.minute + ":" + timer1.seconde);
                //TimerW.setText(timer2.minute + ":" + timer2.seconde);
            }
            if (temps == "3 min") {
                timer1 = new ChessTime(3, 0);
                timer2 = new ChessTime(3, 0);
            }
            if (temps == "5 min") {
                timer1 = new ChessTime(5, 0);
                timer2 = new ChessTime(5, 0);
            }
            if (temps == "10 min") {
                timer1 = new ChessTime(10, 0);
                timer2 = new ChessTime(10, 0);
            }
                    //chercher comment arrêter le temps
                    /*break;
                case "1|1":
                    break;
                case "2|1":
                    break;
                case "3 min":
                    //create 2 timers
                    timer1 = new ChessTime(3, 0);
                    timer2 = new ChessTime(3, 0);
                    timer1.start();
                    timer2.start();
                    TimerB.setText(timer1.minute + ":" + timer1.seconde);
                    TimerW.setText(timer2.minute + ":" + timer2.seconde);
                    //chercher comment arrêter le temps
                    break;
                case "3|2":
                    break;
                case "5 min":
                    //create 2 timers
                    timer1 = new ChessTime(5, 0);
                    timer2 = new ChessTime(5, 0);
                    timer1.start();
                    timer2.start();
                    TimerB.setText(timer1.minute + ":" + timer1.seconde);
                    TimerW.setText(timer2.minute + ":" + timer2.seconde);
                    //chercher comment arrêter le temps
                    break;
                case "5|5":
                    break;
                case "10 min":
                    //create 2 timers
                    timer1 = new ChessTime(10, 0);
                    timer2 = new ChessTime(10, 0);
                    timer1.start();
                    timer2.start();
                    TimerB.setText(timer1.minute + ":" + timer1.seconde);
                    TimerW.setText(timer2.minute + ":" + timer2.seconde);
                    //chercher comment arrêter le temps
                    break;
            }*/
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

    public JComboBox getTempPartie() {
        return TempPartie;
    }

    public void setTempPartie(JComboBox tempPartie) {
        TempPartie = tempPartie;
    }
}





