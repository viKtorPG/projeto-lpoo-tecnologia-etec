/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.utils;

import br.com.etec.view.jinternalframe.TelaCadastroPrefeito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class PartidosNumeros {

    private static Connection connection;

    public static String[] partidos() {
        try {
            connection = DbUtils.getConnection();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        ResultSet rs = null;
        String sql = "select nome_partido, sigla from partido";
        try {
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            rs = statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<String> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(rs.getString("nome_partido") + " - " + (rs.getString("sigla")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        String[] result = new String[list.size()];
        result = list.toArray(result);

        return result;
    }

    /* Retorna o nome e a sigla do partido pelo parametros idPartido e numeroPrefeito */
    public static String partido(int numeroPrefeito) {

        String result = null;
        try {
            connection = DbUtils.getConnection();

            ResultSet rs = null;
            String sql = "select partido.nome_partido, partido.sigla from partido, prefeito where (partido.id_partido = prefeito.id_partido) and prefeito.numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, numeroPrefeito);

            rs = statement.executeQuery();

            while (rs.next()) {
                result = rs.getString("nome_partido") + " - " + (rs.getString("sigla"));
                System.err.println(rs.getString("nome_partido") + " - " + (rs.getString("sigla")));

            }
            return result;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(PartidosNumeros.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String numero(String sigla) {
        String numero = "";
        try {
            connection = DbUtils.getConnection();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        String sql = "select numero from partido where sigla= ?";
        //JOptionPane.showMessageDialog(null, selecionado);
        try {
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, sigla);

            rs = statement.executeQuery();

            while (rs.next()) {
                numero = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return numero;
    }

    public static int getIDPartido(int numero) {
        int result = 0;
        try {
            connection = DbUtils.getConnection();

            ResultSet rs = null;
            String sql = "select id_partido from partido where numero= ?";
            //JOptionPane.showMessageDialog(null, selecionado);

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, numero);

            rs = statement.executeQuery();

            while (rs.next()) {
                result = rs.getInt(1);
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PartidosNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int getIDPrefeito(int numero) {
        int result = 0;
        try {
            connection = DbUtils.getConnection();

            ResultSet rs = null;
            String sql = "select id_prefeito from prefeito where numero= ?";
            //JOptionPane.showMessageDialog(null, selecionado);

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, numero);

            rs = statement.executeQuery();

            while (rs.next()) {
                result = rs.getInt(1);
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PartidosNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int getNumeroPartido(int id) {
        int result = 0;
        try {
            connection = DbUtils.getConnection();

            ResultSet rs = null;
            String sql = "select numero from partido where id_partido= ?";
            //JOptionPane.showMessageDialog(null, selecionado);

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            rs = statement.executeQuery();

            while (rs.next()) {
                result = rs.getInt(1);
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PartidosNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static String getInfoPrefeito(int id) {
        String result = null;

        try {
            connection = DbUtils.getConnection();

            ResultSet rs = null;
            String sql = "select prefeito.nome, partido.sigla from  prefeito inner join partido on prefeito.id_partido = partido.id_partido where prefeito.numero = ?";
            //JOptionPane.showMessageDialog(null, selecionado);

            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            rs = statement.executeQuery();

            while (rs.next()) {
                result = rs.getString("prefeito.nome") + " - " + (rs.getString("partido.sigla"));
                System.err.println(rs.getString("prefeito.nome") + " - " + (rs.getString("partido.sigla")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PartidosNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static String[] getInfoPrefeito() {
        try {
            connection = DbUtils.getConnection();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        ResultSet rs = null;
        String sql = "select prefeito.nome, prefeito.id_prefeito, partido.sigla from  prefeito inner join partido on prefeito.id_partido = partido.id_partido";
        try {
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            rs = statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<String> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(rs.getString("prefeito.nome") + " - " + (rs.getString("partido.sigla")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPrefeito.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        String[] result = new String[list.size()];
        result = list.toArray(result);

        return result;
    }
}
