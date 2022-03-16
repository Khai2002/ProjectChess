package gui;
import board.Board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing .*;


public class Interface extends JFrame implements ActionListener {

    public Chessboard chessBoard;
    public Chessboard newChessBoard;

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

    public int[] startingInputReal;
    public int[] destinationInputReal;

    public static void main(String[] args) throws IOException {
        Board board = new Board(Board.startFEN);
        new Interface(board);
    }

    public Interface(Board board) throws IOException {
        chessBoard = new Chessboard(board,0);

        //ChooseName chooseName1 = new ChooseName();
        //name1 = chooseName1.text.getText();

        //ChooseAvatar chooseAvatar1 = new ChooseAvatar(name1);
        //avatar1 = chooseAvatar1.avatarChosen;

        this.setTitle("Chess");
        this.setSize(1160,660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setBackground(new Color(58,57,53));
        this.getContentPane().setBackground(new Color(58,57,53));
        this.setLayout(null);


        //Logo
        ImageIcon icon = new ImageIcon("logo.jpg");
        Image image = icon.getImage();
        Image imageScaled = image.getScaledInstance(5,5,5);



        chessBoard.setBounds(50,55,512,512);

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

        this.add(chessBoard);
        this.add(startingTextField);
        this.add(destinationTextField);
        this.add(button);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            startingInput = startingTextField.getText();
            destinationInput = destinationTextField.getText();
            buttonActivated = true;
        }
    }

    public void updateInterface(Board board){
        this.chessBoard.updateChessBoard(board);
        this.startingInput = null;
        this.destinationInput = null;
        this.startingTextField.setText("");
        this.destinationTextField.setText("");
        this.buttonActivated = false;

    }
}





