/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author dam
 */
public class ClientConnect implements Runnable {

    private static final String MHOST = "228.5.6.7";
    private static final int PORT = 6788;

    @Override
    public void run() {
        try {

            InetAddress address = InetAddress.getByName(MHOST);
            MulticastSocket msock = new MulticastSocket(PORT);
            msock.joinGroup(address);
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            String dateStr;
            while (true) {
                msock.receive(packet);
                dateStr = new String(packet.getData()).trim();
                System.out.println(packet.getAddress() + " : " + dateStr);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
