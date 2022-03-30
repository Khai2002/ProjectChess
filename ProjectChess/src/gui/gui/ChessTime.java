package gui;

import java.util.Timer;
import java.util.TimerTask;

public class ChessTime {
    public int minute;
    public int seconde;
    public Timer Mytimer;
    public WindowTheEnd FinDeLaPartie;

    public ChessTime(int m, int s) {
        minute = m;
        seconde = s;
        Mytimer = new Timer();

        TimerTask task = new TimerTask() {
            int counter = 60;

            public void run() {
                if (counter > 0) {
                    seconde = counter;
                    counter--;
                } else {
                    if (counter == 0 && (minute != 0)) {
                        minute--;
                        counter = 60;
                        seconde = 60;
                    } else {
                        FinDeLaPartie.setVisible(true);
                    }
                }
            }
        };



    }

    public void start() {
        //Mytimer.scheduleAtFixedRate(taskTimer,0, 1000);
    }



}
