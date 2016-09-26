/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.interfaces.dao;

import java.sql.SQLException;

/**
 *
 * @author Victor
 * @param <T>
 */
public interface IAbstractDaoCandidato<T> {

    T findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
     
    void updateVotos(T entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    
    void update(T entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    
    void delete(T entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

    void insert(T entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
}
