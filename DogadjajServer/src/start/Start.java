package start;


import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaša
 */
public class Start {
    
    public static void main(String[] args) {
        Kontroler k = Kontroler.getInstance();
        try {
            k.pokreniServer(57613);
        } catch (Exception ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
