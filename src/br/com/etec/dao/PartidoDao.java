/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etec.dao;

import br.com.etec.model.Partido;
import br.com.etec.utils.DbUtils;
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
public class PartidoDao implements IAbstractDao<Partido> {

    Connection connection;

    @Override
    public List<Partido> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Partido findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        ResultSet resultSet;
        try {
            connection = DbUtils.getConnection();
            String sql = "select * from partido where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Não há partido com esse número");
                return null;
            } else {

                Partido partido = new Partido();
                partido.setId(resultSet.getInt(1));
                partido.setNome(resultSet.getString(2));
                partido.setNumero(resultSet.getInt(5));
                partido.setSigla(resultSet.getString(3));
                partido.setLogo(resultSet.getBytes(4));
                partido.setSlogan(resultSet.getString(6));
                partido.setData(resultSet.getString(7));
                return partido;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void insert(Partido entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into partido (id,nome, sigla, logo, numero, slogan, data_criacao) values(null,?,?,?,?,?,?)";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getSigla());
            statement.setBytes(3, entidade.getLogo());
            statement.setInt(4, entidade.getNumero());
            statement.setString(5, entidade.getSlogan());
            statement.setString(6, entidade.getData());
            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Partido entidade) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            connection = DbUtils.getConnection();
            String sql = "update partido set nome=?, sigla=?, logo=?, numero=?, slogan=?, data_criacao=? where id=?";
            String sql2 = "update partido set nome=?, sigla=?, numero=?, slogan=?, data_criacao=? where id=?";
            PreparedStatement statement = null;
            if (entidade.getLogo() != null) {
                statement = DbUtils.getPreparedStatement(connection, sql);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getSigla());
                statement.setBytes(3, entidade.getLogo());
                statement.setInt(4, entidade.getNumero());
                statement.setString(5, entidade.getSlogan());
                statement.setString(6, entidade.getData());
                statement.setInt(7, entidade.getId());
            } else {
                statement = DbUtils.getPreparedStatement(connection, sql2);
                statement.setString(1, entidade.getNome());
                statement.setString(2, entidade.getSigla());
                statement.setInt(3, entidade.getNumero());
                statement.setString(4, entidade.getSlogan());
                statement.setString(5, entidade.getData());
                statement.setInt(6, entidade.getId());
            }

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Partido entidade) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from partido where numero=?";
            PreparedStatement statement = DbUtils.getPreparedStatement(connection, sql);
            statement.setInt(1, entidade.getNumero());

            statement.execute();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
