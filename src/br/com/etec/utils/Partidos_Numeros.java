/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.utils;

import br.com.etec.view.jinternalframe.TelaCadastroCandidato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class Partidos_Numeros {

    private static Connection connection;
     public static String[] partidos() {
        try {
            connection = DbUtils.getConnection();
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(TelaCadastroCandidato.class
                    .getName()).log(Level.SEVERE, null, ex);
            
        }
        ResultSet rs = null;
        String sql = "select nome, sigla from partido";
        try {
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            rs = statement.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroCandidato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<String> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(rs.getString("nome") + " - " + (rs.getString("sigla")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroCandidato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] result = new String[list.size()];
        result = list.toArray(result);
        
        return result;
    }
    
    public static String numero(String sigla) {
        String numero = "";
        try {
            connection = DbUtils.getConnection();
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(TelaCadastroCandidato.class
                    .getName()).log(Level.SEVERE, null, ex);            
        }
        ResultSet rs = null;
        String sql = "select numero from partido where sigla='" + sigla + "'";
        //JOptionPane.showMessageDialog(null, selecionado);
        try {
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            rs = statement.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroCandidato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs.next()) {
                numero = "" + rs.getInt("numero");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroCandidato.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        
        return numero;
    }
}
