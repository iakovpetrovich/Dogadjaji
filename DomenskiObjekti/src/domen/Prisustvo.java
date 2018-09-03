/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaša
 */
public class Prisustvo extends OpstiDomenskiObjekat {

    private Nalog korisnik;
    private Dogadjaj dogadjaj;
    private int brojOsoba;
    private String zahtev;

    public Prisustvo(Nalog korisnik, Dogadjaj dogadjaj, int brojOsoba, String zahtev) {
        this.korisnik = korisnik;
        this.dogadjaj = dogadjaj;
        this.brojOsoba = brojOsoba;
        this.zahtev = zahtev;
    }

    public String getZahtev() {
        return zahtev;
    }

    public void setZahtev(String zahtev) {
        this.zahtev = zahtev;
    }

    public Nalog getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Nalog korisnik) {
        this.korisnik = korisnik;
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
    }

    public int getBrojOsoba() {
        return brojOsoba;
    }

    public void setBrojOsoba(int brojOsoba) {
        this.brojOsoba = brojOsoba;
    }

    @Override
    public String vratiNazivTabele() {
        return "prisustvo";
    }

    @Override
    public String vratiVrednostiZaUnos() {
        return "("
                + dogadjaj.getIDD() + ","
                + "'" + korisnik.getIme() + "',"
                + "" + brojOsoba + ","
                + "'" + zahtev + "')";
    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> vratiVezaneObjekte() {
        return null;
    }

    @Override
    public void podesiAtributeDobijeneIzBaze(ResultSet rs) {

    }

    @Override
    public String vratiUslovZaJoin() {
        return "p LEFT JOIN dogadjaj d ON p.dogadjaj_id = d. dogadjaj_id "
                + "LEFT JOIN mesto mesto_dogadjaja ON d.ptt = mesto_dogadjaja.ptt "
                + "LEFT JOIN korisnik kreator ON d.ime = kreator.ime "
                + "LEFT JOIN mesto mesto_kreatora ON kreator.ptt = mesto_kreatora.ptt "
                + "LEFT JOIN korisnik prisutni ON p.ime = prisutni.ime "
                + "LEFT JOIN mesto mesto_prisutnog ON prisutni.ptt = mesto_prisutnog.ptt ";
    }

    @Override
    public String vratiUslovZaPretragu() {
        return "WHERE prisutni.ime= '" + korisnik.getIme() + "'";
    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            LinkedList<OpstiDomenskiObjekat> lista = new LinkedList<>();
            while (rs.next()) {
                Mesto mestoPrisutnog = new Mesto(rs.getString("mesto_prisutnog.ptt"), rs.getString("mesto_prisutnog.naziv"));
                korisnik = new Nalog(rs.getString("prisutni.ime"), "", rs.getString("prisutni.telefon"), mestoPrisutnog);
                Mesto mestoKreatora = new Mesto(rs.getString("mesto_kreatora.ptt"), rs.getString("mesto_kreatora.naziv"));
                Nalog kreator = new Nalog(rs.getString("kreator.ime"), "", rs.getString("kreator.telefon"), mestoKreatora);
                Mesto mestoDogadjaja = new Mesto(rs.getString("mesto_dogadjaja.ptt"), rs.getString("mesto_dogadjaja.naziv"));
                dogadjaj = new Dogadjaj(rs.getString("p.dogadjaj_id"), rs.getString("d.naziv"),
                        rs.getString("d.opis"), rs.getDate("d.datum"), rs.getString("d.adresa"), kreator, new LinkedList<Aktivnost>(), mestoDogadjaja);
                Prisustvo p = new Prisustvo(korisnik, dogadjaj, rs.getInt("p.broj_osoba"), rs.getString("p.zahtev"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Greška pri učitavanju!");
        }
    }

    @Override
    public String vratiJednoznacno() {
        return "d.dogadjaj_id = " + dogadjaj.getIDD() + " AND p.ime = '" + korisnik.getIme()+"'";
    }

    @Override
    public void postavi(OpstiDomenskiObjekat odo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String vratiUslovZaBrisanje() {
        return "dogadjaj_id = " + dogadjaj.getIDD() + " AND ime = '" + korisnik.getIme()+"'";
    }

    @Override
    public String vratiVreddnostZaIzmenu() {
        return ""
                + "broj_osoba=" + brojOsoba + ","
                + "zahtev='" + zahtev + "";
    }
}
