/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.Dogadjaj;
import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import domen.Prisustvo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;
import operacije.Operacije;
import prenos.Odgovor;
import prenos.Zahtev;

/**
 *
 * @author Jaša
 */
public class KomunikacijaSaKlijentom extends Thread {

    Socket socket;
    Nalog nalog;
    Kontroler kontroler;
    Date vremePrisupa;
    boolean zaustavi = false;

    public KomunikacijaSaKlijentom(Socket socket) {
        this.socket = socket;
        kontroler = Kontroler.getInstance();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("Pokrenuta nit klijenta.");
            obradiZahtev();
        }
    }

    public void obradiZahtev() {
        Zahtev zk = primiZahtev();
        Odgovor odg = new Odgovor(null, null, false, "ERR");
        Operacije op = zk.getOperacija();
        OpstiDomenskiObjekat parametar = zk.getParametar();

        switch (op) {
            case ZAPAMTI_DOGADJAJ:
                odg = kontroler.zapamtiDogadjaj((Dogadjaj) parametar);
                break;
            case VRATI_NAJSKORIJE:
                odg = kontroler.vratiNajskorije();
                break;
            case PRONADJI_DOGADJAJE:
                odg = kontroler.ponadjiDogadjaje((Dogadjaj) parametar);
                break;
            case PRIKAZI_DOGADJAJ:
                odg = kontroler.prikaziDogadjaj((Dogadjaj) parametar);
                break;
            case PRONADJI_MOJE_DOGADJAJE:
                odg = kontroler.pronadjiMojeDogadjaje(nalog);
                break;
            case IZMENI_DOGADJAJ:
                odg = kontroler.izmeniDogadjaj((Dogadjaj) parametar);
                break;
            case OBRISI_DOGADJAJ:
                odg = kontroler.obrisiDogadjaj((Dogadjaj) parametar, nalog);
                break;
            case SACUVAJ_NALOG:
                odg = kontroler.sacuvajNalog((Nalog) parametar);
                break;
            case PRONADJI_MOJ_NALOG:
                odg = kontroler.pronadjiMojNalog(nalog);
                break;
            case OBRISI_NALOG:
                odg = kontroler.obrisiNalog(nalog);
                break;
            case SACUVAJ_PRISUSTVO:
                odg = kontroler.sacuvajPrisustvo((Prisustvo) parametar);
                break;
            case PRONADJI_MOJA_PRISUSTVA:
                odg = kontroler.pronadjiMojaPrisustva(nalog);
                break;
            case PRIKAZI_PRISUSTVO:
                odg = kontroler.prikaziPrisustvo((Prisustvo) parametar);
                break;
            case OBRISI_PRISUSTVO:
                odg = kontroler.obrisiPriustvo((Prisustvo) parametar, nalog);
                break;
            case UCITAJ_MESTA:
                odg = kontroler.ucitajMesta();
                break;
            case PRISTUPI:
                odg = kontroler.pristupi((Nalog) parametar);
                if (odg.isUspesno()) {
                    nalog = (Nalog) odg.getRezultat();
                    //((Nalog)odg.getRezultat()).setLozinka("");
                    vremePrisupa = new Date();
                    kontroler.registrujKlijenta(this);
                }
                break;
            case ODJAVI:
                kontroler.odjaviSe(this);
        }
        if (zaustavi) {
            odg = new Odgovor(null, null, true, "odjava");
            System.out.println("_______Server zahteva odjavu!________");
            this.interrupt();
        }
        System.out.println("Obradjen zahtev.");
        posalji(odg);
    }

    public void posalji(Odgovor odgovor) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Priprema za slanje odgovora.");
            oos.writeObject(odgovor);
            System.out.println("Odgovor poslat.");
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaKlijentom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Zahtev primiZahtev() {
        Zahtev zahtev = new Zahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Cekam zahtev.");
            zahtev = (Zahtev) ois.readObject();
            System.out.println("Primljen zahtev.");
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaKlijentom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KomunikacijaSaKlijentom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zahtev;
    }

    public void zaustavi() {
        zaustavi = true;

    }

    public Nalog getNalog() {
        return nalog;
    }

    public Date getVremePrisupa() {
        return vremePrisupa;
    }
    
    

}
