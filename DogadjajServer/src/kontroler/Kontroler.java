/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import start.ServerStatus;
import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;
import domen.Dogadjaj;
import domen.Mesto;
import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import domen.Prisustvo;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Komunikacija;
import komunikacija.KomunikacijaSaKlijentom;
import operacije.IzmeniDogadjaj;
import operacije.ObrisiDogadjaj;
import operacije.ObrisiNalog;
import operacije.ObrisiPrisustvo;
import operacije.OpstaSistemskaOperacija;
import operacije.PrikaziDogadjaj;
import operacije.PrikaziPrisustvo;
import operacije.Pristupi;
import operacije.PronadjMojaPrisustva;
import operacije.Pronadji;
import operacije.PronadjiMojNalog;
import operacije.PronadjiMojeDogadjaje;
import operacije.SacuvajNalog;
import operacije.SacuvajPriustvo;
import operacije.UcitajMesta;
import operacije.VratiNajskorije;
import operacije.ZapamtiDogadjaj;
import prenos.Odgovor;
import sun.security.krb5.internal.tools.Klist;

/**
 *
 * @author Jaša
 */
public class Kontroler {
    
    ServerStatus st;
    
    private static Kontroler instance;
    Komunikacija komunikacija;
    LinkedList<KomunikacijaSaKlijentom> listaAktivnihKlijenata = new LinkedList<>();
    
    private Kontroler() {
        
    }
    
    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }
    
    OpstaSistemskaOperacija oso;
    Odgovor odg;
    
    public Odgovor zapamtiDogadjaj(Dogadjaj dogadjaj) {
        try {
            oso = new ZapamtiDogadjaj();
            oso.opsteIzvrsenjeSO(dogadjaj);
            odg = new Odgovor(((ZapamtiDogadjaj) oso).getDogadjaj(), true, "Sistem je zapamtio događaj.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da zapamti događaj.");
        }
        return odg;
    }
    
    public Odgovor vratiNajskorije() {
        oso = new VratiNajskorije();
        try {
            oso.opsteIzvrsenjeSO(new LinkedList<Dogadjaj>());
            odg = new Odgovor(((VratiNajskorije) oso).getDogadjaji(), true, "");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da vrati listu događaja!");
        }
        return odg;
    }
    
    public Odgovor ponadjiDogadjaje(Dogadjaj dogadjaj) {
        oso = new Pronadji();
        try {
            oso.opsteIzvrsenjeSO(dogadjaj);
            odg = new Odgovor(((Pronadji) oso).getDogadjaji(), true, "Sistem je našao događaje po zadatim vrednostima.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da nađe događaje po zadatim vrenostima!");
        }
        return odg;
    }
    
    public Odgovor prikaziDogadjaj(Dogadjaj dogadjaj) {
        oso = new PrikaziDogadjaj();
        try {
            oso.opsteIzvrsenjeSO(dogadjaj);
            odg = new Odgovor(((PrikaziDogadjaj) oso).getDogadjaj(), true, "Sistem je našao događaje po zadatim vrednostima.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da nađe događaje po zadatim vrenostima!");
        }
        return odg;
    }
    
    public Odgovor pronadjiMojeDogadjaje(Nalog nalog) {
        oso = new PronadjiMojeDogadjaje();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(((PronadjiMojeDogadjaje) oso).getDogadjaji(), true, "Sistem je našao događaje po zadatim vrednostima.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da nađe događaje po zadatim vrenostima!");
        }
        return odg;
    }
    
    public Odgovor izmeniDogadjaj(Dogadjaj dogadjaj) {
        oso = new IzmeniDogadjaj();
        try {
            oso.opsteIzvrsenjeSO(dogadjaj);
            odg = new Odgovor(((IzmeniDogadjaj) oso).getDogadjaj(), true, "Sistem je izmenio događaj.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da zapamti događaj.");
        }
        return odg;
    }
    
    public Odgovor obrisiDogadjaj(Dogadjaj dogadjaj, Nalog korisnik) {
        LinkedList<OpstiDomenskiObjekat> dogadjajINalog = new LinkedList<OpstiDomenskiObjekat>();
        dogadjajINalog.add(dogadjaj);
        dogadjajINalog.add(korisnik);
        oso = new ObrisiDogadjaj();
        try {
            oso.opsteIzvrsenjeSO(dogadjajINalog);
            odg = new Odgovor(null, null, true, "Sistem je obrisao događaj.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da obriše događaj.");
        }
        return odg;
    }
    
    public Odgovor sacuvajNalog(Nalog nalog) {
        oso = new SacuvajNalog();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(null, null, true, "Sistem je sačuvao nalog.");
            //Odjavi ga!
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da sačuva nalog." + ex.getMessage());
        }
        return odg;
    }
    
    public Odgovor pronadjiMojNalog(Nalog nalog) {
        oso = new PronadjiMojNalog();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(((PronadjiMojNalog) oso).getNalog(), true, "Sistem je pronašao nalog.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da prikaže vaš nalog.");
        }
        return odg;
    }
    
    public Odgovor sacuvajPrisustvo(Prisustvo prisustvo) {
        oso = new SacuvajPriustvo();
        try {
            oso.opsteIzvrsenjeSO(prisustvo);
            odg = new Odgovor(((SacuvajPriustvo) oso).getPrisustvo(), true, "Sistem je sačuvao prisustvo.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da sačuva prisustvo.");
        }
        return odg;
    }
    
    public Odgovor pronadjiMojaPrisustva(Nalog nalog) {
        oso = new PronadjMojaPrisustva();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(((PronadjMojaPrisustva) oso).getPrisustva(), true, "Sistem je našao prisustva po zadatoj vredosti.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da nađr prisustva po zadatoj vrednosti.");
        }
        return odg;
    }
    
    public Odgovor prikaziPrisustvo(Prisustvo prisustvo) {
        oso = new PrikaziPrisustvo();
        try {
            oso.opsteIzvrsenjeSO(prisustvo);
            odg = new Odgovor(((PrikaziPrisustvo) oso).getPrisustvo(), true, "Sistem je pronašao prisustvo!");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da vrati prisustvo.");
        }
        return odg;
    }
    
    public Odgovor obrisiPriustvo(Prisustvo prisustvo, Nalog nalog) {
        LinkedList<OpstiDomenskiObjekat> prisustvoINalog = new LinkedList<OpstiDomenskiObjekat>();
        prisustvoINalog.add(prisustvo);
        prisustvoINalog.add(nalog);
        oso = new ObrisiPrisustvo();
        try {
            oso.opsteIzvrsenjeSO(prisustvoINalog);
            odg = new Odgovor(null, null, true, "");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da obiše prisustvo.");
        }
        return odg;
    }
    
    public Odgovor pristupi(Nalog nalog) {
        oso = new Pristupi();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            Nalog n = ((Pristupi) oso).getNalog();
            daLiJeAktivan(n);
            odg = new Odgovor(n, true, "");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, ex.getMessage());
        }
        return odg;
    }
    
    public void registrujKlijenta(KomunikacijaSaKlijentom ksk) {
        listaAktivnihKlijenata.add(ksk);
        System.out.println("Novi klijent je autorizovan i registrovan u listu aktivnih.");
        st.osvezi(listaAktivnihKlijenata);
    }
    
    public void daLiJeAktivan(Nalog n) throws Exception {
        for (KomunikacijaSaKlijentom komunikacijaSaKlijentom : listaAktivnihKlijenata) {
            if (komunikacijaSaKlijentom.getNalog().equals(n)) {
                throw new Exception("Korisnik je već ulogovan!");
            }
        }
    }
    
    public void obavestiSve(Odgovor odgovor) {
        for (KomunikacijaSaKlijentom komunikacijaSaKlijentom : listaAktivnihKlijenata) {
            komunikacijaSaKlijentom.posalji(odgovor);
        }
    }
    
    public void odjaviSe(KomunikacijaSaKlijentom ksk) {
        ksk.zaustavi();
        listaAktivnihKlijenata.remove(ksk);
        
    }
    
    public Odgovor obrisiNalog(Nalog nalog) {
        oso = new ObrisiNalog();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(null, null, true, "Sistem je obrisao nalog.");
            //Odjavi ga!
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da obriše nalog." + ex.getMessage());
        }
        return odg;
        
    }
    
    public Odgovor ucitajMesta() {
        oso = new UcitajMesta();
        try {
            oso.opsteIzvrsenjeSO(new Mesto());
            odg = new Odgovor(((UcitajMesta) oso).getMesta(), true, "");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "");
        }
        return odg;
    }
    
    public void pokreniServer(int port) throws Exception {
        try {
            if (komunikacija == null) {
                komunikacija = new Komunikacija(port);
                komunikacija.start();
                return;
            }
            System.out.println("Nije null sace pokrenem.");
            komunikacija.pokreni(port);
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Greška pri pokretanju!");
        }
    }
    
    public void zaustaviServer(){
        Odgovor zaustavniOdgovor = new Odgovor(null, null, false, "odjava");
        for (KomunikacijaSaKlijentom komunikacijaSaKlijentom : listaAktivnihKlijenata) {
            komunikacijaSaKlijentom.zaustavi();
        }
        listaAktivnihKlijenata.clear();
        st.osvezi(listaAktivnihKlijenata);
        komunikacija.zaustavi();
        
    }
    
    public void registrujFormu(ServerStatus forma){
        st = forma;
    }

}
