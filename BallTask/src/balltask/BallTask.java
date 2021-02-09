/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask;

import javax.swing.JFrame;

public class BallTask extends JFrame {

    public static Ball[] ballArray = new Ball[10];
    public static Blackhole[] bhArray = new Blackhole[2];
    
    public static int frameWidth = 510;
    public static int frameHeight = 300;
    public static Viewer cv = new Viewer();
    private static Thread viewerThread;
    private static ServerConnect sc = new ServerConnect();
    private static Thread serverThread;
    private static ClientConnect cc = new ClientConnect();
    private static Thread clientThread;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setSize(frameWidth, frameHeight);
        frame.add(cv);
        frame.setVisible(true);
        frame.setResizable(false);

        BallTask.viewerThread = new Thread(BallTask.cv);
        BallTask.viewerThread.start();

        /*
        
        BallTask.serverThread = new Thread(BallTask.sc);
        BallTask.serverThread.start();

        BallTask.clientThread = new Thread(BallTask.cc);
        BallTask.clientThread.start();
*/
    }
    
    public static Ball canTheBallMove(Ball ball){
        

        if (((ball.x + ball.xspeed + ball.width) > BallTask.frameWidth) || ((ball.x + ball.xspeed + ball.width) < 0)) {
                ball.ballBounce("x");
            

            }
            if (((ball.y + ball.yspeed + ball.height) > BallTask.frameHeight) || ((ball.y + ball.yspeed) < 0)) {
                ball.ballBounce("y");

            }
        
        
            for (int i = 0; i < bhArray.length; i++) {
                //Entra en el perimetro de uno de los blackholes
                if (inBlackHole(ball, bhArray[i])) {
                    
                    if(!ball.enBlackhole){
                        if (bhArray[i].free) {
                            ball.enBlackhole = true;
                            ball.currentBlackhole = bhArray[i];
                            bhArray[i].in(ball);
                        }else{
                            ball.enEspera = true;
                            bhArray[i].in(ball);
                            ball.enEspera = false;
                            ball.enBlackhole = true;
                            ball.currentBlackhole = bhArray[i];
                        }
                    }            
                }else{
                    
                    if((ball.enBlackhole)){
                        if(ball.currentBlackhole == bhArray[i]){
                            ball.enBlackhole = false;
                            bhArray[i].out();
                            ball.currentBlackhole = null;
                        }
                    }
                    
                    
                }
    }
            return ball;
    }
    
    public static Boolean inBlackHole(Ball ball, Blackhole bh){
         if ((ball.x + ball.xspeed > bh.x) && (ball.x + ball.xspeed < (bh.x + bh.width))
                        && (ball.y + ball.yspeed > (bh.y)) && (ball.y + ball.yspeed < (bh.y + bh.height))) {
             
             return true;
         }else{
             return false;
         }
        
    }
    
    public static Boolean willBounce(Ball ball){
        
        return true;
    }

}
