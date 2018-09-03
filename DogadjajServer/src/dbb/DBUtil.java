/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author Jaša
 */
public class DBUtil {
    
    Properties props;

    public DBUtil() throws IOException {
        props= new Properties();
        props.load(new FileInputStream("db.properties"));
    }
     public String vratiURL(){
         return props.getProperty(DBKonstante.URL);
     }
     
     public String vratiKorisnika(){
         return props.getProperty(DBKonstante.USER);
     }
     
     public String vratiSifru(){
         return props.getProperty(DBKonstante.PASSWORD);
     }
    
    
    
}
