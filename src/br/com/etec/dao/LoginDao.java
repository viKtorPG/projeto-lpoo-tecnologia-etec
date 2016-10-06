/*
 * Classe de CRUD responsavel pelo login.
 */
package br.com.etec.dao;

import br.com.etec.interfaces.dao.IAbstractDaoLogin;
import br.com.etec.log.Log;
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
        String sql = "select * from usuario where login=? and senha=?;";

        try {
            connection = DbUtils.getConnection();
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);

            statement.setString(1, entidade.getLogin());
            statement.setString(2, entidade.getSenha());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString(3).equals(entidade.getLogin()) && resultSet.getString(4).equals(entidade.getSenha())) {
                    String perfil = resultSet.getString(5);
                    new TelaDesktop().execute();

                    if (perfil.equals("admin")) {
                        Log.i("FazerLogin", "admin");
                        TelaDesktop.jmCadastado.setVisible(true);
                        TelaDesktop.jmRelatorio.setVisible(true);
                        TelaDesktop.jmApuracao.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não existe");
                Log.i("FazerLogin", "Usuário não existe");
                new TelaLogin().execute();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
