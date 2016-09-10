/*
 * Clase que fornece a conexão com o banco de dados.
 */
package br.com.etec.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class Conexao{
    //Método responsavel por estabelecer a conexão com o banco
    public static Connection conector() {
        Connection conexao;
      
        String driver = "com.mysql.jdbc.Driver";
       
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
        // Estabelecendo a conexão com o database
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (ClassNotFoundException | SQLException e) {
            // Erro
            
            JOptionPane.showMessageDialog(null,"Erro" + e.getMessage());
            
            return null;
        }
    }
}
