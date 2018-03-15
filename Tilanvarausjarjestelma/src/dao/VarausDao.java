/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Asiakas;
import domain.Tila;
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
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Varaus (id, nimi, asiakas_id, tila_nimi Alkaa, Loppuu) VALUES (?,?,?,?,?,?)");
        stmt.setInt(1, object.getId());
        stmt.setString(2, object.getNimi());
        stmt.setInt(3, object.getAsiakas().getId());
        stmt.setString(4, object.getTila().getNimi());
        stmt.setDate(5, (Date) object.getAlkaa());
        stmt.setDate(6, (Date) object.getLoppuu());
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
        Tila tila = new TilaDao(database).findOne(rs.getString("tila_nimi"));
        Date alkaa = rs.getDate("Alkaa");
        Date loppuu = rs.getDate("Loppuu");

        Varaus v = new Varaus(id, nimi, asiakas, tila, alkaa, loppuu);

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
            Tila tila = new TilaDao(database).findOne(rs.getString("tila_nimi"));
            Date alkaa = rs.getDate("Alkaa");
            Date loppuu = rs.getDate("Loppuu");
            varaukset.add(new Varaus(id, nimi, asiakas, tila, alkaa, loppuu));
        }

        rs.close();
        stmt.close();
        connection.close();

        return varaukset;
    }
    
    
    public List<Varaus> findByTila(Tila object) throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Varaus WHERE tila_nimi = ? ORDER BY Alkaa");
        stmt.setString(1, object.getNimi());
        ResultSet rs = stmt.executeQuery();
        List<Varaus> varaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Asiakas asiakas = new AsiakasDao(database).findOne(rs.getInt("asiakas_id"));
            Tila tila = new TilaDao(database).findOne(rs.getString("tila_nimi"));
            Date alkaa = rs.getDate("Alkaa");
            Date loppuu = rs.getDate("Loppuu");
            varaukset.add(new Varaus(id, nimi, asiakas, tila, alkaa, loppuu));
        }

        rs.close();
        stmt.close();
        connection.close();

        return varaukset;
    }

}
