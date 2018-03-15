/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mina
 */
public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws Exception {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws Exception {

        return DriverManager.getConnection(this.databaseAddress);
    }

}
