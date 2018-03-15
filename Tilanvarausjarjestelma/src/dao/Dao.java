/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mina
 */
public interface Dao<T,K> {
    

    T findOne(K key) throws SQLException, Exception;

    List<T> findAll() throws SQLException, Exception;

    T save(T object) throws SQLException, Exception;

    void delete(K key) throws SQLException, Exception;

    
}
