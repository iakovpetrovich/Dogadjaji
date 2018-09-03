/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaša
 */
public class Dogadjaj extends OpstiDomenskiObjekat {

    private String IDD;
    private String naziv;
    private String opis;
    private Date datum;
    private String adresa;
    private Nalog kreator;
    private LinkedList<Aktivnost> spisakAktivnosti;
    private Mesto mesto;

    public Dogadjaj(String IDD, String naziv, String opis, Date datum, String adresa, Nalog kreator, LinkedList<Aktivnost> spisakAktivnosti, Mesto mesto) {
        this.IDD = IDD;
        this.naziv = naziv;
        this.opis = opis;
        this.datum = datum;
        this.adresa = adresa;
        this.kreator = kreator;
        this.spisakAktivnosti = spisakAktivnosti;
        this.mesto = mesto;
    }

    public Dogadjaj(String opis, Date datum, String adresa) {
        this.opis = opis;
        this.datum = datum;
        this.adresa = adresa;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Nalog getKreator() {
        return kreator;
    }

    public void setKreator(Nalog kreator) {
        this.kreator = kreator;
    }

    public String getIDD() {
        return IDD;
    }

    public void setIDD(String IDD) {
        this.IDD = IDD;
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public LinkedList<Aktivnost> getSpisakAktivnosti() {
        return spisakAktivnosti;
    }

    public void setSpisakAktivnosti(LinkedList<Aktivnost> spisakAktivnosti) {
        this.spisakAktivnosti = spisakAktivnosti;
    }

    @Override
    public String vratiNazivTabele() {
        return "dogadjaj";
    }

    public String konvertujDatum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(datum);
    }

    @Override
    public String vratiVrednostiZaUnos() {
        String datumString = konvertujDatum();
        return "(NULL,"
                + "'" + naziv + "',"
                + "'" + opis + "',"
                + "'" + datumString + "',"
                + "'" + adresa + "',"
                + "'" + mesto.getPtt() + "',"
                + "'" + kreator.getIme() + "')";
    }

//    public static void main(String[] args) {
//        Dogadjaj d= new Dogadjaj("23", "naziv", "opis", new Date(), "Adresa", new Nalog("Pera", "123", "tel"), null, new Mesto("14123", "Kul mesto"));
//        System.out.println(d.vratiVrednostiZaUnos());
//    }
    @Override
    public String vratiUslovZaPretragu() {
        String uslov = "";
        if (datum == null) {
            datum = new Date();
        }
        if (kreator != null) {
            datum = new Date(90, 1, 1, 1, 1);
            uslov = "k.ime = '" + kreator.getIme() + "' AND ";
        }
        if (mesto == null) {
            uslov += " d.ptt LIKE '%'";
        } else if (mesto != null && mesto.getNaziv().equalsIgnoreCase("Sva mesta")) {
            uslov += " d.ptt LIKE '%'";
        } else {
            uslov += "d.ptt='" + mesto.getPtt() + "'";
        }

        String[] kljucneReci = opis.toLowerCase().split(" ");
        String uslovReci = "";
        for (int i = 1; i < kljucneReci.length; i++) {
            uslovReci += " OR d.opis LIKE '%" + kljucneReci[i] + "%'";
        }
        uslov = "WHERE " + uslov + " AND datum >= '" + konvertujDatum() + "' AND (d.opis LIKE '%" + kljucneReci[0] + "%'" + uslovReci + ") ORDER BY datum ASC";
        //System.out.println(uslov);
        return uslov;
    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> vratiVezaneObjekte() {
        LinkedList<OpstiDomenskiObjekat> listaVezanihObjekata = new LinkedList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : spisakAktivnosti) {
            listaVezanihObjekata.add(opstiDomenskiObjekat);
        }
        return listaVezanihObjekata;
    }

    @Override
    public void podesiAtributeDobijeneIzBaze(ResultSet rs) {
        try {
            if (rs.next()) {
                IDD = rs.getInt(1) + "";
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dogadjaj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void postavi(OpstiDomenskiObjekat odo) {
        if (odo instanceof Nalog) {
            kreator = (Nalog) odo;
        }
        if (odo instanceof Mesto) {
            mesto = (Mesto) odo;
        }
        if (odo instanceof Aktivnost) {
            spisakAktivnosti.add((Aktivnost) odo);
        }
    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        LinkedList<OpstiDomenskiObjekat> lista = new LinkedList<>();
        try {

            while (rs.next()) {
                IDD = rs.getString("dogadjaj_id");
                naziv = rs.getString("naziv");
                opis = rs.getString("opis");
                datum = rs.getTimestamp("datum");
                adresa = rs.getString("adresa");
                mesto = new Mesto(rs.getString("m.ptt"), rs.getString("m.naziv"));
                Mesto mestoKreatora = new Mesto(rs.getString("mesto_kreatora.ptt"), rs.getString("mesto_kreatora.ptt"));
                kreator = new Nalog(rs.getString("k.ime"), "", rs.getString("k.telefon"), mestoKreatora);
                Dogadjaj d = new Dogadjaj(IDD, naziv, opis, datum, adresa, kreator, null, mesto);
                lista.add(d);

            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Greška pri učitavanju!");
        }
        //return lista;
    }

    @Override
    public String vratiUslovZaJoin() {
        return ""
                + "d LEFT JOIN korisnik k ON d.ime = k.ime "
                + "LEFT JOIN mesto m ON d.ptt = m.ptt "
                + "LEFT JOIN mesto mesto_kreatora ON k.ptt = mesto_kreatora.ptt";
    }

    @Override
    public String vratiJednoznacno() {
        return "d.dogadjaj_id = " + IDD;
    }

    @Override
    public String vratiUslovZaBrisanje() {
        return "dogadjaj_id = " + IDD;
    }

    @Override
    public String vratiVreddnostZaIzmenu() {
        String datumString = konvertujDatum();
        return ""
                + "naziv = '" + naziv + "',"
                + "opis = '" + opis + "',"
                + "datum = '" + datumString + "',"
                + "adresa ='" + adresa + "',"
                + "ptt = '" + mesto.getPtt() + "'";
    }
}
