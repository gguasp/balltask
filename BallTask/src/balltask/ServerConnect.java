/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam
 */
public class ServerConnect implements Runnable {

    private static final String MHOST = "228.5.6.7";
    private static final int PORT = 6789;

    @Override
    public void run() {
        System.out.print("Ticking");

        try {
            InetAddress address = InetAddress.getByName(MHOST);
            MulticastSocket msock = new MulticastSocket(PORT);

            msock.joinGroup(address);

            DatagramPacket packet;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.print(".");
                String str = (new Date()).toString();
                packet = new DatagramPacket(str.getBytes(), str.length(), address, PORT);
                msock.send(packet);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
