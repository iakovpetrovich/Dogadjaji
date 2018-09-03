/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Dogadjaj;
import domen.Nalog;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.Prisustvo;
import forme.DogadjajForma;
import forme.GlavnaForma;
import forme.NalogForma;
import forme.PristupForma;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import operacije.Operacije;
import prenos.Odgovor;
import prenos.Zahtev;

/**
 *
 * @author Jaša
 */
public class KontrolerKI {

    private static KontrolerKI kontrolerKI;
    private Komunikacija komunikacija;
    private LinkedList<Mesto> mesta = new LinkedList<>();
    private LinkedList<Dogadjaj> dogadjaji = new LinkedList<>();
    private LinkedList<Prisustvo> prisustva = new LinkedList<>();
    private GlavnaForma glavnaForma;
    private NalogForma nalogForma;
    private DogadjajForma dogadjajForma;
    private PristupForma pristupForma;
    private Nalog nalog;

    private Zahtev zahtev;
    private Odgovor odgovor;

    private KontrolerKI() {
        komunikacija = Komunikacija.getInstance();
        zahtev = new Zahtev(null, null, null);
        odgovor = new Odgovor(null, null, false, "Neuspešna komunikacija sa serverom.");
//        komunikacija.start();
//        komunikacija.prekini();
        //
        //ucitajMesta();
    }

    public static KontrolerKI getInstance() {
        if (kontrolerKI == null) {
            kontrolerKI = new KontrolerKI();
        }
        return kontrolerKI;
    }

    public Odgovor pozoviSO(Zahtev zahtev) {
//        komunikacija.prekini();
        komunikacija.posaljiZahtevZaSO(zahtev);
        odgovor = komunikacija.primiOdgovor();
        zahtev = new Zahtev(null, null, null);
//        komunikacija.pokreni();
        return odgovor;
    }

    public void proveri(Odgovor odg) throws Exception {
        if (!odgovor.isUspesno()) {
            throw new Exception(odg.getPoruka());
        }
        if (odgovor.getPoruka().equalsIgnoreCase("odjava")) {
            JOptionPane.showMessageDialog(null, "SERVER NIJE DOSTUPAN! Sistem će se ugasiti. Poslednja promena možda nije izvršena!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public LinkedList<Mesto> getMesta() {
        return mesta;
    }

    public void setMesta(LinkedList<Mesto> mesta) {
        this.mesta = mesta;
    }

    public LinkedList<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }

    public void setDogadjaji(LinkedList<Dogadjaj> dogadjaji) {
        this.dogadjaji = dogadjaji;
    }

    private void ucitajMesta() {
        zahtev = new Zahtev(null, null, Operacije.UCITAJ_MESTA);
        pozoviSO(zahtev);
        LinkedList<OpstiDomenskiObjekat> lista = odgovor.getListaODO();
        if (lista.isEmpty()) {
            System.out.println("Lista je prazna?!");
            return;
        }
        mesta.clear();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            Mesto m = (Mesto) opstiDomenskiObjekat;
            mesta.add(m);
        }

    }

    private void ucitajDogadjaje() {
        zahtev = new Zahtev(null, null, Operacije.VRATI_NAJSKORIJE);
        pozoviSO(zahtev);
        LinkedList<OpstiDomenskiObjekat> lista = odgovor.getListaODO();
        if (lista.isEmpty()) {
            System.out.println("Lista je prazna?!");
        }
        dogadjaji.clear();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            Dogadjaj d = (Dogadjaj) opstiDomenskiObjekat;
            dogadjaji.add(d);
        }
    }

    public void registrujGlavnuFormu(GlavnaForma glavna) {
        glavnaForma = glavna;
    }

    public Odgovor zapamtiDogadjaj(Dogadjaj dogadjaj) throws Exception {
        zahtev = new Zahtev(dogadjaj, null, Operacije.ZAPAMTI_DOGADJAJ);
        pozoviSO(zahtev);
        proveri(odgovor);
        return odgovor;
    }

    public LinkedList<Dogadjaj> vratiNajskorije() {
        ucitajDogadjaje();
        return dogadjaji;
    }

//    public Odgovor pronadji(String kljucneReci,){
//        
//    }
    public Dogadjaj prikaziDogadjaj(Dogadjaj dogadjaj) throws Exception {
        zahtev = new Zahtev(dogadjaj, null, Operacije.PRIKAZI_DOGADJAJ);
        pozoviSO(zahtev);
        proveri(odgovor);
        return (Dogadjaj) odgovor.getRezultat();
    }

    public LinkedList<Dogadjaj> pronadjiDogadjaje(Dogadjaj dogadjaj) {
        zahtev = new Zahtev(dogadjaj, Operacije.PRONADJI_DOGADJAJE);
        pozoviSO(zahtev);
        LinkedList<OpstiDomenskiObjekat> lista = odgovor.getListaODO();
        if (lista.isEmpty()) {
            System.out.println("Lista je prazna?!");
        }
        dogadjaji.clear();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            Dogadjaj d = (Dogadjaj) opstiDomenskiObjekat;
            dogadjaji.add(d);
        }
        return dogadjaji;
    }

    public LinkedList<Dogadjaj> pronadjiMojeDogadjaje() {
        zahtev = new Zahtev(null, null, Operacije.PRONADJI_MOJE_DOGADJAJE);
        pozoviSO(zahtev);
        LinkedList<OpstiDomenskiObjekat> lista = odgovor.getListaODO();
        if (lista.isEmpty()) {
            System.out.println("Lista je prazna?!");
        }
        dogadjaji.clear();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            Dogadjaj d = (Dogadjaj) opstiDomenskiObjekat;
            dogadjaji.add(d);
        }
        return dogadjaji;
    }

    public Odgovor izmeniDogadjaj(Dogadjaj dogadjaj) throws Exception {
        zahtev = new Zahtev(dogadjaj, null, Operacije.IZMENI_DOGADJAJ);
        pozoviSO(zahtev);
        proveri(odgovor);
        return odgovor;
    }

    public Odgovor obrisiDogadjaj(Dogadjaj dogadjaj) {
        zahtev = new Zahtev(dogadjaj, null, Operacije.OBRISI_DOGADJAJ);
        return pozoviSO(zahtev);
    }

    public Odgovor sacuvajNalog(Nalog korisnik) throws Exception {
        zahtev = new Zahtev(korisnik, null, Operacije.SACUVAJ_NALOG);
        pozoviSO(zahtev);
        proveri(odgovor);
        return odgovor;
    }

    public Odgovor pronadjiMojNalog() throws Exception {
        zahtev = new Zahtev(Operacije.PRONADJI_MOJ_NALOG);
        pozoviSO(zahtev);
        proveri(odgovor);
        return odgovor;
    }

    public Odgovor obrisiNalog() throws Exception {
        zahtev = new Zahtev(Operacije.OBRISI_NALOG);
        pozoviSO(zahtev);
        proveri(odgovor);
        return odgovor;
    }

    public Odgovor kreirajPrisustvo(Nalog korisnik, Dogadjaj dogadjaj) {
        OpstiDomenskiObjekat odo1 = korisnik;
        OpstiDomenskiObjekat odo2 = dogadjaj;
        LinkedList<OpstiDomenskiObjekat> lista = new LinkedList<>();
        lista.add(odo1);
        lista.add(odo2);
        zahtev = new Zahtev(lista, Operacije.KREIRAJ_PRISUSTVO);
        return pozoviSO(zahtev);
    }

    public Odgovor sacuvajPrisustvo(Prisustvo prisustvo) throws Exception {
        zahtev = new Zahtev(prisustvo, Operacije.SACUVAJ_PRISUSTVO);
        pozoviSO(zahtev);
        proveri(odgovor);
        return odgovor;
    }

    public LinkedList<Prisustvo> pronadjiMojaPrisustva() throws Exception {
        zahtev = new Zahtev(Operacije.PRONADJI_MOJA_PRISUSTVA);
        pozoviSO(zahtev);
        proveri(odgovor);
        LinkedList<OpstiDomenskiObjekat> lista = odgovor.getListaODO();
        prisustva.clear();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            Prisustvo p = (Prisustvo) opstiDomenskiObjekat;
            prisustva.add(p);
        }

        return prisustva;
    }

    public Odgovor prikaziPrisustvo(Prisustvo prisustvo) throws Exception {
        zahtev = new Zahtev(prisustvo, Operacije.PRIKAZI_PRISUSTVO);
        pozoviSO(zahtev);
        proveri(odgovor);
        return odgovor;
    }

    public Odgovor obrisiPrisustvo(Prisustvo prisustvo) throws Exception {
        zahtev = new Zahtev(prisustvo, Operacije.OBRISI_PRISUSTVO);
        pozoviSO(zahtev);
        proveri(odgovor);
        prisustva.remove(prisustvo);
        return odgovor;
    }

    public LinkedList<Prisustvo> getPrisustva() {
        return prisustva;
    }

    public void setPrisustva(LinkedList<Prisustvo> prisustva) {
        this.prisustva = prisustva;
    }

    public void registrujNalogFormu(NalogForma nf) {
        nalogForma = nf;
    }

    public boolean pristupi(Nalog n) throws Exception {
        zahtev = new Zahtev(n, Operacije.PRISTUPI);
        pozoviSO(zahtev);
        if (!odgovor.isUspesno()) {
            throw new Exception(odgovor.getPoruka());
        }
        nalog = (Nalog) odgovor.getRezultat();
        ucitajMesta();
        ucitajDogadjaje();
        return true;
    }

    public void odjava() {
        zahtev = new Zahtev(Operacije.ODJAVI);
        komunikacija.posaljiZahtevZaSO(zahtev);
        glavnaForma.dispose();
        System.exit(0);
    }

    public void registrujPristupFormu(PristupForma pf) {
        pristupForma = pf;
    }

    public void registrujDogadjajFormu(DogadjajForma df) {
        dogadjajForma = df;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void osvezi() {
        ucitajDogadjaje();
        glavnaForma.osvezi();
    }

    public Odgovor getOdgovor() {
        return odgovor;
    }

}
