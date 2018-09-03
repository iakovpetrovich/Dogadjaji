/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public abstract class OpstiDomenskiObjekat implements Serializable {

    public abstract LinkedList<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception;

    public abstract void podesiAtributeDobijeneIzBaze(ResultSet rs);

    public abstract void postavi(OpstiDomenskiObjekat odo);

    public abstract String vratiNazivTabele();

    public abstract String vratiUslovZaJoin();

    public abstract String vratiUslovZaPretragu();

    public abstract LinkedList<OpstiDomenskiObjekat> vratiVezaneObjekte();

    public abstract String vratiVrednostiZaUnos();

    public abstract String vratiJednoznacno();

    public abstract String vratiUslovZaBrisanje();

    public abstract String vratiVreddnostZaIzmenu();

}
