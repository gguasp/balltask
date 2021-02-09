/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask;

/**
 *
 * @author dam
 */
public class Statistics implements Runnable {

    public static int onBh;
    public static int onWait;
    public static int totalBalls;

    public Statistics() {
        this.onBh = 0;
        this.onWait = 0;
        this.totalBalls = 0;
    }

    @Override
    public void run() {

        while (true) {
            this.onBh = 0;
            this.onWait = 0;
            this.totalBalls = 0;

            for (int i = 0; i < BallTask.ballArray.length; i++) {
                if (BallTask.ballArray[i].getEnBlackhole() == true) {
                    this.onBh++;

                }
                if (BallTask.ballArray[i].getEnEspera() == true) {
                    this.onWait++;
                }
                this.totalBalls++;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }

}
