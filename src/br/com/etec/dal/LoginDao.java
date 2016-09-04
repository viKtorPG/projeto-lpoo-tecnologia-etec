/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dal;

import br.com.etec.model.Login;
import br.com.etec.utils.DbUtils;
import br.com.etec.view.jframe.TelaDesktop;
import br.com.etec.view.jframe.TelaLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class LoginDao implements IAbstractDaoLogin<Login> {
    @Override
    public void fazerLogin(Login entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        Connection connection = null;
        String sql = "select * from db_usuario where login=? and senha=?;";

        try {
            connection = DbUtils.getConnection();
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);

            statement.setString(1, entidade.getLogin());
            statement.setString(2, entidade.getSenha());
            
            ResultSet resultSet = statement.executeQuery();
  
            if (resultSet.next()) {
                if (resultSet.getString("login").equals(entidade.getSenha()) && resultSet.getString("senha").equals(entidade.getSenha())) {
                    String perfil = resultSet.getString(5);
                    TelaDesktop.exibir("Tela");

                    if (perfil.equals("admin")) {
                        TelaDesktop.jmCadastado.setVisible(true);
                        TelaDesktop.jmRelatorio.setVisible(true);
                    }
                } else {
                    new TelaLogin().execute();
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
