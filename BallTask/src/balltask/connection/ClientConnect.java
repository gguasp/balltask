/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask.connection;

import balltask.BallTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dam
 */
public class ClientConnect implements Runnable {

    private static final int PORT = 6787;

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", PORT)) {
            //Setear el canal en el cliente también. DONE
            //Poner un sleep (No hecho ya que espera a recibir texto, no deberá ser así
            //Enviar la identificación
            System.out.println("Conectado a servidor: "+socket.getInetAddress().getHostAddress());
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BallTask.handler.setClientSocket(socket);
            BallTask.handler.setAddress("localhost");
            
            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"bye".equalsIgnoreCase(line)) {
                line = sc.nextLine();
                out.println(line);
                out.flush();
                System.out.println("Server replied " + in.readLine());
            }

            sc.close();
        } catch (IOException e) {
            System.out.println("No se ha encontrado ningún servidor. Buscando clientes.");
        }
    }
}

