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
public class Mesto extends OpstiDomenskiObjekat {

    private String ptt;
    private String naziv;

    public Mesto(String ptt, String naziv) {
        this.ptt = ptt;
        this.naziv = naziv;
    }

    public Mesto() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public String vratiVrednostiZaUnos() {
        return "("
                + "'" + ptt + "',"
                + "'" + naziv + "')";

    }

    @Override
    public String vratiUslovZaPretragu() {
        String uslov = /*"naziv = '" + naziv + "'";*/ "";
        return uslov;
    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> vratiVezaneObjekte() {
        return null;
    }
    
    

    @Override
    public void podesiAtributeDobijeneIzBaze(ResultSet rs) {

    }

    @Override
    public void postavi(OpstiDomenskiObjekat odo) {

    }

    @Override
    public LinkedList<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception{
        try {
            LinkedList<OpstiDomenskiObjekat> lista = new LinkedList<>();
            while (rs.next()) {
                Mesto m = new Mesto();
                m.setPtt(rs.getString("ptt"));
                m.setNaziv(rs.getString("naziv"));
                lista.add(m);
            }
           return lista;
        } catch (SQLException ex) {
            throw new Exception("Greška pri učitavanju!");
        }
    }

    @Override
    public String vratiUslovZaJoin() {
        return "";
    }

    @Override
    public String vratiJednoznacno() {
        return "ptt = '" + ptt + "'";
    }

    @Override
    public String vratiUslovZaBrisanje() {
        return "ptt = '" + ptt + "'";
    }

    @Override
    public String toString() {
        return naziv;
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
        final Mesto other = (Mesto) obj;
        if (!Objects.equals(this.ptt, other.ptt)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiVreddnostZaIzmenu() {
         return ""
                + "ptt='" + ptt + "',"
                + "naziv='" + naziv + "";
    }
    
    

}
