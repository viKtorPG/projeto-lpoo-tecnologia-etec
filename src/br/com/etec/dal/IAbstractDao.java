/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dal;

import java.sql.SQLException;

/**
 *
 * @author jose
 */
public interface IAbstractDao<T> {
    
    void insert(T entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

    void update(T entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;

    void delete(T entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

}
