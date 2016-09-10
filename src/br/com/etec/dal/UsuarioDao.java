/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dal;

import br.com.etec.model.Usuario;
import br.com.etec.utils.DbUtils;
import br.com.etec.view.jinternalframe.TelaCadastroUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class UsuarioDao implements IAbstractDao<Usuario> {

    private Connection connection = null;

    @Override
    public void insert(Usuario entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into db_usuario(nome, login, senha, perfil) values(?,?,?,?)";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getLogin());
            statement.setString(3, entidade.getSenha());
            statement.setString(4, entidade.getPerfil());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Usuario entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();
            String sql = "update db_usuario set nome=?, login=?, senha=?, perfil=? where id_ser=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getLogin());
            statement.setString(3, entidade.getSenha());
            statement.setString(4, entidade.getPerfil());
            statement.setInt(6, entidade.getId());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Usuario entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from db_usuario where id_user=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getId());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResultSet consultar(Usuario entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select * from db_usuario where id_user=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getId());

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "gggggggggg");
            }else{
                 TelaCadastroUsuario.txtId.setText(resultSet.getString(1));
                TelaCadastroUsuario.txtNome.setText(resultSet.getString(2));
                TelaCadastroUsuario.txtLogin.setText(resultSet.getString(3));
                TelaCadastroUsuario.txtSenha.setText(resultSet.getString(4));
                TelaCadastroUsuario.jcPerfil.setSelectedItem(resultSet.getString(5));
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return resultSet;
    }

}
