/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.interfaces.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public interface IAbstractDaoCidadeEstado<T> {
    
    List<T> allCidade() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
    
    List<T> allEstado() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
}
