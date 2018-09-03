/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaša
 */
public class Komunikacija extends Thread {

    ServerSocket serverSocket;
    boolean prekinut = false;

    public Komunikacija(int port) throws Exception {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Greška pri pokretanju servera! Port može biti zauzet.");
        }
        System.out.println("Server je pokrenut");
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("1");
            if (!prekinut) {
                try {
                    System.out.println("Nisam prekinut");
                    System.out.println("+++++++++++++++++Čekam klijenta");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Klijent se nakacio na server.");
                    (new KomunikacijaSaKlijentom(clientSocket)).start();

                } catch (Exception ex) {
                   // Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

//    public void startStop(){
//        if (!isInterrupted()) {
//            interrupt();
//            System.out.println("Server stopiran.");
//        }
//        interrupted();
//        System.out.println("Server ponovo pokrenut.");
//    }
    public void zaustavi() {
//        System.out.println("------Zaustavljam");
//        this.interrupt();
//        
//        if (isInterrupted()) {
//            System.out.println("______________Server stopiran._______________");
//        }
        prekinut = true;
        try {
            serverSocket.close();
            System.out.println(prekinut);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DE GA UHVATI?");
        }
    }

    public void pokreni(int port) {
        prekinut = false;
        try {
            serverSocket = new ServerSocket(57613);
            System.out.println("Pokrenut");
            System.out.println(prekinut);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
