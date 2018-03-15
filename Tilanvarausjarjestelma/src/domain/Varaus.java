/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author mina
 */
public class Varaus {
    private int id;
    private String nimi;
    private Asiakas asiakas;
    private Tila tila;
    private Date Alkaa;
    private Date Loppuu;

    public Varaus(int id, String nimi, Asiakas asiakas,Tila tila, Date Alkaa, Date Loppuu) {
        this.id = id;
        this.nimi = nimi;
        this.asiakas = asiakas;
        this.Alkaa = Alkaa;
        this.Loppuu = Loppuu;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public Tila getTila() {
        return tila;
    }
    
    

    public Date getAlkaa() {
        return Alkaa;
    }

    public Date getLoppuu() {
        return Loppuu;
    }
    
    
    
}
