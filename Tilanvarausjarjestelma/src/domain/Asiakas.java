/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author mina
 */
public class Asiakas {
    private int id;
    private String nimi;
    private  int pNumero;

    public Asiakas(int id, String nimi, int pNumero) {
        this.id = id;
        this.nimi = nimi;
        this.pNumero = pNumero;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public int getpNumero() {
        return pNumero;
    }
    
    
    
    
}
