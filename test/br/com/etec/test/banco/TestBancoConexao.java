/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.test.banco;

import br.com.etec.utils.DbUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;

import org.junit.Test;



/**
 *
 * @author jose
 */
public class TestBancoConexao {

    @Test
    public void setUpClass() throws ClassNotFoundException {
        try {
            Assert.assertNotNull(null, DbUtils.getConnection());
        } catch (InstantiationException ex) {
            Logger.getLogger(TestBancoConexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestBancoConexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestBancoConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
   
}
