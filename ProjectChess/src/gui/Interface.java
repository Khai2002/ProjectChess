package gui;
import java.awt.*;
import javax.swing .*;


public class Interface extends JFrame {

    Chessboard board;

    ImageIcon avatar1;
    ImageIcon avatar2;
    String name1;
    String name2;

    public static void main(String[] args) {
        new Interface();
    }

    Interface(){
        board = new Chessboard();

        ChooseName chooseName1 = new ChooseName();
        name1 = chooseName1.text.getText();

        ChooseAvatar chooseAvatar1 = new ChooseAvatar(name1);
        avatar1 = chooseAvatar1.avatarChosen;

        JFrame window = new JFrame();
        window.setTitle("Chess");
        window.setSize(1160,660);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setBackground(Color.black);

        //Logo
        ImageIcon icon = new ImageIcon("logo.jpg");
        Image image = icon.getImage();
        Image imageScaled = image.getScaledInstance(5,5,5);


        window.add(board);
        this.pack();
        window.setVisible(true);


    }

}





