/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tila;
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
public class TilaDao implements Dao<Tila, String> {

    private Database database;

    public TilaDao(Database database) {
        this.database = database;
    }

    @Override
    public Tila save(Tila object) throws SQLException, Exception {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Tila ( nimi, paikkoja,) VALUES (?,?)");
        stmt.setString(1, object.getNimi());
        stmt.setInt(2, object.getPaikkoja());

        stmt.executeUpdate();

        return findOne(object.getNimi());
    }

    @Override
    public void delete(String nimi) throws SQLException, Exception {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Tila WHERE nimi = ?");
        stmt.setString(1, nimi);
        stmt.executeUpdate();
    }

    @Override
    public Tila findOne(String key) throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tila WHERE nimi = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String nimi = rs.getString("nimi");
        int paikkoja = rs.getInt("paikkoja");
        List<String> varustelu = this.getVarustelu(key);

        Tila t = new Tila(nimi, paikkoja, varustelu);

        rs.close();
        stmt.close();
        connection.close();

        return t;
    }

    @Override
    public List<Tila> findAll() throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tila");

        ResultSet rs = stmt.executeQuery();
        List<Tila> tilat = new ArrayList<>();
        while (rs.next()) {
            String nimi = rs.getString("nimi");
            int paikkoja = rs.getInt("paikkoja");
            List<String> varustelu = this.getVarustelu(nimi);

            tilat.add(new Tila(nimi, paikkoja, varustelu));
        }

        rs.close();
        stmt.close();
        connection.close();
        return tilat;
    }

    private List<String> getVarustelu(String key) throws SQLException, Exception {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT Varustelu.varuste "
                + "FROM Varustelu,TilaVarustelu "
                + "WHERE Varustelu.id = TilaVarustelu.Varustelu_Id AND Tilavarustelu.Tila_nimi = ?");
        stmt.setString(1, key);
        ResultSet rs = stmt.executeQuery();
        List<String> varustelu = new ArrayList<>();
        while (rs.next()) {
            String v = rs.getString("varuste");

            varustelu.add(v);

        }

        rs.close();
        stmt.close();
        connection.close();

        return varustelu;
    }

}
