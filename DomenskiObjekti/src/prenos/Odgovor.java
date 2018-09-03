/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prenos;

import domen.OpstiDomenskiObjekat;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class Odgovor implements Serializable{

    private OpstiDomenskiObjekat rezultat;
    private LinkedList<OpstiDomenskiObjekat> listaODO;
    private boolean uspesno;
    private String poruka;

    public Odgovor(OpstiDomenskiObjekat rezultat, boolean uspesno, String poruka) {
        this.rezultat = rezultat;
        this.uspesno = uspesno;
        this.poruka = poruka;
    }

    public Odgovor(LinkedList<OpstiDomenskiObjekat> listaODO, boolean uspesno, String poruka) {
        this.listaODO = listaODO;
        this.uspesno = uspesno;
        this.poruka = poruka;
    }

    public Odgovor(OpstiDomenskiObjekat rezultat, LinkedList<OpstiDomenskiObjekat> listaODO, boolean uspesno, String poruka) {
        this.rezultat = rezultat;
        this.listaODO = listaODO;
        this.uspesno = uspesno;
        this.poruka = poruka;
    }
    

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(OpstiDomenskiObjekat rezultat) {
        this.rezultat = rezultat;
    }

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public LinkedList<OpstiDomenskiObjekat> getListaODO() {
        return listaODO;
    }

    public void setListaODO(LinkedList<OpstiDomenskiObjekat> listaODO) {
        this.listaODO = listaODO;
    }

   
}
