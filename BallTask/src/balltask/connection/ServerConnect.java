/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask.connection;

import balltask.BallTask;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam
 */
public class ServerConnect implements Runnable {

    private static final int PORT = 6789;

    @Override
    public void run() {
        
        ServerSocket server = null;

        try {
            server = new ServerSocket(PORT);
            server.setReuseAddress(true);
            
            while (true) {
                //Crear channel en el BallTask DONE
                //Solo admitir una conexión. 
                //Identificar al cliente
                //Solo setear socket cuando no funcione o no haya
                Socket clientSock = server.accept();
                String cliAddr = clientSock.getInetAddress().getHostAddress();
                System.out.println("New client connected"+ clientSock.getInetAddress().getHostAddress());
                
                BallTask.handler.setClientSocket(clientSock);
                BallTask.handler.setAddress(cliAddr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
