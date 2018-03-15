/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Asiakas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mina
 */
public class AsiakasDao implements Dao<Asiakas, Integer> {

    private Database database;

    public AsiakasDao(Database database) {
        this.database = database;
    }

    @Override
    public Asiakas save(Asiakas object) throws SQLException, Exception {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Asiakas (id, nimi, pNumero) VALUES (?,?,?)");
        stmt.setInt(1, object.getId());
        stmt.setString(2, object.getNimi());
        stmt.setInt(3, object.getpNumero());
        stmt.executeUpdate();

        return findOne(object.getId());
    }

    @Override
    public void delete(Integer key) throws SQLException, Exception {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Asiakas WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
    }

    @Override
    public Asiakas findOne(Integer key) throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Asiakas WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        Integer pNumero = rs.getInt("pNumero");

        Asiakas a = new Asiakas(id, nimi, pNumero);

        rs.close();
        stmt.close();
        connection.close();

        return a;

    }

    @Override
    public List<Asiakas> findAll() throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Asiakas");

        ResultSet rs = stmt.executeQuery();
        List<Asiakas> asiakkaat = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Integer pNumero = rs.getInt("pNumero");

            asiakkaat.add(new Asiakas(id, nimi, pNumero));
        }

        rs.close();
        stmt.close();
        connection.close();

        return asiakkaat;
    }

}
