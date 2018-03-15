/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Asiakas;
import domain.Varaus;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mina
 */
public class VarausDao implements Dao<Varaus, Integer> {

    private Database database;

    public VarausDao(Database database) {
        this.database = database;
    }

    @Override
    public Varaus save(Varaus object) throws SQLException, Exception {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Varaus (id, nimi, asiakas_id, Alkaa, Loppuu) VALUES (?,?,?,?,?)");
        stmt.setInt(1, object.getId());
        stmt.setString(2, object.getNimi());
        stmt.setInt(3, object.getAsiakas().getId());
        stmt.setDate(4, (Date) object.getAlkaa());
        stmt.setDate(5, (Date) object.getLoppuu());
        stmt.executeUpdate();

        return findOne(object.getId());
    }

    @Override
    public void delete(Integer key) throws SQLException, Exception {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Varaus WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
    }

    @Override
    public Varaus findOne(Integer key) throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Varaus WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        Asiakas asiakas = new AsiakasDao(database).findOne(rs.getInt("asiakas_id"));
        Date alkaa = rs.getDate("Alkaa");
        Date loppuu = rs.getDate("Loppuu");

        Varaus v = new Varaus(id, nimi, asiakas, alkaa, loppuu);

        rs.close();
        stmt.close();
        connection.close();

        return v;
    }

    @Override
    public List<Varaus> findAll() throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Varaus ORDER BY Alkaa");

        ResultSet rs = stmt.executeQuery();
        List<Varaus> varaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Asiakas asiakas = new AsiakasDao(database).findOne(rs.getInt("asiakas_id"));
            Date alkaa = rs.getDate("Alkaa");
            Date loppuu = rs.getDate("Loppuu");
            varaukset.add(new Varaus(id, nimi, asiakas, alkaa, loppuu));
        }

        rs.close();
        stmt.close();
        connection.close();

        return varaukset;
    }

}
