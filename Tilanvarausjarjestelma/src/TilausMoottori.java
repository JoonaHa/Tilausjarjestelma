
import dao.Database;
import dao.VarausDao;
import domain.Varaus;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TilausMoottori {

    private static TilausMoottori moottori;
    private static Database db;

    private TilausMoottori() {
    }

    public static TilausMoottori getInstance(Database database) {
        if (moottori == null) {
            moottori = new TilausMoottori();
        }
        db = database;
        return moottori;
    }

    protected boolean VaraaTila(Varaus var) throws SQLException, Exception {
        VarausDao varauksetDao = new VarausDao(db);
        List<Varaus> varaukset = null;
       //haetaan varauksenet Tilan mukaan
        
        try {
            varaukset = varauksetDao.findByTila(var.getTila());
        } catch (Exception ex) {
            System.out.println("Can not list" + varauksetDao.getClass());
        }

        if (varaukset == null) {
            return false;
        }
        
        if (varaukset.isEmpty()) {
            varauksetDao.save(var);
            return true;
        }
            // tarkistetaan että varaus alkaa tai loppuu ennen muita varauksia
        for (int i = 0; i < varaukset.size(); i++) {
            Varaus get = varaukset.get(i);
            if (get.getLoppuu().before(var.getAlkaa())
                    || var.getLoppuu().before(get.getAlkaa())) {

                continue;

            } else {
                
                return false;
            }

        }
        
        varauksetDao.save(var);
        return true;
    }

}
