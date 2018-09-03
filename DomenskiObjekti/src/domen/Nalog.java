/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaša
 */
public class Nalog extends OpstiDomenskiObjekat {

    private String ime;
    private String lozinka;
    private String telefon;
    Mesto mesto;

    public Nalog(String ime, String lozinka, String telefon, Mesto mesto) {
        this.ime = ime;
        this.lozinka = lozinka;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    public Nalog() {
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nalog other = (Nalog) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.lozinka, other.lozinka)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String vratiNazivTabele() {
        return "korisnik";
    }

    @Override
    public String vratiVrednostiZaUnos() {
        return "("
                + "'" + ime + "',"
                + "'" + lozinka + "',"
                + "'" + telefon + "',"
                + "'" + mesto.getPtt() + "')";
    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> vratiVezaneObjekte() {
        return null;
    }

    @Override
    public String vratiUslovZaPretragu() {
        String upit = "WHERE k.ime='" + ime + "'";
        return upit;
    }

    @Override
    public void podesiAtributeDobijeneIzBaze(ResultSet rs) {

    }

    @Override
    public void postavi(OpstiDomenskiObjekat odo) {

    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        LinkedList<OpstiDomenskiObjekat> lista = new LinkedList<>();
        try {
            while (rs.next()) {
                Nalog n = new Nalog();
                n.setIme(rs.getString("k.ime"));
                n.setTelefon(rs.getString("telefon"));
                Mesto m = new Mesto();
                m.setPtt(rs.getString("m.ptt"));
                m.setNaziv(rs.getString("m.naziv"));
                n.setMesto(m);
                n.setLozinka(rs.getString("k.lozinka"));
                lista.add(n);
            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Greška pri učitavanju!");
        }
    }

    @Override
    public String vratiUslovZaJoin() {
        return "k LEFT JOIN mesto m ON k.ptt=m.ptt";
    }

    @Override
    public String vratiJednoznacno() {
        return "k.ime='" + ime +"'";
    }

    @Override
    public String vratiUslovZaBrisanje() {
        return "ime='" + ime +"'";
    }

    @Override
    public String vratiVreddnostZaIzmenu() {
        return ""
                + "ime='" + ime + "',"
                + "lozinka='" + lozinka + "',"
                + "telefon='" + telefon + "',"
                + "ptt='" + mesto.getPtt() + "";
    }

}
