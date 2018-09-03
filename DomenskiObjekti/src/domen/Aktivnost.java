/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaša
 */
public class Aktivnost extends OpstiDomenskiObjekat {

    private Dogadjaj dogadjaj;
    private int redniBroj;
    private String naziv;
    private String opis;

    public Aktivnost(Dogadjaj dogadjaj, int redniBroj, String naziv, String opis) {
        this.dogadjaj = dogadjaj;
        this.redniBroj = redniBroj;
        this.naziv = naziv;
        this.opis = opis;
    }

    @Override
    public String vratiNazivTabele() {
        return "aktivnost";
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String vratiVrednostiZaUnos() {
        return "("
                + "NULL" + ","
                + dogadjaj.getIDD() + ","
                + "'" + naziv + "',"
                + "'" + opis + "')";

    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> vratiVezaneObjekte() {
        return null;
    }

    @Override
    public void podesiAtributeDobijeneIzBaze(ResultSet rs) {
        try {
            if (rs.next()) {
                redniBroj = rs.getInt(1);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Aktivnost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void postavi(OpstiDomenskiObjekat odo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> napuni(ResultSet rs) {
        LinkedList<OpstiDomenskiObjekat> lista = new LinkedList<>();
        try {
            while (rs.next()) {
                String IDD = rs.getString("dogadjaj_id");
                String naziv = rs.getString("naziv");
                String opis = rs.getString("opis");
                Date datum = rs.getDate("datum");
                String adresa = rs.getString("adresa");
                Mesto mesto = new Mesto(rs.getString("m.ptt"), rs.getString("m.naziv"));
                Mesto mestoKreatora = new Mesto(rs.getString("mesto_kreatora.ptt"), rs.getString("mesto_kreatora.ptt"));
                Nalog kreator = new Nalog(rs.getString("k.ime"), "", rs.getString("k.telefon"), mestoKreatora);
                dogadjaj = new Dogadjaj(IDD, naziv, opis, datum, adresa, kreator, null, mesto);
                redniBroj = rs.getInt("a.redni_broj");
                
                String nazivA = rs.getString("a.naziv");
                String opisA = rs.getString("a.opis");
                Aktivnost a = new Aktivnost(dogadjaj, redniBroj, nazivA, opisA);
                lista.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dogadjaj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String vratiUslovZaJoin() {
        return "a LEFT JOIN dogadjaj d ON d.dogadjaj_id = a.dogadjaj_id "
                + "LEFT JOIN korisnik k ON d.ime = k.ime "
                + "LEFT JOIN mesto m ON d.ptt = m.ptt "
                + "LEFT JOIN mesto mesto_kreatora ON k.ptt = mesto_kreatora.ptt";
    }

    @Override
    public String vratiUslovZaPretragu() {
        String upit = "WHERE d.dogadjaj_id = " + dogadjaj.getIDD();
        return upit;
    }

    @Override
    public String vratiJednoznacno() {
        return "d.dogadjaj_id = " + dogadjaj.getIDD() + " AND " + "a.redni_broj = " + redniBroj;
    }

    @Override
    public String vratiUslovZaBrisanje() {
        return "dogadjaj_id = " + dogadjaj.getIDD() + " AND " + "redni_broj = " + redniBroj;
    }

    @Override
    public String vratiVreddnostZaIzmenu() {
        return ""
                + "naziv='" + naziv + "',"
                + "opis='" + opis + "";
    }
}
