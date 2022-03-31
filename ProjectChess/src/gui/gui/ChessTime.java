package gui;

import java.util.Timer;
import java.util.TimerTask;

public class ChessTime {
    public int minute;
    public int seconde;
    public Timer Mytimer;
    public WindowTheEnd FinDeLaPartie;
    private TimerTask task;

    public ChessTime(int m, int s) {
        minute = m;
        seconde = s;
        Mytimer = new Timer();



        this.task = new TimerTask() {
            int counter = seconde;

            public void run() {
                if (counter > 0) {
                    counter--;
                    seconde = counter;
                    System.out.println("" + minute + " " + seconde);
                } else {
                    if (counter == 0 && minute != 0) {
                        minute--;
                        counter = 59;
                        seconde = 59;
                        System.out.println("" +minute +" " + seconde);
                    } else {
                        FinDeLaPartie = new WindowTheEnd();
                        FinDeLaPartie.setVisible(true);
                        Mytimer.cancel();
                    }
                }
            }
        };

    }

    public void start() {
        Mytimer.scheduleAtFixedRate(this.task, 0, 1000);
    }

    public static void main(String[] args) {
        ChessTime time = new ChessTime(0, 10);
        time.start();
    }
}
