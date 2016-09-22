/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.model.Vice;
import br.com.etec.utils.DbUtils;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class ViceDao implements IAbstractDao<Vice> {

    Connection connection;
    Blob blob;

    @Override
    public Vice findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select * from vice where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não há candidato com esse número");
                return null;
            } else {

                Vice vice = new Vice();
                vice.setId(resultSet.getInt(1));
                vice.setNome(resultSet.getString(2));
                vice.setPartido(resultSet.getString(3));
                vice.setNumero(resultSet.getInt(4));
                vice.setImagem(resultSet.getBytes(5));
                return vice;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Vice entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from vice where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getNumero());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void insert(Vice entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into vice (id, nome, partido, numero, foto) values(null,?,?,?,?)";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getPartido());            
            statement.setInt(3, entidade.getNumero());
            statement.setBytes(4, entidade.getImagem());
            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Vice entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();
            String sql = "update candidato set nome=?, partido=?, numero=?, foto=? where id=?";
            String sql2 = "update candidato set nome=?, partido=?, numero=? where id=?";
            PreparedStatement statement = null;
            if (entidade.getImagem() != null) {
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getPartido());
                statement.setInt(3, entidade.getNumero());
                statement.setBytes(4, entidade.getImagem());
                statement.setInt(5, entidade.getId());
            } else {
                statement = DbUtils.getPreparedStatement(connection, sql2);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getPartido());
                statement.setInt(3, entidade.getNumero());
                statement.setInt(4, entidade.getId());
            }

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Vice> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        return null;
    }
}
