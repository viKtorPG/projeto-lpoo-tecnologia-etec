/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jose
 */
public class DbUtils {

    public static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String stringConexao = "jdbc:mysql://br-cdbr-azure-south-b.cloudapp.net:3306/eleicoes2016?user=bd7fe711d47a1d&password=1298f777";
        Connection connection = DriverManager.getConnection(stringConexao);
        /*try {
            connection = DriverManager.getConnection(stringConexao);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão");
        }*/
        return connection;

    }

    public static ResultSet getResultSet(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
