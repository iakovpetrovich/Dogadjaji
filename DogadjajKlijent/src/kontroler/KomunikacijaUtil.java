/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Jaša
 */
public class KomunikacijaUtil {
    
    Properties props;

    public KomunikacijaUtil() throws IOException {
        props= new Properties();
        props.load(new FileInputStream("conn.properties"));
    }
    
     public String vratiIP(){
         return props.getProperty(KonekcijaKonstante.IP);
     }
     
     public String vratiPort(){
         return props.getProperty(KonekcijaKonstante.PORT);
     }
    
    
}
