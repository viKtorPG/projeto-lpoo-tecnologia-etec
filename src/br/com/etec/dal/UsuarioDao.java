/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dal;

import br.com.etec.model.Usuario;
import br.com.etec.utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jose
 */
public class UsuarioDao implements IAbstractDao<Usuario> {

    @Override
    public void insert(Usuario entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Connection connection = null;
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
    }

    @Override
    public void delete(Usuario entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    }

}
