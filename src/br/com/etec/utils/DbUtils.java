/*
 * Classe de conexão 
 */
package br.com.etec.utils;

import br.com.etec.log.Log;
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
        String stringConexao = "jdbc:mysql://localhost:3306/eleicao?user=root&password=";
        Connection connection = DriverManager.getConnection(stringConexao);
        Log.i("Conexão", "ok");
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
