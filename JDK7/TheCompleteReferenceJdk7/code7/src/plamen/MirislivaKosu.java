package plamen;

import java.sql.SQLException;

/**
 * Created by plamen on 25/09/2014.
 */
public class MirislivaKosu implements Kosu {


    public static void main(String[] args){
        new MirislivaKosu().pruch();
    }

    @Override
    public void pruch(){
        System.out.println("trutka: " + trutka);
    }
}
