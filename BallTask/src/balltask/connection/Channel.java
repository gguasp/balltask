/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author dam
 */
public class Channel implements Runnable {

    private Socket socket;
    private String address;

    public Channel() {
        this.socket = null;
        this.address = null;
    }

    public void run() {
        //el run espera a que alguien hable, es el receptor, espera información.
        //Crear metodos externos para enviar.
        PrintWriter out = null;
        BufferedReader in = null;
        
        while (true) {
            if (this.socket != null) {
            System.out.println("Canal listo");
                try {

                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(" Sent from the client: " + line);
                        out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (in != null) {
                            in.close();
                            socket.close();
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            } //Poner un sleep
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }

    public Socket getClientSocket() {
        return socket;
    }

    public synchronized void setClientSocket(Socket clientSocket) {
        this.socket = clientSocket;
        System.out.println("Socket puesto: "+this.socket);
    }

    public String getAddress() {
        return address;
    }

    public synchronized void setAddress(String address) {
        this.address = address;
    }

}
