
package domain;

import java.util.List;

/**
 *
 * @author JoonaHa
 */
public class Tila {
    private String nimi;
    private int paikkoja;
    private List<String> varustelu ;

    public Tila(String nimi, int paikkoja, List<String> varustelu) {
        this.nimi = nimi;
        this.paikkoja = paikkoja;
        this.varustelu = varustelu;
    }

    public String getNimi() {
        return nimi;
    }

    public int getPaikkoja() {
        return paikkoja;
    }

    public List<String> getVarustelu() {
        return varustelu;
    }
    
    
    
    
}
