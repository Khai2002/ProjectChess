package gui;

import java.util.Timer;
import java.util.TimerTask;


public class ChessTime {
    public int minute;
    public int seconde;
    public int color;
    public Timer Mytimer;
    public WindowTheEnd FinDeLaPartie;
    private TimerTask task;
    boolean paused;

    public ChessTime(int m, int s, int color) {
        minute = m;
        seconde = s;
        this.color = color;
        Mytimer = new Timer();


        this.task = new TimerTask() {
            int theColor = color;
            int counter = seconde;

            public void run() {
                if (paused == true) {

                }
                else {
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
                            if(theColor == 1){
                                FinDeLaPartie = new WindowTheEnd("Time up ! Black Wins");
                            }else{
                                FinDeLaPartie = new WindowTheEnd("Time up ! White Wins");
                            }
                            Mytimer.cancel();
                        }
                    }
                }
            }
        };

    }

    public void start() {
        Mytimer.scheduleAtFixedRate(this.task, 0, 1000);
        this.paused=false;
    }

    public void restart() {
        this.paused=false;
    }


    public void freezetime() {
        this.paused = true;
    }

    public static void main(String[] args) {
        ChessTime time = new ChessTime(0, 10,1);
        time.start();
    }
}