/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import prenos.Odgovor;
import prenos.Zahtev;

/**
 *
 * @author Jaša
 */
public class Komunikacija  {

    private static Komunikacija komunikacija;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    private Komunikacija() {
        povezivanjeSaServerom();
    }

//    @Override
//    public void run() {
//        
//        while (!isInterrupted()) {
//            Odgovor o = primiOdgovor();
//            if (o.getPoruka().equalsIgnoreCase("odjava")) {
//                JOptionPane.showMessageDialog(null, "SERVER NIJE DOSTUPAN! Program će se ugasiti!", "Server je nedostupan", JOptionPane.ERROR_MESSAGE);
//                System.exit(0);
//            }
//        }
//    }

    public static Komunikacija getInstance() {
        if (komunikacija == null) {
            komunikacija = new Komunikacija();
        }
        return komunikacija;
    }

    public void povezivanjeSaServerom() {
        try {
            KomunikacijaUtil ku = new KomunikacijaUtil();
            String localHost = ku.vratiIP();
            int port = Integer.parseInt(ku.vratiPort());
            socket = new Socket(localHost, port);
            System.out.println("Klijent se povezao na server");
        } catch (IOException ex) {
            //Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Greška u povezivanju sa serverom. Ponovo pokrenite program.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
    }

    public void posaljiZahtevZaSO(Zahtev zahtev) {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(zahtev);
            //out.close();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Odgovor primiOdgovor() {
        Odgovor odgovor = new Odgovor(null, null, false, "");
        try {
            in = new ObjectInputStream(socket.getInputStream());
            odgovor = (Odgovor) in.readObject();
            //in.close();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odgovor;
    }
    
//    public void prekini(){
//        System.out.println(isInterrupted());
//        interrupt();
//    }
//    public void pokreni(){
//        this.start();
//    }

}
