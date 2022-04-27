package gui;
import board.Board;
import game.Engine;
import game.Human;
import game.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing .*;


public class Interface extends JFrame implements ActionListener {

    public Chessboard chessBoard;
    public History history;

    public Player player1;
    public Player player2;


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
    public JButton gameMode;
    public JButton blackOrWhite;
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
    public JButton changeLanguage;
    public JLabel currentLanguage;
    public String[] languageList = {"English", "Français", "日本語"};
    public int languageChoice = 0;

    public JPanel taskBar1;
    public JPanel taskBar2;
    public JLabel name1;
    public JLabel name2;
    public JLabel avatar1;
    public JLabel avatar2;


    JButton draw;
    JButton resign;
    ImageIcon flag;
    ImageIcon drawIcon;




    public static void main(String[] args) throws IOException{
        Board board = new Board(Board.startFEN);
        new Interface(board);
    }

    public Interface(Board board) throws IOException {

        chessBoard = new Chessboard(board,2);
        history = new History();

        timerUpdate = new Timer(500,this);

        player1 = new Human();
        player2 = new Engine(3);



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
        ChoixPage1.setFont(new Font("Comics Sans",Font.BOLD,20));
        ChoixPage1.setFocusable(false);
        ChoixPage1.addActionListener(this);

        ChoixPage2 = new JButton("PARTY");
        ChoixPage2.setBounds(170, 0, 170, 55);
        ChoixPage2.setBackground(new Color(34, 32, 32));
        ChoixPage2.setForeground(new Color(232, 235, 211));
        ChoixPage2.setFont(new Font("Comics Sans",Font.BOLD,20));
        ChoixPage2.setFocusable(false);
        ChoixPage2.addActionListener(this);

        ChoixPage3 = new JButton("CUSTOM");
        ChoixPage3.setBounds(340, 0, 170, 55);
        ChoixPage3.setBackground(new Color(34, 32, 32));
        ChoixPage3.setForeground(new Color(232, 235, 211));
        ChoixPage3.setFont(new Font("Comics Sans",Font.BOLD,20));
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
        ElementsTempPartie = new Object[] {"1 min", "3 min", "5 min", "10 min"};
        TempPartie = new JComboBox<>(ElementsTempPartie);
        TempPartie.setBounds(70, 222, 370, 50);
        TempPartie.setFont(new Font("Comics Sans",Font.BOLD,18));
        TempPartie.setFocusable(false);
        Page1.add(TempPartie);
        TempPartie.addActionListener(this);

        // Game Mode
        blackOrWhite = new JButton("White");
        blackOrWhite.setBackground(new Color(224, 224, 224));
        blackOrWhite.setForeground(new Color(71, 71, 71));
        blackOrWhite.setBounds(340,155,100,50);
        blackOrWhite.setFont(new Font("Comics Sans",Font.BOLD,18));
        blackOrWhite.setFocusable(false);
        Page1.add(blackOrWhite);
        blackOrWhite.addActionListener(this);

        gameMode = new JButton("Player vs Computer");
        gameMode.setForeground(new Color(224, 224, 224));
        gameMode.setBackground(new Color(71, 71, 71));
        gameMode.setBounds(70,155,260,50);
        gameMode.setFont(new Font("Comics Sans",Font.BOLD,18));
        gameMode.setFocusable(false);
        Page1.add(gameMode);
        gameMode.addActionListener(this);



        //Bouton "Jouer"
        BJouer = new JButton("PLAY");
        BJouer.setBounds(70, 286, 370, 50);
        BJouer.setFont(new Font("Comic Sans", Font.BOLD, 20));
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

        Page1.setVisible(false);


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

        history.setBounds(55,55,350,400);
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
        //Page2.add(draw);
        //Page2.add(resign);
        Page2.add(history);
        Page2.repaint();
        Page2.setVisible(false);

        //PJoueurs, joueur enregistré? --- actionPerformed récupère nom puis recherche dans une liste qu'on pourrait créer, classement

        menu1 = new CustomizeMenu(1);
        menu1.setBounds(10,10,240,350);

        menu2 = new CustomizeMenu(2);
        menu2.setBounds(260,10,240,350);

        taskBar1 = new JPanel();
        taskBar1.setBounds(50,600,250,40);
        taskBar1.setBackground(Color.GRAY);

        avatar1 = new JLabel();
        avatar1.setBounds(0,0,40,40);
        Image avatarImage1 = menu1.avatarIcon.getImage();
        avatarImage1 = avatarImage1.getScaledInstance(32,32,Image.SCALE_AREA_AVERAGING);
        avatar1.setIcon(new ImageIcon(avatarImage1));


        name1 = new JLabel(menu1.name.getText());
        name1.setBounds(120,0,80,40);
        name1.setForeground(Color.WHITE);
        name1.setFont(new Font("Comic Sans", Font.BOLD, 15));


        taskBar1.add(avatar1);
        taskBar1.add(name1);


        taskBar2 = new JPanel();
        taskBar2.setBounds(50,20,250,40);
        taskBar2.setBackground(Color.GRAY);

        avatar2 = new JLabel();
        avatar2.setBounds(0,0,40,40);
        Image avatarImage2 = menu2.avatarIcon.getImage();
        avatarImage2 = avatarImage2.getScaledInstance(32,32,Image.SCALE_AREA_AVERAGING);
        avatar2.setIcon(new ImageIcon(avatarImage2));


        name2 = new JLabel(menu2.name.getText());
        name2.setBounds(120,0,80,40);
        name2.setForeground(Color.WHITE);
        name2.setFont(new Font("Comic Sans", Font.BOLD, 15));

        taskBar2.add(avatar2);
        taskBar2.add(name2);




        changeTheme = new JButton("Change Theme");
        changeTheme.setBounds(55,380,400,50);
        changeTheme.setBackground(new Color(102, 182, 220));
        changeTheme.setFont(new Font("Comic Sans", Font.BOLD, 28));
        changeTheme.setFocusable(false);
        changeTheme.addActionListener(this);

        changeLanguage = new JButton("Change Language");
        changeLanguage.setBounds(55,450,300,50);
        changeLanguage.setBackground(new Color(102, 182, 220));
        changeLanguage.setFont(new Font("Comic Sans", Font.BOLD, 22));
        changeLanguage.setFocusable(false);
        changeLanguage.addActionListener(this);

        currentLanguage = new JLabel(languageList[languageChoice]);
        currentLanguage.setBounds(365,450,90,50);
        currentLanguage.setBackground(new Color(76, 76, 76));
        currentLanguage.setForeground(new Color(217, 215, 215));
        currentLanguage.setHorizontalAlignment(JLabel.CENTER);
        currentLanguage.setOpaque(true);
        currentLanguage.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        currentLanguage.setFont(new Font("Comic Sans", Font.BOLD, 18));


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
        Page3.add(changeLanguage);
        Page3.add(currentLanguage);
        Page3.setVisible(true);

        ChoixPage1.setBackground(new Color(34, 32, 32));
        ChoixPage1.setForeground(new Color(233, 233, 233));
        ChoixPage2.setBackground(new Color(34, 32, 32));
        ChoixPage2.setForeground(new Color(233, 233, 233));
        ChoixPage3.setBackground(new Color(233, 233, 233));
        ChoixPage3.setForeground(new Color(34, 32, 32));


        this.add(chessBoard);
        //this.add(startingTextField);
        //this.add(destinationTextField);
        //this.add(button);
        this.add(AfftimerB);
        this.add(AfftimerW);
        this.add(taskBar1);
        this.add(taskBar2);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Page 1 Actions
        if(!this.chessBoard.isInGame){
            if(e.getSource() == gameMode){
                if(gameMode.getText().equals("Player vs Computer")){
                    gameMode.setText("Player vs Player");
                    blackOrWhite.setVisible(false);
                }else if(gameMode.getText().equals("Joueur vs Ordinateur")){
                    gameMode.setText("Joueur vs Joueur");
                    blackOrWhite.setVisible(false);
                }else if(gameMode.getText().equals("プレーヤーvsエンジン")){
                    gameMode.setText("プレイヤーvsプレイヤー");
                    blackOrWhite.setVisible(false);
                }else if(gameMode.getText().equals("Joueur vs Joueur")){
                    gameMode.setText("Joueur vs Ordinateur");
                    blackOrWhite.setVisible(true);
                }else if(gameMode.getText().equals("プレイヤーvsプレイヤー")){
                    gameMode.setText("プレーヤーvsエンジン");
                    blackOrWhite.setVisible(true);
                }else{
                    gameMode.setText("Player vs Computer");
                    blackOrWhite.setVisible(true);
                }
            }else if(e.getSource() == blackOrWhite){
                if(blackOrWhite.getText().equals("Black")){
                    blackOrWhite.setText("White");
                    blackOrWhite.setForeground(new Color(71, 71, 71));
                    blackOrWhite.setBackground(new Color(224,224,224));
                }else if(blackOrWhite.getText().equals("Noir")){
                    blackOrWhite.setText("Blanc");
                    blackOrWhite.setForeground(new Color(71, 71, 71));
                    blackOrWhite.setBackground(new Color(224,224,224));
                }else if(blackOrWhite.getText().equals("黒")){
                    blackOrWhite.setText("白");
                    blackOrWhite.setForeground(new Color(71, 71, 71));
                    blackOrWhite.setBackground(new Color(224,224,224));
                }else if(blackOrWhite.getText().equals("Blanc")){
                    blackOrWhite.setText("Noir");
                    blackOrWhite.setBackground(new Color(71, 71, 71));
                    blackOrWhite.setForeground(new Color(224,224,224));
                }else if(blackOrWhite.getText().equals("白")){
                    blackOrWhite.setText("黒");
                    blackOrWhite.setBackground(new Color(71, 71, 71));
                    blackOrWhite.setForeground(new Color(224,224,224));
                }else{
                    blackOrWhite.setText("Black");
                    blackOrWhite.setBackground(new Color(71, 71, 71));
                    blackOrWhite.setForeground(new Color(224,224,224));
                }
            }
        }

        // Page 3 Actions
        if(e.getSource() == changeTheme){
            if(chessBoard.theme == 2){
                chessBoard.theme = 0;
            }else{
                chessBoard.theme ++;
            }
            chessBoard.repaint();

        }else if(e.getSource() == changeLanguage){
            if(languageChoice == 2){
                languageChoice = 0;
            }else{
                languageChoice ++;
            }
            currentLanguage.setText(languageList[languageChoice]);

            if(languageChoice == 0){
                if(gameMode.getText().equals("プレイヤーvsプレイヤー")){
                    gameMode.setText("Player vs Player");
                }else{
                    gameMode.setText("Player vs Computer");
                }
                if(blackOrWhite.getText().equals("白")){
                    blackOrWhite.setText("White");
                }else{
                    blackOrWhite.setText("Black");
                }
                changeTheme.setText("Change Theme");
                changeLanguage.setText("Change Language");
                ChoixPage1.setText("NEW");
                ChoixPage2.setText("PARTY");
                ChoixPage3.setText("CUSTOM");
                BJouer.setText("PLAY");
                ElementsTempPartie = new Object[] {"1 min", "3 min", "5 min", "10 min"};
                DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>(ElementsTempPartie);
                TempPartie.setModel(model);
                menu1.label.setText("Player " + menu1.player);
                menu2.label.setText("Player " + menu2.player);
            }else if(languageChoice == 1){
                if(gameMode.getText().equals("Player vs Player")){
                    gameMode.setText("Joueur vs Joueur");
                }else{
                    gameMode.setText("Joueur vs Ordinateur");
                }
                if(blackOrWhite.getText().equals("White")){
                    blackOrWhite.setText("Blanc");
                }else{
                    blackOrWhite.setText("Noir");
                }
                changeTheme.setText("Change de Theme");
                changeLanguage.setText("Change de Langue");
                ChoixPage1.setText("NOUVEAU");
                ChoixPage2.setText("PARTIE");
                ChoixPage3.setText("CUSTOM");
                BJouer.setText("JOUER");
                ElementsTempPartie = new Object[] {"1 min", "3 min", "5 min", "10 min"};
                DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>(ElementsTempPartie);
                TempPartie.setModel(model);
                menu1.label.setText("Joueur " + menu1.player);
                menu2.label.setText("Joueur " + menu2.player);
            }else if(languageChoice == 2){
                if(gameMode.getText().equals("Joueur vs Joueur")){
                    gameMode.setText("プレイヤーvsプレイヤー");
                }else{
                    gameMode.setText("プレーヤーvsエンジン");
                }
                if(blackOrWhite.getText().equals("Blanc")){
                    blackOrWhite.setText("白");
                }else{
                    blackOrWhite.setText("黒");
                }
                changeTheme.setText("テーマを変更する");
                changeLanguage.setText("言語を変更する");
                ChoixPage1.setText("新着");
                ChoixPage2.setText("パーティ");
                ChoixPage3.setText("カスタマ");
                BJouer.setText("遊ぶ");
                ElementsTempPartie = new Object[] {"1分", "3分", "5分", "10分"};
                DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>(ElementsTempPartie);
                TempPartie.setModel(model);
                menu1.label.setText("プレーヤー " + menu1.player);
                menu2.label.setText("プレーヤー " + menu2.player);
            }
        }

        if(e.getSource() == button){
            startingInput = startingTextField.getText();
            destinationInput = destinationTextField.getText();
            buttonActivated = true;

        }else if(e.getSource() == BJouer && !this.chessBoard.isInGame){

            if(     gameMode.getText().equals("Player vs Player") ||
                    gameMode.getText().equals("Joueur vs Joueur") ||
                    gameMode.getText().equals("プレイヤーvsプレイヤー")
            ){
                player1 = new Human();
                player2 = new Human();
            }else{
                if(     blackOrWhite.getText().equals("White") ||
                        blackOrWhite.getText().equals("Blanc") ||
                        blackOrWhite.getText().equals("白") ){
                    player1 = new Human();
                    player2 = new Engine(3);
                }else{
                    player1 = new Engine(3);
                    player2 = new Human();
                }
            }
            Page1.setVisible(false);
            Page2.setVisible(true);
            Page3.setVisible(false);
            ChoixPage1.setBackground(new Color(34, 32, 32));
            ChoixPage1.setForeground(new Color(233, 233, 233));
            ChoixPage2.setBackground(new Color(233, 233, 233));
            ChoixPage2.setForeground(new Color(34, 32, 32));
            ChoixPage3.setBackground(new Color(34, 32, 32));
            ChoixPage3.setForeground(new Color(233, 233, 233));

            this.chessBoard.isInGame = true;
            this.menu1.isInGame = true;
            this.menu2.isInGame = true;
            this.menu1.checkForAvatar.stop();
            this.menu2.checkForAvatar.stop();
            this.timerUpdate.start();

            if(timer1==null){
                timer1 = new ChessTime(1, 0, -1);
            }
            if(timer2==null){
                timer2 = new ChessTime(1,0,1);
            }

            timer2.start();
            timer1.start();
            timer1.freezetime();

            TempPartie.setEditable(false);

            Image avatarImage1 = menu1.avatarIcon.getImage();
            avatarImage1 = avatarImage1.getScaledInstance(32,32,Image.SCALE_AREA_AVERAGING);
            avatar1.setIcon(new ImageIcon(avatarImage1));

            name1.setText(menu1.name.getText());

            Image avatarImage2 = menu2.avatarIcon.getImage();
            avatarImage2 = avatarImage2.getScaledInstance(32,32,Image.SCALE_AREA_AVERAGING);
            avatar2.setIcon(new ImageIcon(avatarImage2));

            name2.setText(menu2.name.getText());

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
            ChoixPage1.setBackground(new Color(233,233,233));
            ChoixPage1.setForeground(new Color(34, 32, 32));
            ChoixPage2.setBackground(new Color(34, 32, 32));
            ChoixPage2.setForeground(new Color(233, 233, 233));
            ChoixPage3.setBackground(new Color(34, 32, 32));
            ChoixPage3.setForeground(new Color(233, 233, 233));
        } else if(e.getSource() == ChoixPage2) {
            Page1.setVisible(false);
            Page2.setVisible(true);
            Page3.setVisible(false);
            ChoixPage1.setBackground(new Color(34, 32, 32));
            ChoixPage1.setForeground(new Color(233, 233, 233));
            ChoixPage2.setBackground(new Color(233, 233, 233));
            ChoixPage2.setForeground(new Color(34, 32, 32));
            ChoixPage3.setBackground(new Color(34, 32, 32));
            ChoixPage3.setForeground(new Color(233, 233, 233));
        } else if(e.getSource() == ChoixPage3) {
            Page1.setVisible(false);
            Page2.setVisible(false);
            Page3.setVisible(true);
            ChoixPage1.setBackground(new Color(34, 32, 32));
            ChoixPage1.setForeground(new Color(233, 233, 233));
            ChoixPage2.setBackground(new Color(34, 32, 32));
            ChoixPage2.setForeground(new Color(233, 233, 233));
            ChoixPage3.setBackground(new Color(233, 233, 233));
            ChoixPage3.setForeground(new Color(34, 32, 32));
        }
        if(e.getSource() == getTempPartie() && !this.chessBoard.isInGame) {
            String temps = (String) getTempPartie().getSelectedItem();
            if (temps == "1 min" || temps == "1分") {
                timer1 = new ChessTime(1, 0,-1);
                timer2 = new ChessTime(1, 0,1);
            } else if (temps == "3 min" || temps == "3分") {
                timer1 = new ChessTime(3, 0,-1);
                timer2 = new ChessTime(3, 0,1);
            } else if (temps == "5 min" || temps == "5分") {
                timer1 = new ChessTime(5, 0,-1);
                timer2 = new ChessTime(5, 0,1);
            } else if (temps == "10 min" || temps == "10分") {
                timer1 = new ChessTime(10, 0,-1);
                timer2 = new ChessTime(10, 0,1);
            }
        }

    }

    public void updateInterface(Board board){
        System.out.println("Updating stuff");
        this.chessBoard.updateChessBoard(board);
        this.startingInput = null;
        this.destinationInput = null;
        this.startingTextField.setText("");
        this.destinationTextField.setText("");
        this.buttonActivated = false;
        this.history.repaint();
        if(board.colorActive==1){
            this.timer2.restart();
            this.timer1.freezetime();
        }else{
            this.timer1.restart();
            this.timer2.freezetime();
        }
    }

    public JComboBox getTempPartie() {
        return TempPartie;
    }

    public void setTempPartie(JComboBox tempPartie) {
        TempPartie = tempPartie;
    }
}
