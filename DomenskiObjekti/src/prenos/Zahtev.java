/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prenos;

import domen.OpstiDomenskiObjekat;
import java.io.Serializable;
import java.util.LinkedList;
import operacije.Operacije;

/**
 *
 * @author Jaša
 */
public class Zahtev implements Serializable {

    private OpstiDomenskiObjekat parametar;
    private LinkedList<OpstiDomenskiObjekat> listaParametara;
    private Operacije operacija;

    public Zahtev(OpstiDomenskiObjekat parametar, LinkedList<OpstiDomenskiObjekat> listaParametara, Operacije operacija) {
        this.parametar = parametar;
        this.listaParametara = listaParametara;
        this.operacija = operacija;
    }

    public Zahtev(OpstiDomenskiObjekat parametar, Operacije operacija) {
        this.parametar = parametar;
        this.operacija = operacija;
    }

    public Zahtev(LinkedList<OpstiDomenskiObjekat> listaParametara, Operacije operacija) {
        this.listaParametara = listaParametara;
        this.operacija = operacija;
    }

    public Zahtev(Operacije operacija) {
        this.operacija = operacija;
    }

    public Zahtev() {
    }

    public Operacije getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacije operacija) {
        this.operacija = operacija;
    }

    public OpstiDomenskiObjekat getParametar() {
        return parametar;
    }

    public void setParametar(OpstiDomenskiObjekat parametar) {
        this.parametar = parametar;
    }

    public LinkedList<OpstiDomenskiObjekat> getListaParametara() {
        return listaParametara;
    }

    public void setListaParametara(LinkedList<OpstiDomenskiObjekat> listaParametara) {
        this.listaParametara = listaParametara;
    }

}
